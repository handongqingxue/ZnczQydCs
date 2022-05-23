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
	public Integer getDdId() {
		return ddId;
	}
	public void setDdId(Integer ddId) {
		this.ddId = ddId;
	}
	public String getDdh() {
		return ddh;
	}
	public void setDdh(String ddh) {
		this.ddh = ddh;
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
	private Integer yfwjlId;
	private Integer ddId;
	private String ddh;
	private String pdsj;
	private Integer dlh;
	private Integer pdh;
	private Integer zt;
	private Integer qytb;//企业同步 1.未同步 2.同步中 3.已同步
	private String qyh;
}
