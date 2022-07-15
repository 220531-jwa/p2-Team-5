package dev.stenope.services;

import java.util.List;

import dev.stenope.models.User;
import dev.stenope.models.UserComment;
import dev.stenope.models.UserCommentReader;
import dev.stenope.respositories.UserDAO;

public class UserService {
	private static UserDAO userDao;
	
	public UserService(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	public User createUser(User u) {
		User uCreated = userDao.createUser(u);
		return uCreated;
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
	
	public User viewOtherUsersPage(String uName) {
		User otherUser = userDao.getUserByUserName(uName);
		return otherUser;
	}
	
	public List<UserCommentReader> getComments(int hId) {
		return userDao.getComments(hId);
	}
}
