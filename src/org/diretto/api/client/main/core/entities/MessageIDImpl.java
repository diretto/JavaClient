package org.diretto.api.client.main.core.entities;

import java.net.URL;

import org.diretto.api.client.base.entities.AbstractEntityID;

/**
 * This class is the implementation class of the {@link MessageID} interface.
 * 
 * @author Tobias Schlecht
 */
final class MessageIDImpl extends AbstractEntityID implements MessageID
{
	/**
	 * Constructs an object of the {@link MessageID} interface.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 */
	MessageIDImpl(URL uniqueResourceURL)
	{
		super(uniqueResourceURL);
	}
}
