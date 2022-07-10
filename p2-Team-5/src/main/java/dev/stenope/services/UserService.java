package dev.stenope.services;

import dev.stenope.models.User;
import dev.stenope.respositories.UserDAO;

public class UserService {

	private static UserDAO userDao;
	
	public UserService(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	public User getUserByUserName(String uName) {
		
		return null;
	}
	
	public User login(User goshdangUser) {
		
		return null;
	}
	
	public User getUserByID(int id) {
		return null;
	}

	public boolean editUser(User u) {
		
		return false;
	}
}
