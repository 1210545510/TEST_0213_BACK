package com.aml.api.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.Surrogate;
import org.snaker.engine.helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aml.api.dao.TaskApprovalMapper;
import com.aml.api.dao.TaskFileMapper;
import com.aml.api.dto.TaskApprovalDTO;
import com.aml.api.pojo.TaskApproval;
import com.aml.api.pojo.TaskFile;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.core.Result;

/**
 * 
 * @className: TaskApprovalService
 * @description: 审批服务类
 * @author huangliangbao
 * @date 2017年11月15日 下午12:09:45
 *
 */
@Service
public class TaskApprovalService {
	public static final Logger logger = LoggerFactory.getLogger(TaskApprovalService.class);
	@Autowired
	TaskApprovalMapper taskApprovalMapper;
	@Autowired
	TaskFileMapper taskFileMapper;

	/**
	 * 
	 * @title: save
	 * @description: 保存/修改审批记录（包括附件）
	 *
	 * @param dto 审批记录
	 * @param fileList 附件集合
	 * @return Result
	 * @date 2017年11月30日 下午4:29:10
	 */
	public Result save ( TaskApprovalDTO dto, List<TaskFile> fileList ) {
		Result result = new Result(ErrorTypeEnum.OK);
		// 保存
		if (ObjectUtils.isEmpty(dto.getApprovalId())) {
			int flag = taskApprovalMapper.insert(dto);
			if (flag > 0) {
				if (!ObjectUtils.isEmpty(fileList)) {
					taskFileMapper.insertBatch(fileList);
				}
			} else {
				result.setErrorType(ErrorTypeEnum.INSERT_ERROR);
			}
		} else {// 修改
			int flag = taskApprovalMapper.update(dto);
			if (flag > 0) {
				if (!ObjectUtils.isEmpty(fileList)) {
					taskFileMapper.deleteByTaskId(dto.getTaskId());
					taskFileMapper.insertBatch(fileList);
				}
			} else {
				result.setErrorType(ErrorTypeEnum.UPDATE_ERROR);
			}
		}
		return result;
	}

	/**
	 * 
	 * @title: findByTaskId
	 * @description: 查询一条审批记录（包括附件）
	 *
	 * @param taskId
	 * @return TaskApproval
	 * @date 2017年11月16日 上午9:34:49
	 */
	public TaskApproval findByTaskId ( String taskId ) {
		return taskApprovalMapper.findByTaskId(taskId);
	}

	/**
	 * 
	 * @title: findHistoryOrder
	 * @description: 查询历史流程实例
	 *
	 * @param id
	 * @return
	 * @date 2018年1月20日 下午12:34:35
	 */
	public HistoryOrder findHistoryOrder ( String id ) {
		return taskApprovalMapper.findHistoryOrder(id);
	}

	/**
	 * 
	 * @title: queryHistoryApprove
	 * @description: 查询历史审批记录列表
	 *
	 * @param orderId
	 * @param dicId
	 * @return List<Map<String, Object>>
	 * @date 2017年11月16日 上午9:34:49
	 */
	public List<Map<String, Object>> queryHistoryApprove ( String orderId, Long dicId ) {
		return taskApprovalMapper.queryHistoryApprove(dicId, orderId);
	}
	
	/**
	 * 
	 * @title: queryLastAlertApprove
	 * @description: 查询Alert最后一条审批记录
	 *
	 * @param orderId
	 * @param dicId
	 * @return List<Map<String, Object>>
	 * @date 2017年11月16日 上午9:34:49
	 */
	public Map<String, Object> queryLastAlertApprove ( String orderId, Long dicId ) {
		return taskApprovalMapper.queryLastAlertApprove(dicId, orderId);
	}

	/**
	 * 
	 * @title: saveOrUpdate
	 * @description: 保存/修改授权记录
	 *
	 * @param entity 授权对象
	 * @return Result
	 * @date 2018年1月3日 下午2:24:08
	 */
	public Result saveOrUpdate ( Surrogate entity ) {
		Result result = new Result(ErrorTypeEnum.OK);
		// 保存
		if (ObjectUtils.isEmpty(entity.getId())) {
			entity.setId(StringHelper.getPrimaryKey());
			int flag = taskApprovalMapper.saveSurrogate(entity);
			if (flag <= 0) {
				result.setErrorType(ErrorTypeEnum.INSERT_ERROR);
			}
		} else {// 修改
			int flag = taskApprovalMapper.updateSurrogate(entity);
			if (flag <= 0) {
				result.setErrorType(ErrorTypeEnum.UPDATE_ERROR);
			}
		}

		return result;
	}

}
