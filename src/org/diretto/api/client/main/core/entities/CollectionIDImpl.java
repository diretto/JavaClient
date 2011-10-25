package org.diretto.api.client.main.core.entities;

import java.net.URL;

import org.diretto.api.client.base.entities.AbstractEntityID;

/**
 * This class is the implementation class of the {@link CollectionID} interface.
 * 
 * @author Tobias Schlecht
 */
final class CollectionIDImpl extends AbstractEntityID implements CollectionID
{
	/**
	 * Constructs an object of the {@link CollectionID} interface.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 */
	CollectionIDImpl(URL uniqueResourceURL)
	{
		super(uniqueResourceURL);
	}
}
