package dev.stenope.services;

import dev.stenope.models.Item;
import dev.stenope.respositories.ItemDAO;

public class ItemService {

	static ItemDAO itemDao = new ItemDAO();
	
	
	public boolean modifyItem(Item i) {
		
		return false;
	}
	
}
