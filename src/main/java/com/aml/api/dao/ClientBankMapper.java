package com.aml.api.dao;

import com.aml.api.dto.ClientBankDto;
import com.aml.api.pojo.ClientBank;
import com.github.pagehelper.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ClientBankMapper {
	int deleteByPrimaryKey ( BigDecimal instnSeqId );

	int insert ( ClientBank record );

	int insertSelective ( ClientBank record );

	ClientBank selectByPrimaryKey ( BigDecimal instnSeqId );

	int updateByPrimaryKeySelective ( ClientBank record );

	int updateByPrimaryKey ( ClientBank record );

	Page<ClientBank> getClientBankList ( ClientBankDto dto );

	List<Map<String, Object>> downClientBankExcel ( ClientBankDto dto );
}