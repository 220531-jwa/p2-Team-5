package dev.stenope.models;

public class PetType {
	private int id;
	private String sName;
	private String sSRC;
	
	public int getID() {return id;}
	public String getSName() {return sName;}
	public String getSSRC() {return sSRC;}
	
	public void setID(int id) {this.id = id;}
	public void setSName(String sName) {this.sName = sName;}
	public void setSSRC(String sSRC) {this.sSRC = sSRC;}
	
	public PetType() {;}
	public PetType(int i, String n, String r) {id=i; sName=n; sSRC=r;}
}
