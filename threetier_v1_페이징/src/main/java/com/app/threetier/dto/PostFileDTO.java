package com.app.threetier.dto;

import com.app.threetier.audit.Period;
import com.app.threetier.common.enumeration.FileContentType;
import com.app.threetier.domain.FileVO;
import com.app.threetier.domain.PostFileVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class PostFileDTO {
    private Long id;
    private String filePath;
    private String fileName;
    private String fileOriginalName;
    private String fileSize;
    private FileContentType fileContentType;
    private Long postId;
    private String createdDatetime;
    private String updatedDatetime;

    public FileVO toFileVO(){
        return FileVO.builder()
                .id(id)
                .filePath(filePath)
                .fileName(fileName)
                .fileOriginalName(fileOriginalName)
                .fileSize(fileSize)
                .fileContentType(fileContentType)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }

    public PostFileVO toPostFileVO(){
        return PostFileVO.builder()
                .id(id)
                .postId(postId)
                .build();
    }
}












