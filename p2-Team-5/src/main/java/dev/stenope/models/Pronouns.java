/**
 * This is the Pronouns class for the Stenope Pet Management System application
 * which includes a String array of available pronoun sets
 * 
 * @author joshuacoombs
 * @author wlcross
 * @author TCPrater
 * 
 * @version 1.0
 */

package dev.stenope.models;

public class Pronouns {
	public static String pronouns[] = 
		{	//subject / object / adj. possessive / obj. possesive / reflexive
			"e/em/eir/eirs/emself", //Spivak set
			"ey/em/eir/eirs/emself", //Elverson set
			"he/him/his/his/himself", //traditional masculine
			"she/her/her/hers/herself",  //traditional feminine
			"they/them/thier/theirs/themself", //singular 'they'
			"xe/xem/xyr/xyrs/xemself", // There's like 15 ways of spelling this one
			"ze/zir/zir/zirs/zirself" // Ze/zir salad
		}; //pulled from en.pronouns.page
}
