package dev.stenope.services;

import dev.stenope.models.User;
import dev.stenope.respositories.UserDAO;

public class UserService {

	static UserDAO userDao = new UserDAO();
	
	public User getUserByUserName(String uName) {
		
		return null;
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
