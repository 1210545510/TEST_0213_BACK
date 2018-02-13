package com.aml.api.dao;

import java.util.HashMap;

import com.aml.api.dto.DicDTO;
import com.aml.api.pojo.Dic;
import com.github.pagehelper.Page;

public interface DicMapper {
	int deleteByPrimaryKey ( Long dicId );

	int insert ( Dic record );

	int insertSelective ( Dic record );

	Dic selectByPrimaryKey ( Long dicId );

	int updateByPrimaryKeySelective ( Dic record );

	int updateByPrimaryKey ( Dic record );

	int delete ( Long dicId );

	int update ( DicDTO dto );

	int insert ( DicDTO dto );

	Dic findDicById ( Long dicId );

	Page<Dic> queryDicts ( HashMap<String, String> params );

}