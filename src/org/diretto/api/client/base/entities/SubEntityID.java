package org.diretto.api.client.base.entities;

/**
 * This interface is for the identification of a {@link SubEntity}.
 * 
 * @author Tobias Schlecht
 * 
 * @param <R> The type of the corresponding root {@code EntityID}
 * @param <P> The type of the corresponding parent {@code EntityID}
 */
public interface SubEntityID<R extends EntityID, P extends EntityID> extends EntityID
{
	/**
	 * Returns the {@link EntityID} of the root {@link Entity}.
	 * 
	 * @return The {@code EntityID} of the root {@code Entity}
	 */
	R getRootID();

	/**
	 * Returns the {@link EntityID} of the parent {@link Entity}.
	 * 
	 * @return The {@code EntityID} of the parent {@code Entity}
	 */
	P getParentID();
}
