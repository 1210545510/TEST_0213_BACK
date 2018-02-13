package com.aml.api.dao;

import com.aml.api.dto.CtrPersonInfoDto;
import com.aml.api.pojo.CtrPersonInfo;

public interface CtrPersonInfoMapper {
	int deleteByPrimaryKey ( Long personId );

	int insert ( CtrPersonInfo record );

	int insertSelective ( CtrPersonInfo record );

	CtrPersonInfo selectByPrimaryKey ( Long personId );

	int updateByPrimaryKeySelective ( CtrPersonInfo record );

	int updateByPrimaryKey ( CtrPersonInfo record );

	int getCount ( CtrPersonInfoDto dto );

	int insertSelectiveDto ( CtrPersonInfoDto dto );

	int updateByPrimaryKeySelectiveDto ( CtrPersonInfoDto dto );

	CtrPersonInfo queryCtrPersonInfo ( CtrPersonInfoDto dto );
}