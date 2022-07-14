package dev.stenope.services;

import java.util.List;

import dev.stenope.models.Item;
import dev.stenope.models.ItemType;
import dev.stenope.models.Pet;
import dev.stenope.respositories.ItemDAO;
import dev.stenope.respositories.PetDAO;

public class ItemService {

	static ItemDAO itemDao = new ItemDAO();
	static PetService petService = new PetService(new PetDAO());
	
	public ItemService(ItemDAO i, PetService p) {
		itemDao = i;
		petService = p;
	}
	
	public ItemService() { }
	
	public Item createItem(int type, int owner) {
		ItemType newType = itemDao.getSpecificItemType(type);
		
		Item newItem = new Item(1, newType, owner, 0);
		newItem = itemDao.createItem(newItem);
		return newItem;
	}
	
	public boolean modifyItem(Item i) {
		return itemDao.modifyItem(i);
	}
	
	public boolean changeItemOwner(int itemId, int user, int newOwner) {
		//Checks if user owns the pet they are transferring to
		Item item = itemDao.getItemByID(itemId);
		Pet reciever = petService.getPetByID(newOwner);
		if (item == null || reciever == null || reciever.getuID() != user) {
			return false;
		}
		item.setpID(newOwner);
		return itemDao.modifyItem(item);
	}
	
	
	public Item getItemByID(int id) {
		return itemDao.getItemByID(id);
	}
	
	public List<Item> getItemList(int id) {
		return itemDao.getItemList(id);
	}
	
	public List<Item> getPetItemList(int id) {
		return itemDao.getPetItemList(id);
	}
	
	public List<ItemType> getItemTypes() {
		return itemDao.getItemTypes();
	}
	
}
