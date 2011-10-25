package org.diretto.api.client.main.core.entities;

import java.net.MalformedURLException;
import java.net.URL;

import org.diretto.api.client.base.entities.EntityID;

/**
 * The {@code CoreServiceEntityIDFactory} is a noninstantiable factory class and
 * is responsible for creating all kinds of {@link EntityID} instances.
 * 
 * @author Tobias Schlecht
 */
public final class CoreServiceEntityIDFactory
{
	/**
	 * The constructor is {@code private} to suppress the default constructor
	 * for noninstantiability.
	 */
	private CoreServiceEntityIDFactory()
	{
		throw new AssertionError();
	}

	/**
	 * Returns an instance of the requested <i>anonymous</i> {@link TagID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @return An instance of the requested {@code TagID}
	 */
	public static TagID getAnonymousTagIDInstance(URL uniqueResourceURL)
	{
		return new AnonymousTagIDImpl(uniqueResourceURL);
	}

	/**
	 * Returns an instance of the requested <i>anonymous</i> {@link TagID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @return An instance of the requested {@code TagID}
	 */
	public static TagID getAnonymousTagIDInstance(String uniqueResourceURL)
	{
		TagID tagID = null;

		try
		{
			tagID = new AnonymousTagIDImpl(new URL(uniqueResourceURL));
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}

		return tagID;
	}

	/**
	 * Returns an instance of the requested {@link AttachmentID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @param rootEntityID The {@code EntityID} of the root {@code Entity}
	 * @param parentEntityID The {@code EntityID} of the parent {@code Entity}
	 * @return An instance of the requested {@code AttachmentID}
	 */
	public static AttachmentID getAttachmentIDInstance(URL uniqueResourceURL, EntityID rootEntityID, EntityID parentEntityID)
	{
		return new AttachmentIDImpl(uniqueResourceURL, rootEntityID, parentEntityID);
	}

	/**
	 * Returns an instance of the requested {@link AttachmentID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @param rootEntityID The {@code EntityID} of the root {@code Entity}
	 * @param parentEntityID The {@code EntityID} of the parent {@code Entity}
	 * @return An instance of the requested {@code AttachmentID}
	 */
	public static AttachmentID getAttachmentIDInstance(String uniqueResourceURL, EntityID rootEntityID, EntityID parentEntityID)
	{
		AttachmentID attachmentID = null;

		try
		{
			attachmentID = new AttachmentIDImpl(new URL(uniqueResourceURL), rootEntityID, parentEntityID);
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}

		return attachmentID;
	}

	/**
	 * Returns an instance of the requested {@link CollectionID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @return An instance of the requested {@code CollectionID}
	 */
	public static CollectionID getCollectionIDInstance(URL uniqueResourceURL)
	{
		return new CollectionIDImpl(uniqueResourceURL);
	}

	/**
	 * Returns an instance of the requested {@link CollectionID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @return An instance of the requested {@code CollectionID}
	 */
	public static CollectionID getCollectionIDInstance(String uniqueResourceURL)
	{
		CollectionID collectionID = null;

		try
		{
			collectionID = new CollectionIDImpl(new URL(uniqueResourceURL));
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}

		return collectionID;
	}

	/**
	 * Returns an instance of the requested {@link CommentID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @param rootEntityID The {@code EntityID} of the root {@code Entity}
	 * @param parentEntityID The {@code EntityID} of the parent {@code Entity}
	 * @return An instance of the requested {@code CommentID}
	 */
	public static CommentID getCommentIDInstance(URL uniqueResourceURL, EntityID rootEntityID, EntityID parentEntityID)
	{
		return new CommentIDImpl(uniqueResourceURL, rootEntityID, parentEntityID);
	}

	/**
	 * Returns an instance of the requested {@link CommentID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @param rootEntityID The {@code EntityID} of the root {@code Entity}
	 * @param parentEntityID The {@code EntityID} of the parent {@code Entity}
	 * @return An instance of the requested {@code CommentID}
	 */
	public static CommentID getCommentIDInstance(String uniqueResourceURL, EntityID rootEntityID, EntityID parentEntityID)
	{
		CommentID commentID = null;

		try
		{
			commentID = new CommentIDImpl(new URL(uniqueResourceURL), rootEntityID, parentEntityID);
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}

		return commentID;
	}

	/**
	 * Returns an instance of the requested {@link DocumentID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @return An instance of the requested {@code DocumentID}
	 */
	public static DocumentID getDocumentIDInstance(URL uniqueResourceURL)
	{
		return new DocumentIDImpl(uniqueResourceURL);
	}

	/**
	 * Returns an instance of the requested {@link DocumentID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @return An instance of the requested {@code DocumentID}
	 */
	public static DocumentID getDocumentIDInstance(String uniqueResourceURL)
	{
		DocumentID documentID = null;

		try
		{
			documentID = new DocumentIDImpl(new URL(uniqueResourceURL));
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}

		return documentID;
	}

	/**
	 * Returns an instance of the requested {@link KeyValueID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @param rootEntityID The {@code EntityID} of the root {@code Entity}
	 * @param parentEntityID The {@code EntityID} of the parent {@code Entity}
	 * @return An instance of the requested {@code KeyValueID}
	 */
	public static KeyValueID getKeyValueIDInstance(URL uniqueResourceURL, EntityID rootEntityID, EntityID parentEntityID)
	{
		return new KeyValueIDImpl(uniqueResourceURL, rootEntityID, parentEntityID);
	}

	/**
	 * Returns an instance of the requested {@link KeyValueID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @param rootEntityID The {@code EntityID} of the root {@code Entity}
	 * @param parentEntityID The {@code EntityID} of the parent {@code Entity}
	 * @return An instance of the requested {@code KeyValueID}
	 */
	public static KeyValueID getKeyValueIDInstance(String uniqueResourceURL, EntityID rootEntityID, EntityID parentEntityID)
	{
		KeyValueID keyValueID = null;

		try
		{
			keyValueID = new KeyValueIDImpl(new URL(uniqueResourceURL), rootEntityID, parentEntityID);
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}

		return keyValueID;
	}

	/**
	 * Returns an instance of the requested {@link LinkID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @param rootEntityID The {@code EntityID} of the root {@code Entity}
	 * @param parentEntityID The {@code EntityID} of the parent {@code Entity}
	 * @return An instance of the requested {@code LinkID}
	 */
	public static LinkID getLinkIDInstance(URL uniqueResourceURL, EntityID rootEntityID, EntityID parentEntityID)
	{
		return new LinkIDImpl(uniqueResourceURL, rootEntityID, parentEntityID);
	}

	/**
	 * Returns an instance of the requested {@link LinkID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @param rootEntityID The {@code EntityID} of the root {@code Entity}
	 * @param parentEntityID The {@code EntityID} of the parent {@code Entity}
	 * @return An instance of the requested {@code LinkID}
	 */
	public static LinkID getLinkIDInstance(String uniqueResourceURL, EntityID rootEntityID, EntityID parentEntityID)
	{
		LinkID linkID = null;

		try
		{
			linkID = new LinkIDImpl(new URL(uniqueResourceURL), rootEntityID, parentEntityID);
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}

		return linkID;
	}

	/**
	 * Returns an instance of the requested {@link MessageID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @return An instance of the requested {@code MessageID}
	 */
	public static MessageID getMessageIDInstance(URL uniqueResourceURL)
	{
		return new MessageIDImpl(uniqueResourceURL);
	}

	/**
	 * Returns an instance of the requested {@link MessageID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @return An instance of the requested {@code MessageID}
	 */
	public static MessageID getMessageIDInstance(String uniqueResourceURL)
	{
		MessageID messageID = null;

		try
		{
			messageID = new MessageIDImpl(new URL(uniqueResourceURL));
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}

		return messageID;
	}

	/**
	 * Returns an instance of the requested {@link PositionID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @param rootEntityID The {@code EntityID} of the root {@code Entity}
	 * @param parentEntityID The {@code EntityID} of the parent {@code Entity}
	 * @return An instance of the requested {@code PositionID}
	 */
	public static PositionID getPositionIDInstance(URL uniqueResourceURL, EntityID rootEntityID, EntityID parentEntityID)
	{
		return new PositionIDImpl(uniqueResourceURL, rootEntityID, parentEntityID);
	}

	/**
	 * Returns an instance of the requested {@link PositionID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @param rootEntityID The {@code EntityID} of the root {@code Entity}
	 * @param parentEntityID The {@code EntityID} of the parent {@code Entity}
	 * @return An instance of the requested {@code PositionID}
	 */
	public static PositionID getPositionIDInstance(String uniqueResourceURL, EntityID rootEntityID, EntityID parentEntityID)
	{
		PositionID positionID = null;

		try
		{
			positionID = new PositionIDImpl(new URL(uniqueResourceURL), rootEntityID, parentEntityID);
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}

		return positionID;
	}

	/**
	 * Returns an instance of the requested {@link TagID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @param rootEntityID The {@code EntityID} of the root {@code Entity}
	 * @param parentEntityID The {@code EntityID} of the parent {@code Entity}
	 * @return An instance of the requested {@code TagID}
	 */
	public static TagID getTagIDInstance(URL uniqueResourceURL, EntityID rootEntityID, EntityID parentEntityID)
	{
		return new TagIDImpl(uniqueResourceURL, rootEntityID, parentEntityID);
	}

	/**
	 * Returns an instance of the requested {@link TagID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @param rootEntityID The {@code EntityID} of the root {@code Entity}
	 * @param parentEntityID The {@code EntityID} of the parent {@code Entity}
	 * @return An instance of the requested {@code TagID}
	 */
	public static TagID getTagIDInstance(String uniqueResourceURL, EntityID rootEntityID, EntityID parentEntityID)
	{
		TagID tagID = null;

		try
		{
			tagID = new TagIDImpl(new URL(uniqueResourceURL), rootEntityID, parentEntityID);
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}

		return tagID;
	}

	/**
	 * Returns an instance of the requested {@link TimeID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @param rootEntityID The {@code EntityID} of the root {@code Entity}
	 * @param parentEntityID The {@code EntityID} of the parent {@code Entity}
	 * @return An instance of the requested {@code TimeID}
	 */
	public static TimeID getTimeIDInstance(URL uniqueResourceURL, EntityID rootEntityID, EntityID parentEntityID)
	{
		return new TimeIDImpl(uniqueResourceURL, rootEntityID, parentEntityID);
	}

	/**
	 * Returns an instance of the requested {@link TimeID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @param rootEntityID The {@code EntityID} of the root {@code Entity}
	 * @param parentEntityID The {@code EntityID} of the parent {@code Entity}
	 * @return An instance of the requested {@code TimeID}
	 */
	public static TimeID getTimeIDInstance(String uniqueResourceURL, EntityID rootEntityID, EntityID parentEntityID)
	{
		TimeID timeID = null;

		try
		{
			timeID = new TimeIDImpl(new URL(uniqueResourceURL), rootEntityID, parentEntityID);
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}

		return timeID;
	}
}
