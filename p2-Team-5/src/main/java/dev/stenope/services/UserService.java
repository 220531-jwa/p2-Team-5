package dev.stenope.services;

import dev.stenope.models.User;
import dev.stenope.models.UserComment;
import dev.stenope.respositories.UserDAO;

public class UserService {
	private static UserDAO userDao;
	
	public UserService(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	public User login(String uName, String pKey) {
		User u = userDao.getUserByUserName(uName);
		if (u != null) {
			if (u.getpKey().equals(pKey)) {
				return u;
			}
		}
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
	
	public UserComment addComment(int wId, int hId, String body) {
		UserComment comment = userDao.addComment(wId, hId, body);
		return comment;
	}
}
