package org.diretto.api.client.base.exceptions;

import org.diretto.api.client.service.Service;

/**
 * A {@code NoSuchServiceException} is thrown when a particular {@link Service}
 * is requested but is not available in the specified API.
 * 
 * @author Tobias Schlecht
 */
public class NoSuchServiceException extends RuntimeException
{
	private static final long serialVersionUID = -6501927390422658027L;

	/**
	 * Constructs a {@code NoSuchServiceException} with no specified detail
	 * message.
	 */
	public NoSuchServiceException()
	{
		super();
	}

	/**
	 * Constructs a {@code NoSuchServiceException} with the specified detail
	 * message.
	 * 
	 * @param message The detail message
	 */
	public NoSuchServiceException(String message)
	{
		super(message);
	}
}
