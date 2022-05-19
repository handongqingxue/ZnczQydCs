package com.znczQydCs.entity;

public class ZhiJianJiLu {

	public static final Integer HE_GE=1;//合格
	public static final Integer BU_HE_GE=2;//不合格
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDdId() {
		return ddId;
	}
	public void setDdId(Integer ddId) {
		this.ddId = ddId;
	}
	public Integer getZjyId() {
		return zjyId;
	}
	public void setZjyId(Integer zjyId) {
		this.zjyId = zjyId;
	}
	public String getZjyZsxm() {
		return zjyZsxm;
	}
	public void setZjyZsxm(String zjyZsxm) {
		this.zjyZsxm = zjyZsxm;
	}
	public String getZjsj() {
		return zjsj;
	}
	public void setZjsj(String zjsj) {
		this.zjsj = zjsj;
	}
	public Integer getJg() {
		return jg;
	}
	public void setJg(Integer jg) {
		this.jg = jg;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	private Integer ddId;
	private Integer zjyId;
	private String zjyZsxm;
	private String zjsj;
	private Integer jg;//结果 1.合格 2.不合格
	private String bz;
}
