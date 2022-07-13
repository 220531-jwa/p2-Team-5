package dev.stenope.services;

import dev.stenope.respositories.PetDAO;

import java.util.List;

import dev.stenope.models.Item;
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
	public Pet modifyPet(Pet p, Item i, String operation) //add double-checking, pound support, etc.
	{	
		Pet output = p;
		if (operation.equals("itemUse")) 
		{
			if (i.getType().gettCat().equals("food")) {output.setFood(output.getFood()+1);}
			else if (i.getType().gettCat().equals("toy")) {output.setFun(output.getFun()+1);}
		}
		else {;}
		return pDAO.modifyPet(output);
	}
	
	//Delete
}
