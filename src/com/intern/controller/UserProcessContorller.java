package com.intern.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intern.model.TranData;
import com.intern.model.UserBean;
import com.intern.model.UserBeanProcess;

@Controller
public class UserProcessContorller {

	@RequestMapping("/userprocess")
	public String userProcess(final HttpServletRequest request, String flag) {
		String userName = (String) request.getSession()
				.getAttribute("userName");

		if ("paging".equals(flag)) {

			String s_pageNow = request.getParameter("pageNow");

			try {
				int pageNow = Integer.parseInt(s_pageNow);
				UserBeanProcess ubp = new UserBeanProcess();

				List<UserBean> al = ubp.getUsersByPage(pageNow);
				int pageCount = ubp.getPageCount();

				// put al, pageCount, pageNow into request
				request.setAttribute("result", al);
				request.setAttribute("pageCount", pageCount + "");
				request.setAttribute("pageNow", pageNow + "");

				return "userList";

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if ("delUser".equals(flag)) {

			String userId = request.getParameter("userId");

			UserBeanProcess ubp = new UserBeanProcess();

			if (ubp.delUserById(userId)) {
				return "success";
			} else {
				return "error";
			}

		} else if ("addForm".equals(flag)) {
			return "addUser";
		}

		else if ("addUser".equals(flag)) {

			String passwd = request.getParameter("passwd");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			String address = request.getParameter("address");
			String name = request.getParameter("userName");
			UserBeanProcess ubp = new UserBeanProcess();

			if (ubp.addUser(name, passwd, email, phoneNumber, address)) {
				return "success";
			} else {
				return "error";
			}
			
		} else if ("updateForm".equals(flag)) {
              return "updateUser";
		}

		else if ("updateUser".equals(flag)) {

			String userId = request.getParameter("userId");
			String passwd = request.getParameter("passwd");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			String address = request.getParameter("address");
			String name = request.getParameter("userName");

			UserBeanProcess ubp = new UserBeanProcess();

			if (ubp.updateUser(userId, name, passwd, email, phoneNumber,
					address)) {
				// Add successcess
				return "success";
			} else {
				return "error";
			}

		} else if ("showIndividual".equals(flag)) {

			try {

				UserBeanProcess ubp = new UserBeanProcess();

				UserBean ub = ubp.getUserByUserName(userName);

				request.setAttribute("result", ub);

				return "user";

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "success";

	}

	/*
	@RequestMapping("/update")
	public String userTransaction(final HttpServletRequest request) {
		String userName = (String) request.getSession()
				.getAttribute("userName");

		return "updateUser";
	}*/

}
