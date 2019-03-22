package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.Archives;
import com.ujiuye.usual.mapper.ArchivesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArchivesServiceImpl implements  ArchivesService {
    @Resource
    private ArchivesMapper archivesMapper;

    @Override
    public int BatchAdd(List<Archives> list) {
        return archivesMapper.BatchAdd(list);
    }
}
