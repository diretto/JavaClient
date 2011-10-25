package org.diretto.api.client.base.exceptions;

import org.diretto.api.client.base.entities.Entity;

/**
 * A {@code TooManyEntitiesRequestedException} is thrown when too many
 * {@link Entity}s are requested with a single method invocation.
 * 
 * @author Tobias Schlecht
 */
public class TooManyEntitiesRequestedException extends RuntimeException
{
	private static final long serialVersionUID = -3360801294620615513L;

	/**
	 * Constructs a {@code TooManyEntitiesRequestedException} with no specified
	 * detail message.
	 */
	public TooManyEntitiesRequestedException()
	{
		super();
	}

	/**
	 * Constructs a {@code TooManyEntitiesRequestedException} with the specified
	 * detail message.
	 * 
	 * @param message The detail message
	 */
	public TooManyEntitiesRequestedException(String message)
	{
		super(message);
	}
}
