package service;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestComent {

	@Test
	public void test() {
		String atString="@121212_ @12sd_12_  @121212_   @121212_ ";
		String regex="@([\u4e00-\u9fa5|A-Za-z0-9|_]{6,9}) ";//匹配长度为2到9个汉字数字或者下划线
		Pattern pattern =Pattern.compile(regex);
		 Matcher matcher = pattern.matcher(atString);
		 while(matcher.find()){
			 String userName=matcher.group(0);
			 System.out.println(userName);
		 }
	}

}
