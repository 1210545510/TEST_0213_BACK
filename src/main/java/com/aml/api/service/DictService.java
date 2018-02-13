package com.aml.api.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aml.api.dao.DicMapper;
import com.aml.api.dao.DicSubMapper;
import com.aml.api.dao.WfBusStatusMapper;
import com.aml.api.dto.DicDTO;
import com.aml.api.dto.DicSubDTO;
import com.aml.api.dto.WfBusStatusDTO;
import com.aml.api.pojo.Dic;
import com.aml.api.pojo.DicSub;
import com.aml.api.pojo.WfBusStatus;
import com.aml.common.core.ErrorTypeEnum;
import com.aml.common.core.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @className: DictService
 * @description: 数据字典服务类
 * @author huangliangbao
 * @date 2017年11月15日 下午12:09:45
 *
 */
@Service
public class DictService {
	public static final Logger logger = LoggerFactory.getLogger(DictService.class);
	@Autowired
	DicMapper dicMapper;
	@Autowired
	DicSubMapper dicSubMapper;
	@Autowired
	WfBusStatusMapper wfBusStatusMapper;

	/**
	 * 
	 * @title: delete
	 * @description: 删除
	 *
	 * @param dicId
	 * @return Result
	 * @date 2017年11月16日 上午9:09:43
	 */
	public Result delete ( Long dicId ) {
		Result result = new Result(ErrorTypeEnum.OK);
		int flag = dicMapper.delete(dicId);
		if (flag > 0) {
			dicSubMapper.deleteByDicId(dicId);
		} else {
			result.setErrorType(ErrorTypeEnum.DELETE_ERROR);
		}

		return result;
	}

	/**
	 * 
	 * @title: deleteDicSub
	 * @description: 删除字典项
	 *
	 * @param dicSId
	 * @return Result
	 * @date 2017年11月16日 上午9:09:43
	 */
	public Result deleteDicSub ( Long dicSId ) {
		Result result = new Result(ErrorTypeEnum.OK);
		int flag = dicSubMapper.delete(dicSId);
		if (flag <= 0) {
			result.setErrorType(ErrorTypeEnum.DELETE_ERROR);
		}

		return result;
	}

	/**
	 * 
	 * @title: insert
	 * @description: 新增
	 *
	 * @param params
	 * @return
	 * @date 2017年11月16日 上午9:27:10
	 */
	public Result insert ( DicDTO dto ) {
		Result result = new Result(ErrorTypeEnum.OK);
		int flag = dicMapper.insert(dto);
		if (flag > 0) {
			if (!ObjectUtils.isEmpty(dto.getDicSubs())) {
				for (DicSubDTO dicSub : dto.getDicSubs()) {
					dicSub.setDicId(dto.getDicId());
				}
				dicSubMapper.insertBatch(dto.getDicSubs());
			}
		} else {
			result.setErrorType(ErrorTypeEnum.INSERT_ERROR);
		}
		return result;
	}

	/**
	 * 
	 * @title: findDicById
	 * @description: 查询一条记录
	 *
	 * @param dicId
	 * @return
	 * @date 2017年11月16日 上午9:34:49
	 */
	public Dic findDicById ( Long dicId ) {
		return dicMapper.findDicById(dicId);
	}

	/**
	 * 
	 * @title: update
	 * @description: 修改
	 *
	 * @param params
	 * @return Result
	 * @date 2017年11月16日 上午9:32:07
	 */
	public Result update ( DicDTO dto ) {
		Result result = new Result(ErrorTypeEnum.OK);
		int flag = dicMapper.update(dto);
		if (flag > 0) {
			if (!ObjectUtils.isEmpty(dto.getDicSubs())) {
				for (DicSubDTO dicSub : dto.getDicSubs()) {
					dicSub.setDicId(dto.getDicId());
				}
				dicSubMapper.deleteByDicId(dto.getDicId());
				dicSubMapper.insertBatch(dto.getDicSubs());
			}
		} else {
			result.setErrorType(ErrorTypeEnum.UPDATE_ERROR);
		}
		return result;
	}

	/**
	 * 
	 * @title: queryDicts
	 * @description: 查询所有数据字典
	 *
	 * @param params 查询条件
	 * @return Page<Dic>
	 * @date 2017年11月15日 下午12:11:22
	 */
	public Page<Dic> queryDicts ( HashMap<String, String> params ) {
		return dicMapper.queryDicts(params);
	}
	
	/**
	 * 
	 * @title: queryDicSubsByDicId
	 * @description: 根据字典ID, 查询所有字典项列表
	 *
	 * @param dicId
	 * @return List<DicSub>
	 * @date 2017年11月15日 下午12:11:22
	 */
	public List<DicSub> queryDicSubsByDicId ( Long dicId ) {
		return dicSubMapper.queryDicSubsByDicId(dicId);
	}

	/**
	 * 分页查询流程字典项列表
	 * @param dicId
	 * @return
	 * @date 2017年11月30日
	 */
	public Page<DicSub> pageProcessState(WfBusStatusDTO dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		return dicSubMapper.pageProcessState(dto);
	}

	/**
	 * 查询流程配置
	 * @param flowId
	 * @param didSid
	 * @return
	 * @date 2017年12月1日
	 */
	public List<WfBusStatus> getconfigurationStatus(String flowId, String didSid) {
		return wfBusStatusMapper.getconfigurationStatus(flowId, didSid);
	}

}
