/**
 * Domain Object也称为实体类，它代表了业务的状态，一般来说，领域对象属于业务层。但它贯穿
 * 于展现层、业务层和持久层，并最终被持久化到数据库中。一般领域对象对应于数据库表
 */
package com.baobaotao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 *领域对象一般要实现Serializable接口，以便可以序列化
 */
public class User implements Serializable {
	private int userId;
	private String userName;
	private String password;
	private int credits;
	private String lastIp;
	private Date lastVisit;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public Date getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}
	
}
