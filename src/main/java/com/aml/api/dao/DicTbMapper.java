package com.aml.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aml.api.dto.DicTbDTO;
import com.aml.api.pojo.DicTb;

public interface DicTbMapper {
	int deleteByPrimaryKey ( Integer id );

	int insert ( DicTb record );

	int insertSelective ( DicTb record );

	DicTb selectByPrimaryKey ( Integer id );

	int updateByPrimaryKeySelective ( DicTb record );

	int updateByPrimaryKey ( DicTb record );

	int deleteByDicId ( Long dicId );

	int insertBatch ( @Param("list") List<DicTbDTO> dicSubs );

	List<DicTb> queryDicTbsByDicId ( Long dicId );
}