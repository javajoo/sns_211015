package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.dao.UserDAO;
import com.sns.user.model.User;

@Service
public class UserBO {

	@Autowired
	private UserDAO userDAO;
	
	public boolean existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}
	
	public User getUserById(int id) {
		return userDAO.selectUserById(id);
	}
	public int addUser(String loginId, String password, String name,String email) {
		return userDAO.insertUser(loginId, password, name, email);
	}
	
	public User getUserByLoginIdPassword (String loginId,String password) {
		return userDAO.selectUserByLoginIdPassword(loginId, password);
	}
	
	public User getUserByuserId(int userId) {
		return userDAO.getUserByuserId(userId);
	}
	
}
