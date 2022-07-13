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
	
	//Create
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

	//Read
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
	
	public static void getPetList(Context ctx) {
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
	
	//Update
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
	//Delete
}
