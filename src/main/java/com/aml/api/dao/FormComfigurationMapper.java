package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.FormComfigurationDto;
import com.aml.api.pojo.FormComfiguration;
import com.github.pagehelper.Page;

public interface FormComfigurationMapper {
	int deleteByPrimaryKey ( Integer id );

	int insert ( FormComfiguration record );

	int insertSelective ( FormComfiguration record );

	FormComfiguration selectByPrimaryKey ( Integer id );

	int updateByPrimaryKeySelective ( FormComfiguration record );

	int updateByPrimaryKey ( FormComfiguration record );

	Page<FormComfiguration> getFormComfigurationList ( FormComfigurationDto dto );

	List<Map<String, Object>> downFormComfigurationExcel ( FormComfigurationDto dto );
}