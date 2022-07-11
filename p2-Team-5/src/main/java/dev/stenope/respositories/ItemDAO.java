package dev.stenope.respositories;

import java.util.List;

import dev.stenope.models.Item;
import dev.stenope.utils.ConnectionUtil;

public class ItemDAO {
		
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public Item createItem(Item i) {
		
		return null;
	}
	
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
