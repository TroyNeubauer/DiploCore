package com.troy.diplo.game;

import java.net.*;

public class DiploConstants {

	public static final int PORT = 8344;

	public static final InetAddress ADDRESS = getServerAddress();

	private static final String SERVER_URL = "diplogame.com";
	
	public static final boolean USE_SSL = false;

	public static final int DEFAULT_WIDTH = 1280;
	
	public static final int DEFAULT_HEIGHT = 720;
	
	private DiploConstants() {

	}

	private static InetAddress getServerAddress() {
		try {
			System.out.println(InetAddress.getByName(SERVER_URL).getHostAddress());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		if (false) {
			try {
				return InetAddress.getByName(SERVER_URL);
			} catch (Exception e) {
				throw new RuntimeException("Unable to find diplogame server at " + SERVER_URL, e);
			}
		} else
			return InetAddress.getLoopbackAddress();

	}

}
