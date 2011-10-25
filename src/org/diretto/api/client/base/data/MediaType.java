package org.diretto.api.client.base.data;

/**
 * This interface represents a {@code MediaType}.
 * 
 * @author Tobias Schlecht
 */
public interface MediaType
{
	/**
	 * Returns the <i>ID</i> {@code String}. <br/><br/>
	 * 
	 * <i>Annotation:</i> Examples for <i>ID</i> {@code String}s are
	 * {@code "image/jpeg"} and {@code "video/x-diretto-youtube+url"}.
	 * 
	 * @return The <i>ID</i> {@code String}
	 */
	String getID();

	/**
	 * Returns the {@link MediaMainType}.
	 * 
	 * @return The {@code MediaMainType}
	 */
	MediaMainType getMediaMainType();

	/**
	 * Returns the <i>ID</i> {@code String} of this {@link MediaType}.
	 * <br/><br/>
	 * 
	 * <i>Annotation:</i> Examples for <i>ID</i> {@code String}s are
	 * {@code "image/jpeg"} and {@code "video/x-diretto-youtube+url"}.
	 * 
	 * @return The <i>ID</i> {@code String}
	 */
	@Override
	String toString();
}
