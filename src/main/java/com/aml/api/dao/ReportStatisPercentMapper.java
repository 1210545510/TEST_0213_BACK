package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.ReportStatisPercentDto;
import com.aml.api.pojo.ReportStatisPercent;
import com.github.pagehelper.Page;

public interface ReportStatisPercentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReportStatisPercent record);

    int insertSelective(ReportStatisPercent record);

    ReportStatisPercent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReportStatisPercent record);

    int updateByPrimaryKey(ReportStatisPercent record);

	List<Map<String, Object>> getYield(ReportStatisPercentDto dto);

	Page<List<Map<String, Object>>> querySarYield(ReportStatisPercentDto dto);
}