package org.diretto.api.client.main.core.entities;

import java.net.URL;

import org.diretto.api.client.base.entities.AbstractSubEntityID;
import org.diretto.api.client.base.entities.EntityID;

/**
 * This class is the implementation class of the {@link LinkID} interface.
 * 
 * @author Tobias Schlecht
 */
final class LinkIDImpl extends AbstractSubEntityID<EntityID, EntityID> implements LinkID
{
	/**
	 * Constructs an object of the {@link LinkID} interface.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @param rootEntityID The {@code EntityID} of the root {@code Entity}
	 * @param parentEntityID The {@code EntityID} of the parent {@code Entity}
	 */
	LinkIDImpl(URL uniqueResourceURL, EntityID rootEntityID, EntityID parentEntityID)
	{
		super(uniqueResourceURL, rootEntityID, parentEntityID);
	}
}
