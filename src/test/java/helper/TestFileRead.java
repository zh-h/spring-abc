package helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/applicationContext.xml")
public class TestFileRead {
	@Test
	public void testRead(){
		String code="22333";
		String content="";
		try {
			Resource resource=new ClassPathResource("mailTemplet/code.html");//读取校验码模板
			File file=resource.getFile();
			InputStream urlStream = new FileInputStream(file);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(urlStream, "UTF-8"));
			String temp = ""; //每一行的缓存
			while ((temp = bufferedReader.readLine()) != null) {
				content += temp;
			}
			bufferedReader.close();
			System.out.println(content);
			content=String.format(content, code);
			System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
