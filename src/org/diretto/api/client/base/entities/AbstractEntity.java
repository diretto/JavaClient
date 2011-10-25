package org.diretto.api.client.base.entities;

/**
 * This {@code abstract} class provides a skeletal implementation of the
 * {@link Entity} interface, to minimize the effort required to implement this
 * interface.
 * 
 * @author Tobias Schlecht
 * 
 * @param <T> The type of the corresponding {@code EntityID}
 */
public abstract class AbstractEntity<T extends EntityID> implements Entity<T>
{
	protected final T entityID;

	/**
	 * Provides base implementation to construct an {@link Entity}.
	 * 
	 * @param entityID The corresponding {@code EntityID}
	 */
	public AbstractEntity(T entityID)
	{
		this.entityID = entityID;

		if(this.entityID == null)
		{
			throw new NullPointerException();
		}
	}

	@Override
	public T getID()
	{
		return entityID;
	}

	@Override
	public String toString()
	{
		return entityID.toString();
	}
}
