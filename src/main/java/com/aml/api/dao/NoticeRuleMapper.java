package com.aml.api.dao;

import java.util.List;

import com.aml.api.dto.NoticeRuleDTO;
import com.aml.api.pojo.NoticeRule;

public interface NoticeRuleMapper {
	int deleteByPrimaryKey ( Long id );

	int insert ( NoticeRule record );

	int insertSelective ( NoticeRule record );

	NoticeRule selectByPrimaryKey ( Long id );

	int updateByPrimaryKeySelective ( NoticeRule record );

	int updateByPrimaryKey ( NoticeRule record );

	int insert ( NoticeRuleDTO dto );

	int update ( NoticeRuleDTO dto );

	List<NoticeRule> queryNoticeRule ();
}