package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.SequencesMapper;
import com.aml.api.dto.SequencesDto;
import com.aml.api.pojo.Sequences;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: SequencesService
 * @description: SequencesService Class
 * @author Novan
 * @date 2017-11-22
 *
 */
@Service
public class SequencesService {
	public static final Logger logger = LoggerFactory.getLogger(SequencesService.class);

	@Autowired
	SequencesMapper sequencesMapper;
	
	/**
	 * Query all records, list Pagination
	 * @param dto
	 * @return
	 */
	public Page<Sequences> getSequencesList(SequencesDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return sequencesMapper.getSequencesList(dto);
	}

	/**
	 * export
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downSequencesExcel(SequencesDto dto) {
		return sequencesMapper.downSequencesExcel(dto);
	}

	/**
	 * import
	 * @param sequencesList
	 */
	public void insertSequences(List<Sequences> sequencesList) {
		for (Sequences sequences : sequencesList) {
			sequencesMapper.insertSelective(sequences);
		}
	}
	
}
