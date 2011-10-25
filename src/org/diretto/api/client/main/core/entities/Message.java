package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.entities.Entity;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This interface represents a {@code Message}.
 * 
 * @author Tobias Schlecht
 */
public interface Message extends Entity<MessageID>
{
	/**
	 * Returns the creation {@link DateTime}.
	 * 
	 * @return The creation {@code DateTime}
	 */
	DateTime getCreationTime();

	/**
	 * Returns the {@link UserID} of the {@link Message} sender.
	 * 
	 * @return The {@code UserID} of the {@code Message} sender
	 */
	UserID getSender();

	/**
	 * Returns the {@link UserID} of the {@link Message} receiver.
	 * 
	 * @return The {@code UserID} of the {@code Message} receiver
	 */
	UserID getReceiver();

	/**
	 * Returns the title of the {@link Message}.
	 * 
	 * @return The title of the {@code Message}
	 */
	String getTitle();

	/**
	 * Returns the content of the {@link Message}.
	 * 
	 * @return The content of the {@code Message}
	 */
	String getContent();
}
