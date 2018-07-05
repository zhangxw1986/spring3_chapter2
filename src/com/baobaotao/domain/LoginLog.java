/**
 * Domain Object也称为实体类，它代表了业务的状态，一般来说，领域对象属于业务层。但它贯穿
 * 于展现层、业务层和持久层，并最终被持久化到数据库中。一般领域对象对应于数据库表
 */
package com.baobaotao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class LoginLog implements Serializable{
	private int loginLogId;
	private int userId;
	private String Ip;
	private Date loginDate;
	public int getLoginLogId() {
		return loginLogId;
	}
	public void setLoginLogId(int loginLogId) {
		this.loginLogId = loginLogId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getIp() {
		return Ip;
	}
	public void setIp(String ip) {
		Ip = ip;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
}
