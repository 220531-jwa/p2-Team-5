package dev.stenope.models;

public class Pet {

	private int id;
	private int uID; //owner ID
	private int sID; //Src Image ID
	private String pName; //Pet name
	private String pSet; //Pronouns
	private int fun;
	private int food; 
	private int level;
	private PetType type;
	public Pet(int id, int uID, int sID, String pName, String pSet, int fun, int food, int level, PetType type) {
		super();
		this.id = id;
		this.uID = uID;
		this.sID = sID;
		this.pName = pName;
		this.pSet = pSet;
		this.fun = fun;
		this.food = food;
		this.level = level;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getuID() {
		return uID;
	}
	public void setuID(int uID) {
		this.uID = uID;
	}
	public int getsID() {
		return sID;
	}
	public void setsID(int sID) {
		this.sID = sID;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpSet() {
		return pSet;
	}
	public void setpSet(String pSet) {
		this.pSet = pSet;
	}
	public int getFun() {
		return fun;
	}
	public void setFun(int fun) {
		this.fun = fun;
	}
	public int getFood() {
		return food;
	}
	public void setFood(int food) {
		this.food = food;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public PetType getType() {
		return type;
	}
	public void setType(PetType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Pet [id=" + id + ", uID=" + uID + ", sID=" + sID + ", pName=" + pName + ", pSet=" + pSet + ", fun="
				+ fun + ", food=" + food + ", level=" + level + ", type=" + type + "]";
	}
}
