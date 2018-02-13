package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.ReportStatisGroupSarDto;
import com.aml.api.pojo.ReportStatisGroupSar;
import com.github.pagehelper.Page;

public interface ReportStatisGroupSarMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReportStatisGroupSar record);

    int insertSelective(ReportStatisGroupSar record);

    ReportStatisGroupSar selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReportStatisGroupSar record);

    int updateByPrimaryKey(ReportStatisGroupSar record);

	List<Map<String, Object>> getTrendTotalOfSar(ReportStatisGroupSarDto dto);

	Page<List<Map<String, Object>>> queryEachTotalOfSar(ReportStatisGroupSarDto dto);
}