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
	
	public static void logout(Context ctx) {
		ctx.status(200);
		ctx.req.getSession().invalidate();
	}
	
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
