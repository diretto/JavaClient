package org.diretto.api.client.main.core.entities;

import java.net.URL;

import org.diretto.api.client.base.entities.AbstractEntityID;
import org.diretto.api.client.base.entities.EntityID;

/**
 * This class is an implementation class of the {@link TagID} interface and
 * represents an <i>anonymous</i> {@code TagID}.
 * 
 * @author Tobias Schlecht
 */
final class AnonymousTagIDImpl extends AbstractEntityID implements TagID
{
	/**
	 * Constructs an <i>anonymous</i> object of the {@link TagID} interface.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 */
	AnonymousTagIDImpl(URL uniqueResourceURL)
	{
		super(uniqueResourceURL);
	}

	@Override
	public EntityID getRootID()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public EntityID getParentID()
	{
		throw new UnsupportedOperationException();
	}
}
