package org.diretto.api.client.base.data;

import org.diretto.api.client.base.entities.Entity;
import org.diretto.api.client.base.entities.EntityID;

/**
 * A {@code ResultSet} is a special data structure that maps {@link EntityID}s
 * to {@link Entity}s. Furthermore it simplifies and encapsulates the requests
 * of the remote data. This is very helpful, because therefore in most cases
 * many method invocations can be saved. <br/><br/>
 * 
 * <i>Annotation:</i> This interface extends the {@link Iterable} interface and
 * so the {@code Entity}s can be the targets of the {@code foreach} statement.
 * 
 * @author Tobias Schlecht
 * 
 * @param <I> The type of the corresponding {@code EntityID}
 * @param <E> The type of the corresponding {@code Entity}
 */
public interface ResultSet<I extends EntityID, E extends Entity<I>> extends Iterable<E>
{
	/**
	 * Checks if this {@link ResultSet} is empty.
	 * 
	 * @return {@code true} if this {@code ResultSet} contains no mappings;
	 *         {@code false} otherwise
	 */
	public boolean isEmpty();

	/**
	 * Returns the {@link Entity} to which the specified {@link EntityID} is
	 * mapped, or {@code null} if this {@link ResultSet} contains no mapping for
	 * the {@code EntityID}.
	 * 
	 * @param entityID The {@code EntityID} whose associated {@code Entity} is
	 *        to be returned
	 * @return The {@code Entity} to which the specified {@code EntityID} is
	 *         mapped
	 */
	public E get(I entityID);
}
