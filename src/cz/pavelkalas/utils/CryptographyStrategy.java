package cz.pavelkalas.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Cryptography class for hashing.
 */
public class CryptographyStrategy {
	
	/**
	 * Converts basic string to SHA512 hash.
	 * 
	 * @param text - Text to be hashed
	 * @return Hashed text in SHA512
	 */
	public static String getSHA512FromString(String text) {
	    try {
	        MessageDigest digest = MessageDigest.getInstance("SHA-512");
	        
	        byte[] hashBytes = digest.digest(text.getBytes());
	        
	        StringBuilder hexString = new StringBuilder();
	        for (byte b : hashBytes) {
	            String hex = Integer.toHexString(0xff & b);
	            if (hex.length() == 1) {
	                hexString.append('0');
	            }
	            hexString.append(hex);
	        }
	        
	        return hexString.toString();
	    } catch (NoSuchAlgorithmException e) {
	    	return null;
	    }
	}
}
