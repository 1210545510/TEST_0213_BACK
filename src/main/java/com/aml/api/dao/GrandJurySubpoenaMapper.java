package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import com.aml.api.dto.GrandJurySubpoenaDto;
import com.aml.api.pojo.GrandJurySubpoena;
import com.github.pagehelper.Page;

public interface GrandJurySubpoenaMapper {
	int insert ( GrandJurySubpoena record );

	int insertSelective ( GrandJurySubpoena record );

	Page<GrandJurySubpoena> getGrandJurySubpoenaList ( GrandJurySubpoenaDto dto );

	List<Map<String, Object>> downGrandJurySubpoenaExcel ( GrandJurySubpoenaDto dto );
}