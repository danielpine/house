package com.house.pojo;

import java.util.Date;

public class Order {
	private String orderid;
	private Integer houseid;
	private Integer userid;
	private String status;
	private Date createdate;
	private Date startdate;
	private Date enddate;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Integer getHouseid() {
		return houseid;
	}

	public void setHouseid(Integer houseid) {
		this.houseid = houseid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", houseid=" + houseid + ", userid=" + userid + ", status=" + status
				+ ", createdate=" + createdate + ", startdate=" + startdate + ", enddate=" + enddate + "]";
	}

}
