package org.diretto.api.client.base.entities;

import java.net.URL;

/**
 * This interface is for the identification of an {@link Entity}.
 * 
 * @author Tobias Schlecht
 */
public interface EntityID
{
	/**
	 * Returns the resource identifier. <br/><br/>
	 * 
	 * <i>Annotation:</i> Note that the resource identifier is only unique in
	 * respect of the same resource kind. For instance an {@code EntityA} could
	 * have the same resource identifier like an {@code EntityB}. Hence either
	 * the whole {@code EntityID} object or the unique resource {@code URL}
	 * should be normally used for unique identification. An example for a
	 * resource identifier is {@code 2671f383-a28c-44d5-b86b-f7f04bb0a015}
	 * 
	 * @return The resource identifier
	 */
	public String getResourceIdentifier();

	/**
	 * Returns the unique resource {@code URL}. <br/><br/>
	 * 
	 * <i>Annotation:</i> An example for the {@code String} representation of an
	 * unique resource {@code URL} is
	 * {@code http://api.diretto.org/v2/document/2671f383-a28c-44d5-b86b-f7f04bb0a015}
	 * 
	 * @return The unique resource {@code URL}
	 */
	public URL getUniqueResourceURL();

	/**
	 * Returns the {@code String} representation of the unique resource
	 * {@code URL}. <br/><br/>
	 * 
	 * <i>Annotation:</i> An example for the {@code String} representation of an
	 * unique resource {@code URL} is
	 * {@code http://api.diretto.org/v2/document/2671f383-a28c-44d5-b86b-f7f04bb0a015}
	 * 
	 * @return The {@code String} representation of the unique resource
	 *         {@code URL}
	 */
	@Override
	public String toString();
}
