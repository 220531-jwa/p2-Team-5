package dev.stenope.services;

import java.util.List;

import dev.stenope.models.Item;
import dev.stenope.respositories.ItemDAO;

public class ItemService {

	static ItemDAO itemDao = new ItemDAO();
	
	
	public boolean modifyItem(Item i) {
		
		return false;
	}
	
	public Item getItemByID(int id) {
		
		return null;
	}
	
	public List<Item> getItemList(int id) {
		
		return null;
	}
	
}
