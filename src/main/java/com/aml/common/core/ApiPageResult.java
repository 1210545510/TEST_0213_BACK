package com.aml.common.core;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 分页返回结果类(若要返回分页数据时，需要调用)
 * 
 * @author zouwei
 * @since 2017年6月23日
 * @param <T>
 */
@JsonInclude(Include.NON_NULL)
public class ApiPageResult<T> extends Result {
	private List<T> data;

	/**
	 * 页码，从1开始
	 */
	private int pageNum;

	/**
	 * 页面大小
	 */
	private int pageSize;

	/**
	 * 起始行
	 */
	private int startRow;
	/**
	 * 末行
	 */
	private int endRow;

	/**
	 * 总数
	 */
	private long total;
	/**
	 * 总页数
	 */
	private int pages;

	/**
	 * 排序
	 */
	private String orderBy;

	public ApiPageResult() {
		super();
	}

	public ApiPageResult(boolean success) {
		super(success);
	}

	public ApiPageResult(ErrorTypeEnum errorType) {
		super(errorType);
	}

	public List<T> getData () {
		return data;
	}

	public void setData ( List<T> data ) {
		this.data = data;
	}

	public int getPageNum () {
		return pageNum;
	}

	public void setPageNum ( int pageNum ) {
		this.pageNum = pageNum;
	}

	public int getPageSize () {
		return pageSize;
	}

	public void setPageSize ( int pageSize ) {
		this.pageSize = pageSize;
	}

	public int getStartRow () {
		return startRow;
	}

	public void setStartRow ( int startRow ) {
		this.startRow = startRow;
	}

	public int getEndRow () {
		return endRow;
	}

	public void setEndRow ( int endRow ) {
		this.endRow = endRow;
	}

	public long getTotal () {
		return total;
	}

	public void setTotal ( long total ) {
		this.total = total;
	}

	public int getPages () {
		return pages;
	}

	public void setPages ( int pages ) {
		this.pages = pages;
	}

	public String getOrderBy () {
		return orderBy;
	}

	public void setOrderBy ( String orderBy ) {
		this.orderBy = orderBy;
	}

	private static final long serialVersionUID = -2761384604892715319L;

}
