package dev.stenope.models;

public class PetType {
	private int id;
	private String sName;
	private String sSRC;
	
	public int getId() {return id;}
	public String getsName() {return sName;}
	public String getsSRC() {return sSRC;}
	
	public void setId(int id) {this.id = id;}
	public void setsName(String sName) {this.sName = sName;}
	public void setsSRC(String sSRC) {this.sSRC = sSRC;}
	
	public PetType() {;}
	public PetType(int i, String n, String r) {id=i; sName=n; sSRC=r;}
}
