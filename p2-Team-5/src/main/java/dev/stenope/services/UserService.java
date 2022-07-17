/**
 * This is the UserService class for the Stenope Pet Management System application
 * 
 * @author joshuacoombs
 * @author wlcross
 * @author TCPrater
 * 
 * @version 1.0
 */

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
	
	/**
	 * This method creates a User
	 * @param u
	 * @return
	 */
	public User createUser(User u) {
		User uCreated = userDao.createUser(u);
		return uCreated;
	}
	
	/**
	 * This method allows the User to login to the application
	 * @param uName
	 * @param pKey
	 * @return
	 */
	public User login(String uName, String pKey) {
		User u = userDao.getUserByUserName(uName);
		if (u != null) {
			if (u.getpKey().equals(pKey)) {
				return u;
			}
		}
		return null;
	}
	
	/**
	 * This method retrieves a User by their id
	 * @param id
	 * @return
	 */
	public User getUserByID(int id) {
		User u = userDao.getUserByID(id);
		return u;
	}

	/**
	 * This method modifies a User
	 * @param uId
	 * @return
	 */
	public User editUser(int uId) {
		User uNew = userDao.editUser(uId);
		return uNew;
	}
	
	/**
	 * This method allows a User to make a UserComment
	 * @param wId
	 * @param hId
	 * @param body
	 * @return
	 */
	public UserComment addComment(int wId, int hId, String body) {
		UserComment comment = userDao.addComment(wId, hId, body);
		return comment;
	}
	
	/**
	 * This method allows a User to view another User's Page
	 * @param uName
	 * @return
	 */
	public User viewOtherUsersPage(String uName) {
		User otherUser = userDao.getUserByUserName(uName);
		return otherUser;
	}
	
	/**
	 * This method retrieves the comments on a User's Page
	 * @param hId
	 * @return
	 */
	public List<UserCommentReader> getComments(int hId) {
		return userDao.getComments(hId);
	}
}
