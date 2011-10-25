package org.diretto.api.client.base.data;

/**
 * This interface represents a {@code Builder} for objects of the specified
 * type.
 * 
 * @author Tobias Schlecht
 * 
 * @param <T> The type of the corresponding objects
 */
public interface Builder<T>
{
	/**
	 * Builds and returns an instance of the specified type.
	 * 
	 * @return The built object
	 */
	public T build();
}
