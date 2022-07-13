package dev.stenope.services;

import dev.stenope.respositories.PetDAO;

import java.util.List;

import dev.stenope.models.Pet;
import dev.stenope.models.PetType;

public class PetService {

	private static PetDAO pDAO;
	
	public PetService(PetDAO petDao) {pDAO = petDao;}
	
	//Create 
	public Pet createPet(Pet p) {return pDAO.createPet(p);}
	
	//Read 
	public Pet getPetByID(int id) {return pDAO.getPetByID(id);}
	
	public List<Pet> getPetListByUserID(int id)	{return pDAO.getPetListByUserID(id);}
	
	public List<PetType> getPetTypes() {return pDAO.getPetTypes();}
	
	//Update 
	public Pet modifyPet(Pet p) {return pDAO.modifyPet(p);} //add double-checking, pound support, etc.
	
	//Delete
}
