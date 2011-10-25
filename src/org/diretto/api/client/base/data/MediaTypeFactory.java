package org.diretto.api.client.base.data;

/**
 * The {@code MediaTypeFactory} is a noninstantiable factory class and is
 * responsible for creating {@link MediaType} instances.
 * 
 * @author Tobias Schlecht
 */
public class MediaTypeFactory
{
	/**
	 * The constructor is {@code private} to suppress the default constructor
	 * for noninstantiability.
	 */
	private MediaTypeFactory()
	{
		throw new AssertionError();
	}

	/**
	 * Returns an instance of the requested {@link ExternalMediaType}.
	 * <br/><br/>
	 * 
	 * <i>Annotation:</i> An example for an <i>ID</i> {@code String} is
	 * {@code "video/x-diretto-youtube+url"}.
	 * 
	 * @param id The <i>ID</i> {@code String}
	 * @param mediaMainType The {@code MediaMainType}
	 * @param name The name
	 * @return An instance of the requested {@code ExternalMediaType}
	 */
	public static ExternalMediaType getExternalMediaTypeInstance(String id, MediaMainType mediaMainType, String name)
	{
		return new ExternalMediaType(id, mediaMainType, name);
	}

	/**
	 * Returns an instance of the requested {@link PlatformMediaType}.
	 * <br/><br/>
	 * 
	 * <i>Annotation:</i> An example for an <i>ID</i> {@code String} is
	 * {@code "image/jpeg"}.
	 * 
	 * @param id The <i>ID</i> {@code String}
	 * @param mediaMainType The {@code MediaMainType}
	 * @param extension The extension
	 * @param maxSize The maximum size in {@code Bytes}
	 * @return An instance of the requested {@code PlatformMediaType}
	 */
	public static PlatformMediaType getPlatformMediaTypeInstance(String id, MediaMainType mediaMainType, String extension, long maxSize)
	{
		return new PlatformMediaType(id, mediaMainType, extension, maxSize);
	}

	/**
	 * Returns an instance of the requested {@link MediaMainType}. <br/><br/>
	 * 
	 * <i>Annotation:</i> An example for the {@code String} representation of a
	 * {@code MediaMainType} is {@code "image"}.
	 * 
	 * @param type The {@code String} representation of the main type
	 * @return An instance of the requested {@code MediaMainType}
	 */
	public static MediaMainType getMediaMainType(String type)
	{
		return new MediaMainType(type);
	}
}
