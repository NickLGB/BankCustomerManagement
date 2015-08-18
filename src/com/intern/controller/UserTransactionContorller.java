package com.intern.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intern.model.TranData;
import com.intern.model.UserBeanProcess;

@Controller
public class UserTransactionContorller {
	@RequestMapping("/transaction")
  	public String userTransaction(final HttpServletRequest request, String userId) {
		String userName = (String) request.getSession().getAttribute("userName");
		String role = (String) request.getSession().getAttribute("userRole");
		UserBeanProcess process = new UserBeanProcess();
		List<TranData> list = process.getUserTransactions(userId);
		
		request.setAttribute("result", list);
/*
		if("admin".equals(role))
			return "showTransactions";
		else{
			return "showTranForIndividual";
		}*/
		
		return "showTransactions";
	}
}
