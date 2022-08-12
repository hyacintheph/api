package com.campost.campost;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.codec.Hex;

public class HmacSHA256 {
	 static public  byte[] calcHmacSha256(byte[] secretKey, byte[] message) {
	    byte[] hmacSha256 = null;
	    try {
	      Mac mac = Mac.getInstance("HmacSHA256");
	      SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "HmacSHA256");
	      mac.init(secretKeySpec);
	      hmacSha256 = mac.doFinal(message);
	    } catch (Exception e) {
	      throw new RuntimeException("Failed to calculate hmac-sha256", e);
	    }
	    return hmacSha256;
	  }
	}
