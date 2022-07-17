/**
 * This is the ItemService class for the Stenope Pet Management System application
 * 
 * @author joshuacoombs
 * @author wlcross
 * @author TCPrater
 * 
 * @version 1.0
 */

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
	
	public ItemService() {
		super();
	}
	
	public ItemService(ItemDAO i, PetService p) {
		super();
		itemDao = i;
		petService = p;
	}
	
	/**
	 * This method creates an Item
	 * @param type
	 * @param owner
	 * @return
	 */
	public Item createItem(int type, int owner) {
		ItemType newType = itemDao.getSpecificItemType(type);
		
		Item newItem = new Item(1, newType, owner, 0);
		newItem = itemDao.createItem(newItem);
		return newItem;
	}
	
	/**
	 * This method modifies and Item
	 * @param i
	 * @return
	 */
	public boolean modifyItem(Item i) {
		return itemDao.modifyItem(i);
	}
	
	/**
	 * This method changes the owner of a specific Item
	 * @param itemId
	 * @param user
	 * @param newOwner
	 * @return
	 */
	public boolean changeItemOwner(int itemId, int user, int newOwner) {
		//Checks if user owns the pet they are transferring to
		Item item = itemDao.getItemByID(itemId);
		if (newOwner == 0) {
			if (item == null) {
				return false;
			}
			return itemDao.returnToOwner(item);
		} else {
			Pet reciever = petService.getPetByID(newOwner);
			if (item == null || reciever == null || reciever.getuID() != user) {
				return false;
			}
			item.setpID(newOwner);
			return itemDao.modifyItem(item);
		}
		
		
	}
	
	/**
	 * This method retrieves an Item by its id
	 * @param id
	 * @return
	 */
	public Item getItemByID(int id) {
		return itemDao.getItemByID(id);
	}
	
	/**
	 * This method retrieves an Item list for a User
	 * @param id
	 * @return
	 */
	public List<Item> getItemList(int id) {
		return itemDao.getItemList(id);
	}
	
	/**
	 * This method retrieves an Item list for a Pet
	 * @param id
	 * @return
	 */
	public List<Item> getPetItemList(int id) {
		return itemDao.getPetItemList(id);
	}
	
	/**
	 * This method retrieves a list of ItemTypes
	 * @return
	 */
	public List<ItemType> getItemTypes() {
		return itemDao.getItemTypes();
	}
	
	/**
	 * This method deletes an Item
	 * @param own
	 * @param item
	 * @return
	 */
	public boolean deleteItem(int own, int item) {
		Item deadItemWalking = itemDao.getItemByID(item);
		if (deadItemWalking == null || deadItemWalking.getuID() != own) {
			return false;
		}
		
		return itemDao.deleteItem(item);
	}
	
}
