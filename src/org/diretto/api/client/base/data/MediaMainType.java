package org.diretto.api.client.base.data;

/**
 * The {@code MediaMainType} class represents the main type of a
 * {@link MediaType}. <br/><br/>
 * 
 * <i>Annotation:</i> It is an immutable class.
 * 
 * @author Tobias Schlecht
 */
public final class MediaMainType
{
	public final String TYPE;

	/**
	 * Constructs a {@link MediaMainType}. <br/><br/>
	 * 
	 * <i>Annotation:</i> An example for the {@code String} representation of a
	 * {@code MediaMainType} is {@code "image"}.
	 * 
	 * @param type The {@code String} representation of the main type
	 */
	MediaMainType(String type)
	{
		TYPE = type;

		if(TYPE == null)
		{
			throw new NullPointerException();
		}

		if(TYPE.equals(""))
		{
			throw new IllegalArgumentException();
		}
	}

	@Override
	public boolean equals(Object o)
	{
		if(o != null && o instanceof MediaMainType)
		{
			return TYPE.equals(((MediaMainType) o).TYPE);
		}

		return false;
	}

	@Override
	public int hashCode()
	{
		return TYPE.hashCode();
	}

	@Override
	public String toString()
	{
		return TYPE;
	}
}
