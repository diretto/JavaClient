package org.diretto.api.client.base.entities;

/**
 * This {@code abstract} class provides a skeletal implementation of the
 * {@link SubEntity} interface, to minimize the effort required to implement
 * this interface.
 * 
 * @author Tobias Schlecht
 * 
 * @param <T> The type of the corresponding {@code EntityID}
 */
public abstract class AbstractSubEntity<T extends EntityID> extends AbstractEntity<T> implements SubEntity<T>
{
	/**
	 * Provides base implementation to construct a {@link SubEntity}.
	 * 
	 * @param entityID The corresponding {@code EntityID}
	 */
	public AbstractSubEntity(T entityID)
	{
		super(entityID);
	}
}
