package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.WfHistOrderDto;
import com.aml.api.pojo.WfHistOrder;

public interface WfHistOrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(WfHistOrder record);

    int insertSelective(WfHistOrder record);

    WfHistOrder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WfHistOrder record);

    int updateByPrimaryKey(WfHistOrder record);

	List<Map<String, Object>> getWorkflowData(WfHistOrderDto dto);
}