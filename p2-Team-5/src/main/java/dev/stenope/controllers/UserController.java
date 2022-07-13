package dev.stenope.controllers;

import dev.stenope.models.User;
import dev.stenope.respositories.UserDAO;
import dev.stenope.services.UserService;
import io.javalin.http.Context;

public class UserController {
	private static UserService us = new UserService(new UserDAO());
	
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
		User u = ctx.bodyAsClass(User.class);
		User uGetById = us.getUserByID(u.getId());
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
		User uEdit = us.editUser(u);
		if (uEdit != null) {
			ctx.status(200);
			ctx.json(uEdit);
			ctx.sessionAttribute("User updated: ", uEdit);
		} else {
			ctx.status(404);
			ctx.sessionAttribute("No such user exists, cannot edit");
		}
	}
}
