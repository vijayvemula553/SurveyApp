package org.vijay.survey.utils;

import java.util.Base64;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class EncryptedDataSource extends DriverManagerDataSource {

	@Override
	public String getPassword() {
		String password = super.getPassword();
		return decode(password);
	}

	/***
	 * Decode Password
	 */
	private String decode(String decode) {
		decode = new String(Base64.getDecoder().decode(decode));
		return decode;
	}

}