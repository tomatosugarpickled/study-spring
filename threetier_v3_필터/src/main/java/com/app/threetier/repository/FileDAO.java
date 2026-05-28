package com.app.threetier.repository;

import com.app.threetier.dto.FileDTO;
import com.app.threetier.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;

//    추가
    public void save(FileDTO fileDTO) {
        fileMapper.insert(fileDTO);
    }
}
