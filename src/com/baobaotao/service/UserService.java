package com.baobaotao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baobaotao.dao.LoginLogDao;
import com.baobaotao.dao.UserDao;
import com.baobaotao.domain.LoginLog;
import com.baobaotao.domain.User;

/**
 * 该web工程中，业务层仅有一个业务类，即UserService
 * UserService负责将持久层的UserDao和LoginLogDao组织起来完成用户/密码认证，登陆日志记录等操作。
 */
@Service //将UserService标注为一个服务层的Bean
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private LoginLogDao loginLogDao;
/**
 * 功能：用于检查用户名/密码的正确性
 * @param userName
 * @param password
 * @return 返回验证结果true为验证正确，false为验证失败
 */
	public boolean hasMatchUser(String userName,String password){
		int matchCount = userDao.getMatchCount(userName, password);
		return matchCount > 0 ;
	}

/**
 * 以用户名条件加载User对象
 * @param userName
 * @return
 */
	public User findUserByName(String userName){
		return userDao.findUserByUserName(userName);
	}
	
/**
 * 功能：在用户登陆成功后调用，更新用户最后登陆时间和IP信息同时记录用户登陆日志
 * @param user
 */
	public void loginSuccess(User user){
		user.setCredits(5+user.getCredits());
		LoginLog loginLog =new LoginLog();
		loginLog.setUserId(user.getUserId());
		loginLog.setIp(user.getLastIp());
		loginLog.setLoginDate(user.getLastVisit());
		userDao.updateLoginInfo(user);
		loginLogDao.insertLoginLog(loginLog);
	}
}
