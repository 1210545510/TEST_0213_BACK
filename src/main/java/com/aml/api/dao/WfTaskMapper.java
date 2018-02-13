package com.aml.api.dao;

import com.aml.api.pojo.WfTask;

public interface WfTaskMapper {
	int deleteByPrimaryKey ( String id );

	int insert ( WfTask record );

	int insertSelective ( WfTask record );

	WfTask selectByPrimaryKey ( String id );

	int updateByPrimaryKeySelective ( WfTask record );

	int updateByPrimaryKey ( WfTask record );

}