package dev.stenope.services;

import dev.stenope.respositories.PetDAO;

import java.util.List;

import dev.stenope.models.Pet;

public class PetService {

	static PetDAO petDao = new PetDAO();
	
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
