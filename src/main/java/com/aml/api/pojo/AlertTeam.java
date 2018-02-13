package com.aml.api.pojo;

import java.util.List;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AlertTeam extends BasePoJo {
	private static final long serialVersionUID = 1L;
	private String ownerOrg;
	private List<String> list;
	public String getOwnerOrg () {
		return ownerOrg;
	}
	public void setOwnerOrg ( String ownerOrg ) {
		this.ownerOrg = ownerOrg;
	}
	public List<String> getList () {
		return list;
	}
	public void setList ( List<String> list ) {
		this.list = list;
	}
	
	
	
}
