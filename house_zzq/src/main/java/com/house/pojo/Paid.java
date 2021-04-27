package com.house.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "paid")
public class Paid {

	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "`id`")
	private Integer id;

	@Column(name = "address")
	private String address;

	@Column(name = "price")
	private Double price;

	@Column(name = "date")
	private Date date;

	@Column(name = "paydate")
	private Date payDate;

	@Column(name = "name")
	private String name;

	@Column(name = "userlist_id")
	private Integer userlist_id;

	@Column(name = "status")
	private String status;

	@Column(name = "paytype")
	private String paytype;

	@Column(name = "houseid")
	private Integer houseId;

	@Column(name = "orderid")
	private String orderid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUserlist_id() {
		return userlist_id;
	}

	public void setUserlist_id(Integer userlist_id) {
		this.userlist_id = userlist_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

}
