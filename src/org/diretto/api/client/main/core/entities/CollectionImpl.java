package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.data.ResultSet;
import org.diretto.api.client.base.data.ResultSetImpl;
import org.diretto.api.client.base.entities.AbstractEntity;
import org.diretto.api.client.base.types.LoadType;
import org.diretto.api.client.main.core.DataManagerImpl;
import org.diretto.api.client.session.UserSession;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This class is the implementation class of the {@link Collection} interface.
 * 
 * @author Tobias Schlecht
 */
final class CollectionImpl extends AbstractEntity<CollectionID> implements Collection
{
	private final DataManagerImpl dataManager;

	private final ResultSet<DocumentID, Document> documents;

	private final String title;
	private final String description;
	private final DateTime creationTime;
	private final UserID creator;
	private final boolean privateSetting;

	/**
	 * Constructs an object of the {@link Collection} interface.
	 * 
	 * @param builder The {@code CollectionBuilder} object
	 */
	CollectionImpl(CollectionBuilder builder)
	{
		super(builder.getCollectionID());

		dataManager = builder.getDataManager();

		documents = builder.getDocuments();

		title = builder.getTitle();
		description = builder.getDescription();
		creationTime = builder.getCreationTime();
		creator = builder.getCreator();
		privateSetting = builder.getPrivateSetting();

		if(dataManager == null || documents == null)
		{
			throw new NullPointerException();
		}
	}

	@Override
	public String getTitle()
	{
		return title;
	}

	@Override
	public boolean changeTitle(UserSession userSession, String title)
	{
		return dataManager.changeCollection(userSession, getID(), title, description, privateSetting);
	}

	@Override
	public String getDescription()
	{
		return description;
	}

	@Override
	public boolean changeDescription(UserSession userSession, String description)
	{
		return dataManager.changeCollection(userSession, getID(), title, description, privateSetting);
	}

	@Override
	public DateTime getCreationTime()
	{
		return creationTime;
	}

	@Override
	public UserID getCreator()
	{
		return creator;
	}

	@Override
	public boolean getPrivateSetting()
	{
		return privateSetting;
	}

	@Override
	public boolean changePrivateSetting(UserSession userSession, boolean privateSetting)
	{
		return dataManager.changeCollection(userSession, getID(), title, description, privateSetting);
	}

	@Override
	public boolean addDocument(UserSession userSession, DocumentID documentID)
	{
		return dataManager.addDocumentToCollection(userSession, getID(), documentID);
	}

	@Override
	public boolean removeDocument(UserSession userSession, DocumentID documentID)
	{
		return dataManager.removeDocumentFromCollection(userSession, getID(), documentID);
	}

	@Override
	public Document getDocument(DocumentID documentID)
	{
		return documents.get(documentID);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocuments()
	{
		if(((ResultSetImpl<DocumentID, Document>) documents).getNextPageURL() != null || !documents.isEmpty())
		{
			return documents;
		}
		else
		{
			return dataManager.getCollectionWithDocuments((UserSession) ((ResultSetImpl<DocumentID, Document>) documents).getSession(), getID(), LoadType.SNAPSHOT).getDocuments();
		}
	}
}
