package dev.stenope.services;

import java.util.List;

import dev.stenope.models.Item;
import dev.stenope.respositories.ItemDAO;
import dev.stenope.respositories.PetDAO;

public class ItemService {

	static ItemDAO itemDao = new ItemDAO();
	static PetService petService = new PetService(new PetDAO());
	
	public ItemService(ItemDAO i, PetService p) {
		itemDao = i;
		petService = p;
	}
	
	public Item createItem(Item i) {
		
		return null;
	}
	
	public boolean modifyItem(Item i) {
		
		return false;
	}
	
	public boolean changeItemOwner(Item i, int newOwner) {
		
		return false;
	}
	
	public Item getItemByID(int id) {
		
		return null;
	}
	
	public List<Item> getItemList(int id) {
		
		return null;
	}
	
	public List<Item> getPetItemList(int id) {
		
		return null;
	}
	
}
