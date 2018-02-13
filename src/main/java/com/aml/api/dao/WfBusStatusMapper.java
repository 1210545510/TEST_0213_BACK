package com.aml.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aml.api.pojo.WfBusStatus;

public interface WfBusStatusMapper {
	int deleteByPrimaryKey ( Integer id );

	int insert ( WfBusStatus record );

	int insertSelective ( WfBusStatus record );

	WfBusStatus selectByPrimaryKey ( Integer id );

	int updateByPrimaryKeySelective ( WfBusStatus record );

	int updateByPrimaryKey ( WfBusStatus record );

	List<WfBusStatus> getconfigurationStatus ( @Param("flowId") String flowId, @Param("didSid") String didSid );

	int updateByIsDeleted ( @Param("flowId") String flowId, @Param("didSid") String didSid );

	WfBusStatus findStatus ( @Param("flowId") String flowId, @Param("dicKey") Integer dicKey,
			@Param("statusType") Integer statusType, @Param("paramValue") String paramValue );
}