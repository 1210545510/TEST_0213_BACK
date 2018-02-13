package com.aml.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.DozerBeanMapper;

/**
 * 
 * @className: DozerUtils
 * @description: DTO/VO/DO等之间的转换
 * @author huangliangbao
 * @date 2017年11月8日 下午6:12:11
 *
 */
public class DozerUtils {
	/**
	 * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
	 */
	private static DozerBeanMapper dozer = new DozerBeanMapper();

	/**
	 * 
	 * @title: map
	 * @description: 单个对象相互转换
	 *
	 * @param source 源对象
	 * @param destinationClass 目标对象
	 * @return
	 * @date 2017年11月8日 下午6:08:54
	 */
	public static <T> T map ( Object source, Class<T> destinationClass ) {
		return dozer.map(source, destinationClass);
	}

	/**
	 * 
	 * @title: mapList
	 * @description: 集合对象相互转换
	 *
	 * @param sourceList
	 * @param destinationClass
	 * @return
	 * @date 2017年11月8日 下午6:09:41
	 */
	@SuppressWarnings("rawtypes")
	public static <T> List<T> mapList ( Collection sourceList, Class<T> destinationClass ) {
		List<T> destinationList = new ArrayList<T>();
		for (Object sourceObject : sourceList) {
			T destinationObject = dozer.map(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		}
		
		return destinationList;
	}

}
