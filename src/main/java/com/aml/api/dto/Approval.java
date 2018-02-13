package com.aml.api.dto;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author yuqs
 */
@JsonInclude(Include.NON_NULL)
public class Approval extends BaseDto {
	private static final long serialVersionUID = 1280430261161731105L;
	/** 主键标识ID */
	private Long id;
	/** 流程实例ID */
	private String orderId;
	/** 任务ID */
	private String taskId;
	private String operator;
	private String operateTime;
	private String result;
	private String content;
	private String taskName;
	private String description;

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public String getOrderId () {
		return orderId;
	}

	public void setOrderId ( String orderId ) {
		this.orderId = orderId;
	}

	public String getTaskId () {
		return taskId;
	}

	public void setTaskId ( String taskId ) {
		this.taskId = taskId;
	}

	public String getOperator () {
		return operator;
	}

	public void setOperator ( String operator ) {
		this.operator = operator;
	}

	public String getOperateTime () {
		return operateTime;
	}

	public void setOperateTime ( String operateTime ) {
		this.operateTime = operateTime;
	}

	public String getResult () {
		return result;
	}

	public void setResult ( String result ) {
		this.result = result;
	}

	public String getContent () {
		return content;
	}

	public void setContent ( String content ) {
		this.content = content;
	}

	public String getTaskName () {
		return taskName;
	}

	public void setTaskName ( String taskName ) {
		this.taskName = taskName;
	}

	public String getDescription () {
		return description;
	}

	public void setDescription ( String description ) {
		this.description = description;
	}

}
