/**
 * This is the Item class for the Stenope Pet Management System application
 * which includes getters, setters, and a toString() method
 * 
 * @author joshuacoombs
 * @author wlcross
 * @author TCPrater
 * 
 * @version 1.0
 */

package dev.stenope.models;

public class Item {

	private int id;
	private ItemType type; //Item Type
	private int uID; //User Owner
	private int pID; //Pet Owner
	
	
	public Item(int id, ItemType type, int uID, int pID) {
		super();
		this.id = id;
		this.type = type;
		this.uID = uID;
		this.pID = pID;
	}
	
	public Item() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public ItemType getType() {
		return type;
	}


	public void setType(ItemType type) {
		this.type = type;
	}


	public int getuID() {
		return uID;
	}


	public void setuID(int uID) {
		this.uID = uID;
	}


	public int getpID() {
		return pID;
	}


	public void setpID(int pID) {
		this.pID = pID;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", type=" + type + ", uID=" + uID + ", pID=" + pID + "]";
	}
	
	
	
}
