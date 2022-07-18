/**
 * This is the UserController class for the Stenope Pet Management System application
 * 
 * @author joshuacoombs
 * @author wlcross
 * @author TCPrater
 * 
 * @version 1.0
 */

package dev.stenope.controllers;

import java.util.List;

import dev.stenope.models.User;
import dev.stenope.models.UserComment;
import dev.stenope.models.UserCommentReader;
import dev.stenope.respositories.UserDAO;
import dev.stenope.services.UserService;
import io.javalin.http.Context;

public class UserController {
	private static UserService us = new UserService(new UserDAO());
	
	/**
	 * This method creates a User
	 * @param ctx
	 */
	public static void createUser(Context ctx) {
		User u = ctx.bodyAsClass(User.class);
		User uCreated = us.createUser(u);
		if (uCreated != null) {
			ctx.status(201);
			ctx.json(uCreated);
			ctx.sessionAttribute("User created: ", uCreated.getuName());
		} else {
			ctx.status(404);
			ctx.json("user couldn't be created");
		}
	}
	
	/**
	 * This method allows the User to login to the application
	 * @param ctx
	 */
	public static void login(Context ctx) {
		User u = ctx.bodyAsClass(User.class);
		User uLogin = us.login(u.getuName(), u.getpKey());
		if (uLogin != null) {
			ctx.status(200);
			ctx.json(uLogin);
			ctx.sessionAttribute("logging in: ", uLogin.getuName());
		} else {
			ctx.status(404);
			ctx.json("login failed: user doesn't exist");
		}
	}
	
	/**
	 * This methods allows the User to logout of the application
	 * @param ctx
	 */
	public static void logout(Context ctx) {
		ctx.status(200);
		ctx.req.getSession().invalidate();
	}
	
	/**
	 * This methods retrieves a specific User by their id
	 * @param ctx
	 */
	public static void getUserByID(Context ctx) {
		String u = ctx.pathParam("{id0}");
		int uId = Integer.parseInt(u);
		User uGetById = us.getUserByID(uId);
		if (uGetById != null) {
			ctx.status(200);
			ctx.json(uGetById);
			ctx.sessionAttribute("User: ", uGetById);
		} else {
			ctx.status(404);
			ctx.json("No such user by that id");
		}
	}
	
	/**
	 * This method allows the User to edit their attributes
	 * @param ctx
	 */
	public static void editUser(Context ctx) {
		User u = ctx.bodyAsClass(User.class);
		User uEdit = us.editUser(u.getId());
		if (uEdit != null) {
			ctx.status(200);
			ctx.json(uEdit);
			ctx.sessionAttribute("User updated: ", uEdit);
		} else {
			ctx.status(404);
			ctx.sessionAttribute("No such user exists, cannot edit");
		}
	}
	
	/**
	 * This method allows the User to add a UserComment on a User's Page
	 * @param ctx
	 */
	public static void addComment(Context ctx) {
		UserComment c = ctx.bodyAsClass(UserComment.class);
		c.setwID(Integer.parseInt(ctx.pathParam("{id0}")));
		c.sethID(Integer.parseInt(ctx.pathParam("{idOther}")));
		UserComment cAdd = us.addComment(c.getwID(), c.gethID(), c.getBody());
		if (cAdd != null) {
			ctx.status(201);
			ctx.json(cAdd);
			ctx.sessionAttribute("New comment: ", cAdd);
		} else {
			ctx.status(404);
			ctx.sessionAttribute("Unable to add comment");
		}
	}
	
	/**
	 * This method allows a User to view another User's Page
	 * @param ctx
	 */
	public static void viewOtherUsersPage(Context ctx) {
		User u = ctx.bodyAsClass(User.class);
		User otherU = us.viewOtherUsersPage(u.getuName());
		if (otherU != null) {
			ctx.status(200);
			ctx.json(otherU);
			ctx.sessionAttribute("Other User info: ", otherU);
		} else {
			ctx.status(404);
			ctx.sessionAttribute("User not found");
		}
	}
	
	/**
	 * This method retrieves the comments on a User's Page
	 * @param ctx
	 */
	public static void getComments(Context ctx) {
		int hId = Integer.parseInt(ctx.pathParam("{id0}"));
		List<UserCommentReader> comments = us.getComments(hId);
		
		if (comments == null) {
			ctx.status(404);
		} else if (comments.isEmpty()) {
			ctx.status(204);
		} else {
			ctx.json(comments);
			ctx.status(200);
		}
	}
}
