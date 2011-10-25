package org.diretto.api.client.base.entities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * This {@code abstract} class provides a skeletal implementation of the
 * {@link SubEntityID} interface, to minimize the effort required to implement
 * this interface.
 * 
 * @author Tobias Schlecht
 * 
 * @param <R> The type of the corresponding root {@code EntityID}
 * @param <P> The type of the corresponding parent {@code EntityID}
 */
public abstract class AbstractSubEntityID<R extends EntityID, P extends EntityID> extends AbstractEntityID implements SubEntityID<R, P>
{
	protected final R rootEntityID;
	protected final P parentEntityID;

	/**
	 * Provides base implementation to construct a {@link SubEntityID}.
	 * 
	 * @param uniqueResourceURL The corresponding unique resource {@code URL}
	 * @param rootEntityID The {@code EntityID} of the root {@code Entity}
	 * @param parentEntityID The {@code EntityID} of the parent {@code Entity}
	 */
	public AbstractSubEntityID(URL uniqueResourceURL, R rootEntityID, P parentEntityID)
	{
		super(uniqueResourceURL);

		this.rootEntityID = rootEntityID;
		this.parentEntityID = parentEntityID;

		if(this.rootEntityID == null || this.parentEntityID == null)
		{
			throw new NullPointerException();
		}
	}

	/**
	 * Provides base implementation to construct a {@link SubEntityID}.
	 * 
	 * @param uniqueResourceURL The {@code String} representation of the
	 *        corresponding unique resource {@code URL}
	 * @param rootEntityID The {@code EntityID} of the root {@code Entity}
	 * @param parentEntityID The {@code EntityID} of the parent {@code Entity}
	 * @throws MalformedURLException
	 */
	public AbstractSubEntityID(String uniqueResourceURL, R rootEntityID, P parentEntityID) throws MalformedURLException
	{
		this(new URL(uniqueResourceURL), rootEntityID, parentEntityID);
	}

	@Override
	public R getRootID()
	{
		return rootEntityID;
	}

	@Override
	public P getParentID()
	{
		return parentEntityID;
	}
}
