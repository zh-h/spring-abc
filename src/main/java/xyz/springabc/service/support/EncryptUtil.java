package xyz.springabc.service.support;

import org.apache.commons.codec.digest.DigestUtils;

public class EncryptUtil {
	
	public static String encryptUsernameAndPassword(String username,String password){
		return DigestUtils.md5Hex(password+password);
	}
}
