package dev.stenope.respositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dev.stenope.models.User;
import dev.stenope.models.UserComment;
import dev.stenope.utils.ConnectionUtil;

public class UserDAO {

	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	public User getUserByUserName(String uName) {
		String sql = "select * from users where uname = ?";
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
	
	public User getUserByID(int id) {
		String sql = "select * from users where id = ?";
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
		String sql = "update users set (uname = ?, dname = ?, dblurb = ?, pset = ?) where id = ? returning *";
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
	
}
