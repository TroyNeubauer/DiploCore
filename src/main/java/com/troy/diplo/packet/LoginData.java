package com.troy.diplo.packet;

import java.util.Arrays;

public class LoginData implements java.io.Serializable {
	private char[] username, password;

	public LoginData(char[] username, char[] password) {
		this.username = username;
		this.password = password;
	}

	public char[] getUsername() {
		return username;
	}

	public char[] getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "LoginData [username=" + Arrays.toString(username) + ", password=" + Arrays.toString(password) + "]";
	}
	
	

}
