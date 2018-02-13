package com.aml.api.dto;

import java.util.List;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class DicDTO extends BaseDto {
	private static final long serialVersionUID = 1L;
	private Long dicId;
	private String dicCode;
	private String dicName;
	private String dicValue;
	private Integer sortIndex;
	private String remark;
	/** 数据字典子项 */
	private List<DicSubDTO> dicSubs;

	public Long getDicId () {
		return dicId;
	}

	public void setDicId ( Long dicId ) {
		this.dicId = dicId;
	}

	public String getDicCode () {
		return dicCode;
	}

	public void setDicCode ( String dicCode ) {
		this.dicCode = dicCode == null ? null : dicCode.trim();
	}

	public String getDicName () {
		return dicName;
	}

	public void setDicName ( String dicName ) {
		this.dicName = dicName == null ? null : dicName.trim();
	}

	public String getDicValue () {
		return dicValue;
	}

	public void setDicValue ( String dicValue ) {
		this.dicValue = dicValue == null ? null : dicValue.trim();
	}

	public Integer getSortIndex () {
		return sortIndex;
	}

	public void setSortIndex ( Integer sortIndex ) {
		this.sortIndex = sortIndex;
	}

	public String getRemark () {
		return remark;
	}

	public void setRemark ( String remark ) {
		this.remark = remark == null ? null : remark.trim();
	}

	public List<DicSubDTO> getDicSubs () {
		return dicSubs;
	}

	public void setDicSubs ( List<DicSubDTO> dicSubs ) {
		this.dicSubs = dicSubs;
	}

}