package dev.stenope.services;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.*;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.stenope.models.User;
import dev.stenope.models.UserComment;
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
		User testUser = new User(1, "User", "Pass", "Test", "Test", 0);
		when(mockUserDao.getUserByUserName(testUser.getuName())).thenReturn(testUser);
		assertEquals(userService.login(testUser.getuName(), testUser.getpKey()), testUser);
	}
	
	@Test
	public void logInIncorrectPasswordTest() {
		User testUser = new User(1, "User", "WrongPass", "Test", "Test", 0);
		User testUserReal = new User(1, "User", "Pass", "Test", "Test", 0);
		when(mockUserDao.getUserByUserName(testUser.getuName())).thenReturn(testUserReal);
		assertEquals(userService.login(testUser.getuName(), "dfkjhadfvuhadf"), null);
	}
	
	@Test
	public void logInNoSuchUserTest() {
		User testUser = new User(1, "UserNotFound", "Pass", "Test", "Test", 0);
		when(mockUserDao.getUserByUserName(testUser.getuName())).thenReturn(null);
		assertEquals(userService.login(testUser.getuName(), testUser.getpKey()), null);
	}
	
	@Test
	public void getUserByIDTest() {
		User testUser = new User(1, "User", "Pass", "Test", "Test", 0);
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
		when(mockUserDao.editUser(testUser)).thenReturn(testUser);
		assertEquals(userService.editUser(testUser), testUser);
	}
	
	@Test
	public void editUserInvalidTest() {
		User testUser = new User(100, "User", "Pass", "Test", "Test", 0);
		when(mockUserDao.editUser(testUser)).thenReturn(null);
		assertEquals(userService.editUser(testUser), null);
	}
	
	@Test
	public void addCommentTestPositive() {
		
	}
	
	@Test
	public void addCommentTestNegative() {
		
	}
	
	@Test
	public void viewOtherUserTestPositive() {
		
	}
	
	@Test
	public void viewOtherUserTestNegative() {
		
	}
}
