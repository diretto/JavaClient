package org.diretto.api.client.base.types;

import org.diretto.api.client.main.core.entities.Message;

/**
 * A {@code MessageBoxType} represents the box type of {@link Message}s.
 * 
 * @author Tobias Schlecht
 */
public enum MessageBoxType
{
	/**
	 * This type represents the message box which contains all received
	 * {@link Message}s.
	 */
	INBOX("inbox"),

	/**
	 * This type represents the message box which contains all sent
	 * {@link Message}s.
	 */
	OUTBOX("outbox");

	private final String urlParameter;

	/**
	 * Constructs a {@link MessageBoxType}.
	 * 
	 * @param urlParameter The API {@code URL} parameter
	 */
	MessageBoxType(String urlParameter)
	{
		this.urlParameter = urlParameter;
	}

	/**
	 * Returns the API {@code URL} parameter.
	 * 
	 * @return The API {@code URL} parameter
	 */
	public String getURLParameter()
	{
		return urlParameter;
	}
}
