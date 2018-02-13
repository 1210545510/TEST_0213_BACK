package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.Data314ADto;
import com.aml.api.pojo.Data314A;
import com.github.pagehelper.Page;

public interface Data314AMapper {
	int insert ( Data314A record );

	int insertSelective ( Data314A record );

	Page<Data314A> getData314AList ( Data314ADto dto );

	List<Map<String, Object>> downData314AExcel ( Data314ADto dto );
}