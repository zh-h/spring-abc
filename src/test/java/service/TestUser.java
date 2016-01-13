package service;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xyz.springabc.domin.User;
import xyz.springabc.error.ValidateError;
import xyz.springabc.repository.UserRepo;
import xyz.springabc.service.UserServ;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestUser {
	
	@Autowired
	private UserServ userServ;
	@Autowired 
	private UserRepo userRepo;
	
//	@Test
//	public void signup() {
//		User user=new User();
//		user.setUsername("2333");
//		user.setPassword("666666");
//		user.setEmail("zh.h@outlook.com");
//		try {
//			userServ.signup(user, "666666");
//		} catch (ValidateError e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	@Test
//	public void signin(){
//		User user=new User();
//		user.setUsername("2333");
//		user.setPassword("666666");
//		try {
//			String username=userServ.signin(user).getUsername();
//			System.out.println(username);
//		} catch (ValidateError e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void update(){
//		User user=userRepo.findOneByUsername("2333");
//		user.setEmail("zhzzz.h@outlook.com");
//	}
	
//	@Test
//	public void email(){
//		assertNotNull(userServ.getUserByNickOrEmailOrUsername("zh.h@outlsook.com"));
//	}
//	
//	@Test
//	public void username(){
//		assertNotNull(userServ.getUserByNickOrEmailOrUsername("zonghusda"));
//	}
//	
//	@Test
//	public void nick(){
//		assertNotNull(userServ.getUserByNickOrEmailOrUsername("哈哈哈s哈哈"));
//	}
	
//	@Test
//	public void email(){
//		boolean result=userServ.sendValidateEmail("zh.h@outlook.com", "2333");
//		assertFalse(result);
//	}
	
	@Test
	public void findSomeThins(){
		//userServ.getByNickLike("zonghua", 1);
	}
	
	@Test
	public void testCache(){
		userServ.getByUserId(1);
		userServ.getByUserId(1);
		userServ.getByUserId(1);
		userServ.getByUserId(1);
	}
}
