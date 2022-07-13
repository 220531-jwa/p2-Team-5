package dev.stenope.models;

public class User {
	
	private int id;
	private String uName; //username
	private String pKey; //passkey
	private String dName; //display name
	private String dBlurb; //description blurb
	private int pSet; //pronouns
	
	public User() {
		super();
	}
	public User(int id, String uName, String pKey, String dName, String dBlurb, int pSet) {
		super();
		this.id = id;
		this.uName = uName;
		this.pKey = pKey;
		this.dName = dName;
		this.dBlurb = dBlurb;
		this.pSet = pSet;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getpKey() {
		return pKey;
	}
	public void setpKey(String pKey) {
		this.pKey = pKey;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getdBlurb() {
		return dBlurb;
	}
	public void setdBlurb(String dBlurb) {
		this.dBlurb = dBlurb;
	}
	public int getpSet() {
		return pSet;
	}
	public void setpSet(int pSet) {
		this.pSet = pSet;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", uName=" + uName + ", pKey=" + pKey + ", dName=" + dName + ", dBlurb=" + dBlurb
				+ ", pSet=" + pSet + "]";
	}
}
