package com.example.TM.Util;

import java.util.Base64;

public class Encrypt {

	public static String encode(String txt) {
		Base64.Encoder encoder = Base64.getEncoder();
		byte[] encoded= encoder.encode(txt.getBytes());
		return new String(encoded);
	}
	
	public String decode(String encryptdTxt) {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] decoded =decoder.decode(encryptdTxt.getBytes());
		return new String(decoded);
	}
}
