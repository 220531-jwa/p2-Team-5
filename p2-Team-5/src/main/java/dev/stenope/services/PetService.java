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
	
	public List<Pet> getPetListByPName(String pname) {return pDAO.getPetListByPName(pname);}
	
	public List<Pet> getAllPets() {return pDAO.getAllPets();}
	
	public List<PetType> getPetTypes() {return pDAO.getPetTypes();}
	
	//Update 
	public Pet modifyPet(Pet p, Item i, String operation) //add double-checking, pound support, etc.
	{	
		Pet output = p;
		if (p.getId()<1) {return null;}
		else
		{
			if (operation.equals("itemUse")) 
			{
				if (i.getType().gettCat().equals("food") && output.getFood()<6) {output.setFood(output.getFood()+1);}
				else if (i.getType().gettCat().equals("toy") && output.getFun()<6) {output.setFun(output.getFun()+1);}
			}
			else if (operation.equals("hunger"))
			{
				p.setFood(Math.max(p.getFood()-1, 0));
			}
			else {;}
			return pDAO.modifyPet(output);
		}
	}
	
	//Delete
}
