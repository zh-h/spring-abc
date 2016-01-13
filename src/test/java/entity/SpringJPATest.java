package entity;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.validation.constraints.AssertTrue;

import org.apache.lucene.analysis.cn.smart.hhmm.HHMMSegmenter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xyz.springabc.domin.Topic;
import xyz.springabc.domin.User;
import xyz.springabc.repository.UserRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/applicationContext.xml" })
public class SpringJPATest {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	@Test
	public void save(){
		User user=new User();
		user.setNick("hhhh");
		user.setCreateAt(new Date());
		user.setAvatar("2323");
		userRepo.save(user);
	}
	
	@Test
	public void load(){
		User user=userRepo.findOne(10);
		Assert.assertNotNull(user);
	}
	
	@Test
	
	public void delete(){
		if(userRepo.exists(2)){
			userRepo.delete(2);
		}
		
	}
	
	@Test
	public void update(){
		EntityManager e=entityManagerFactory.createEntityManager();
		e.getTransaction().begin();
		User user = (User)e.find(User.class, new Integer(1));
		user.setAvatar("6666");
		user.setNick("~~~~~~~~~~~~~~~~~~~~~`");
		e.persist(user);
		e.getTransaction().commit();
	}
	
	@Test
	public void updateTopic(){
		EntityManager e=entityManagerFactory.createEntityManager();
		e.getTransaction().begin();
		Topic t=e.find(Topic.class, new Integer(1));
		User user = (User)e.find(User.class, new Integer(1));
		user.setAvatar("yes");
		e.persist(t);
		e.getTransaction().commit();
	}
}
