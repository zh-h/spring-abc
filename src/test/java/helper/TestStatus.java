package helper;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import xyz.springabc.web.helper.Status;

public class TestStatus {

//	@Test
//	public void test() throws IOException {
//		InputStream s=Runtime.getRuntime().exec("java -V").getInputStream();
//		InputStreamReader reader=new InputStreamReader(s);
//		BufferedReader br=new BufferedReader(new InputStreamReader(s));
//		String content="";
//		String temp = ""; //每一行的缓存
//		while ((temp = br.readLine()) != null) {
//			content += temp+"\n";
//		}
//		System.out.println(content);
//	}
	
	@Test 
	public void status(){
		System.out.println(new Status().getRamFree());
	}
	
	@Test
	public void StringFormat(){
		double sum=22222.2324234242424;
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println(df.format(sum));
	}
	
	@Test
	public void getFilePath(){
		Resource resource=new ClassPathResource("../");
		System.out.println(resource.isOpen());
	}

}
