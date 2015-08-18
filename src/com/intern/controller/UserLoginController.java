package com.intern.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intern.model.UserBean;
import com.intern.model.UserBeanProcess;


@Controller
public class UserLoginController {
 
    @RequestMapping("/user")
    public String userLogin(final HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		UserBeanProcess ubp = new UserBeanProcess();
		request.getSession().setAttribute("userName", userName);
    	
		if(userName.equals("admin") && password.equals("admin")){
			request.getSession().setAttribute("userRole", "admin");
			List<UserBean> al=ubp.getUsersByPage(1);
			int pageCount = ubp.getPageCount();
			
			// put al, pageCount, pageNow into request
			request.setAttribute("result", al);
			request.setAttribute("pageCount", pageCount+"");
			request.setAttribute("pageNow", "1");
				
			return "main";	
		}else if(ubp.checkUser(userName,password)){
			request.getSession().setAttribute("userRole", "user");
			UserBean ub = ubp.getUserByUserName(userName);
			request.setAttribute("result", ub);
	    	return "user";
		}else{
			request.setAttribute("err","2");
			return "login";
		}
    }
    
    @RequestMapping("/home")
    public String userHome(final HttpServletRequest request) {
		String userName = (String)request.getSession().getAttribute("userName");
		UserBeanProcess ubp = new UserBeanProcess();
		UserBean ub = ubp.getUserByUserName(userName);
		String role = (String) request.getSession().getAttribute("userRole");
		request.setAttribute("result", ub);
		if("user".equals(role))
			return "user";
		else{
			return "main";
		}
    }
 
    @RequestMapping("/logoff")
    public String userLogoff(final HttpServletRequest request) {
		request.getSession().invalidate();
		return "login";		
    }
 }
