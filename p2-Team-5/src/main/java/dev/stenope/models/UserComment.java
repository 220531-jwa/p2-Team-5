package dev.stenope.models;

public class UserComment {

	private int id;
	private int wID; //writer user id
	private int hID; //host user id (that is, which profile it's posted to)
	private String body;
}
