package helper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.springabc.web.helper.Qiniu;

import java.io.File;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestQinit {

	@Autowired
	private Qiniu qiniu;

	private File file;

	@Test
	public void test() throws IOException {
		//qiniu.upload(file, "2333.text");

	}

//	@Test
//	public void testUpload() throws QiniuException {
//		UploadManager uploadManager = new UploadManager();
//		String token = auth.uploadToken("spring-abc");
//		Response r = uploadManager.put("spring-abc".getBytes(), "yourkey", token);
//	}

}
