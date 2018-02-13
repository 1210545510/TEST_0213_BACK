package com.aml.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aml.api.pojo.SendRFI;

public interface SendRFIMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SendRFI record);

    int insertSelective(SendRFI record);

    SendRFI selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SendRFI record);

    int updateByPrimaryKey(SendRFI record);

	List<SendRFI> queryAllFile(@Param("relateId") Long relateId,@Param("source") Integer source);
}