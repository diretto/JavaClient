package org.diretto.api.client.base.exceptions;

import net.sf.ehcache.Cache;

/**
 * A {@code CacheNotActivatedException} is thrown if an attempt is made to use
 * the {@link Cache} but it is deactivated in the configuration.
 * 
 * @author Tobias Schlecht
 */
public class CacheNotActivatedException extends RuntimeException
{
	private static final long serialVersionUID = 564352089114240798L;

	/**
	 * Constructs a {@code CacheNotActivatedException} with no specified detail
	 * message.
	 */
	public CacheNotActivatedException()
	{
		super();
	}

	/**
	 * Constructs a {@code CacheNotActivatedException} with the specified detail
	 * message.
	 * 
	 * @param message The detail message
	 */
	public CacheNotActivatedException(String message)
	{
		super(message);
	}
}
