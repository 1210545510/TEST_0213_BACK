package com.aml.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aml.api.pojo.WfTaskFile;

public interface WfTaskFileMapper {
    int deleteByPrimaryKey(Long fileId);

    int insert(WfTaskFile record);

    int insertSelective(WfTaskFile record);

    WfTaskFile selectByPrimaryKey(Long fileId);

    int updateByPrimaryKeySelective(WfTaskFile record);

    int updateByPrimaryKey(WfTaskFile record);

	List<WfTaskFile> queryAllFile(@Param("alertId") Long alertId,@Param("caseId") Long caseId);
}