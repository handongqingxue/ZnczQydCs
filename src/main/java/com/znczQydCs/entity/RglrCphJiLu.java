package com.znczQydCs.entity;

/**
 * 人工录入车牌号记录
 * */
public class RglrCphJiLu {

	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSjc() {
		return sjc;
	}
	public void setSjc(String sjc) {
		this.sjc = sjc;
	}
	public String getWscph() {
		return wscph;
	}
	public void setWscph(String wscph) {
		this.wscph = wscph;
	}
	public String getCph() {
		return cph;
	}
	public void setCph(String cph) {
		this.cph = cph;
	}
	public String getLrsj() {
		return lrsj;
	}
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	public Integer getDdId() {
		return ddId;
	}
	public void setDdId(Integer ddId) {
		this.ddId = ddId;
	}
	private String sjc;
	private String wscph;
	private String cph;
	private String lrsj;
	private Integer ddId;
}
