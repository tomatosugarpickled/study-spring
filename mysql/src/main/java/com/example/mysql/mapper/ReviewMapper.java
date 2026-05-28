package com.example.mysql.mapper;

import com.example.mysql.domain.ReviewVO;
import com.example.mysql.dto.ReviewDTO;
import com.example.mysql.dto.ReviewMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReviewMapper {
//    추가
    public void insert(ReviewVO reviewVO);
//    조회
    public Optional<ReviewDTO> selectById(Long id);
//    조회(회원정보와 함께)
    public Optional<ReviewMemberDTO> selectWithMemberById(Long id);
//    전체 조회
    public List<ReviewMemberDTO> selectAll();
}





















