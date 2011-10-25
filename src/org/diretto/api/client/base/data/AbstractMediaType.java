package org.diretto.api.client.base.data;

/**
 * This {@code abstract} class provides a skeletal implementation of the
 * {@link MediaType} interface, to minimize the effort required to implement
 * this interface.
 * 
 * @author Tobias Schlecht
 */
public abstract class AbstractMediaType implements MediaType
{
	protected final String id;
	protected final MediaMainType mediaMainType;

	/**
	 * Provides base implementation to construct a {@link MediaType}. <br/><br/>
	 * 
	 * <i>Annotation:</i> Examples for <i>ID</i> {@code String}s are
	 * {@code "image/jpeg"} and {@code "video/x-diretto-youtube+url"}.
	 * 
	 * @param id The <i>ID</i> {@code String}
	 * @param mediaMainType The {@code MediaMainType}
	 */
	AbstractMediaType(String id, MediaMainType mediaMainType)
	{
		this.id = id;
		this.mediaMainType = mediaMainType;

		if(this.id == null || this.mediaMainType == null)
		{
			throw new NullPointerException();
		}

		if(this.id.equals(""))
		{
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String getID()
	{
		return id;
	}

	@Override
	public MediaMainType getMediaMainType()
	{
		return mediaMainType;
	}

	@Override
	public boolean equals(Object o)
	{
		return id.equals(o);
	}

	@Override
	public int hashCode()
	{
		return id.hashCode();
	}

	@Override
	public String toString()
	{
		return id;
	}
}
