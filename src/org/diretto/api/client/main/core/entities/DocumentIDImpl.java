package org.diretto.api.client.main.core.entities;

import java.net.URL;

import org.diretto.api.client.base.entities.AbstractEntityID;

/**
 * This class is the implementation class of the {@link DocumentID} interface.
 * 
 * @author Tobias Schlecht
 */
final class DocumentIDImpl extends AbstractEntityID implements DocumentID
{
	/**
	 * Constructs an object of the {@link DocumentID} interface.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 */
	DocumentIDImpl(URL uniqueResourceURL)
	{
		super(uniqueResourceURL);
	}

	@Override
	public AttachmentID getInitialAttachmentID()
	{
		String attachmentID = uniqueResourceURL.toExternalForm() + "/attachment/" + resourceIdentifier;

		return CoreServiceEntityIDFactory.getAttachmentIDInstance(attachmentID, this, this);
	}
}
