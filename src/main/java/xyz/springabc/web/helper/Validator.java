package xyz.springabc.web.helper;

import java.util.regex.Pattern;

import org.hamcrest.Matcher;

public class Validator {
	
	public static boolean isEmail(String email){
		 String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  
		 Pattern regex = Pattern.compile(check);  
		 java.util.regex.Matcher matcher = regex.matcher(email);
		 return matcher.matches();
	}
	
	public static void main(String[] args){
		System.out.println(isEmail("zh.houtlook.com"));
	}
}
