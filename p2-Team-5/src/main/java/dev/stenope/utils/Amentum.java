/**
 * This is the helper class which is invoked by the AppDriver class 
 * for the Stenope Pet Management System application.
 * 
 * @author joshuacoombs
 * @author wlcross
 * @author TCPrater
 * 
 * @version 1.0
 */

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
	
	/**
	 * This method is the server request handler which starts Javalin, connects to the database, 
	 * and outlines the RESTful endpoints of Controller methods.
	 */
	public void serverRequestHandler() 
	{
		Javalin app = Javalin.create(config -> {
			//config.enableCorsForOrigin("http://ec2-54-67-101-32.us-west-1.compute.amazonaws.com:");
			config.enableCorsForAllOrigins();
			config.addStaticFiles("/public", Location.CLASSPATH);});
		app.start(8080);
		
		app.routes(() -> {
			//S3 link http://p2-t5-stenope-bucket.s3-website-us-west-1.amazonaws.com
			//get((ctx) ->ctx.redirect("http://http://p2-t5-stenope-bucket.s3-website-us-west-1.amazonaws.com/homePage.html",301));
			path("/doHungerTick", () -> {get(PetController::doHungerTick);});
			path("/create", () -> {post(UserController::createUser);});
			path("/login", () -> {post(UserController::login);});
			path("/logout", () -> {post(UserController::logout);});
			path("/petTypes", () -> {get(PetController::getPetTypes);});
			path("/itemTypes", () -> {get(ItemController::getItemTypes);});
			path("/search", () -> {
				path("/{name}", () -> {
					path("/pets", () -> {
						get(PetController::getPetListByPName);
					});
				});
			});
			path("/users", ()  -> {
				path("/{id0}", () -> {
					path("/comments", () -> {
						get(UserController::getComments);
					});
					path("/edit", () -> {
						put(UserController::editUser);
					});
					get(UserController::getUserByID);
					path("/pets", () -> {
						get(PetController::getPetListByUID);
						post(PetController::createPet);
						path("/{id1}", () -> {
							get(PetController::getPetByID);
							put(PetController::modifyPet);
							path("/items", () -> {
								get(ItemController::getPetItemList);
							});
						});
					});
					path("/items", () -> {
						get(ItemController::getItemList);
						post(ItemController::createItem);
						path("/{itemId}", () -> {
							get(ItemController::getItem);
							delete(ItemController::deleteItem);
							put(ItemController::modifyItem);
							path("/give", () -> {
								path("/{petId}", () -> {
									put(ItemController::changeItemOwner);
								});
							});
							path("/use", () -> {
								path("/{petId}", () ->{
									patch(PetController::useItemOnPet);
								});
							});
						});
					});
					path("/{idOther}", () -> {
						get(UserController::viewOtherUsersPage);
						path("/comment", () ->  {
							post(UserController::addComment);
						});
					});
				});
			});
		});
		
		app.exception(Exception.class, (e, ctx) -> {
		    ctx.status(404);
		    e.printStackTrace();
		    ctx.result("Exception 404 Message");
		});
	}
}
