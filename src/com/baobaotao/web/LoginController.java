package com.baobaotao.web;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.context.ContextLoaderListener;
import com.baobaotao.domain.User;
import com.baobaotao.service.UserService;

/**
 * @Controller将类标注成为一个spring mvc的controller
 * @author Administrator
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	//负责处理/index.html的请求
	@RequestMapping(value="/index.html")
	public String loginPage(){
		return "login";
	}
	
	//负责处理/loginCheck.html的请求
	//ModelAndView方法第一个参数代表视图的逻辑名，第二个、第三个参数分别为数据模型名称和数据模型对象，数据模型对象将以数据模型名称为参数名放置到request的属性中
	@RequestMapping(value="/loginCheck.html")
	public ModelAndView loginCheck(HttpServletRequest request,LoginCommand loginCommand){
		boolean isValidUser=userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
		if(!isValidUser){
			return new ModelAndView("login","error","用户名或密码错误");
		}else {
			User user= userService.findUserByName(loginCommand.getUserName());
			user.setLastIp(request.getRemoteAddr());
			user.setLastVisit(new Date());
			userService.loginSuccess(user);
			request.getSession().setAttribute("user", user);
			return new ModelAndView("main");
		}
		
	}
}
