package service;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import antlr.collections.List;
import xyz.springabc.domin.Comment;
import xyz.springabc.domin.Topic;
import xyz.springabc.domin.User;
import xyz.springabc.error.ValidateError;
import xyz.springabc.repository.CommentRepo;
import xyz.springabc.repository.TopicRepo;
import xyz.springabc.repository.UserRepo;
import xyz.springabc.service.CommentServ;
import xyz.springabc.service.TopicServ;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestComment {
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private CommentServ c;
	@Autowired
	private TopicRepo t;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private TopicServ topicServ;
	
	@Test
	public void getComments() {
		Topic topic=t.findOne(2013);
		Page<Comment> commentPage=c.getByTopic(topic, 1);
		java.util.List<Comment> comments= commentPage.getContent();
		for(Comment com:comments){
			System.out.println(com.getContent());
		}
	}
	
	@Transactional
	@Test
	public void like(){
		Topic topic=t.findOne(9);
		User user=userRepo.findOne(new Integer(1));
		Comment comment=c.getOne(new Integer(164));
		System.out.println(c.like(comment, user));
	}
	
	/*@Test
	public void batchComment(){
		User user=userRepo.findOne(1);
		Topic topic=t.findOne(2017);
		Comment comment=new Comment();
		
		int size=100;
		for(int i=0;i<100;i++){
			comment.setContent("hhahflhagaghagj"+i);
			comment.setUser(user);
			comment.setTopic(topic);
			comment.setId(i);
			commentRepo.saveAndFlush(comment);
			commentRepo.flush();
		}
	}*/
}
