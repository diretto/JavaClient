package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.entities.EntityID;

/**
 * This interface is for the identification of a {@link Document}.
 * 
 * @author Tobias Schlecht
 */
public interface DocumentID extends EntityID
{
	/**
	 * Returns the {@link AttachmentID} of the corresponding initial
	 * {@link Attachment}.
	 * 
	 * @return The {@code AttachmentID} of the corresponding initial
	 *         {@code Attachment}
	 */
	AttachmentID getInitialAttachmentID();
}
