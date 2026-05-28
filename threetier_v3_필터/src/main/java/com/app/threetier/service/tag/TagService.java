package com.app.threetier.service.tag;

import com.app.threetier.domain.TagVO;
import com.app.threetier.mapper.TagMapper;
import com.app.threetier.repository.TagDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TagService {
    private final TagDAO tagDAO;

    public List<String> selectAll(){
        return tagDAO.findAll();
    }
}
