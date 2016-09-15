package entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.springabc.domin.User;
import xyz.springabc.repository.UserRepo;

import static org.junit.Assert.assertNotNull;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class UserTest {
	@Autowired
	private UserRepo userRepo;
	
//	@Test
//	public void test() {
//		List<User> zonghua=userRepo.findByUsername("zonghua");
//		System.out.println(zonghua.size());
//		Assert.assertNotNull(zonghua);
//	}
	
	@Test
	public void pluse(){
		User user=userRepo.getLastCommentUserByTopicId(new Integer(1));
		assertNotNull(user);
	}

}
