package com.aml.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.Surrogate;

import com.aml.api.dto.TaskApprovalDTO;
import com.aml.api.pojo.TaskApproval;

public interface TaskApprovalMapper {
	int deleteByPrimaryKey ( Long approvalId );

	int insert ( TaskApproval record );

	int insertSelective ( TaskApproval record );

	TaskApproval selectByPrimaryKey ( Long approvalId );

	int updateByPrimaryKeySelective ( TaskApproval record );

	int updateByPrimaryKey ( TaskApproval record );

	int insert ( TaskApprovalDTO dto );

	int update ( TaskApprovalDTO dto );

	int deleteByTaskId ( String taskId );

	TaskApproval findByTaskId ( String taskId );

	HistoryOrder findHistoryOrder ( String id );

	List<Map<String, Object>> queryHistoryApprove ( @Param("dicId") Long dicId, @Param("orderId") String orderId );

	Map<String, Object> queryLastAlertApprove ( @Param("dicId") Long dicId, @Param("orderId") String orderId );

	List<String> queryUserNameByRoleId ( @Param("orderId") String orderId, @Param("roleId") String roleId );

	int saveSurrogate ( Surrogate entity );

	int updateSurrogate ( Surrogate entity );

}