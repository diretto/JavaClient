package org.diretto.api.client.base.exceptions;

/**
 * A {@code NoResultsException} is thrown when the request has returned no
 * results.
 * 
 * @author Tobias Schlecht
 */
public class NoResultsException extends Exception
{
	private static final long serialVersionUID = 7115476653454617325L;

	/**
	 * Constructs a {@code NoResultsException} with no specified detail message.
	 */
	public NoResultsException()
	{
		super();
	}

	/**
	 * Constructs a {@code NoResultsException} with the specified detail
	 * message.
	 * 
	 * @param message The detail message
	 */
	public NoResultsException(String message)
	{
		super(message);
	}
}
