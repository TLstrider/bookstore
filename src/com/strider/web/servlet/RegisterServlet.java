package com.strider.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.strider.domain.User;
import com.strider.exception.UserException;
import com.strider.service.UserService;

public class RegisterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理验证码
		String ckcode = request.getParameter("ckcode");
		String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");
		if(!checkcode_session.equals(ckcode)){//如果验证码不一致跳回注册页面
			request.setAttribute("ckcode_msg", "验证码有误");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		//获取表单数据
		User user = new User();
		try {
			
			BeanUtils.populate(user, request.getParameterMap());
			user.setActiveCode(UUID.randomUUID().toString());//手动设置激活码
			//调用业务逻辑
			UserService us = new UserService();
			us.regist(user);
			//分发转向
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/registersuccess.jsp").forward(request, response);
		} catch (UserException e) {
			request.setAttribute("user_msg", e.getMessage());
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}catch(Exception e){
			
			e.printStackTrace();;
		}
		
		//调用业务逻辑
		
		//分发转向
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
