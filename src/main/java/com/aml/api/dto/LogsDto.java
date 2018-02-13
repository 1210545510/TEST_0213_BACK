package com.aml.api.dto;

import com.aml.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class LogsDto extends BaseDto {
	private static final long serialVersionUID = -8845048801279691314L;
	private Integer id;
	private String codeName;
	private String name;
	private String precess;
	private String enbled;
	private String modiffied;
	private Integer isDeleted;
	private String createdBy;
	private String created;
	private String midiffiedBy;
	private String param;

	public Integer getId () {
		return id;
	}

	public void setId ( Integer id ) {
		this.id = id;
	}

	public String getCodeName () {
		return codeName;
	}

	public void setCodeName ( String codeName ) {
		this.codeName = codeName == null ? null : codeName.trim();
	}

	public String getName () {
		return name;
	}

	public void setName ( String name ) {
		this.name = name == null ? null : name.trim();
	}

	public String getPrecess () {
		return precess;
	}

	public void setPrecess ( String precess ) {
		this.precess = precess == null ? null : precess.trim();
	}

	public String getEnbled () {
		return enbled;
	}

	public void setEnbled ( String enbled ) {
		this.enbled = enbled == null ? null : enbled.trim();
	}

	public String getModiffied () {
		return modiffied;
	}

	public void setModiffied ( String modiffied ) {
		this.modiffied = modiffied == null ? null : modiffied.trim();
	}

	public Integer getIsDeleted () {
		return isDeleted;
	}

	public void setIsDeleted ( Integer isDeleted ) {
		this.isDeleted = isDeleted;
	}

	public String getCreatedBy () {
		return createdBy;
	}

	public void setCreatedBy ( String createdBy ) {
		this.createdBy = createdBy == null ? null : createdBy.trim();
	}

	public String getCreated () {
		return created;
	}

	public void setCreated ( String created ) {
		this.created = created == null ? null : created.trim();
	}

	public String getMidiffiedBy () {
		return midiffiedBy;
	}

	public void setMidiffiedBy ( String midiffiedBy ) {
		this.midiffiedBy = midiffiedBy == null ? null : midiffiedBy.trim();
	}

	public String getParam () {
		return param;
	}

	public void setParam ( String param ) {
		this.param = param;
	}

}