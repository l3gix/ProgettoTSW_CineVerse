package it.unisa.cineverse.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class PasswordUtil 
{
	private PasswordUtil() {}
	
	public static String toDigest(String password)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] digestBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
			
			StringBuilder sb = new StringBuilder();
			for(byte b : digestBytes) 
			{
				sb.append(String.format("%02x", b));
			}
			
			return sb.toString();
		}catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Algortmo SHA-512 non disponibile");
		}
	}
}
