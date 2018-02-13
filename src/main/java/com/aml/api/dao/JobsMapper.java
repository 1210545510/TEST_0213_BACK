package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.JobsDto;
import com.aml.api.pojo.Jobs;
import com.github.pagehelper.Page;

public interface JobsMapper {
	int deleteByPrimaryKey ( Integer id );

	int insert ( Jobs record );

	int insertSelective ( Jobs record );

	Jobs selectByPrimaryKey ( Integer id );

	int updateByPrimaryKeySelective ( Jobs record );

	int updateByPrimaryKey ( Jobs record );

	Page<Jobs> getJobsList ( JobsDto dto );

	List<Map<String, Object>> downJobsExcel ( JobsDto dto );

	int getJobsCountByCode ( Jobs jobs );
}