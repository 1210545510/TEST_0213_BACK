package com.aml.common.util;

import com.aml.common.core.ApiPageResult;
import com.github.pagehelper.Page;

/**
 * 分页工具类
 * 
 * @author zouwei
 * @since 2016年11月21日
 */
public class PageUtils {

	/**
	 * mybatis pagehelper page类型转成 ApiPageResult类型
	 * 
	 * @param page
	 * @return
	 */
	public static <T> void toApiPageResult ( ApiPageResult<T> result, Page<T> page ) {
		if (page == null) {
			return;
		}
		result.setData(page);
		result.setPageNum(page.getPageNum());
		result.setPageSize(page.getPageSize());
		result.setStartRow(page.getStartRow());
		result.setEndRow(page.getEndRow());
		result.setTotal(page.getTotal());
		result.setPages(page.getPages());
		result.setOrderBy(page.getOrderBy());
	}

}
