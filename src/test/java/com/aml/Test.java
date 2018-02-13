package com.aml;

import com.aml.common.util.CodeUtils;

public class Test {

	public static void main(String[] args) {
		String pwd = "msqGLS_4dm1n999";
		String encrypt = CodeUtils.Encrypt(pwd , 6, 9);
		System.out.println(encrypt);
	}
}
