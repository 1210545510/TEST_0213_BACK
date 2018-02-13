package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.ReportStatisCustomersDto;
import com.aml.api.pojo.ReportStatisCustomers;
import com.github.pagehelper.Page;

public interface ReportStatisCustomersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReportStatisCustomers record);

    int insertSelective(ReportStatisCustomers record);

    ReportStatisCustomers selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReportStatisCustomers record);

    int updateByPrimaryKey(ReportStatisCustomers record);

	List<Map<String, Object>> getStatisCust(ReportStatisCustomersDto dto);

	Page<List<Map<String, Object>>> queryEachTotalOfSar(ReportStatisCustomersDto dto);
}