package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.aml.api.dto.Data314ADto;
import com.aml.api.pojo.Rfi;
import com.github.pagehelper.Page;

public interface RfiMapper {
    int deleteByPrimaryKey(Integer control);

    int insert(Rfi record);

    int insertSelective(Rfi record);

    Rfi selectByPrimaryKey(Integer control);

    int updateByPrimaryKeySelective(Rfi record);

    int updateByPrimaryKey(Rfi record);

	Long findNewControlId();

	Long queryControlByRelateIdAndSource(@Param("relateId") Long relateId,@Param("source") Integer source);

	Page<Rfi> queryRfiList(Data314ADto dto);

	Long queryAllFile(Long alertId, Long caseId);

	Long queryAllFile(Long relateId, Integer source);

	Rfi queryRfiHistory(Long control);

	List<Map<String, Object>> queryRfiExport(Data314ADto dto);
}