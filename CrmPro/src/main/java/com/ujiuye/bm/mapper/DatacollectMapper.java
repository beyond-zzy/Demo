package com.ujiuye.bm.mapper;

import com.ujiuye.bm.bean.Datacollect;
import com.ujiuye.bm.bean.DatacollectExample;
import java.util.List;

import com.ujiuye.bm.bean.DatacollectQueryVo;
import org.apache.ibatis.annotations.Param;

public interface DatacollectMapper {
    int countByExample(DatacollectExample example);

    int deleteByExample(DatacollectExample example);

    int deleteByPrimaryKey(Integer daid);

    int insert(Datacollect record);

    int insertSelective(Datacollect record);

    List<Datacollect> selectByExample(DatacollectExample example);

    Datacollect selectByPrimaryKey(Integer daid);

    int updateByExampleSelective(@Param("record") Datacollect record, @Param("example") DatacollectExample example);

    int updateByExample(@Param("record") Datacollect record, @Param("example") DatacollectExample example);

    int updateByPrimaryKeySelective(Datacollect record);

    int updateByPrimaryKey(Datacollect record);

    // 批量添加采集的企业信息
    int BatchInsert(List<Datacollect> list);

    // 查询对比企业中最进年份的数据
     List<Datacollect> getInfoGroup();

     // 根据企业的名称获取当前企业最新的数据
    Datacollect getOneBycname(@Param("cname") String cname);

    // 按照条件查询数据信息

    List<Datacollect> showPng(DatacollectQueryVo vo);


}