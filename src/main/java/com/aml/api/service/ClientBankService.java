package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.ClientBankMapper;
import com.aml.api.dto.ClientBankDto;
import com.aml.api.pojo.ClientBank;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: ClientBankService
 * @description: ClientBankService  Class
 * @author Novan
 * @date 2017-11-27
 *
 */
@Service
public class ClientBankService {
	public static final Logger logger = LoggerFactory.getLogger(ClientBankService.class);

	@Autowired
	ClientBankMapper clientBankMapper;
	
	public Page<ClientBank> getClientBankList(ClientBankDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return clientBankMapper.getClientBankList(dto);
	}

	/**
	 * export
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> downClientBankExcel(ClientBankDto dto) {
		return clientBankMapper.downClientBankExcel(dto);
	}
}
