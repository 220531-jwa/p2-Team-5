package dev.stenope.utils;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;
import static io.javalin.apibuilder.ApiBuilder.patch;
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
<<<<<<< HEAD
			get((ctx) ->ctx.result("HOMEPAGE WIP"));
			path("/login", () -> {post(UserController::login);});
=======
			get((ctx) ->{ctx.redirect("http://localhost:8080/homePage.html", 301);});
>>>>>>> 9475945ea733aa8198472ea86e438ce171957c40
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
							path("/item", () -> {
								patch(PetController::useItemOnPet);
							});
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
