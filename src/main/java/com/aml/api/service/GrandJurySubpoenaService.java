package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.GrandJurySubpoenaMapper;
import com.aml.api.dto.GrandJurySubpoenaDto;
import com.aml.api.pojo.GrandJurySubpoena;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: GrandJurySubpoenaService
 * @description: GrandJurySubpoenaService  Class
 * @author Novan
 * @date 2017-11-27
 *
 */
@Service
public class GrandJurySubpoenaService {
	public static final Logger logger = LoggerFactory.getLogger(GrandJurySubpoenaService.class);

	@Autowired
	GrandJurySubpoenaMapper grandJurySubpoenaMapper;
	
	/**
	 * query
	 * @param dto
	 * @return
	 */
	public Page<GrandJurySubpoena> getGrandJurySubpoenaList(GrandJurySubpoenaDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return grandJurySubpoenaMapper.getGrandJurySubpoenaList(dto);
	}

	/**
	 * Export
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downGrandJurySubpoenaExcel(GrandJurySubpoenaDto dto) {
		return grandJurySubpoenaMapper.downGrandJurySubpoenaExcel(dto);
	}

	/**
	 * import
	 * @param grandJurySubpoenaList
	 */
	public void insertGrandJurySubpoena(List<GrandJurySubpoena> grandJurySubpoenaList) {
		for (GrandJurySubpoena grandJurySubpoena : grandJurySubpoenaList) {
			grandJurySubpoenaMapper.insertSelective(grandJurySubpoena);
		}
	}
	
	

}
