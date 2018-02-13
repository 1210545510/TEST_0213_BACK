package com.aml.api.dto;

import java.util.List;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FieldDTO extends BaseDto {
	private static final long serialVersionUID = 1L;
	private Long dicId;
	/** 数据字典子项 */
	private List<DicTbDTO> dicTbs;

	public Long getDicId () {
		return dicId;
	}

	public void setDicId ( Long dicId ) {
		this.dicId = dicId;
	}

	public List<DicTbDTO> getDicTbs () {
		return dicTbs;
	}

	public void setDicTbs ( List<DicTbDTO> dicTbs ) {
		this.dicTbs = dicTbs;
	}

}