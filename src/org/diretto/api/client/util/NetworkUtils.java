package org.diretto.api.client.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * {@code NetworkUtils} is a noninstantiable utility class and provides
 * miscellaneous methods in respect of network aspects.
 * 
 * @author Tobias Schlecht
 */
public final class NetworkUtils
{
	/**
	 * The constructor is {@code private} to suppress the default constructor
	 * for noninstantiability.
	 */
	private NetworkUtils()
	{
		throw new AssertionError();
	}

	/**
	 * Returns the global (not the local) IP address of the host by using the
	 * given automation page. <br/><br/>
	 * 
	 * <i>Annotation:</i> For example <a
	 * href="http://www.whatismyip.com/">www.whatismyip.com</a> offers an
	 * automation page.
	 * 
	 * @param automationPage An automation page
	 * @return The global IP address of the host
	 */
	public static String getGlobalIPAddress(URL automationPage)
	{
		InputStream inputStream = null;

		try
		{
			URLConnection urlConnection = automationPage.openConnection();

			int contentLength = Integer.valueOf(urlConnection.getHeaderField("Content-Length"));

			byte[] buffer = new byte[contentLength];

			inputStream = urlConnection.getInputStream();

			inputStream.read(buffer);

			return new String(buffer);
		}
		catch(IOException e)
		{
			e.printStackTrace();

			return null;
		}
		finally
		{
			if(inputStream != null)
			{
				try
				{
					inputStream.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
