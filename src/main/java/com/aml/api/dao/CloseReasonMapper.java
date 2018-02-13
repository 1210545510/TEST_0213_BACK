package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.CloseReasonDto;
import com.aml.api.pojo.CloseReason;
import com.github.pagehelper.Page;

public interface CloseReasonMapper {
	int deleteByPrimaryKey ( Integer id );

	int insert ( CloseReason record );

	int insertSelective ( CloseReason record );

	CloseReason selectByPrimaryKey ( Integer id );

	int updateByPrimaryKeySelective ( CloseReason record );

	int updateByPrimaryKey ( CloseReason record );

	Page<CloseReason> getCloseReasonList ( CloseReasonDto dto );

	List<Map<String, Object>> downCloseReasonExcel ( CloseReasonDto dto );

	int getCloseReasonCountByCode ( CloseReason closeReason );
}