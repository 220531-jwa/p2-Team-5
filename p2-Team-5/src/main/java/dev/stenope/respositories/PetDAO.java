package dev.stenope.respositories;

import java.util.List;

import dev.stenope.models.Pet;
import dev.stenope.models.PetType;
import dev.stenope.utils.ConnectionUtil;

public class PetDAO {
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
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
	
	public List<PetType> getPetTypes() {
		
		return null;
	}
}
