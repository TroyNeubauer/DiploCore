package com.troy.diplo.packet;

import java.util.Arrays;

public class RegisterData implements java.io.Serializable {
	private char[] username, password, email;

	public RegisterData(char[] username, char[] password, char[] email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public char[] getUsername() {
		return username;
	}

	public char[] getPassword() {
		return password;
	}

	public char[] getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "RegisterData [username=" + Arrays.toString(username) + ", password=" + Arrays.toString(password) + ", email="
				+ Arrays.toString(email) + "]";
	}
	
	
	
	
}
