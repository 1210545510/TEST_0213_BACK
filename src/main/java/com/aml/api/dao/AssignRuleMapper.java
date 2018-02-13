package com.aml.api.dao;

import com.aml.api.dto.AssignRuleDTO;
import com.aml.api.pojo.AssignRule;

public interface AssignRuleMapper {
	int deleteByPrimaryKey ( Long id );

	int insert ( AssignRule record );

	int insertSelective ( AssignRule record );

	AssignRule selectByPrimaryKey ( Long id );

	int updateByPrimaryKeySelective ( AssignRule record );

	int updateByPrimaryKey ( AssignRule record );

	AssignRule queryAssignRuleByRoleId ( Long roleId );

	AssignRule querySamplingRule ();

	int insert ( AssignRuleDTO dto );

	int update ( AssignRuleDTO dto );
}