package entity;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xyz.springabc.domin.Topic;
import xyz.springabc.repository.TopicRepo;
import xyz.springabc.service.TopicServ;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestTopic {
	@Autowired
	private TopicServ topicServ;
	@Autowired 
	private TopicRepo topicRepo;

//	@Test
//	public void generation(){
//		for (int i = 0; i < 1000; i++) {
//			Topic topic=new Topic();
//			topic.setTitle(""+new Random(1000000).nextLong());
//			topicRepo.save(topic);
//		}
//	}
	
	@Test
	public void testLike(){
		String username="哈";
		String title="耗时8ms时8ms耗时8ms耗时8ms";
		Pageable pageRequest=new PageRequest(0, 10);
		List<Topic> topics=topicRepo.findByTitleLikeAndStatus(title, 0, pageRequest).getContent();
		int size=topics.size();
		System.err.println(size);
	}
	
	@Test
	public void testFocus(){
		Pageable pageRequest=new PageRequest(0, 10);
		List<Topic> topics=topicRepo.findByFocusedOrderByDefault(1, pageRequest).getContent();
		for(Topic t:topics){
			System.out.println(t.getTitle());
		}
	}
}
