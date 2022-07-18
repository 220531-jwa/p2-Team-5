/**
 * This is the AppDriver for the Stenope Pet Management System application.
 * 
 * @author joshuacoombs
 * @author wlcross
 * @author TCPrater
 * 
 * @version 1.0
 */

package dev.stenope;

import dev.stenope.utils.Amentum;

public class AppDriver {
	public static void main(String[] args) 
	{
		Amentum amn = new Amentum();
		amn.serverRequestHandler();
	}

}
