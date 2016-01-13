package service;

import static org.junit.Assert.*;

import org.junit.Test;

import xyz.springabc.service.support.EncryptUtil;

public class TestEncrypt {

	@Test
	public void test() {
		String username="zonghua";
		String password="zonghua";
		String result=EncryptUtil.encryptUsernameAndPassword(username, password);
		System.out.println(result);
	}

}
