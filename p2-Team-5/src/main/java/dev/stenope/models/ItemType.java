package dev.stenope.models;

public class ItemType {
	public static String preloaded[][] = { //first batch is tName, then tCat, then tSRC
			{"bread", "cake", "pizza", "salad", "steak", 
			"ball", "book", "paint", "sled", "video game",
			"bed", "bike", "chair", "silverware", "toothbrush"},
			{"food", "food", "food", "food", "food",
			"toy", "toy", "toy", "toy", "toy", 
			"other", "other", "other", "other", "other"},
			{"🍞", "🍰", "🍕", "🥗", "🥩", 
			"⚽", "📖", "🎨", "🛷", "🎮",
			"🛏️", "🚲", "🪑", "🍽️", "🪥"}};
	
	private int id; 
	private int leftovers; 
	private String tName; //type name (ex: pizza)
	private String tCat; //type category (ex: food)
	private String tSRC; //type image source
	
	
}
