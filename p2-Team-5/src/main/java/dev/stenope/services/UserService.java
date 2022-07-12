package dev.stenope.services;

import dev.stenope.models.User;
import dev.stenope.respositories.UserDAO;

public class UserService {

	private static UserDAO userDao;
	
	public UserService(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	public User login(User u) {
		//after lunch
		return null;
	}
	
	public User getUserByID(int id) {
		User u = userDao.getUserByID(id);
		return u;
	}

	public User editUser(User u) {
		User uNew = userDao.editUser(u);
		return uNew;
	}
}
