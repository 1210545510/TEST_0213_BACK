package com.aml.api.dto;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FormComfigurationDto extends BaseDto {
	private static final long serialVersionUID = 5314214481041106624L;
	private Integer id;
	private String displayName;
	private String eormName;
	private String editable;
	private String tableName;
	private String colunmName;
	private Integer isDeleted;
	private String param;

	public Integer getId () {
		return id;
	}

	public void setId ( Integer id ) {
		this.id = id;
	}

	public String getDisplayName () {
		return displayName;
	}

	public void setDisplayName ( String displayName ) {
		this.displayName = displayName == null ? null : displayName.trim();
	}

	public String getEormName () {
		return eormName;
	}

	public void setEormName ( String eormName ) {
		this.eormName = eormName == null ? null : eormName.trim();
	}

	public String getEditable () {
		return editable;
	}

	public void setEditable ( String editable ) {
		this.editable = editable == null ? null : editable.trim();
	}

	public String getTableName () {
		return tableName;
	}

	public void setTableName ( String tableName ) {
		this.tableName = tableName == null ? null : tableName.trim();
	}

	public String getColunmName () {
		return colunmName;
	}

	public void setColunmName ( String colunmName ) {
		this.colunmName = colunmName == null ? null : colunmName.trim();
	}

	public Integer getIsDeleted () {
		return isDeleted;
	}

	public void setIsDeleted ( Integer isDeleted ) {
		this.isDeleted = isDeleted;
	}

	public String getParam () {
		return param;
	}

	public void setParam ( String param ) {
		this.param = param;
	}

}