package com.ujiuye.auth.service;

import com.ujiuye.auth.bean.Sources;
import com.ujiuye.auth.bean.SourcesExample;
import com.ujiuye.auth.mapper.SourcesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SourcesServiceImpl implements  SourcesService {

    @Resource
    private SourcesMapper sourcesMapper;

    @Override
    public List<Sources> getOne(SourcesExample se) {
        return sourcesMapper.selectByExample(se);
    }

    @Override
    public boolean insertInfo(Sources sources) {
        int i = sourcesMapper.insertSelective(sources);
        return i>0;
    }

    @Override
    public boolean updateInfo(Sources sources) {
        int i = sourcesMapper.updateByPrimaryKeySelective(sources);
        return i>0;
    }

    @Override
    public boolean deleteInfoByPk(Integer id) {
        int i = sourcesMapper.deleteByPrimaryKey(id);
        return i>0;
    }
}
