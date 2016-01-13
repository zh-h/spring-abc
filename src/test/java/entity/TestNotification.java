package entity;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xyz.springabc.domin.User;
import xyz.springabc.repository.NotificationRepo;
import xyz.springabc.repository.UserRepo;

import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestNotification {

	@Autowired
	private NotificationRepo notificationRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Test
	public void test() {
		User user=userRepo.findOneByUsername("zonghua");
		long size=notificationRepo.countByUserAndStatus(user, 0);
		System.out.println(size);
	}

}
