package dev.stenope.services;

import dev.stenope.models.User;
import dev.stenope.respositories.UserDAO;

public class UserService {

	private static UserDAO userDao;
	
	public UserService(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	UserService(UserDAO u) {
		userDao = u;
	}
	
	public User login(User u) {
		
		return null;
	}
	
	public User getUserByID(int id) {
		return null;
	}

	public boolean editUser(User u) {
		
		return false;
	}
}
