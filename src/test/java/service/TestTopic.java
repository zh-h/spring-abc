package service;

import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javafx.print.PageRange;
import xyz.springabc.domin.Comment;
import xyz.springabc.domin.Topic;
import xyz.springabc.domin.User;
import xyz.springabc.repository.TopicRepo;
import xyz.springabc.repository.UserRepo;
import xyz.springabc.service.TopicServ;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestTopic {
	@Autowired
	private TopicServ t;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private TopicRepo topicRepo;
	
	@Test
	public void test() {
		Topic topic=t.getOne(2);
		
	}
	
	@Test
	public void get(){
		User user=userRepo.findOne(new Integer(1));
		Topic topic=topicRepo.findOne(new Integer(1));
		String username=topic.getUser().getUsername();
		assertNotNull(username);
		System.out.println(username);
	}
	
	
	
	@Test
	public void getFollow(){
		User user=userRepo.findOne(new Integer(1));
		t.getByUser(user, 1, 10);
	}
	
	@Test
	public void getBySeciotnName(){
		List<Topic> topics=topicRepo.findByNodeSectionAndStatusOrderByLastCommentAtDesc("test", 0, new PageRequest(0, 30)).getContent();
		for(Topic t:topics){
			System.out.println(t.getContent());
		}
		List<Topic> topics2=t.getBySectionDefault("test", 1).getContent();
		for(Topic t:topics2){
			System.out.println(t.getContent());
		}
	}
	

}
