package com.troy.diplo.game;

import com.troyberry.util.serialization.*;

public class Account implements TroySerializable {

	private static interface AccountLookup {
		public Account lookup(long id);
	};

	private static AccountLookup lookup;

	public static void setLookup(AccountLookup lookup) {
		Account.lookup = lookup;
	}

	public static Account lookup(long id) {
		return lookup.lookup(id);
	}

	public int id;
	public String username, email;

	public Profile profile;

	public Account() {
	}

	public Account(int id, String username, String email, Profile profile) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.profile = profile;
	}

	public Account(int id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.profile = new Profile(this);
	}

	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public Profile getProfile() {
		return profile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", email=" + email + ", profile=" + profile + "]";
	}

	@Override
	public void read(TroyBuffer buffer) {
		this.id = buffer.readInt();
		this.username = buffer.readString();
		this.email = buffer.readString();
		this.profile = TroyBuffer.createInstance(Profile.class);
		this.profile.readAndSet(buffer, this);

	}

	@Override
	public void write(TroyBuffer buffer) {
		buffer.writeInt(id);
		buffer.writeString(username);
		buffer.writeString(email);
		profile.write(buffer);
	}

}
