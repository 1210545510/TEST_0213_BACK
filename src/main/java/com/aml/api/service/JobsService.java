package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.JobsMapper;
import com.aml.api.dto.JobsDto;
import com.aml.api.pojo.Jobs;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: JobsService
 * @description: JobsService Class
 * @author Novan
 * @date 2017-11-22
 *
 */
@Service
public class JobsService {
	public static final Logger logger = LoggerFactory.getLogger(JobsService.class);

	@Autowired
	JobsMapper jobsMapper;
	
	/**
	 * Query all records, list Pagination
	 * @param dto
	 * @return
	 */
	public Page<Jobs> getJobsList(JobsDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return jobsMapper.getJobsList(dto);
	}

	/**
	 * export
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downJobsExcel(JobsDto dto) {
		return jobsMapper.downJobsExcel(dto);
	}

	/**
	 * import
	 * @param jobsList
	 */
	public void insertJobs(List<Jobs> jobsList) {
		for (Jobs jobs : jobsList) {
			jobsMapper.insertSelective(jobs);
		}
	}

	/**
	 * 校验code是否存在
	 * @param jobs
	 * @return
	 */
	public int getJobsCountByCode(Jobs jobs) {
		return jobsMapper.getJobsCountByCode(jobs);
	}
	
}
