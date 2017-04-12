package com.strider.service;

import java.sql.SQLException;

import com.strider.dao.UserDao;
import com.strider.domain.User;
import com.strider.exception.UserException;
import com.strider.utils.SendJMail;

public class UserService {
	
	UserDao ud = new UserDao();
	
	public void regist(User user) throws UserException {
		try {
			ud.addUser(user);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			String emailMsg = "注册成功，请<a href='http://www.bookstore.com?activeCode="+user.getActiveCode()+"'>激活</a>";
			SendJMail.sendMail(user.getEmail(), emailMsg);
			throw new UserException("注册失败");
		}
		
	}

}
