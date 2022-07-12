package dev.stenope.models;

public class ItemType {
	private int id; 
	private int leftovers; 
	private String tName; //type name (ex: pizza)
	private String tCat; //type category (ex: food)
	private String tSRC; //type image source
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLeftovers() {
		return leftovers;
	}
	public void setLeftovers(int leftovers) {
		this.leftovers = leftovers;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public String gettCat() {
		return tCat;
	}
	public void settCat(String tCat) {
		this.tCat = tCat;
	}
	public String gettSRC() {
		return tSRC;
	}
	public void settSRC(String tSRC) {
		this.tSRC = tSRC;
	}
	
	
}
