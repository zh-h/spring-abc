package service;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xyz.springabc.domin.User;
import xyz.springabc.repository.UserRepo;
import xyz.springabc.service.FollowServ;
import xyz.springabc.service.UserServ;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestFollow {
	
	@Autowired
	private FollowServ followSer;
	@Autowired
	private UserRepo userRepo;
	@Transactional
	@Test
	public void test() {
		String username=followSer.getFollowingByUser(userRepo.findOne(new Integer(3)), 1).getContent().get(0).getUserByFollowingId().getUsername();
		System.out.println(username);
	}
	
//	@Test
//	public void follow(){
//		User self=userRepo.findOne(new Integer(1));
//		User other=userRepo.findOne(new Integer(3));
//		followSer.follow(self, other);
//	}
//	
	@Test
	public void unfollow(){
		User self=userRepo.findOne(new Integer(1));
		User other=userRepo.findOne(new Integer(3));
		followSer.unfollow(self, other);
	}

}
