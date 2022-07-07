package dev.stenope.respositories;

import dev.stenope.models.User;
import dev.stenope.utils.ConnectionUtil;

public class UserDAO {

	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	public User getUserByUserName(String uName) {
		
		return null;
	}
	
	public User getUserByID(int id) {
		return null;
	}

	public boolean editUser(User u) {
		
		return false;
	}
	
}
