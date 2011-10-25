package org.diretto.api.client.util;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;

/**
 * {@code FileUtils} is a noninstantiable utility class and is responsible for
 * {@link File} related aspects.
 * 
 * @author Tobias Schlecht
 */
public final class FileUtils
{
	/**
	 * The constructor is {@code private} to suppress the default constructor
	 * for noninstantiability.
	 */
	private FileUtils()
	{
		throw new AssertionError();
	}

	/**
	 * Returns the MIME type of the given file name.
	 * 
	 * @param fileName The file name to be analyzed
	 * @return The MIME type
	 */
	public static String getMIMEType(String fileName)
	{
		FileNameMap fileNameMap = URLConnection.getFileNameMap();

		return fileNameMap.getContentTypeFor(fileName);
	}

	/**
	 * Returns the MIME type of the given {@link File}.
	 * 
	 * @param file The {@code File} to be analyzed
	 * @return The MIME type
	 */
	public static String getMIMEType(File file)
	{
		FileNameMap fileNameMap = URLConnection.getFileNameMap();

		return fileNameMap.getContentTypeFor(file.getName());
	}
}
