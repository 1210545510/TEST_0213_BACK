package com.aml.api.dao;

import java.util.List;

import com.aml.api.dto.DicSubDTO;
import com.aml.api.dto.WfBusStatusDTO;
import com.aml.api.pojo.DicSub;
import com.github.pagehelper.Page;

public interface DicSubMapper {
	int deleteByPrimaryKey ( Integer dicSid );

	int insert ( DicSub record );

	int insertSelective ( DicSub record );

	DicSub selectByPrimaryKey ( Integer dicSid );

	int updateByPrimaryKeySelective ( DicSub record );

	int updateByPrimaryKey ( DicSub record );

	int delete ( Long dicSid );

	int deleteByDicId ( Long dicId );

	int insertBatch ( List<DicSubDTO> dicSubs );

	List<DicSub> queryDicSubsByDicId ( Long dicId );

	Page<DicSub> pageProcessState ( WfBusStatusDTO dto );
}