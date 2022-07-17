/**
 * This is the PetService class for the Stenope Pet Management System application
 * 
 * @author joshuacoombs
 * @author wlcross
 * @author TCPrater
 * 
 * @version 1.0
 */

package dev.stenope.services;

import dev.stenope.respositories.PetDAO;

import java.util.List;

import dev.stenope.models.Item;
import dev.stenope.models.Pet;
import dev.stenope.models.PetType;

public class PetService {
private static PetDAO pDAO;
	
	public PetService(PetDAO petDao) {pDAO = petDao;}
	
	/**
	 * This method creates a Pet 
	 * @param p
	 * @return
	 */
	public Pet createPet(Pet p) {return pDAO.createPet(p);}
	
	/**
	 * This methods retrieves a Pet by its id
	 * @param id
	 * @return
	 */
	public Pet getPetByID(int id) {return pDAO.getPetByID(id);}
	
	/**
	 * This method retrieves a list of Pets by User id
	 * @param id
	 * @return
	 */
	public List<Pet> getPetListByUserID(int id)	{return pDAO.getPetListByUserID(id);}
	
	/**
	 * This method retrieves a list of Pets owned by another Pet
	 * @param pname
	 * @return
	 */
	public List<Pet> getPetListByPName(String pname) {return pDAO.getPetListByPName(pname);}
	
	/**
	 * This method gets all Pets
	 * @return
	 */
	public List<Pet> getAllPets() {return pDAO.getAllPets();}
	
	/**
	 * This method Retrieves a list of PetTypes
	 * @return
	 */
	public List<PetType> getPetTypes() {return pDAO.getPetTypes();}
	
	/**
	 * This method modifies a Pet
	 * @param p
	 * @param i
	 * @param operation
	 * @return
	 */
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
