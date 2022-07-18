/**
 * This is the PetController class for the Stenope Pet Management System application.
 * 
 * @author joshuacoombs
 * @author wlcross
 * @author TCPrater
 * 
 * @version 1.0
 */

package dev.stenope.controllers;

import java.util.ArrayList;
import java.util.List;

import dev.stenope.models.Item;
import dev.stenope.models.ItemType;
import dev.stenope.models.Pet;
import dev.stenope.models.PetType;
import dev.stenope.respositories.PetDAO;
import dev.stenope.services.PetService;
import io.javalin.http.Context;

public class PetController {
	private static PetDAO pDAO = new PetDAO();
	private static PetService pServ = new PetService(pDAO);
	
	public PetController(PetService petservice) {pServ = petservice;}
	
	/**
	 * This method creates a Pet
	 * @param ctx
	 */
	public static void createPet(Context ctx) {
		Pet stray = ctx.bodyAsClass(Pet.class);
		Pet adopted = pServ.createPet(stray);
		if (adopted != null)
		{
			ctx.status(201);
			ctx.json(adopted);
		}
		else 
		{
			ctx.status(404);
			ctx.json("{error:pet_creation}");
		}
	}

	/**
	 * This method retrieves a Pet by their id
	 * @param ctx
	 */
	public static void getPetByID(Context ctx) {
		Pet output = pServ.getPetByID(Integer.parseInt(ctx.pathParam("{id1}")));
		if (output != null)
		{
			ctx.status(200);
			ctx.json(output);
		}
		else 
		{
			ctx.status(404);
			ctx.json("{error:pet_by_id}");
		}
	}
	
	/**
	 * This method retrieves a list of Pets associated with a specific User
	 * @param ctx
	 */
	public static void getPetListByUID(Context ctx) {
		List<Pet> pList = new ArrayList<>(); 
		pList = pServ.getPetListByUserID(Integer.parseInt(ctx.pathParam("{id0}")));
		if (pList != null)
		{
			ctx.status(200);
			ctx.json(pList);
		}
		else 
		{
			ctx.status(404);
			ctx.json("{error:pet_list}");
		}
	}
	
	/**
	 * This method retrieves the list of Pets owned by another Pet
	 * @param ctx
	 */
	public static void getPetListByPName(Context ctx) {
		List<Pet> pList = new ArrayList<>(); 
		pList = pServ.getPetListByPName(ctx.pathParam("{name}"));
		if (pList != null)
		{
			ctx.status(200);
			ctx.json(pList);
		}
		else 
		{
			ctx.status(404);
			ctx.json("{error:pet_list}");
		}
	}
	
	/**
	 * This method retrieves the list of PetTypes
	 * @param ctx
	 */
	public static void getPetTypes(Context ctx) {
		List<PetType> ptList = pServ.getPetTypes();
		if (ptList != null)
		{
			ctx.status(200);
			ctx.json(ptList);
		}
		else 
		{
			ctx.status(404);
			ctx.json("{error:pet_type_list}");
		}
	}
	
	/**
	 * This method modifies the attributes of a Pet
	 * @param ctx
	 */
	public static void modifyPet(Context ctx) {
		Pet stray = ctx.bodyAsClass(Pet.class);
		Item doodad = new Item(0,new ItemType(0,0,"","",""),0,0);
		Pet adopted = pServ.modifyPet(stray, doodad, "");
		if (adopted != null)
		{
			ctx.status(200);
			ctx.json(adopted);
		}
		else 
		{
			ctx.status(404);
			ctx.json("{error:pet_modification}");
		}
	}
	
	/**
	 * This method allows an Item to be used on a Pet
	 * @param ctx
	 */
	public static void useItemOnPet(Context ctx)
	{
		Item input = ctx.bodyAsClass(Item.class);
		Pet output = pServ.modifyPet(pServ.getPetByID(Integer.parseInt(ctx.pathParam("{petId}"))),input,"itemUse");
		if (output != null)
		{
			ctx.status(200);
			ctx.json(output);
		}
		else 
		{
			ctx.status(404);
			ctx.json("{error:pet_item_use}");
		}
	}
	
	/**
	 * This method keeps track of the hunger attribute of a Pet
	 * @param ctx
	 */
	public static void doHungerTick(Context ctx)
	{
		List<Pet> pList = pServ.getAllPets();
		Item doodad = new Item(0,new ItemType(0,0,"","",""),0,0);
		for (Pet p : pList)	{pServ.modifyPet(p, doodad, "hunger");}
		ctx.status(204);
	}
	
	//Delete
}
