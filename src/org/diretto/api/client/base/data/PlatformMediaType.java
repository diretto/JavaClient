package org.diretto.api.client.base.data;

/**
 * This class represents a {@code PlatformMediaType}. <br/><br/>
 * 
 * <i>Annotation:</i> It is an immutable class.
 * 
 * @author Tobias Schlecht
 */
public final class PlatformMediaType extends AbstractMediaType
{
	private final String extension;
	private final long maxSize;

	/**
	 * Constructs a {@link PlatformMediaType}. <br/><br/>
	 * 
	 * <i>Annotation:</i> An example for an <i>ID</i> {@code String} is
	 * {@code "image/jpeg"}.
	 * 
	 * @param id The <i>ID</i> {@code String}
	 * @param mediaMainType The {@code MediaMainType}
	 * @param extension The extension
	 * @param maxSize The maximum size in {@code Bytes}
	 */
	PlatformMediaType(String id, MediaMainType mediaMainType, String extension, long maxSize)
	{
		super(id, mediaMainType);

		this.extension = extension;
		this.maxSize = maxSize;

		if(this.extension == null)
		{
			throw new NullPointerException();
		}

		if(this.extension.equals("") || this.maxSize <= 0)
		{
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns the extension.
	 * 
	 * @return The extension
	 */
	public String getExtension()
	{
		return extension;
	}

	/**
	 * Returns the maximum size in {@code Bytes}.
	 * 
	 * @return The maximum size in {@code Bytes}
	 */
	public long getMaxSize()
	{
		return maxSize;
	}
}
