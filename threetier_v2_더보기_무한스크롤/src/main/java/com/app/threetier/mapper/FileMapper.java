package com.app.threetier.mapper;

import com.app.threetier.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
//    추가
    public void insert(FileDTO fileDTO);
}
