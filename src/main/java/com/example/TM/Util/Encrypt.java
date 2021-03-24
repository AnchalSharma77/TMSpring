package com.example.TM.Util;

import java.util.Base64;

public class Encrypt {

	public static String encode(String pass) {
		Base64.Encoder encoder = Base64.getEncoder();
		byte[] encoded= encoder.encode(pass.getBytes());
		return new String(encoded);
	}
}
