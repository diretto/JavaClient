package org.diretto.api.client.base.entities;

/**
 * This interface represents an {@code Entity}.
 * 
 * @author Tobias Schlecht
 * 
 * @param <T> The type of the corresponding {@code EntityID}
 */
public interface Entity<T extends EntityID>
{
	/**
	 * Returns the corresponding {@link EntityID}.
	 * 
	 * @return The corresponding {@code EntityID}
	 */
	T getID();
}
