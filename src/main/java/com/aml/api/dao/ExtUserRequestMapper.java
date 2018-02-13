package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.ExtUserRequestDto;
import com.aml.api.pojo.ExtUserRequest;
import com.github.pagehelper.Page;

public interface ExtUserRequestMapper {
	int deleteByPrimaryKey ( String extRequestId );

	int insert ( ExtUserRequest record );

	int insertSelective ( ExtUserRequest record );

	ExtUserRequest selectByPrimaryKey ( String extRequestId );

	int updateByPrimaryKeySelective ( ExtUserRequest record );

	int updateByPrimaryKeyWithBLOBs ( ExtUserRequest record );

	int updateByPrimaryKey ( ExtUserRequest record );

	Page<ExtUserRequest> getExtUserRequestList ( ExtUserRequestDto dto );

	List<Map<String, Object>> downExtUserRequestExcel ( ExtUserRequestDto dto );
}