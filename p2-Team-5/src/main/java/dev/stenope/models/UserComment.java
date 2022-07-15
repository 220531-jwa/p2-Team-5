package dev.stenope.models;

public class UserComment {

	private int id;
	private int wID; //writer user id
	private int hID; //host user id (that is, which profile it's posted to)
	private String body;
	
	public UserComment(int id, int wID, int hID, String body) {
		super();
		this.id = id;
		this.wID = wID;
		this.hID = hID;
		this.body = body;
	}
	
	public UserComment() {
		super();
	}

	public int getId() {
		return id;
	}

	public int getwID() {
		return wID;
	}

	public int gethID() {
		return hID;
	}

	public String getBody() {
		return body;
	}
	
	

	public void setId(int id) {
		this.id = id;
	}

	public void setwID(int wID) {
		this.wID = wID;
	}

	public void sethID(int hID) {
		this.hID = hID;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "UserComment [id=" + id + ", wID=" + wID + ", hID=" + hID + ", body=" + body + "]";
	}
}
