package org.diretto.api.client.base.types;

import org.diretto.api.client.base.characteristic.Votable;

/**
 * A {@code VoteType} represents a type how for a {@link Votable} object can be
 * voted.
 * 
 * @author Tobias Schlecht
 */
public enum VoteType
{
	UP("up"), DOWN("down");

	private final String urlParameter;

	/**
	 * Constructs a {@link VoteType}.
	 * 
	 * @param urlParameter The API {@code URL} parameter
	 */
	VoteType(String urlParameter)
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

	/**
	 * Returns the {@link VoteType} of the given API {@code URL} parameter.
	 * 
	 * @param urlParameter The {@code URL} parameter
	 * @return The corresponding {@code VoteType}
	 */
	public static VoteType getValue(String urlParameter)
	{
		if(urlParameter.equals("up"))
		{
			return VoteType.UP;
		}
		else
		{
			return VoteType.DOWN;
		}
	}
}
