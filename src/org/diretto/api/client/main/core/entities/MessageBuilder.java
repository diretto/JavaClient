package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.data.Builder;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This class implements the {@link Builder} interface and serves for creating
 * {@code Builder} objects for {@link Message}s.
 * 
 * @author Tobias Schlecht
 */
public final class MessageBuilder implements Builder<Message>
{
	private final MessageID messageID;

	private DateTime creationTime = null;
	private UserID sender = null;
	private UserID receiver = null;
	private String title = null;
	private String content = null;

	/**
	 * Constructs a {@link MessageBuilder} object.
	 * 
	 * @param messageID The {@code MessageID}
	 */
	public MessageBuilder(MessageID messageID)
	{
		this.messageID = messageID;
	}

	/**
	 * Sets the creation {@link DateTime}.
	 * 
	 * @param creationTime The creation {@code DateTime}
	 * @return The {@code MessageBuilder} object
	 */
	public MessageBuilder creationTime(DateTime creationTime)
	{
		this.creationTime = creationTime;
		return this;
	}

	/**
	 * Sets the {@link UserID} of the sender.
	 * 
	 * @param sender The {@code UserID} of the sender
	 * @return The {@code MessageBuilder} object
	 */
	public MessageBuilder sender(UserID sender)
	{
		this.sender = sender;
		return this;
	}

	/**
	 * Sets the {@link UserID} of the receiver.
	 * 
	 * @param receiver The {@code UserID} of the receiver
	 * @return The {@code MessageBuilder} object
	 */
	public MessageBuilder receiver(UserID receiver)
	{
		this.receiver = receiver;
		return this;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title The title
	 * @return The {@code MessageBuilder} object
	 */
	public MessageBuilder title(String title)
	{
		this.title = title;
		return this;
	}

	/**
	 * Sets the content.
	 * 
	 * @param content The content
	 * @return The {@code MessageBuilder} object
	 */
	public MessageBuilder content(String content)
	{
		this.content = content;
		return this;
	}

	/**
	 * Returns the {@link MessageID}.
	 * 
	 * @return The {@code MessageID}
	 */
	public MessageID getMessageID()
	{
		return messageID;
	}

	/**
	 * Returns the creation {@link DateTime}.
	 * 
	 * @return The creation {@code DateTime}
	 */
	public DateTime getCreationTime()
	{
		return creationTime;
	}

	/**
	 * Returns the {@link UserID} of the sender.
	 * 
	 * @return The {@code UserID} of the sender
	 */
	public UserID getSender()
	{
		return sender;
	}

	/**
	 * Returns the {@link UserID} of the receiver.
	 * 
	 * @return The {@code UserID} of the receiver
	 */
	public UserID getReceiver()
	{
		return receiver;
	}

	/**
	 * Returns the title.
	 * 
	 * @return The title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Returns the content.
	 * 
	 * @return The content
	 */
	public String getContent()
	{
		return content;
	}

	@Override
	public Message build()
	{
		return new MessageImpl(this);
	}
}
