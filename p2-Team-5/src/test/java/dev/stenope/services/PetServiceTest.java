/**
 * This is the PetServiceTest class for the Stenope Pet Management System application.
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

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.*;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.stenope.respositories.PetDAO;
import dev.stenope.models.Item;
import dev.stenope.models.ItemType;
import dev.stenope.models.Pet;
import dev.stenope.models.PetType;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

	private static PetService petService;
	
	private static PetDAO mockPetDao;
	
	@BeforeAll
	public static void setUp() {
		mockPetDao = mock(PetDAO.class);
		petService = new PetService(mockPetDao);
	}
	
	@Test
	public void createPetTest() {
		Pet testPet = new Pet(1, 1, "Test", 0, 0, 0, 0, null);
		when(mockPetDao.createPet(testPet)).thenReturn(testPet);
		assertEquals(petService.createPet(testPet), testPet);
	}
	
	
	@Test
	public void getPetTest() {
		Pet testPet = new Pet(1, 1, "Test", 0, 0, 0, 0, null);
		when(mockPetDao.getPetByID(1)).thenReturn(testPet);
		assertEquals(petService.getPetByID(1), testPet);
	}
	
	@Test
	public void getPetInvalidTest() {
		when(mockPetDao.getPetByID(-1)).thenReturn(null);
		assertEquals(petService.getPetByID(-1), null);
	}
	
	@Test
	public void getPetListByIDTest() {
		ArrayList<Pet> testList = new ArrayList<Pet>();
		testList.add(new Pet(1, 1, "Test1", 0, 0, 0, 0, null));
		testList.add(new Pet(2, 1, "Test2", 0, 0, 0, 0, null));
		testList.add(new Pet(3, 1, "Test3", 0, 0, 0, 0, null));
		
		when(mockPetDao.getPetListByUserID(1)).thenReturn(testList);
		assertEquals(petService.getPetListByUserID(1), testList);
	}
	
	@Test
	public void getPetListByPNameTest() {
		ArrayList<Pet> testList = new ArrayList<Pet>();
		testList.add(new Pet(1, 1, "Test1", 0, 0, 0, 0, null));
		testList.add(new Pet(2, 2, "Test1", 0, 0, 0, 0, null));
		testList.add(new Pet(3, 3, "Test1", 0, 0, 0, 0, null));
		
		when(mockPetDao.getPetListByPName("Test1")).thenReturn(testList);
		assertEquals(petService.getPetListByPName("Test1"), testList);
	}
	
	@Test
	public void getAllPets() {
		ArrayList<Pet> testList = new ArrayList<Pet>();
		testList.add(new Pet(1, 1, "Test1", 0, 0, 0, 0, null));
		testList.add(new Pet(2, 2, "Test1", 0, 0, 0, 0, null));
		testList.add(new Pet(3, 3, "Test1", 0, 0, 0, 0, null));
		
		when(mockPetDao.getAllPets()).thenReturn(testList);
		assertEquals(petService.getAllPets(), testList);
	}
	
	@Test
	public void getPetInvalidQueryTest() {
		when(mockPetDao.getPetByID(-1)).thenReturn(null);
		assertEquals(petService.getPetByID(-1), null);
	}
	
	@Test
	public void getPetEmptyListTest() {
		ArrayList<Pet> testList = new ArrayList<Pet>();
		when(mockPetDao.getPetListByUserID(1)).thenReturn(testList);
		assertEquals(petService.getPetListByUserID(1), testList);
	}
	
	@Test
	public void getAllPetTypes() {
		ArrayList<PetType> testList = new ArrayList<>();
		testList.add(new PetType(1,"a","b"));
		testList.add(new PetType(2,"c","d"));
		testList.add(new PetType(3,"e","f"));
		
		when(mockPetDao.getPetTypes()).thenReturn(testList);
		assertEquals(petService.getPetTypes(), testList);
	}
	
	@Test
	public void modifyPetTestDefault() {
		Pet testPet = new Pet(1, 1, "Test", 0, 0, 0, 0, null);
		Item doodad = new Item(0,new ItemType(0,0,"","",""),0,0);
		when(mockPetDao.modifyPet(testPet)).thenReturn(testPet);
		assertEquals(petService.modifyPet(testPet,doodad,""), testPet);
	}
	
	@Test
	public void modifyPetInvalidTest() {
		Pet testPet = new Pet(-1, 1, "Test", 0, 0, 0, 0, null);
		Item doodad = new Item(0,new ItemType(0,0,"","",""),0,0);
		when(mockPetDao.modifyPet(testPet)).thenReturn(testPet);
		assertEquals(petService.modifyPet(testPet,doodad,""), null);
	}
	
	@Test
	public void modifyPetTestFood() {
		Pet testPet1 = new Pet(1, 1, "Test", 0, 0, 0, 0, null);
		Pet testPet2 = new Pet(1, 1, "Test", 0, 0, 1, 0, null);
		Item doodad = new Item(0,new ItemType(0,0,"","food",""),0,0);
		when(mockPetDao.modifyPet(testPet1)).thenReturn(testPet1);
		assertEquals(petService.modifyPet(testPet1,doodad,"itemUse").getFood(), testPet2.getFood());
	}
	
	@Test
	public void modifyPetTestFoodOverfull() {
		Pet testPet1 = new Pet(1, 1, "Test", 0, 0, 6, 0, null);
		Pet testPet2 = new Pet(1, 1, "Test", 0, 0, 6, 0, null);
		Item doodad = new Item(0,new ItemType(0,0,"","food",""),0,0);
		when(mockPetDao.modifyPet(testPet1)).thenReturn(testPet1);
		assertEquals(petService.modifyPet(testPet1,doodad,"itemUse").getFood(), testPet2.getFood());
	}
	
	@Test
	public void modifyPetTestToy() {
		Pet testPet1 = new Pet(1, 1, "Test", 0, 0, 0, 0, null);
		Pet testPet2 = new Pet(1, 1, "Test", 0, 1, 0, 0, null);
		Item doodad = new Item(0,new ItemType(0,0,"","toy",""),0,0);
		when(mockPetDao.modifyPet(testPet1)).thenReturn(testPet1);
		assertEquals(petService.modifyPet(testPet1,doodad,"itemUse").getFun(), testPet2.getFun());
	}
	
	@Test
	public void modifyPetTestToyBlissedOut() {
		Pet testPet1 = new Pet(1, 1, "Test", 0, 6, 0, 0, null);
		Pet testPet2 = new Pet(1, 1, "Test", 0, 6, 0, 0, null);
		Item doodad = new Item(0,new ItemType(0,0,"","toy",""),0,0);
		when(mockPetDao.modifyPet(testPet1)).thenReturn(testPet1);
		assertEquals(petService.modifyPet(testPet1,doodad,"itemUse").getFun(), testPet2.getFun());
	}
	
	@Test
	public void modifyPetTestOther() {
		Pet testPet1 = new Pet(1, 1, "Test", 0, 0, 0, 0, null);
		Item doodad = new Item(0,new ItemType(0,0,"","toy",""),0,0);
		when(mockPetDao.modifyPet(testPet1)).thenReturn(testPet1);
		assertEquals(petService.modifyPet(testPet1,doodad,"itemUse"),testPet1);
	}
	
	@Test
	public void modifyPetTestHunger() {
		Pet testPet1 = new Pet(1, 1, "Test", 0, 0, 1, 0, null);
		Pet testPet2 = new Pet(1, 1, "Test", 0, 0, 0, 0, null);
		Item doodad = new Item(0,new ItemType(0,0,"","",""),0,0);
		when(mockPetDao.modifyPet(testPet1)).thenReturn(testPet1);
		assertEquals(petService.modifyPet(testPet1,doodad,"hunger").getFood(), testPet2.getFood());
	}
}
