package com.jd.em.base.utils;

import java.util.UUID;

/**
 * @author KING
 *
 * <p>Description: 32uuid生成器</p>
 *
 * 2019年1月21日
 *
 */
public class UuidUtil {

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	
	
	public static int getUUID() {
		int clockSequence = UUID.randomUUID().clockSequence();
		return 0;
		
	}
	
	
	public static void main(String[] args) {
		System.out.println(get32UUID());
	}

}

