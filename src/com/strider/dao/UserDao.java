package com.strider.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.strider.domain.User;
import com.strider.utils.C3P0Util;

public class UserDao {

	public void addUser(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "INSERT INTO USER(username,PASSWORD,gender,email,telephone,activeCode,introduce,state,registTime) "
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		
		qr.update(sql, user.getUsername(), user.getPassword(), user.getGender(), user.getEmail(),
					user.getTelephone(), user.getActiveCode(), user.getIntroduce(), user.getState(),
					user.getRegistTime());
	}
}
