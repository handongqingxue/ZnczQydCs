package com.znczQydCs.entity;

public class ShouHuoBuMen {

	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getYfwjlId() {
		return yfwjlId;
	}
	public void setYfwjlId(Integer yfwjlId) {
		this.yfwjlId = yfwjlId;
	}
	public Integer getQyjlId() {
		return qyjlId;
	}
	public void setQyjlId(Integer qyjlId) {
		this.qyjlId = qyjlId;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getBjsj() {
		return bjsj;
	}
	public void setBjsj(String bjsj) {
		this.bjsj = bjsj;
	}
	public Integer getYfwtb() {
		return yfwtb;
	}
	public void setYfwtb(Integer yfwtb) {
		this.yfwtb = yfwtb;
	}
	private Integer yfwjlId;
	private Integer qyjlId;
	private String mc;//名称
	private String bjsj;//编辑时间
	private Integer yfwtb;//云服务同步 1.未同步 2.同步中 3.已同步
}
