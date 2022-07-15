package dev.stenope.respositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.stenope.models.Pet;
import dev.stenope.models.PetType;
import dev.stenope.utils.ConnectionUtil;

public class PetDAO {
private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	//Create
	public Pet createPet(Pet p) {
		String sql = "insert into pets values (default,?,?,?,?,?,?,?) returning *;";
		try (Connection conn = cu.getConnection();)
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, p.getuID());
			ps.setInt(2, p.getType().getID());
			ps.setInt(3, p.getpSet());
			ps.setString(4, p.getpName());
			ps.setInt(5, p.getFun());
			ps.setInt(6, p.getFood());
			ps.setInt(7, p.getLevel());
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				return new Pet(rs.getInt("id"), rs.getInt("uid"), rs.getString("pName"), rs.getInt("pset"), rs.getInt("fun"), 
							rs.getInt("food"), rs.getInt("plevel"), getPTypeByID(rs.getInt("sid")));
			}
		}
		catch (SQLException e){e.printStackTrace();}
		return null;
	}
	
	//Read	
	public PetType getPTypeByID(int sid) 
	{
		String sql = "select * from pettypes where id = ?;";
		
		try (Connection conn = cu.getConnection();)
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sid);			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				return new PetType(rs.getInt("id"),rs.getString("sname"),rs.getString("ssrc"));
			}
		}
		catch (SQLException e){e.printStackTrace();}
		return null;
	}
	
	public Pet getPetByID(int id) {
		String sql = "select * from pets where id = ?;";
		try (Connection conn = cu.getConnection();)
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				return new Pet(rs.getInt("id"), rs.getInt("uid"), rs.getString("pName"), rs.getInt("pset"), rs.getInt("fun"), 
							rs.getInt("food"), rs.getInt("plevel"), getPTypeByID(rs.getInt("sid")));
			}
		}
		catch (SQLException e){e.printStackTrace();}
		return null;
	}
	
	public List<Pet> getPetListByUserID(int uid) {
		List<Pet> pList = new ArrayList<>();
		String sql = (uid == 0) ? "select * from pets where uid is null;":  "select * from pets where uid = ?;";
		try (Connection conn = cu.getConnection();)
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			if (uid>0) {ps.setInt(1, uid);}
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				pList.add(new Pet(rs.getInt("id"), rs.getInt("uid"), rs.getString("pName"), rs.getInt("pset"), rs.getInt("fun"), 
							rs.getInt("food"), rs.getInt("plevel"), getPTypeByID(rs.getInt("sid"))));
			}
			return pList;
		}
		catch (SQLException e){e.printStackTrace();}
		return null;
	}
	
	public List<Pet> getPetListByPName(String pname)
	{
		List<Pet> pList = new ArrayList<>();
		String sql = "select * from pets where pname = ?;";
		try(Connection conn = cu.getConnection();)
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pname);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				pList.add(new Pet(rs.getInt("id"), rs.getInt("uid"), rs.getString("pname"), rs.getInt("pset"), rs.getInt("fun"), 
						rs.getInt("food"), rs.getInt("plevel"), getPTypeByID(rs.getInt("sid"))));
			}
			return pList;
		}
		catch(SQLException e) {e.printStackTrace();}
		return null;
	}
	
	public List<Pet> getAllPets() 
	{
		List<Pet> pList = new ArrayList<>();
		String sql = "select * from pets;";
		try(Connection conn = cu.getConnection();)
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				pList.add(new Pet(rs.getInt("id"), rs.getInt("uid"), rs.getString("pname"), rs.getInt("pset"), rs.getInt("fun"), 
						rs.getInt("food"), rs.getInt("plevel"), getPTypeByID(rs.getInt("sid"))));
			}
			return pList;
		}
		catch(SQLException e) {e.printStackTrace();}
		return null;
	}
	
	public List<PetType> getPetTypes() {
		List<PetType> ptList = new ArrayList<>();
		String sql = "select * from pettypes;";
		
		try (Connection conn = cu.getConnection();)
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				ptList.add(new PetType(rs.getInt("id"), rs.getString("sname"),rs.getString("ssrc")));
			}
			return ptList;
		}
		catch (SQLException e){e.printStackTrace();}
		return null;
	}
	
	//Update
	public Pet modifyPet(Pet p) {
		Pet output = null;
		String sql = "update pets set uid = ?, sid = ?, pset = ?, pname = ?, fun = ?, food = ?, plevel = ? where id = ? returning *;";
		if (p.getuID()==0) {sql = "update pets set uid = null, sid = ?, pset = ?, pname = ?, fun = ?, food = ?, plevel = ? where id = ? returning *;";}
		try (Connection conn = cu.getConnection();)
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			if (p.getuID()!=0)
			{
				
				ps.setInt(1, p.getuID());
				ps.setInt(2, p.getType().getID());
				ps.setInt(3, p.getpSet());
				ps.setString(4, p.getpName());
				ps.setInt(5, p.getFun());
				ps.setInt(6, p.getFood());
				ps.setInt(7, p.getLevel());
				ps.setInt(8, p.getId());				
			}
			else 
			{
				ps.setInt(1, p.getType().getID());
				ps.setInt(2, p.getpSet());
				ps.setString(3, p.getpName());
				ps.setInt(4, p.getFun());
				ps.setInt(5, p.getFood());
				ps.setInt(6, p.getLevel());
				ps.setInt(7, p.getId());	
			}
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				return new Pet(rs.getInt("id"), rs.getInt("uid"), rs.getString("pName"), rs.getInt("pset"), rs.getInt("fun"), 
						rs.getInt("food"), rs.getInt("plevel"), getPTypeByID(rs.getInt("sid")));
			}
		}
		catch(Exception e) {return null;}
		
		return output;
	}
	
	//Destroy
}
