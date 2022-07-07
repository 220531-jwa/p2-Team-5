package dev.stenope.models;

public class PetType {
	public static String preloaded[][] = { //row 1 is sName, row 2 is sSRC
			{"cockroach", "spider", "butterfly", "ladybug",
			"earthworm", "octopus", "snail", "velvet worm", 
			"dog", "cat", "rat", "horse", 
			"owl", "t-rex", "frog", "fish"}, 
			{"🪳", "🕷️", "🦋", "🐞",
			"🪱", "🐙", "🐌", "🐛", 
			"🐕", "🐈", "🐀", "🐎",
			"🦉", "🦖", "🐸", "🐟"}
			};
	
	private int id;
	private String sName;
	private String sSRC;
}
