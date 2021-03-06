package com.grass.admin.dao;

import com.grass.admin.model.RolePower;
import com.grass.admin.model.RolePowerExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RolePowerDao {
    long countByExample(RolePowerExample example);

    int deleteByExample(RolePowerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RolePower record);

    int insertSelective(RolePower record);

    List<RolePower> selectByExample(RolePowerExample example);

    RolePower selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RolePower record, @Param("example") RolePowerExample example);

    int updateByExample(@Param("record") RolePower record, @Param("example") RolePowerExample example);

    int updateByPrimaryKeySelective(RolePower record);

    int updateByPrimaryKey(RolePower record);
}