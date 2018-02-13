package com.aml.api.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aml.api.dao.AssignRuleItemMapper;
import com.aml.api.dao.AssignRuleMapper;
import com.aml.api.dao.DicTbMapper;
import com.aml.api.dao.NoticeRuleMapper;
import com.aml.api.dao.WfBusStatusMapper;
import com.aml.api.dto.AssignRuleDTO;
import com.aml.api.dto.AssignRuleItemDTO;
import com.aml.api.dto.DicTbDTO;
import com.aml.api.dto.NoticeRuleDTO;
import com.aml.api.dto.WfBusStatusDTO;
import com.aml.api.pojo.AssignRule;
import com.aml.api.pojo.DicTb;
import com.aml.api.pojo.NoticeRule;
import com.aml.api.pojo.WfBusStatus;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.core.Result;
import com.aml.common.util.DozerUtils;

/**
 * 
 * @className: ConfigService
 * @description: 系统设置服务类
 * @author huangliangbao
 * @date 2017年11月15日 下午12:09:45
 *
 */
@Service
public class ConfigService {
	public static final Logger logger = LoggerFactory.getLogger(ConfigService.class);
	@Autowired
	DicTbMapper dicTbMapper;
	@Autowired
	AssignRuleMapper assignRuleMapper;
	@Autowired
	AssignRuleItemMapper assignRuleItemMapper;
	@Autowired
	NoticeRuleMapper noticeRuleMapper;
	@Autowired
	private WfBusStatusMapper wfBusStatusMapper;

	/**
	 * 
	 * @title: addFields
	 * @description: 保存自定义显示列（先删除，再新增）
	 *
	 * @param dicTbs
	 * @param dicId
	 * @return Result
	 * @date 2017年11月16日 上午9:27:10
	 */
	public Result addFields ( List<DicTbDTO> dicTbs, Long dicId ) {
		Result result = new Result(ErrorTypeEnum.OK);
		dicTbMapper.deleteByDicId(dicId);
		if (!ObjectUtils.isEmpty(dicTbs)) {
			dicTbMapper.insertBatch(dicTbs);
		}
		return result;
	}

	/**
	 * 
	 * @title: queryDicTbsByDicId
	 * @description: 根据字典ID, 查询所有显示列
	 *
	 * @param dicId
	 * @return List<DicTb>
	 * @date 2017年11月15日 下午12:11:22
	 */
	public List<DicTb> queryDicTbsByDicId ( Long dicId ) {
		return dicTbMapper.queryDicTbsByDicId(dicId);
	}

	/**
	 * 
	 * @title: queryAssignRuleByRoleId
	 * @description: 根据角色ID，查询QA/Analyst分配规则
	 *
	 * @param roleId
	 * @return AssignRule
	 * @date 2017年11月15日 下午12:11:22
	 */
	public AssignRule queryAssignRuleByRoleId ( Long roleId ) {
		return assignRuleMapper.queryAssignRuleByRoleId(roleId);
	}
	
	/**
	 * 
	 * @title: querySamplingRule
	 * @description: 查询Alert Sampling settings
	 *
	 * @param roleId
	 * @return AssignRule
	 * @date 2017年11月15日 下午12:11:22
	 */
	public AssignRule querySamplingRule ( ) {
		return assignRuleMapper.querySamplingRule();
	}

	/**
	 * 
	 * @title: addAssignRule
	 * @description: 新增分配规则
	 *
	 * @param dto
	 * @return Result
	 * @date 2017年11月23日 上午10:55:01
	 */
	public Result addAssignRule ( AssignRuleDTO dto ) {
		Result result = new Result(ErrorTypeEnum.OK);
		int flag = assignRuleMapper.insert(dto);
		if (flag > 0) {
			if (!ObjectUtils.isEmpty(dto.getRuleItems())) {
				for (AssignRuleItemDTO ruleItem : dto.getRuleItems()) {
					ruleItem.setRuleId(dto.getId());
				}
				assignRuleItemMapper.insertBatch(dto.getRuleItems());
			}
		} else {
			result.setErrorType(ErrorTypeEnum.INSERT_ERROR);
		}
		return result;
	}

	/**
	 * 
	 * @title: queryNoticeRule
	 * @description: 查询Days Settings
	 *
	 * @return List<NoticeRule>
	 * @date 2017年11月15日 下午12:11:22
	 */
	public List<NoticeRule> queryNoticeRule () {
		return noticeRuleMapper.queryNoticeRule();
	}

	/**
	 * 
	 * @title: saveNoticeRule
	 * @description: 保存Days Settings
	 *
	 * @param dto
	 * @return Result
	 * @date 2017年11月23日 上午10:55:01
	 */
	public Result saveNoticeRule ( NoticeRuleDTO dto ) {
		Result result = new Result(ErrorTypeEnum.OK);
		if (ObjectUtils.isEmpty(dto.getId())) {
			noticeRuleMapper.insert(dto);
		} else {
			noticeRuleMapper.update(dto);
		}
		return result;
	}

	/**
	 * 
	 * @title: updateAssignRule
	 * @description: 修改分配规则
	 *
	 * @param dto
	 * @return Result
	 * @date 2017年11月23日 上午10:55:01
	 */
	public Result updateAssignRule ( AssignRuleDTO dto ) {
		Result result = new Result(ErrorTypeEnum.OK);
		int flag = assignRuleMapper.update(dto);
		if (flag > 0) {
			assignRuleItemMapper.deleteByRuleId(dto.getId());
			if (!ObjectUtils.isEmpty(dto.getRuleItems())) {
				for (AssignRuleItemDTO ruleItem : dto.getRuleItems()) {
					ruleItem.setRuleId(dto.getId());
				}
				assignRuleItemMapper.insertBatch(dto.getRuleItems());
			}
		} else {
			result.setErrorType(ErrorTypeEnum.INSERT_ERROR);
		}
		return result;
	}

	/**
	 * 
	 * @title: saveConfigurationStatus
	 * @description: 保存流程状态配置
	 *
	 * @param dto
	 * @param didSid
	 * @date 2017年12月4日 下午3:10:59
	 */
	public void saveConfigurationStatus ( List<WfBusStatusDTO> dto, String didSid ) {
		List<WfBusStatus> mapList = DozerUtils.mapList(dto, WfBusStatus.class);
		int i = 0;
		wfBusStatusMapper.updateByIsDeleted(mapList.get(0).getFlowId(), didSid);
		for (WfBusStatus data : mapList) {
			Integer id = data.getId();
			data.setDidSid(didSid);
			if (ObjectUtils.isEmpty(id)) {
				data.setSortIndex(String.valueOf(i));
				data.setCreateTime(new Date());
				wfBusStatusMapper.insertSelective(data);
				i++;
			} else {
				data.setUpdateTime(new Date());
				data.setIdDeleted((byte) 0);
				wfBusStatusMapper.updateByPrimaryKeySelective(data);
			}
		}
	}

	public WfBusStatus findStatus ( String flowId, Integer dicKey, Integer statusType, String paramValue ) {
		return wfBusStatusMapper.findStatus(flowId, dicKey, statusType, paramValue);
	}

}
