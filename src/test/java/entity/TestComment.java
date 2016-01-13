package entity;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xyz.springabc.domin.User;
import xyz.springabc.repository.CommentRepo;
import xyz.springabc.repository.UserRepo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestComment {
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private UserRepo userRepo;
	
//	@Test
//	public void test() {
//		me.zonghua.sync.entity.Comment comment=commentRepo.getLastCommentByTopicId(new Integer(1));
//		assertNotNull(comment);
//	}
//	
//	@Test
//	public void findbyUser(){
//		User user=userRepo.findOne(new Integer(1));
//		PageRequest p=new PageRequest(0, 20);
//		Date size=commentRepo.findByUserOrderByCreateAtDesc(user, p).getContent().get(1).getCreateAt();
//		System.out.println(size);
//	}
	
	@Test
	public void like(){
		String title="测试一下拦截器测试一下拦截器测试一下拦截器测试一下拦截器测试一下拦截器测试一下拦截器";
		PageRequest p=new PageRequest(0, 20);
		int size=commentRepo.findByTopicTitleLikeOrderByCreateAtDesc(title, p).getContent().size();
		System.out.println(size);
	}

}
