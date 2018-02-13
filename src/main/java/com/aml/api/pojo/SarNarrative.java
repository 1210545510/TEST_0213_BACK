package com.aml.api.pojo;

import com.aml.common.base.BasePoJo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SarNarrative extends BasePoJo {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6189307674177017621L;

	private Long narrativeId;

    private Long rid;

    private Byte isDeleted;

    private String content;

    public Long getNarrativeId() {
        return narrativeId;
    }

    public void setNarrativeId(Long narrativeId) {
        this.narrativeId = narrativeId;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}