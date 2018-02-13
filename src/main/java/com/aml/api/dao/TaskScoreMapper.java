package com.aml.api.dao;

import com.aml.api.dto.TaskScoreDTO;
import com.aml.api.pojo.TaskScore;

public interface TaskScoreMapper {
	int insert ( TaskScore record );

	int insertSelective ( TaskScore record );

	int insert ( TaskScoreDTO dto );

	int update ( TaskScoreDTO dto );

	TaskScore findScoreByTaskId ( String taskId );
}