package dev.stenope.utils;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;
import static io.javalin.apibuilder.ApiBuilder.delete;

import dev.stenope.controllers.*;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

/*for those who don't get the joke: an amentum increases javelin speed much like a sling increases rock speed*/
public class Amentum {
	public void serverRequestHandler() 
	{
		Javalin app = Javalin.create(config -> config.addStaticFiles("/public", Location.CLASSPATH));
		app.start(8080);
		
		app.routes(() -> {
			get((ctx) ->ctx.result("HOMEPAGE WIP"));
			path("/login", () -> {post(UserController::login);});
			path("/petTypes", () -> {get(PetController::getPetTypes);});
			path("/users", ()  -> {
				path("/{id0}", () -> {
					get(UserController::getUserByID);
					put(UserController::editUser);
					path("/pets", () -> {
						get(PetController::getPetList);
						post(PetController::createPet);
						path("/{id1}", () -> {
							get(PetController::getPetByID);
							put(PetController::modifyPet);
						});
					});
				});
			});
		});
		
		app.exception(Exception.class, (e, ctx) -> {
		    ctx.status(404);
		    ctx.result("Generic 404 Message");
		});
	}
}
