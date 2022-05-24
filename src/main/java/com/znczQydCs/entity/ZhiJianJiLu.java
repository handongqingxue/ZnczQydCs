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
	public Integer getYfwjlId() {
		return yfwjlId;
	}
	public void setYfwjlId(Integer yfwjlId) {
		this.yfwjlId = yfwjlId;
	}
	public Integer getYfwDdId() {
		return yfwDdId;
	}
	public void setYfwDdId(Integer yfwDdId) {
		this.yfwDdId = yfwDdId;
	}
	public Integer getQyDdId() {
		return qyDdId;
	}
	public void setQyDdId(Integer qyDdId) {
		this.qyDdId = qyDdId;
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
	public Integer getQytb() {
		return qytb;
	}
	public void setQytb(Integer qytb) {
		this.qytb = qytb;
	}
	public String getQyh() {
		return qyh;
	}
	public void setQyh(String qyh) {
		this.qyh = qyh;
	}
	public String getDdh() {
		return ddh;
	}
	public void setDdh(String ddh) {
		this.ddh = ddh;
	}
	public String getDdztMc() {
		return ddztMc;
	}
	public void setDdztMc(String ddztMc) {
		this.ddztMc = ddztMc;
	}
	private Integer yfwjlId;
	private Integer yfwDdId;
	private Integer qyDdId;
	private Integer zjyId;
	private String zjyZsxm;
	private String zjsj;
	private Integer jg;//结果 1.合格 2.不合格
	private String bz;
	private Integer qytb;//企业已同步 1.未同步 2.同步中 3.已同步
	private String qyh;
	private String ddh;//订单号
	private String ddztMc;
}
