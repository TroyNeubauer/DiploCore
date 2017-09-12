package com.troy.diplo.packet;

import com.troy.diplo.game.Profile;

public class LoginReply implements java.io.Serializable {
	private boolean accept;
	private Profile profile;

	public LoginReply(boolean accept, Profile profile) {
		this.accept = accept;
		this.profile = profile;
	}

	public boolean getAccept() {
		return accept;
	}

	public Profile getProfile() {
		return profile;
	}

}
