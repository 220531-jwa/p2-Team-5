package dev.stenope.respositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.stenope.models.User;
import dev.stenope.models.UserComment;
import dev.stenope.utils.ConnectionUtil;

public class UserDAO {

	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	public User createUser(User u) {
		//User sender = UserDAO.getUserByID(wId);
		//User recipient = UserDAO.getUserByID(hId);
		String sql = "insert into p2t5.users(uname, pkey, dname, pset, dblurb) values (?, ?, ?, ?)"
				+ "where not exists (select * from p2t5.users);";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getuName());
			ps.setString(2, u.getpKey());
			ps.setString(3, u.getdName());
			ps.setInt(4, u.getpSet());
			ps.setString(5, u.getdBlurb());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new User(
						rs.getInt("id"),
						rs.getString("uname"),
						rs.getString("pkey"),
						rs.getString("dname"),
						rs.getString("dblurb"),
						rs.getInt("pset")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUserByUserName(String uName) {
		String sql = "select * from p2t5.users where uname = ?";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new User(
						rs.getInt("id"),
						rs.getString("uname"),
						rs.getString("pkey"),
						rs.getString("dname"),
						rs.getString("dblurb"),
						rs.getInt("pset")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static User getUserByID(int id) {
		String sql = "select * from p2t5.users where id = ?";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new User(
						rs.getInt("id"),
						rs.getString("uname"),
						rs.getString("pkey"),
						rs.getString("dname"),
						rs.getString("dblurb"),
						rs.getInt("pset")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User editUser(User u) {
		String sql = "update p2t5.users set (uname = ?, dname = ?, dblurb = ?, pset = ?) where id = ? returning *";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getuName());
			ps.setString(2, u.getdName());
			ps.setString(3, u.getdBlurb());
			ps.setInt(4, u.getpSet());
			ps.setInt(5, u.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new User(
						rs.getInt("id"),
						rs.getString("uname"),
						rs.getString("pkey"),
						rs.getString("dname"),
						rs.getString("dblurb"),
						rs.getInt("pset")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public UserComment addComment(int wId, int hId, String commentText) {
		//User sender = UserDAO.getUserByID(wId);
		//User recipient = UserDAO.getUserByID(hId);
		String sql = "insert into p2t5.comments(wid, hid, body) values (?, ?, ?);";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, wId);
			ps.setInt(2, hId);
			ps.setString(3, commentText);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new UserComment(
						rs.getInt("id"),
						rs.getInt("wid"),
						rs.getInt("hid"),
						rs.getString("body")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<UserComment> getComments(int hId) {
		ArrayList<UserComment> commentList = new ArrayList<UserComment>();
		String sql = "select * from p2t5.comments where hid = ?";
		
		try(Connection conn = cu.getConnection()) {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, hId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				do {
					commentList.add(
						new UserComment(rs.getInt("id"), rs.getInt("wid"), rs.getInt("hid"), rs.getString("body"))
							);
					
				} while (rs.next());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			commentList = null;
		}
		
		return commentList;
	}
}
