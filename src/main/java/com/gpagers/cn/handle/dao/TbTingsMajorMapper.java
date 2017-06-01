package com.gpagers.cn.handle.dao;

import com.gpagers.cn.handle.model.TbTingsMajor;
import com.gpagers.cn.handle.model.TbTingsMajorExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbTingsMajorMapper {
    int countByExample(TbTingsMajorExample example);

    int deleteByExample(TbTingsMajorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbTingsMajor record);

    int insertSelective(TbTingsMajor record);

    List<TbTingsMajor> selectByExampleWithBLOBs(TbTingsMajorExample example);

    List<TbTingsMajor> selectByExample(TbTingsMajorExample example);

    TbTingsMajor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbTingsMajor record, @Param("example") TbTingsMajorExample example);

    int updateByExampleWithBLOBs(@Param("record") TbTingsMajor record, @Param("example") TbTingsMajorExample example);

    int updateByExample(@Param("record") TbTingsMajor record, @Param("example") TbTingsMajorExample example);

    int updateByPrimaryKeySelective(TbTingsMajor record);

    int updateByPrimaryKeyWithBLOBs(TbTingsMajor record);

    int updateByPrimaryKey(TbTingsMajor record);
}