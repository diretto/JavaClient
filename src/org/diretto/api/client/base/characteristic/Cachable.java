package org.diretto.api.client.base.characteristic;

import org.diretto.api.client.base.types.LoadType;

/**
 * A class implements the {@code Cachable} interface to indicate that the class
 * has got methods for providing the necessary information to be able to cache
 * an object of the implementing class.
 * 
 * @author Tobias Schlecht
 */
public interface Cachable
{
	/**
	 * Returns whether the {@code Cache} object is completely loaded or not.
	 * 
	 * @return {@code true} if the complete {@code Cache} object and all of its
	 *         sub elements have already been loaded; otherwise {@code false}
	 */
	boolean isCompletelyLoaded();

	/**
	 * Returns the {@link LoadType} of the {@code Cache} object.
	 * 
	 * @return The {@code LoadType}
	 */
	LoadType getLoadType();
}
