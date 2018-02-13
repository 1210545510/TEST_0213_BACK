package com.aml.api.dao;

import java.util.List;

import com.aml.api.dto.AssignRuleItemDTO;
import com.aml.api.pojo.AssignRuleItem;

public interface AssignRuleItemMapper {
	int deleteByPrimaryKey ( Long id );

	int insert ( AssignRuleItem record );

	int insertSelective ( AssignRuleItem record );

	AssignRuleItem selectByPrimaryKey ( Long id );

	int updateByPrimaryKeySelective ( AssignRuleItem record );

	int updateByPrimaryKey ( AssignRuleItem record );

	int insertBatch ( List<AssignRuleItemDTO> ruleItems );

	int deleteByRuleId ( Long ruleId );
}