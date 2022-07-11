package dev.stenope.services;

import dev.stenope.respositories.PetDAO;

import java.util.List;

import dev.stenope.models.Pet;

public class PetService {

	private static PetDAO petDao;
	
	public PetService(PetDAO petDao) {
		this.petDao = petDao;
	}
	
	public Pet createPet(Pet p) {
		
		return null;
	}
	
	public boolean modifyPet(Pet p) {
		
		return false;
	}
	
	public Pet getPetByID(int id) {
		
		return null;
	}
	
	public List<Pet> getPetListByUserID(int id) {
		
		return null;
	}
}
