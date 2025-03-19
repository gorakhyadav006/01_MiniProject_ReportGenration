package com.fotp.util;

import org.apache.commons.lang3.RandomStringUtils;

public class PwdUtils {

	public static String generateRandomPwd() {		
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@!#$";
		String randomPwd = RandomStringUtils.random(8, characters);
		System.out.println( randomPwd );
		return randomPwd;
	}
}
