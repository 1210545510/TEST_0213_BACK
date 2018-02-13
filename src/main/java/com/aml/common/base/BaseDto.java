package com.aml.common.base;

import org.apache.commons.lang3.StringUtils;

/**
 * 请求baseDto
 * 
 * @author zouwei
 * @since 2017年6月23日
 */
public abstract class BaseDto extends BaseBean {
	private static final long serialVersionUID = 321152699355553927L;

	/** 请求token */
	private String token;

	/** 用户Id */
	private Long userId;

	/** 用户名 */
	private String userName;

	/** 请求当前页码，默认为1 */
	private Integer pageNum = 1;

	/** 请求每页显示多少条数据，默认20 */
	private Integer pageSize = 20;

	/** 偏移量，mysql用于分页的第一个参数 */
	private int offset;

	/** 排序字段 */
	private String orderby = "update_time";

	public BaseDto() {

	}

	public String getToken () {
		return token;
	}

	public void setToken ( String token ) {
		this.token = token;
	}

	public Long getUserId () {
		return userId;
	}

	public void setUserId ( Long userId ) {
		this.userId = userId;
	}

	public Integer getPageNum () {
		return pageNum;
	}

	public void setPageNum ( Integer pageNum ) {
		if (pageNum == null || pageNum == 0) {
			this.pageNum = 1;
		} else {
			this.pageNum = pageNum;
		}
		this.offset = (this.pageNum - 1) * this.pageSize;
	}

	public Integer getPageSize () {
		return pageSize;
	}

	public void setPageSize ( Integer pageSize ) {
		if (pageSize == null || pageSize == 0) {
			this.pageSize = 20;
		} else {
			this.pageSize = pageSize;
		}
		this.offset = (this.pageNum - 1) * this.pageSize;
	}

	public String getOrderby () {
		return orderby;
	}

	public void setOrderby ( String orderby ) {
		if (StringUtils.isBlank(orderby)) {
			this.orderby = "update_time";
		} else {
			this.orderby = orderby;
		}
	}

	public int getOffset () {
		return offset;
	}

	public void setOffset ( int offset ) {
		this.offset = offset;
	}

	public String getUserName () {
		return userName;
	}

	public void setUserName ( String userName ) {
		this.userName = userName;
	}
}
