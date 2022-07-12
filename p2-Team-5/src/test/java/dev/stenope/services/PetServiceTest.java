package dev.stenope.services;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.*;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.stenope.respositories.PetDAO;
import dev.stenope.models.Pet;

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
	public void modifyPetTest() {
		Pet testPet = new Pet(1, 1, "Test", 0, 0, 0, 0, null);
		when(mockPetDao.modifyPet(testPet)).thenReturn(true);
		assertEquals(petService.modifyPet(testPet), true);
	}
	
	@Test
	public void modifyPetInvalidTest() {
		Pet testPet = new Pet(-1, 1, "Test", 0, 0, 0, 0, null);
		when(mockPetDao.modifyPet(testPet)).thenReturn(false);
		assertEquals(petService.modifyPet(testPet), false);
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
	public void getPetListTest() {
		ArrayList<Pet> testList = new ArrayList<Pet>();
		testList.add(new Pet(1, 1, "Test1", 0, 0, 0, 0, null));
		testList.add(new Pet(2, 1, "Test2", 0, 0, 0, 0, null));
		testList.add(new Pet(3, 1, "Test3", 0, 0, 0, 0, null));
		
		when(mockPetDao.getPetListByUserID(1)).thenReturn(testList);
		assertEquals(petService.getPetByID(1), testList);
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
		assertEquals(petService.getPetByID(1), testList);
	}
	
}