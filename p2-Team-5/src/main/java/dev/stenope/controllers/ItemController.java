/**
 * This is the ItemController class for the Stenope Pet Management System application.
 * 
 * @author joshuacoombs
 * @author wlcross
 * @author TCPrater
 * 
 * @version 1.0
 */

package dev.stenope.controllers;

import java.util.List;

import dev.stenope.models.Item;
import dev.stenope.models.ItemType;
import dev.stenope.respositories.ItemDAO;
import dev.stenope.services.ItemService;
import io.javalin.http.Context;

public class ItemController {
	
private static ItemService itemService = new ItemService();
	
	/**
	 * This method creates an Item
	 * @param ctx
	 */
	public static void createItem(Context ctx) {
		int tID = Integer.parseInt(ctx.queryParam("typeId"));
		int userId = Integer.parseInt(ctx.pathParam("id0"));
		Item rItem = itemService.createItem(tID, userId);
		if (rItem != null) {
			ctx.json(rItem);
			ctx.status(200);
		} else {
			ctx.status(400);
		}
	}

	/**
	 * This method modifies an Item
	 * @param ctx
	 */
	public static void modifyItem(Context ctx) {
		Item item = ctx.bodyAsClass(Item.class);
		if (itemService.modifyItem(item)) {
			ctx.status(200);
		} else {
			ctx.status(400);
		}
	}
	
	/**
	 * This method changes the owner of a specific Item
	 * @param ctx
	 */
	public static void changeItemOwner(Context ctx) {
		int itemId = Integer.parseInt(ctx.pathParam("itemId"));
		int userId = Integer.parseInt(ctx.pathParam("id0"));
		int newOwner = Integer.parseInt(ctx.pathParam("petId"));
		if (itemService.changeItemOwner(itemId, userId, newOwner)) {
			ctx.status(200);
		} else {
			ctx.status(400);
		}
	}
	
	/**
	 * This method retrieves a specific Item
	 * @param ctx
	 */
	public static void getItem(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("itemId"));
		Item i = itemService.getItemByID(id);
		if (i != null) {
			ctx.json(i);
			ctx.status(200);
		} else {
			ctx.status(404);
		}
	}
	
	/**
	 * This method retrieves a list of Items associated with a User
	 * @param ctx
	 */
	public static void getItemList(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id0"));
		List<Item> items = itemService.getItemList(id);
		if (items == null) {
			ctx.status(400);
		} else if (items.isEmpty()) {
			ctx.json(items);
			ctx.status(204);
		} else {
			ctx.json(items);
			ctx.status(200);
		}
	}
	
	/**
	 * This method retrieves a list of Items associated with a Pet
	 * @param ctx
	 */
	public static void getPetItemList(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id1"));
		List<Item> items = itemService.getPetItemList(id);
		if (items == null) {
			ctx.status(404);
		} else if (items.isEmpty()) {
			ctx.status(204);
			ctx.json(items);
		} else {
			ctx.json(items);
			ctx.status(200);
		}
	}
	
	/**
	 * This method retrieves the list of ItemTypes
	 * @param ctx
	 */
	public static void getItemTypes(Context ctx) {
		List<ItemType> types = itemService.getItemTypes();
		if (types != null && !types.isEmpty()) {
			ctx.status(200);
			ctx.json(types);
		} else {
			//Should never be reached
			ctx.status(400);
		}
	}
	
	/**
	 * This method deletes an Item
	 * @param ctx
	 */
	public static void deleteItem(Context ctx) {
		int itemId = Integer.parseInt(ctx.pathParam("itemId"));
		int userId = Integer.parseInt(ctx.pathParam("id0"));
		if (itemService.deleteItem(userId, itemId)) {
			ctx.status(200);
		} else {
			ctx.status(404);
		}
	}
	
}
