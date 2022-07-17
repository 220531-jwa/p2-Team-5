/**
 * This is the UserCommentReader class for the Stenope Pet Management System application
 * which includes getters, setters, and a toString() method
 * 
 * @author joshuacoombs
 * @author wlcross
 * @author TCPrater
 * 
 * @version 1.0
 */

package dev.stenope.models;

public class UserCommentReader {

	private int id;
	private String wID; //writer user id
	private int hID; //host user id (that is, which profile it's posted to)
	private String body;
	
	public UserCommentReader(int id, String wID, int hID, String body) {
		super();
		this.id = id;
		this.wID = wID;
		this.hID = hID;
		this.body = body;
	}
	
	public UserCommentReader() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getwID() {
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

	public void setwID(String wID) {
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
		return "UserCommentReader [id=" + id + ", wID=" + wID + ", hID=" + hID + ", body=" + body + "]";
	}
	
}
