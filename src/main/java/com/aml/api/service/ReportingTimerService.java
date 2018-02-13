package com.aml.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aml.api.dao.AlertGroupMapper;
import com.aml.api.dao.CaseGroupMapper;
import com.aml.common.util.PropertiesUtil;


/**
 * Reporting 每日数据insert
 * @author Novan
 *
 */
@Service
public class ReportingTimerService {
	/**SAR QA的角色id*/
	protected static final Integer SARQA = Integer.valueOf(PropertiesUtil.getValue("Sar.QA"));
	/**SAR analyst 分析员的角色id*/
	protected static final Integer SARANALYST = Integer.valueOf(PropertiesUtil.getValue("Sar.analyst"));

	@Autowired
	private AlertGroupMapper alertGroupMapper;
	
	@Autowired
	private CaseGroupMapper caseGroupMapper;
	
	/**
	 * insert ReportStatisTotal
	 */
	public void insertData(){
		//统计前一天系统自动生成的Alert数量
		Integer alertCount = alertGroupMapper.queryAlertCountMantas();
		//统计前一天Alert升级为Case的总数
		Integer caseCount = caseGroupMapper.queryCaseCountByAlert();
		//统计前一天最终Filed SAR的总数
		Integer sarCount = caseGroupMapper.querySarCountByCase();
		
		//统计前一天SAR QA待处理Case的数量
		Integer sarQaCaseCount = caseGroupMapper.queryCaseCountByRoleId(SARQA);
		//统计前一天SAR analyst 待处理Case的数量
		Integer sarAnalystCaseCount = caseGroupMapper.queryCaseCountByRoleId(SARANALYST);
		//统计前一天手工创建Case的数量
		Integer createCaseCount = caseGroupMapper.queryCaseCountCreate();
		
		//统计前一天Alert被QA驳回并最终Waived数量
		Integer reversedAlertWaivedCount = alertGroupMapper.queryAlertWaivedByQa();
		//统计前一天Case最终升级为SAR的数量
		Integer CaseSarCount = caseGroupMapper.queryCaseToSarCount();
		//统计前一天Case被Reasonable的数量
		Integer CaseReasonableCount = caseGroupMapper.queryCaseReasonableCount();
		System.out.println(alertCount);
		System.out.println(caseCount);
		System.out.println(sarCount);
		System.out.println(sarQaCaseCount);
		System.out.println(sarAnalystCaseCount);
		System.out.println(createCaseCount);
		System.out.println(reversedAlertWaivedCount);
		System.out.println(CaseSarCount);
		System.out.println(CaseReasonableCount);
	}
	
}
