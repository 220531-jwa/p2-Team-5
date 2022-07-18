/**
 * This is the ItemServiceTest class for the Stenope Pet Management System application.
 * This class is used in the JUnit/Mockito unit tests which cover the Service classes.
 * 
 * @author joshuacoombs
 * @author wlcross
 * @author TCPrater
 * 
 * @version 1.0
 */

package dev.stenope.services;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.stenope.respositories.ItemDAO;
import dev.stenope.models.Item;
import dev.stenope.models.ItemType;
import dev.stenope.models.Pet;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

	private static ItemService itemService;
	
	
	private static ItemDAO mockItemDao;
	private static PetService mockPetService;
	
	@BeforeAll
	public static void setUp() {
		mockItemDao = mock(ItemDAO.class);
		mockPetService = mock(PetService.class);
		itemService = new ItemService(mockItemDao, mockPetService);
	}
	
	@Test
	public void createItemTest() {
		ItemType testType = new ItemType(3, 0, "pizza", "food", "🍕");
		Item expectedItem = new Item(1, testType, 1, 0);
		
		when(mockItemDao.getSpecificItemType(3)).thenReturn(testType);
		when(mockItemDao.createItem(any(Item.class))).then(AdditionalAnswers.returnsFirstArg());
		Item testItem = itemService.createItem(3, 1);
		
		assertEquals(expectedItem.getType(), testItem.getType());
		assertEquals(expectedItem.getpID(), testItem.getpID());
		assertEquals(expectedItem.getuID(), testItem.getuID());
	}
	
	@Test
	public void modifyItemTest() {
		Item testItem = new Item(1, null, 1, 1);
		when(mockItemDao.modifyItem(testItem)).thenReturn(true);
		assertEquals(itemService.modifyItem(testItem), true);
	}
	
	@Test
	public void modifyItemInvalidTest() {
		Item testItem = new Item(-1, null, 1, 1);
		when(mockItemDao.modifyItem(testItem)).thenReturn(false);
		assertEquals(itemService.modifyItem(testItem), false);
	}
	
	@Test
	public void changeItemOwnerTest() {
		Item testItem = new Item(1, null, 1, 1);
		Pet testPet = new Pet(1, 1, "Test", 0, 0, 0, 0, null);
		when(mockItemDao.getItemByID(1)).thenReturn(testItem);
		when(mockItemDao.modifyItem(testItem)).thenReturn(true);
		when(mockPetService.getPetByID(1)).thenReturn(testPet);
		assertEquals(itemService.changeItemOwner(1, 1, 1), true);
	}
	
	@Test
	public void changeItemOwnerInvalidItemTest() {
		//Item testItem = new Item(-1, null, 1, 1);
		Pet testPet = new Pet(1, 1, "Test", 0, 0, 0, 0, null);
		when(mockItemDao.getItemByID(-1)).thenReturn(null);
		//when(mockItemDao.modifyItem(testItem)).thenReturn(false);
		when(mockPetService.getPetByID(1)).thenReturn(testPet);
		assertEquals(itemService.changeItemOwner(-1, 1, 1), false);
	}
	
	@Test
	public void changeItemOwnerInvalidRecipientTest() {
		//Item testItem = new Item(1, null, 1, -1);
		//Pet testPet = new Pet(1, 1, "Test", 0, 0, 0, 0, null);
		//when(mockItemDao.modifyItem(testItem)).thenReturn(true);
		when(mockPetService.getPetByID(-1)).thenReturn(null);
		assertEquals(itemService.changeItemOwner(1, 1, -1), false);
	}
	
	@Test
	public void changeItemOwnerWrongOwnerTest() {
		//Item testItem = new Item(1, null, 1, 2);
		Pet testPet = new Pet(2, 2, "Test", 0, 0, 0, 0, null);
		//when(mockItemDao.modifyItem(testItem)).thenReturn(true);
		when(mockPetService.getPetByID(2)).thenReturn(testPet);
		assertEquals(itemService.changeItemOwner(1, 1, 2), false);
	}
	
	@Test
	public void returnItemOwnerTest() {
		Item testItem = new Item(1, null, 1, 1);
		//Pet testPet = new Pet(1, 1, "Test", 0, 0, 0, 0, null);
		when(mockItemDao.getItemByID(1)).thenReturn(testItem);
		when(mockItemDao.returnToOwner(testItem)).thenReturn(true);
		//when(mockPetService.getPetByID(1)).thenReturn(testPet);
		assertEquals(itemService.changeItemOwner(1, 1, 0), true);
	}
	
	@Test
	public void returnItemOwnerInvalidItemTest() {
		//Item testItem = new Item(-1, null, 1, 1);
		Pet testPet = new Pet(1, 1, "Test", 0, 0, 0, 0, null);
		when(mockItemDao.getItemByID(-1)).thenReturn(null);
		//when(mockItemDao.modifyItem(testItem)).thenReturn(false);
		when(mockPetService.getPetByID(1)).thenReturn(testPet);
		assertEquals(itemService.changeItemOwner(-1, 1, 0), false);
	}
	
	@Test
	public void getItemByIDTest() {
		Item testItem = new Item(1, null, 1, 1);
		when(mockItemDao.getItemByID(1)).thenReturn(testItem);
		assertEquals(itemService.getItemByID(1), testItem);
	}
	
	@Test
	public void getItemByIDInvalidTest() {
		when(mockItemDao.getItemByID(-1)).thenReturn(null);
		assertEquals(itemService.getItemByID(-1), null);
	}
	
	@Test
	public void getItemListTest() {
		ArrayList<Item> testList = new ArrayList<Item>();
		testList.add(new Item(1, null, 1, 1));
		testList.add(new Item(2, null, 1, 1));
		testList.add(new Item(3, null, 1, 1));
		when(mockItemDao.getItemList(1)).thenReturn(testList);
		assertEquals(itemService.getItemList(1), testList);
	}
	
	@Test
	public void getItemListInvalidTest() {
		when(mockItemDao.getItemList(-1)).thenReturn(null);
		assertEquals(itemService.getItemList(-1), null);
	}
	
	@Test
	public void getItemListEmptyTest() {
		ArrayList<Item> testList = new ArrayList<Item>();
		when(mockItemDao.getItemList(1)).thenReturn(testList);
		assertEquals(itemService.getItemList(1), testList);
	}
	
	@Test
	public void getPetItemListTest() {
		ArrayList<Item> testList = new ArrayList<Item>();
		testList.add(new Item(1, null, 1, 1));
		testList.add(new Item(2, null, 1, 1));
		testList.add(new Item(3, null, 1, 1));
		when(mockItemDao.getPetItemList(1)).thenReturn(testList);
		assertEquals(itemService.getPetItemList(1), testList);
	}
	
	@Test
	public void getPetItemListInvalidTest() {
		when(mockItemDao.getPetItemList(-1)).thenReturn(null);
		assertEquals(itemService.getPetItemList(-1), null);
	}
	
	@Test
	public void getPetItemListEmptyTest() {
		ArrayList<Item> testList = new ArrayList<Item>();
		when(mockItemDao.getPetItemList(1)).thenReturn(testList);
		assertEquals(itemService.getPetItemList(1), testList);
	}
	
	@Test
	public void deleteItemTest() {
		Item testItem = new Item(1, null, 1, 1);
		when(mockItemDao.deleteItem(1)).thenReturn(true);
		when(mockItemDao.getItemByID(1)).thenReturn(testItem);
		
		assertEquals(itemService.deleteItem(1, 1), true);
	}
	
	@Test
	public void deleteItemWrongOwnerTest() {
		Item testItem = new Item(1, null, 1, 1);
		when(mockItemDao.deleteItem(1)).thenReturn(true);
		when(mockItemDao.getItemByID(1)).thenReturn(testItem);
		
		assertEquals(itemService.deleteItem(12, 1), false);
	}
	
	@Test
	public void noSuchOwnerTest() {
		Item testItem = new Item(1, null, 1, 1);
		when(mockItemDao.deleteItem(1)).thenReturn(true);
		when(mockItemDao.getItemByID(1)).thenReturn(testItem);
		
		assertEquals(itemService.deleteItem(-1, 1), false);
	}
	
	@Test
	public void deleteItemNoSuchItemTest() {
		//Item testItem = new Item(1, null, 1, 1);
		when(mockItemDao.deleteItem(-1)).thenReturn(false);
		when(mockItemDao.getItemByID(-1)).thenReturn(null);
		
		assertEquals(itemService.deleteItem(1, -1), false);
	}
	
	@Test
	public void getItemTypesTest(){
		ArrayList<ItemType> testItemTypes = new ArrayList<ItemType>();
		when(mockItemDao.getItemTypes()).thenReturn(testItemTypes);
		assertEquals(itemService.getItemTypes(), testItemTypes);
	}
	
}
