package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.entities.AbstractEntity;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This class is the implementation class of the {@link Message} interface.
 * 
 * @author Tobias Schlecht
 */
final class MessageImpl extends AbstractEntity<MessageID> implements Message
{
	private final DateTime creationTime;
	private final UserID sender;
	private final UserID receiver;
	private final String title;
	private final String content;

	/**
	 * Constructs an object of the {@link Message} interface.
	 * 
	 * @param builder The {@code MessageBuilder} object
	 */
	MessageImpl(MessageBuilder builder)
	{
		super(builder.getMessageID());

		creationTime = builder.getCreationTime();
		sender = builder.getSender();
		receiver = builder.getReceiver();
		title = builder.getTitle();
		content = builder.getContent();
	}

	@Override
	public DateTime getCreationTime()
	{
		return creationTime;
	}

	@Override
	public UserID getSender()
	{
		return sender;
	}

	@Override
	public UserID getReceiver()
	{
		return receiver;
	}

	@Override
	public String getTitle()
	{
		return title;
	}

	@Override
	public String getContent()
	{
		return content;
	}
}
