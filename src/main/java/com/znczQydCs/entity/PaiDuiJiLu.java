package com.znczQydCs.entity;

public class PaiDuiJiLu {

	public static final Integer PAI_DUI_ZHONG=1;//排队中
	public static final Integer YI_WAN_CHENG=2;//已完成
	public static final Integer YI_GUO_HAO=3;//已过号
	
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
	public String getDdh() {
		return ddh;
	}
	public void setDdh(String ddh) {
		this.ddh = ddh;
	}
	public String getCph() {
		return cph;
	}
	public void setCph(String cph) {
		this.cph = cph;
	}
	public String getPdsj() {
		return pdsj;
	}
	public void setPdsj(String pdsj) {
		this.pdsj = pdsj;
	}
	public Integer getDlh() {
		return dlh;
	}
	public void setDlh(Integer dlh) {
		this.dlh = dlh;
	}
	public Integer getPdh() {
		return pdh;
	}
	public void setPdh(Integer pdh) {
		this.pdh = pdh;
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	public Integer getYfwtb() {
		return yfwtb;
	}
	public void setYfwtb(Integer yfwtb) {
		this.yfwtb = yfwtb;
	}
	public String getQyh() {
		return qyh;
	}
	public void setQyh(String qyh) {
		this.qyh = qyh;
	}
	private Integer yfwjlId;
	private Integer yfwDdId;
	private Integer qyDdId;
	private String ddh;
	private String cph;
	private String pdsj;
	private Integer dlh;
	private Integer pdh;
	private Integer zt;
	private Integer yfwtb;//云服务同步 1.未同步 2.同步中 3.已同步
	private String qyh;
}
