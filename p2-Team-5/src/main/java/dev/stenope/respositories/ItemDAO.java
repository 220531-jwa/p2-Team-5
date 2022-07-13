package dev.stenope.respositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.stenope.models.Item;
import dev.stenope.models.ItemType;
import dev.stenope.utils.ConnectionUtil;

public class ItemDAO {
		
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public Item createItem(Item i) {
		String sql = "insert into p2t5.items "
				+ "(id, tid, uid, pid)"
				+ "(default, ?, ?, ?)" 
				+ "returning *";
		try(Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i.getType().getId());
			ps.setInt(2, i.getuID());
			ps.setInt(3, i.getpID());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				i.setId(rs.getInt("id"));
			} else {
				i = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			i = null;
		}
		return i;
	}
	
	public boolean modifyItem(Item i) {
		String sql = "update p2t5.items set "
				+ "(tid, uid, pid) "
				+ "= (?, ?, ?) "
				+ "where id = ?";
		try(Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i.getType().getId());
			ps.setInt(2, i.getuID());
			ps.setInt(3, i.getpID());
			ps.setInt(4, i.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				i = new Item(
					rs.getInt("id"),
					new ItemType(rs.getInt("itemtypes.id"), rs.getInt("leftovers"), rs.getString("tname"), rs.getString("tcat"), rs.getString("tsrc")),
					rs.getInt("uid"),
					rs.getInt("pid")
				);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Item getItemByID(int id) {
		Item i = null;
		String sql = "select items.*, itemtypes.* from p2t5.items items, p2t5.itemtypes itemtypes"
				+ "where items.tid = itemtypes.id"
				+ "and items.id = ?";
		try(Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				i = new Item(
					rs.getInt("id"),
					new ItemType(rs.getInt("itemtypes.id"), rs.getInt("leftovers"), rs.getString("tname"), rs.getString("tcat"), rs.getString("tsrc")),
					rs.getInt("uid"),
					rs.getInt("pid")
				);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public List<Item> getItemList(int id) {
		ArrayList<Item> list = new ArrayList<Item>();
		String sql = "select items.*, itemtypes.* from p2t5.items items, p2t5.itemtypes itemtypes"
				+ "where items.tid = itemtypes.id"
				+ "and items.uid = ?";
		try(Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				do {
					list.add( new Item(
						rs.getInt("id"),
						new ItemType(rs.getInt("itemtypes.id"), rs.getInt("leftovers"), rs.getString("tname"), rs.getString("tcat"), rs.getString("tsrc")),
						rs.getInt("uid"),
						rs.getInt("pid")
					));
				} while (rs.next());
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	
	public List<Item> getPetItemList(int id) {
		ArrayList<Item> list = new ArrayList<Item>();
		String sql = "select items.*, itemtypes.* from p2t5.items items, p2t5.itemtypes itemtypes"
				+ "where items.tid = itemtypes.id"
				+ "and items.pid = ?";
		try(Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			do {
				list.add( new Item(
					rs.getInt("id"),
					new ItemType(rs.getInt("itemtypes.id"), rs.getInt("leftovers"), rs.getString("tname"), rs.getString("tcat"), rs.getString("tsrc")),
					rs.getInt("uid"),
					rs.getInt("pid")
				));
			} while (rs.next()); 
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	
	public List<ItemType> getItemTypes () {
		ArrayList<ItemType> list = new ArrayList<ItemType>();
		String sql = "select * from p2t5.itemtypes";
		try(Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				do {
					list.add( 
						new ItemType(rs.getInt("itemtypes.id"), rs.getInt("leftovers"), rs.getString("tname"), rs.getString("tcat"), rs.getString("tsrc"))
					);
				} while (rs.next());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		}
		return list;
	}
}
