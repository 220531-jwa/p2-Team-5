/**
 * This is the UserServiceTest class for the Stenope Pet Management System application.
 * This class is used in the JUnit/Mockito unit tests which cover the Service classes.
 * 
 * @author joshuacoombs
 * @author wlcross
 * @author TCPrater
 * 
 * @version 1.0
 */

package dev.stenope.services;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.stenope.models.User;
import dev.stenope.models.UserComment;
import dev.stenope.models.UserCommentReader;
import dev.stenope.respositories.UserDAO;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	private static UserService userService;
	
	private static UserDAO mockUserDao;
	
	@BeforeAll
	public static void setUp() {
		mockUserDao = mock(UserDAO.class);
		userService = new UserService(mockUserDao);
	}
	
	@Test
	public void createUserTestPositive() {
		User testUser = new User(1, "User", "Pass", "Test", "Test", 0);
		when(mockUserDao.createUser(testUser)).thenReturn(testUser);
		assertEquals(userService.createUser(testUser), testUser);
	}
	
	@Test
	public void createUserTestNoSuchUser() {
		when(mockUserDao.createUser(null)).thenReturn(null);
		assertEquals(userService.createUser(null), null);
	}
	
	@Test
	public void logInTest() {
		User testUser = new User(1, "Example_Man", "Password", "Example", "I am an example user", 2);
		when(mockUserDao.getUserByUserName(testUser.getuName())).thenReturn(testUser);
		assertEquals(userService.login(testUser.getuName(), testUser.getpKey()), testUser);
	}
	
	@Test
	public void logInIncorrectPasswordTest() {
		User testUser = new User(1, "Example_Man", "Password", "Example", "I am an example user", 2);
		when(mockUserDao.getUserByUserName(testUser.getuName())).thenReturn(testUser);
		assertEquals(userService.login(testUser.getuName(), "dfkjhadfvuhadf"), null);
	}
	
	@Test
	public void logInNoSuchUserTest() {
		User testUser = new User(500, "UserNotFound", "Pass", "Test", "Test", 0);
		when(mockUserDao.getUserByUserName(testUser.getuName())).thenReturn(null);
		assertEquals(userService.login(testUser.getuName(), testUser.getpKey()), null);
	}
	
	@Test
	public void getUserByIDTest() {
		User testUser = new User(1, "Example_Man", "Password", "Example", "I am an example user", 2);
		when(mockUserDao.getUserByID(1)).thenReturn(testUser);
		assertEquals(userService.getUserByID(1), testUser);
	}
	
	@Test
	public void getUserByIDInvalidTest() {
		when(mockUserDao.getUserByID(-1)).thenReturn(null);
		assertEquals(userService.getUserByID(-1), null);
	}
	
	
	@Test
	public void editUserTest() {
		User testUser = new User(1, "User", "Pass", "Test", "Test", 0);
		when(mockUserDao.editUser(testUser.getId())).thenReturn(testUser);
		assertEquals(userService.editUser(testUser.getId()), testUser);
	}
	
	@Test
	public void editUserInvalidTest() {
		when(mockUserDao.editUser(100)).thenReturn(null);
		assertEquals(userService.editUser(100), null);
	}
	
	@Test
	public void addCommentTestPositive() {
		UserComment testComment = new UserComment(1, 1, 2, "comment");
		when(mockUserDao.addComment(1, 2, "comment")).thenReturn(testComment);
		assertEquals(userService.addComment(1, 2, "comment"), testComment);
	}
	
	@Test
	public void addCommentTestNegative() {
		when(mockUserDao.addComment(0, 0, null)).thenReturn(null);
		assertEquals(userService.addComment(0, 0, null), null);
	}
	
	@Test
	public void getCommentsTestPositive() {
		UserCommentReader testComment1 = new UserCommentReader(1, "thisUser", 2, "comment");
		UserCommentReader testComment2 = new UserCommentReader(2, "anotherThisUser", 2, "another comment");
		List<UserCommentReader> testGetComments = new ArrayList<>();
		testGetComments.add(testComment1);
		testGetComments.add(testComment2);
		when(mockUserDao.getComments(2)).thenReturn(testGetComments);
		assertEquals(userService.getComments(2), testGetComments);
	}
	
	@Test
	public void getCommentsTestNegative() {
		when(mockUserDao.getComments(5000)).thenReturn(null);
		assertEquals(userService.getComments(5000), null);
	}
	
	@Test
	public void viewOtherUserTestPositive() {
		User testUser = new User(1, "User", "Pass", "Test", "Test", 0);
		when(mockUserDao.getUserByUserName("User")).thenReturn(testUser);
		assertEquals(userService.viewOtherUsersPage("User"), testUser);
	}
	
	@Test
	public void viewOtherUserTestNegative() {
		when(mockUserDao.getUserByUserName(null)).thenReturn(null);
		assertEquals(userService.viewOtherUsersPage(null), null);
	}
}
