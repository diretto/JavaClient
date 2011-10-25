package org.diretto.api.client.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * {@code HashUtils} is a noninstantiable utility class and provides methods for
 * generating miscellaneous hashes.
 * 
 * @author Tobias Schlecht
 */
public final class HashUtils
{
	/**
	 * The constructor is {@code private} to suppress the default constructor
	 * for noninstantiability.
	 */
	private HashUtils()
	{
		throw new AssertionError();
	}

	/**
	 * Generates a MD5 hash of the given input {@code String}.
	 * 
	 * @param input The input {@code String}
	 * @return The generated MD5 hash
	 */
	public static String generateMD5Hash(String input)
	{
		try
		{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.reset();
			md5.update(input.getBytes());
			byte[] result = md5.digest();

			StringBuffer hexString = new StringBuffer();

			for(int i = 0; i < result.length; i++)
			{
				String s = Integer.toHexString(0xFF & result[i]);
				hexString.append(((s.length() == 1) ? "0" : "") + s);
			}

			return hexString.toString();
		}
		catch(NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
