package com.aml.api.dao;

import java.util.List;

import com.aml.api.pojo.TaskFile;

public interface TaskFileMapper {
	int deleteByPrimaryKey ( Long fileId );

	int insert ( TaskFile record );

	int insertSelective ( TaskFile record );

	TaskFile selectByPrimaryKey ( Long fileId );

	int updateByPrimaryKeySelective ( TaskFile record );

	int updateByPrimaryKey ( TaskFile record );

	int insertBatch ( List<TaskFile> fileList );

	int deleteByTaskId ( String taskId );
}