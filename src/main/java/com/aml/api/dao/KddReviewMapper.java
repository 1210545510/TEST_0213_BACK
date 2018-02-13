package com.aml.api.dao;

import java.util.List;

import com.aml.api.pojo.KddReview;

public interface KddReviewMapper {
	int insert ( KddReview record );

	int insertSelective ( KddReview record );

	List<String> queryAcctId ( Integer originalId );

}