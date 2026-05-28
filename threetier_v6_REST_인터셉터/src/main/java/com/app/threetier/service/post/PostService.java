package com.app.threetier.service.post;

import com.app.threetier.common.enumeration.FileContentType;
import com.app.threetier.common.exception.FileNotFoundException;
import com.app.threetier.common.exception.PostNotFoundException;
import com.app.threetier.common.pagination.Criteria;
import com.app.threetier.common.search.Search;
import com.app.threetier.domain.FileVO;
import com.app.threetier.domain.TagVO;
import com.app.threetier.dto.*;
import com.app.threetier.repository.FileDAO;
import com.app.threetier.repository.PostDAO;
import com.app.threetier.repository.PostFileDAO;
import com.app.threetier.repository.TagDAO;
import com.app.threetier.util.DateUtils;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class PostService {
    private final PostDAO postDAO;
    private final TagDAO tagDAO;
    private final FileDAO fileDAO;
    private final PostFileDAO postFileDAO;

//    추가
    public void write(PostDTO postDTO, ArrayList<MultipartFile> multipartFiles) {
        String rootPath = "C:/file/";
        String todayPath = getTodayPath();
        String path = rootPath + todayPath;

        FileDTO fileDTO = new FileDTO();
        PostFileDTO postFileDTO = new PostFileDTO();

        postDAO.save(postDTO);
        postDTO.getTags().forEach(tagDTO -> {
            tagDTO.setPostId(postDTO.getId());
            tagDAO.save(tagDTO.toVO());
        });
        postFileDTO.setPostId(postDTO.getId());
        multipartFiles.forEach(multipartFile -> {
            if(multipartFile.getOriginalFilename().isEmpty()){
                return;
            }
            UUID uuid = UUID.randomUUID();
            fileDTO.setFilePath(todayPath);
            fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
            fileDTO.setFileOriginalName(multipartFile.getOriginalFilename());
            fileDTO.setFileName(uuid.toString() + "_" + multipartFile.getOriginalFilename());
            fileDTO.setFileContentType(multipartFile.getContentType().contains("image") ? FileContentType.IMAGE : FileContentType.OTHER);
            fileDAO.save(fileDTO);

            postFileDTO.setId(fileDTO.getId());
            postFileDAO.save(postFileDTO.toPostFileVO());

            File directory = new File(rootPath + "/" + fileDTO.getFilePath());
            if(!directory.exists()){
                directory.mkdirs();
            }
            try {
                multipartFile.transferTo(new File(path, fileDTO.getFileName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

//    목록
    public PostWithPagingDTO list(int page, Search search){
        PostWithPagingDTO postWithPagingDTO = new PostWithPagingDTO();
        Criteria criteria = new Criteria(page, postDAO.findTotal(search));

        List<PostDTO> posts = postDAO.findAll(criteria, search);

        criteria.setHasMore(posts.size() > criteria.getRowCount());
        postWithPagingDTO.setCriteria(criteria);

        if(criteria.isHasMore()){
            posts.remove(posts.size() - 1);
        }

        posts.forEach(postDTO -> {
            postDTO.setCreatedDatetime(DateUtils.toRelativeTime(postDTO.getCreatedDatetime()));
            postDTO.setTags(tagDAO.findAllByPostId(postDTO.getId())
                    .stream().map((tagVO) -> toTagDTO(tagVO)).collect(Collectors.toList()));
            postDTO.setPostFiles(postFileDAO.findAllByPostId(postDTO.getId()));
        });
        postWithPagingDTO.setPosts(posts);

        return postWithPagingDTO;
    }

//    조회
    public PostDTO detail(Long id) {
        Optional<PostDTO> foundPost = postDAO.findById(id);
        PostDTO postDTO = foundPost.orElseThrow(PostNotFoundException::new);
        postDTO.setTags(tagDAO.findAllByPostId(postDTO.getId())
                .stream().map((tagVO) -> toTagDTO(tagVO)).collect(Collectors.toList()));
        postDTO.setPostFiles(postFileDAO.findAllByPostId(postDTO.getId()));

        return postDTO;
    }

    public String getTodayPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public TagDTO toTagDTO(TagVO tagVO){
        TagDTO tagDTO = new TagDTO();

        tagDTO.setId(tagVO.getId());
        tagDTO.setPostId(tagVO.getPostId());
        tagDTO.setTagName(tagVO.getTagName());

        return tagDTO;
    }

//    수정
    public void update(PostDTO postDTO, List<MultipartFile> multipartFiles){
        String rootPath = "C:/file/";
        String todayPath = getTodayPath();
        String path = rootPath + todayPath;

        postDAO.setPost(postDTO.toVO());

        FileDTO fileDTO = new FileDTO();
        PostFileDTO postFileDTO = new PostFileDTO();

        postDTO.getTags().forEach(tagDTO -> {
            tagDTO.setPostId(postDTO.getId());
            tagDAO.save(tagDTO.toVO());
        });

        postFileDTO.setPostId(postDTO.getId());
        multipartFiles.forEach((multipartFile -> {
            if(multipartFile.getOriginalFilename().isEmpty()){
                return;
            }
            UUID uuid = UUID.randomUUID();
            fileDTO.setFilePath(todayPath);
            fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
            fileDTO.setFileOriginalName(multipartFile.getOriginalFilename());
            fileDTO.setFileName(uuid.toString() + "_" + multipartFile.getOriginalFilename());
            fileDTO.setFileContentType(multipartFile.getContentType().contains("image") ? FileContentType.IMAGE : FileContentType.OTHER);
            fileDAO.save(fileDTO);

            postFileDTO.setId(fileDTO.getId());
            postFileDAO.save(postFileDTO.toPostFileVO());

            File directory = new File(rootPath + "/" + fileDTO.getFilePath());
            if(!directory.exists()){
                directory.mkdirs();
            }
            try {
                multipartFile.transferTo(new File(path, fileDTO.getFileName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));

//        ############################# 삭제 #############################
//        태그
        if(postDTO.getTagIdsToDelete() != null) {
            Arrays.stream(postDTO.getTagIdsToDelete()).forEach((tagId) -> {
                tagDAO.delete(Long.valueOf(tagId));
            });
        }

        if(postDTO.getFileIdsToDelete() != null) {
//        파일
            Arrays.stream(postDTO.getFileIdsToDelete()).forEach((fileId) -> {
                FileVO fileVO = fileDAO.findById(Long.valueOf(fileId)).orElseThrow(FileNotFoundException::new);
                File file = new File(rootPath + fileVO.getFilePath(), fileVO.getFileName());
                if (file.exists()) {
                    file.delete();
                }
                postFileDAO.delete(Long.valueOf(fileId));
                fileDAO.delete(Long.valueOf(fileId));
            });
        }
    }

//    삭제
    public void delete(Long id){
        tagDAO.deleteByPostId(id);
        postFileDAO.findAllByPostId(id).forEach(postFileDTO -> {
            File file = new File("C:/file/" + postFileDTO.getFilePath(), postFileDTO.getFileName());
            if (file.exists()) {
                file.delete();
            }

            Long fileId = postFileDTO.getId();
            postFileDAO.delete(fileId);
            fileDAO.delete(fileId);
        });
        postDAO.delete(id);
    }
}















