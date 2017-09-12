package com.troy.diplo.packet;

public class RegisterReply implements java.io.Serializable {
	
	private RegisterReplyEnum reply;
	
	public RegisterReply(RegisterReplyEnum reply) {
		this.reply = reply;
	}


	public enum RegisterReplyEnum implements java.io.Serializable {
		REGISTER_SUCEED, REGISTER_FAIL_EMAIL_IN_USE, REGISTER_FAIL_USERNAME_IN_USE
	}

	public RegisterReplyEnum getReply() {
		return reply;
	}
}
