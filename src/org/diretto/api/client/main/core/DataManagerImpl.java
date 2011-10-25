package org.diretto.api.client.main.core;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.diretto.api.client.base.characteristic.Cachable;
import org.diretto.api.client.base.characteristic.ResultSetManager;
import org.diretto.api.client.base.characteristic.VoteManager;
import org.diretto.api.client.base.data.BoundingBox;
import org.diretto.api.client.base.data.ExternalMediaType;
import org.diretto.api.client.base.data.MediaType;
import org.diretto.api.client.base.data.PlatformMediaType;
import org.diretto.api.client.base.data.ResultSet;
import org.diretto.api.client.base.data.ResultSetFactory;
import org.diretto.api.client.base.data.ResultSetImpl;
import org.diretto.api.client.base.data.TimeRange;
import org.diretto.api.client.base.data.TopographicPoint;
import org.diretto.api.client.base.data.UploadInfo;
import org.diretto.api.client.base.entities.Entity;
import org.diretto.api.client.base.entities.EntityID;
import org.diretto.api.client.base.entities.SubEntityID;
import org.diretto.api.client.base.exceptions.CacheNotActivatedException;
import org.diretto.api.client.base.exceptions.TooManyEntitiesRequestedException;
import org.diretto.api.client.base.types.LoadType;
import org.diretto.api.client.base.types.MessageBoxType;
import org.diretto.api.client.base.types.VoteType;
import org.diretto.api.client.main.core.entities.Attachment;
import org.diretto.api.client.main.core.entities.AttachmentID;
import org.diretto.api.client.main.core.entities.Collection;
import org.diretto.api.client.main.core.entities.CollectionID;
import org.diretto.api.client.main.core.entities.Comment;
import org.diretto.api.client.main.core.entities.CommentID;
import org.diretto.api.client.main.core.entities.Document;
import org.diretto.api.client.main.core.entities.DocumentID;
import org.diretto.api.client.main.core.entities.KeyValue;
import org.diretto.api.client.main.core.entities.KeyValueID;
import org.diretto.api.client.main.core.entities.Link;
import org.diretto.api.client.main.core.entities.LinkID;
import org.diretto.api.client.main.core.entities.Message;
import org.diretto.api.client.main.core.entities.MessageID;
import org.diretto.api.client.main.core.entities.Position;
import org.diretto.api.client.main.core.entities.PositionID;
import org.diretto.api.client.main.core.entities.Tag;
import org.diretto.api.client.main.core.entities.TagID;
import org.diretto.api.client.main.core.entities.Time;
import org.diretto.api.client.main.core.entities.TimeID;
import org.diretto.api.client.main.core.entities.creation.ExternalAttachmentCreationData;
import org.diretto.api.client.main.core.entities.creation.PlatformAttachmentCreationData;
import org.diretto.api.client.session.SessionFactory;
import org.diretto.api.client.session.SystemSession;
import org.diretto.api.client.session.UserSession;
import org.diretto.api.client.user.User;
import org.diretto.api.client.user.UserFactory;
import org.diretto.api.client.user.UserID;
import org.diretto.api.client.user.UserInfo;
import org.joda.time.DateTime;
import org.restlet.Client;

/**
 * This class is the implementation class of the {@link DataManager} interface.
 * 
 * @author Tobias Schlecht
 */
public final class DataManagerImpl implements DataManager, VoteManager
{
	final CommentResultSetManager COMMENT_RESULT_SET_MANAGER = new CommentResultSetManager();
	final DocumentResultSetManager DOCUMENT_RESULT_SET_MANAGER = new DocumentResultSetManager();
	final LinkResultSetManager LINK_RESULT_SET_MANAGER = new LinkResultSetManager();
	final MessageResultSetManager MESSAGE_RESULT_SET_MANAGER = new MessageResultSetManager();
	final TagResultSetManager TAG_RESULT_SET_MANAGER = new TagResultSetManager();
	final UserInfoResultSetManager USER_INFO_RESULT_SET_MANAGER = new UserInfoResultSetManager();

	private final CoreService coreService;
	private final URL apiBaseURL;
	private final Client restletClient;
	private final SystemSession systemSession;

	private final int maxEntityRequestSize;

	private CacheManager cacheManager = null;
	private DataFactory dataFactory = null;
	private ResourceManager resourceManager = null;

	private final Cache cache;
	private final boolean cacheActivated;

	/**
	 * The constructor is {@code private} to have strict control what instances
	 * exist at any time. Instead of the constructor the {@code public}
	 * <i>static factory method</i>
	 * {@link #getInstance(CoreService, URL, Client, SystemSession)} returns the
	 * instances of the class.
	 * 
	 * @param coreService The corresponding {@code CoreService}
	 * @param apiBaseURL The API base {@code URL}
	 * @param restletClient The restlet {@link Client}
	 * @param systemSession The corresponding {@code SystemSession}
	 */
	private DataManagerImpl(CoreService coreService, URL apiBaseURL, Client restletClient, SystemSession systemSession)
	{
		this.coreService = coreService;
		this.apiBaseURL = apiBaseURL;
		this.restletClient = restletClient;
		this.systemSession = systemSession;

		maxEntityRequestSize = coreService.getMaxEntityRequestSize();

		CacheManager cacheManager = getCacheManager();

		if(cacheManager.isCacheActivated())
		{
			cache = cacheManager.getCache();
		}
		else
		{
			cache = null;
		}

		cacheActivated = cacheManager.isCacheActivated();
	}

	/**
	 * Returns a {@link DataManager} instance for the specified API base
	 * {@link URL}.
	 * 
	 * @param coreService The corresponding {@code CoreService}
	 * @param apiBaseURL The API base {@code URL}
	 * @param restletClient The restlet {@link Client}
	 * @param systemSession The corresponding {@code SystemSession}
	 * @return A {@code DataManager} instance
	 */
	static synchronized DataManager getInstance(CoreService coreService, URL apiBaseURL, Client restletClient, SystemSession systemSession)
	{
		return new DataManagerImpl(coreService, apiBaseURL, restletClient, systemSession);
	}

	/**
	 * Returns the corresponding {@link CoreService}.
	 * 
	 * @return The corresponding {@code CoreService}
	 */
	CoreService getCoreService()
	{
		return coreService;
	}

	/**
	 * Returns the API base {@link URL}.
	 * 
	 * @return The API base {@code URL}
	 */
	URL getAPIBaseURL()
	{
		return apiBaseURL;
	}

	/**
	 * Returns the corresponding {@link CacheManager}.
	 * 
	 * @return The corresponding {@code CacheManager}
	 */
	CacheManager getCacheManager()
	{
		if(cacheManager == null)
		{
			cacheManager = CacheManager.getInstance(this);
		}

		return cacheManager;
	}

	/**
	 * Returns the corresponding {@link DataFactory}.
	 * 
	 * @return The corresponding {@code DataFactory}
	 */
	DataFactory getDataFactory()
	{
		if(dataFactory == null)
		{
			dataFactory = DataFactory.getInstance(this);
		}

		return dataFactory;
	}

	/**
	 * Returns the corresponding restlet {@link Client}.
	 * 
	 * @return The corresponding restlet {@code Client}
	 */
	Client getResletClient()
	{
		return restletClient;
	}

	/**
	 * Returns the corresponding {@link ResourceManager}.
	 * 
	 * @return The corresponding {@code ResourceManager}
	 */
	ResourceManager getResourceManager()
	{
		if(resourceManager == null)
		{
			resourceManager = ResourceManager.getInstance(this);
		}

		return resourceManager;
	}

	/**
	 * Returns the corresponding {@link SystemSession}.
	 * 
	 * @return The corresponding {@code SystemSession}
	 */
	SystemSession getSystemSession()
	{
		return systemSession;
	}

	@Override
	public CollectionID createCollection(UserSession userSession, String title, String description, boolean privateSetting)
	{
		if(userSession == null || title == null)
		{
			throw new NullPointerException();
		}
		else if(title.trim().equals(""))
		{
			throw new IllegalArgumentException();
		}

		if(description == null)
		{
			description = "";
		}

		return getDataFactory().createCollection(userSession, title, description, privateSetting);
	}

	@Override
	public DocumentID createDocument(UserSession userSession, ExternalAttachmentCreationData externalAttachmentCreationData, TopographicPoint topographicPoint, TimeRange timeRange)
	{
		return createDocument(userSession, UUID.randomUUID(), externalAttachmentCreationData, topographicPoint, timeRange);
	}

	@Override
	public UploadInfo createDocument(UserSession userSession, PlatformAttachmentCreationData platformAttachmentCreationData, TopographicPoint topographicPoint, TimeRange timeRange)
	{
		return createDocument(userSession, UUID.randomUUID(), platformAttachmentCreationData, topographicPoint, timeRange);
	}

	@Override
	public DocumentID createDocument(UserSession userSession, UUID uuid, ExternalAttachmentCreationData externalAttachmentCreationData, TopographicPoint topographicPoint, TimeRange timeRange)
	{
		if(userSession == null || uuid == null || externalAttachmentCreationData == null || topographicPoint == null || timeRange == null)
		{
			throw new NullPointerException();
		}

		DocumentID documentID = getDataFactory().createDocument(userSession, uuid, externalAttachmentCreationData, topographicPoint, timeRange);

		if(cacheActivated)
		{
			getDocument(documentID, LoadType.COMPLETE, true);
		}

		return documentID;
	}

	@Override
	public UploadInfo createDocument(UserSession userSession, UUID uuid, PlatformAttachmentCreationData platformAttachmentCreationData, TopographicPoint topographicPoint, TimeRange timeRange)
	{
		if(userSession == null || uuid == null || platformAttachmentCreationData == null || topographicPoint == null || timeRange == null)
		{
			throw new NullPointerException();
		}

		return getDataFactory().createDocument(userSession, uuid, platformAttachmentCreationData, topographicPoint, timeRange);
	}

	@Override
	public UserID createUser(String emailAddress, String password, String userName)
	{
		if(emailAddress == null || password == null || userName == null)
		{
			throw new NullPointerException();
		}
		else if(emailAddress.trim().equals("") || password.trim().equals("") || userName.trim().equals(""))
		{
			throw new IllegalArgumentException();
		}

		return getDataFactory().createUser(emailAddress, password, userName);
	}

	@Override
	public ResultSet<DocumentID, Document> getAllCachedDocuments()
	{
		if(cacheActivated)
		{
			LinkedHashMap<DocumentID, Document> documents = new LinkedHashMap<DocumentID, Document>();

			Element element;
			Document document;

			for(Object key : cache.getKeysWithExpiryCheck())
			{
				element = cache.get(key);

				if(element != null)
				{
					document = (Document) element.getObjectValue();
					documents.put(document.getID(), document);
				}
			}

			return ResultSetFactory.getResultSetInstance(null, systemSession, false, LoadType.METADATA, documents, null);
		}
		else
		{
			throw new CacheNotActivatedException();
		}
	}

	@Override
	public ResultSet<DocumentID, Document> getAllDocuments()
	{
		return getAllDocuments(LoadType.METADATA);
	}

	@Override
	public ResultSet<DocumentID, Document> getAllDocuments(LoadType loadType)
	{
		if(loadType == null)
		{
			throw new NullPointerException();
		}

		ResultSetImpl<DocumentID, Document> resultSet = getDataFactory().getAllDocuments(loadType);

		putDocumentsIntoCache(resultSet);

		return resultSet;
	}

	@Override
	public ResultSet<LinkID, Link> getAllLinks()
	{
		return getDataFactory().getAllLinks();
	}

	@Override
	public ResultSet<TagID, Tag> getAllTags()
	{
		return getDataFactory().getAllTags();
	}

	@Override
	public ResultSet<UserID, UserInfo> getAllUserInfos()
	{
		return getDataFactory().getAllUserInfos();
	}

	@Override
	public Collection getCollection(UserSession userSession, CollectionID collectionID)
	{
		if(userSession == null || collectionID == null)
		{
			throw new NullPointerException();
		}

		return getDataFactory().getCollection(userSession, collectionID);
	}

	@Override
	public ResultSet<CollectionID, Collection> getCollectionsByUser(UserSession userSession, UserID creator)
	{
		if(userSession == null || creator == null)
		{
			throw new NullPointerException();
		}

		return getDataFactory().getCollectionsByUser(userSession, creator);
	}

	@Override
	public ResultSet<CollectionID, Collection> getCollectionsWithDocumentsByUser(UserSession userSession, UserID creator)
	{
		return getCollectionsWithDocumentsByUser(userSession, creator, LoadType.METADATA);
	}

	@Override
	public ResultSet<CollectionID, Collection> getCollectionsWithDocumentsByUser(UserSession userSession, UserID userID, LoadType loadType)
	{
		if(userSession == null || userID == null || loadType == null)
		{
			throw new NullPointerException();
		}

		ResultSet<CollectionID, Collection> resultSet = getDataFactory().getCollectionsWithDocumentsByUser(userSession, userID, loadType);

		for(Collection collection : resultSet)
		{
			putDocumentsIntoCache((ResultSetImpl<DocumentID, Document>) collection.getDocuments());
		}

		return resultSet;
	}

	@Override
	public Collection getCollectionWithDocuments(UserSession userSession, CollectionID collectionID)
	{
		return getCollectionWithDocuments(userSession, collectionID, LoadType.METADATA);
	}

	@Override
	public Collection getCollectionWithDocuments(UserSession userSession, CollectionID collectionID, LoadType loadType)
	{
		if(userSession == null || collectionID == null || loadType == null)
		{
			throw new NullPointerException();
		}

		Collection collection = getDataFactory().getCollectionWithDocuments(userSession, collectionID, loadType);

		putDocumentsIntoCache((ResultSetImpl<DocumentID, Document>) collection.getDocuments());

		return collection;
	}

	@Override
	public ResultSet<CommentID, Comment> getCommentsByUser(UserID userID)
	{
		if(userID == null)
		{
			throw new NullPointerException();
		}

		return getDataFactory().getCommentsByUser(userID);
	}

	@Override
	public Document getDocument(DocumentID documentID)
	{
		return getDocument(documentID, LoadType.METADATA, false);
	}

	@Override
	public Document getDocument(DocumentID documentID, LoadType loadType, boolean forceAPICall)
	{
		if(documentID == null || loadType == null)
		{
			throw new NullPointerException();
		}

		Document document;

		if(cacheActivated)
		{
			if(!forceAPICall)
			{
				Element element = cache.get(documentID);

				if(element != null)
				{
					document = (Document) element.getObjectValue();

					if(!loadType.isSubsetFrom(((Cachable) document).getLoadType()))
					{
						document = getDataFactory().getDocument(documentID, loadType);
					}
				}
				else
				{
					document = getDataFactory().getDocument(documentID, loadType);
				}
			}
			else
			{
				document = getDataFactory().getDocument(documentID, loadType);
			}

			if(document != null)
			{
				cache.put(new Element(document.getID(), document));
			}
		}
		else
		{
			document = getDataFactory().getDocument(documentID, loadType);
		}

		return document;
	}

	@Override
	public ResultSet<DocumentID, Document> getDocuments(List<String> tags, BoundingBox boundingBox, TimeRange timeRange)
	{
		return getDocuments(tags, boundingBox, timeRange, LoadType.METADATA);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocuments(List<String> tags, BoundingBox boundingBox, TimeRange timeRange, LoadType loadType)
	{
		if(tags == null || boundingBox == null || timeRange == null || loadType == null)
		{
			throw new NullPointerException();
		}

		ResultSetImpl<DocumentID, Document> resultSet = getDataFactory().getDocuments(tags, boundingBox, timeRange, loadType);

		putDocumentsIntoCache(resultSet);

		return resultSet;
	}

	@Override
	public ResultSet<DocumentID, Document> getDocuments(List<String> tags, BoundingBox boundingBox, TimeRange timeRange, TimeRange publishingTimeRange)
	{
		return getDocuments(tags, boundingBox, timeRange, publishingTimeRange, LoadType.METADATA);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocuments(List<String> tags, BoundingBox boundingBox, TimeRange timeRange, TimeRange publishingTimeRange, LoadType loadType)
	{
		if(tags == null || boundingBox == null || timeRange == null || publishingTimeRange == null || loadType == null)
		{
			throw new NullPointerException();
		}

		ResultSetImpl<DocumentID, Document> resultSet = getDataFactory().getDocuments(tags, boundingBox, timeRange, publishingTimeRange, loadType);

		putDocumentsIntoCache(resultSet);

		return resultSet;
	}

	@Override
	public ResultSet<DocumentID, Document> getDocumentsAfter(DateTime time)
	{
		return getDocumentsAfter(time, LoadType.METADATA);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocumentsAfter(DateTime time, LoadType loadType)
	{
		if(time == null || loadType == null)
		{
			throw new NullPointerException();
		}

		ResultSetImpl<DocumentID, Document> resultSet = getDataFactory().getDocumentsAfter(time, loadType);

		putDocumentsIntoCache(resultSet);

		return resultSet;
	}

	@Override
	public ResultSet<DocumentID, Document> getDocumentsByIDs(List<DocumentID> documentIDs)
	{
		return getDocumentsByIDs(documentIDs, LoadType.METADATA, false);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocumentsByIDs(List<DocumentID> documentIDs, LoadType loadType, boolean forceAPICall)
	{
		LinkedHashMap<DocumentID, Document> resultMap;

		if(documentIDs == null || loadType == null)
		{
			throw new NullPointerException();
		}
		else if(documentIDs.size() > maxEntityRequestSize)
		{
			throw new TooManyEntitiesRequestedException();
		}
		else if(documentIDs.size() == 0)
		{
			return ResultSetFactory.getResultSetInstance(null, systemSession, false, loadType, new LinkedHashMap<DocumentID, Document>(), null);
		}
		else if(documentIDs.size() == 1)
		{
			resultMap = new LinkedHashMap<DocumentID, Document>();
			resultMap.put(documentIDs.get(0), getDocument(documentIDs.get(0), loadType, forceAPICall));

			return ResultSetFactory.getResultSetInstance(null, systemSession, false, loadType, resultMap, null);
		}

		if(cacheActivated)
		{
			if(!forceAPICall)
			{
				resultMap = new LinkedHashMap<DocumentID, Document>();
				LinkedHashMap<DocumentID, Document> tempMap = new LinkedHashMap<DocumentID, Document>();
				List<DocumentID> missingDocuments = new LinkedList<DocumentID>();

				Document document;
				Element element;

				for(DocumentID documentID : documentIDs)
				{
					element = cache.get(documentID);

					if(element != null)
					{
						document = (Document) element.getObjectValue();

						if(!loadType.isSubsetFrom(((Cachable) document).getLoadType()))
						{
							missingDocuments.add(documentID);
						}
						else
						{
							tempMap.put(documentID, document);
						}
					}
					else
					{
						missingDocuments.add(documentID);
					}
				}

				if(missingDocuments.size() > 0)
				{
					tempMap.putAll(getDataFactory().getDocumentsByIDs(missingDocuments, loadType));

					for(DocumentID documentID : documentIDs)
					{
						resultMap.put(documentID, tempMap.get(documentID));
					}
				}
				else
				{
					return ResultSetFactory.getResultSetInstance(null, systemSession, false, loadType, tempMap, null);
				}
			}
			else
			{
				resultMap = getDataFactory().getDocumentsByIDs(documentIDs, loadType);
			}

			if(resultMap.size() > 0)
			{
				for(Document document : resultMap.values())
				{
					cache.put(new Element(document.getID(), document));
				}
			}
		}
		else
		{
			resultMap = getDataFactory().getDocumentsByIDs(documentIDs, loadType);
		}

		return ResultSetFactory.getResultSetInstance(null, systemSession, false, loadType, resultMap, null);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocumentsByTag(String value)
	{
		return getDocumentsByTag(value, LoadType.METADATA);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocumentsByTag(String value, LoadType loadType)
	{
		if(value == null || loadType == null)
		{
			throw new NullPointerException();
		}
		else if(value.trim().equals(""))
		{
			throw new IllegalArgumentException();
		}

		ResultSetImpl<DocumentID, Document> resultSet = getDataFactory().getDocumentsByTag(value, loadType);

		putDocumentsIntoCache(resultSet);

		return resultSet;
	}

	@Override
	public ResultSet<DocumentID, Document> getDocumentsByUser(UserID publisher)
	{
		return getDocumentsByUser(publisher, LoadType.METADATA);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocumentsByUser(UserID publisher, LoadType loadType)
	{
		if(publisher == null || loadType == null)
		{
			throw new NullPointerException();
		}

		ResultSetImpl<DocumentID, Document> resultSet = getDataFactory().getDocumentsByUser(publisher, loadType);

		putDocumentsIntoCache(resultSet);

		return resultSet;
	}

	@Override
	public ResultSet<LinkID, Link> getLinksAfter(DateTime time)
	{
		if(time == null)
		{
			throw new NullPointerException();
		}

		return getDataFactory().getLinksAfter(time);
	}

	@Override
	public ResultSet<LinkID, Link> getLinksByTag(String value)
	{
		if(value == null)
		{
			throw new NullPointerException();
		}
		else if(value.trim().equals(""))
		{
			throw new IllegalArgumentException();
		}

		return getDataFactory().getLinksByTag(value);
	}

	@Override
	public Message getMessage(UserSession userSession, MessageID messageID)
	{
		if(userSession == null || messageID == null)
		{
			throw new NullPointerException();
		}

		return getDataFactory().getMessage(userSession, messageID);
	}

	@Override
	public ResultSet<MessageID, Message> getMessages(UserSession userSession, MessageBoxType messageBoxType)
	{
		if(userSession == null || messageBoxType == null)
		{
			throw new NullPointerException();
		}

		return getDataFactory().getMessages(userSession, messageBoxType);
	}

	@Override
	public ResultSet<MessageID, Message> getMessagesAfter(UserSession userSession, MessageBoxType messageBoxType, DateTime time)
	{
		if(userSession == null || messageBoxType == null || time == null)
		{
			throw new NullPointerException();
		}

		return getDataFactory().getMessagesAfter(userSession, messageBoxType, time);
	}

	@Override
	public User getUser(String emailAddress, String password)
	{
		if(emailAddress == null || password == null)
		{
			throw new NullPointerException();
		}
		else if(emailAddress.trim().equals("") || password.trim().equals(""))
		{
			throw new IllegalArgumentException();
		}

		UserInfo userInfo = getDataFactory().getUserInfo(emailAddress, password);

		if(userInfo == null)
		{
			return null;
		}

		return UserFactory.getUserInstance(this, userInfo, apiBaseURL, emailAddress, password);
	}

	@Override
	public UserInfo getUserInfo(UserID userID)
	{
		if(userID == null)
		{
			throw new NullPointerException();
		}

		return getDataFactory().getUserInfo(userID);
	}

	@Override
	public ResultSet<UserID, UserInfo> getUserInfosByIDs(List<UserID> userIDs)
	{
		if(userIDs == null)
		{
			throw new NullPointerException();
		}
		else if(userIDs.size() > maxEntityRequestSize)
		{
			throw new TooManyEntitiesRequestedException();
		}
		else if(userIDs.size() == 0)
		{
			return ResultSetFactory.getResultSetInstance(USER_INFO_RESULT_SET_MANAGER, systemSession, false, LoadType.COMPLETE, new LinkedHashMap<UserID, UserInfo>(), null);
		}
		else if(userIDs.size() == 1)
		{
			LinkedHashMap<UserID, UserInfo> resultMap = new LinkedHashMap<UserID, UserInfo>();
			resultMap.put(userIDs.get(0), getDataFactory().getUserInfo(userIDs.get(0)));

			return ResultSetFactory.getResultSetInstance(USER_INFO_RESULT_SET_MANAGER, systemSession, false, LoadType.COMPLETE, resultMap, null);
		}

		return getDataFactory().getUserInfosByIDs(userIDs);
	}

	@Override
	public UserSession getUserSession(User user)
	{
		if(user == null)
		{
			throw new NullPointerException();
		}

		return SessionFactory.getUserSessionInstance(user);
	}

	@Override
	public VoteType getUserVote(UserSession userSession, EntityID entityID)
	{
		if(userSession == null || entityID == null)
		{
			throw new NullPointerException();
		}

		return getDataFactory().getUserVote(userSession, entityID);
	}

	@Override
	public boolean hasUser(String emailAddress)
	{
		if(emailAddress == null)
		{
			throw new NullPointerException();
		}
		else if(emailAddress.trim().equals(""))
		{
			throw new IllegalArgumentException();
		}

		return getDataFactory().hasUser(emailAddress);
	}

	@Override
	public boolean hasUser(String emailAddress, String password)
	{
		if(emailAddress == null || password == null)
		{
			throw new NullPointerException();
		}
		else if(emailAddress.trim().equals("") || password.trim().equals(""))
		{
			throw new IllegalArgumentException();
		}

		return getDataFactory().hasUser(emailAddress, password);
	}

	@Override
	public boolean isCacheActivated()
	{
		return cacheActivated;
	}

	@Override
	public boolean removeCollection(UserSession userSession, CollectionID collectionID)
	{
		if(userSession == null || collectionID == null)
		{
			throw new NullPointerException();
		}

		return getDataFactory().removeCollection(userSession, collectionID);
	}

	@Override
	public boolean removeMessage(UserSession userSession, MessageID messageID)
	{
		if(userSession == null || messageID == null)
		{
			throw new NullPointerException();
		}

		return getDataFactory().removeMessage(userSession, messageID);
	}

	@Override
	public boolean removeUserVote(UserSession userSession, EntityID entityID)
	{
		if(userSession == null || entityID == null)
		{
			throw new NullPointerException();
		}

		boolean wasSuccessful = getDataFactory().removeUserVote(userSession, entityID);

		if(cacheActivated)
		{
			DocumentID documentID;

			if(entityID instanceof SubEntityID<?, ?>)
			{
				@SuppressWarnings("unchecked")
				SubEntityID<DocumentID, ?> subEntityID = (SubEntityID<DocumentID, ?>) entityID;
				documentID = subEntityID.getRootID();

				getDocument(documentID, LoadType.COMPLETE, true);
			}
			else
			{
				documentID = (DocumentID) entityID;

				getDocument(documentID, LoadType.METADATA, true);
			}
		}

		return wasSuccessful;
	}

	@Override
	public MessageID sendMessage(UserSession userSession, UserID receiver, String title, String content)
	{
		if(userSession == null || receiver == null)
		{
			throw new NullPointerException();
		}

		if(title == null)
		{
			title = "";
		}

		if(content == null)
		{
			content = "";
		}

		return getDataFactory().sendMessage(userSession, receiver, title, content);
	}

	@Override
	public boolean setUserVote(UserSession userSession, VoteType voteType, EntityID entityID)
	{
		if(userSession == null || voteType == null || entityID == null)
		{
			throw new NullPointerException();
		}

		boolean wasSuccessful = getDataFactory().setUserVote(userSession, voteType, entityID);

		if(cacheActivated)
		{
			DocumentID documentID;

			if(entityID instanceof SubEntityID<?, ?>)
			{
				@SuppressWarnings("unchecked")
				SubEntityID<DocumentID, ?> subEntityID = (SubEntityID<DocumentID, ?>) entityID;
				documentID = subEntityID.getRootID();

				getDocument(documentID, LoadType.COMPLETE, true);
			}
			else
			{
				documentID = (DocumentID) entityID;

				getDocument(documentID, LoadType.METADATA, true);
			}
		}

		return wasSuccessful;
	}

	/**
	 * Adds a new alternative {@link Position} to a {@link Document} and returns
	 * the {@link PositionID} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param documentID The corresponding {@code DocumentID}
	 * @param topographicPoint The {@code TopographicPoint} of the
	 *        {@code Position}
	 * @return The corresponding {@code PositionID}
	 */
	public PositionID addAlternativePositionToDocument(UserSession userSession, DocumentID documentID, TopographicPoint topographicPoint)
	{
		if(userSession == null || documentID == null || topographicPoint == null)
		{
			throw new NullPointerException();
		}

		PositionID positionID = getDataFactory().addAlternativePositionToDocument(userSession, documentID, topographicPoint);

		if(cacheActivated)
		{
			getDocument(documentID, LoadType.COMPLETE, true);
		}

		return positionID;
	}

	/**
	 * Adds a new alternative {@link Time} to a {@link Document} and returns the
	 * {@link TimeID} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param documentID The corresponding {@code DocumentID}
	 * @param timeRange The creation {@code TimeRange} of the {@code Document}
	 * @return The corresponding {@code TimeID}
	 */
	public TimeID addAlternativeTimeToDocument(UserSession userSession, DocumentID documentID, TimeRange timeRange)
	{
		if(userSession == null || documentID == null || timeRange == null)
		{
			throw new NullPointerException();
		}

		TimeID timeID = getDataFactory().addAlternativeTimeToDocument(userSession, documentID, timeRange);

		if(cacheActivated)
		{
			getDocument(documentID, LoadType.COMPLETE, true);
		}

		return timeID;
	}

	/**
	 * Adds a new {@link Comment} to a {@link Document} and returns the
	 * {@link CommentID} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param documentID The corresponding {@code DocumentID}
	 * @param content The content of the {@code Comment}
	 * @return The corresponding {@code CommentID}
	 */
	public CommentID addCommentToDocument(UserSession userSession, DocumentID documentID, String content)
	{
		if(userSession == null || documentID == null || content == null)
		{
			throw new NullPointerException();
		}
		else if(content.trim().equals(""))
		{
			throw new IllegalArgumentException();
		}

		CommentID commentID = getDataFactory().addCommentToDocument(userSession, documentID, content);

		if(cacheActivated)
		{
			getDocument(documentID, LoadType.COMPLETE, true);
		}

		return commentID;
	}

	/**
	 * Adds the {@link Document} with the specified {@link DocumentID} to the
	 * {@link Collection} with the specified {@link CollectionID}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param collectionID The corresponding {@code CollectionID}
	 * @param documentID A {@code DocumentID}
	 * @return {@code true} if the addition was successful; otherwise
	 *         {@code false}
	 */
	public boolean addDocumentToCollection(UserSession userSession, CollectionID collectionID, DocumentID documentID)
	{
		if(userSession == null || collectionID == null || documentID == null)
		{
			throw new NullPointerException();
		}

		return getDataFactory().addDocumentToCollection(userSession, collectionID, documentID);
	}

	/**
	 * Adds a new external {@link Attachment} to a {@link Document} and returns
	 * the {@link AttachmentID} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param documentID The corresponding {@code DocumentID}
	 * @param externalAttachmentCreationData The
	 *        {@code ExternalAttachmentCreationData} object
	 * @return The corresponding {@code AttachmentID}
	 */
	public AttachmentID addExternalAttachmentToDocument(UserSession userSession, DocumentID documentID, ExternalAttachmentCreationData externalAttachmentCreationData)
	{
		if(userSession == null || documentID == null || externalAttachmentCreationData == null)
		{
			throw new NullPointerException();
		}

		AttachmentID attachmentID = getDataFactory().addExternalAttachmentToDocument(userSession, documentID, externalAttachmentCreationData);

		if(cacheActivated)
		{
			getDocument(documentID, LoadType.COMPLETE, true);
		}

		return attachmentID;
	}

	/**
	 * Adds a new {@link KeyValue} to a {@link Document} and returns the
	 * {@link KeyValueID} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param documentID The corresponding {@code DocumentID}
	 * @param key The key of the {@code KeyValue}
	 * @param value The value of the {@code KeyValue}
	 * @return The corresponding {@code KeyValueID}
	 */
	public KeyValueID addKeyValueToDocument(UserSession userSession, DocumentID documentID, String key, String value)
	{
		if(userSession == null || documentID == null || key == null || value == null)
		{
			throw new NullPointerException();
		}
		else if(key.trim().equals("") || value.trim().equals("") || !key.matches("[a-zA-Z0-9-:._]+"))
		{
			throw new IllegalArgumentException();
		}

		KeyValueID keyValueID = getDataFactory().addKeyValueToDocument(userSession, documentID, key, value);

		if(cacheActivated)
		{
			getDocument(documentID, LoadType.COMPLETE, true);
		}

		return keyValueID;
	}

	/**
	 * Adds a new {@link Link} between a source {@link Document} and a
	 * destination {@code Document} and returns the {@link LinkID} if it was
	 * successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param sourceDocumentID The {@code DocumentID} of the source
	 *        {@code Document}
	 * @param destinationDocumentID The {@code DocumentID} of the destination
	 *        {@code Document}
	 * @param title The title of the {@code Link}
	 * @param description The description of the {@code Link}
	 * @return The corresponding {@code LinkID}
	 */
	public LinkID addLinkBetweenDocuments(UserSession userSession, DocumentID sourceDocumentID, DocumentID destinationDocumentID, String title, String description)
	{
		if(userSession == null || sourceDocumentID == null || destinationDocumentID == null || title == null)
		{
			throw new NullPointerException();
		}
		else if(title.trim().equals(""))
		{
			throw new IllegalArgumentException();
		}
		else if(description == null)
		{
			description = "";
		}

		LinkID linkID = getDataFactory().addLinkBetweenDocuments(userSession, sourceDocumentID, destinationDocumentID, title, description);

		if(cacheActivated)
		{
			List<DocumentID> documentIDs = new LinkedList<DocumentID>();

			documentIDs.add(sourceDocumentID);
			documentIDs.add(destinationDocumentID);

			getDocumentsByIDs(documentIDs, LoadType.COMPLETE, true);
		}

		return linkID;
	}

	/**
	 * Adds a new platform {@link Attachment} to a {@link Document} and returns
	 * the {@link UploadInfo} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param documentID The corresponding {@code DocumentID}
	 * @param platformAttachmentCreationData The
	 *        {@code PlatformAttachmentCreationData} object
	 * @return The {@code UploadInfo} object
	 */
	public UploadInfo addPlatformAttachmentToDocument(UserSession userSession, DocumentID documentID, PlatformAttachmentCreationData platformAttachmentCreationData)
	{
		if(userSession == null || documentID == null || platformAttachmentCreationData == null)
		{
			throw new NullPointerException();
		}

		return getDataFactory().addPlatformAttachmentToDocument(userSession, documentID, platformAttachmentCreationData);
	}

	/**
	 * Adds a new {@link Tag} to an {@link Entity} and returns the {@link TagID}
	 * if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param entityID The corresponding {@code EntityID}
	 * @param value The value of the {@code Tag}
	 * @return The corresponding {@code TagID}
	 */
	public TagID addTagToEntity(UserSession userSession, EntityID entityID, String value)
	{
		if(userSession == null || entityID == null || value == null)
		{
			throw new NullPointerException();
		}
		else if(value.trim().equals(""))
		{
			throw new IllegalArgumentException();
		}

		TagID tagID = getDataFactory().addTagToEntity(userSession, entityID, value);

		if(cacheActivated)
		{
			DocumentID documentID;

			if(entityID instanceof SubEntityID<?, ?>)
			{
				@SuppressWarnings("unchecked")
				SubEntityID<DocumentID, ?> subEntityID = (SubEntityID<DocumentID, ?>) entityID;
				documentID = subEntityID.getRootID();
			}
			else
			{
				documentID = (DocumentID) entityID;
			}

			getDocument(documentID, LoadType.COMPLETE, true);
		}

		return tagID;
	}

	/**
	 * Changes the meta data of the {@link Collection} with the given
	 * {@link CollectionID}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param collectionID The corresponding {@code CollectionID}
	 * @param title The title of the {@code Collection}
	 * @param description The description of the {@code Collection}
	 * @param privateSetting {@code true} if the {@code Collection} is
	 *        {@code private} (then only the {@code User} who created the
	 *        {@code Collection} has access to the {@code Collection});
	 *        {@code false} if the {@code Collection} is {@code public}
	 * @return {@code true} if the change was successful; otherwise
	 *         {@code false}
	 */
	public boolean changeCollection(UserSession userSession, CollectionID collectionID, String title, String description, boolean privateSetting)
	{
		if(userSession == null || collectionID == null || title == null)
		{
			throw new NullPointerException();
		}
		else if(title.trim().equals(""))
		{
			throw new IllegalArgumentException();
		}
		else if(description == null)
		{
			description = "";
		}

		return getDataFactory().changeCollection(userSession, collectionID, title, description, privateSetting);
	}

	/**
	 * Changes the password of the {@link User}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param password The new password of the {@code User}
	 * @return {@code true} if the change was successful; otherwise
	 *         {@code false}
	 */
	public boolean changePassword(UserSession userSession, String password)
	{
		if(userSession == null || password == null)
		{
			throw new NullPointerException();
		}
		else if(password.trim().equals(""))
		{
			throw new IllegalArgumentException();
		}

		return getDataFactory().changePassword(userSession, password);
	}

	/**
	 * Changes the name of the {@link User}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param userName The new name of the {@code User}
	 * @return {@code true} if the change was successful; otherwise
	 *         {@code false}
	 */
	public boolean changeUserName(UserSession userSession, String userName)
	{
		if(userSession == null || userName == null)
		{
			throw new NullPointerException();
		}
		else if(userName.trim().equals(""))
		{
			throw new IllegalArgumentException();
		}

		return getDataFactory().changeUserName(userSession, userName);
	}

	/**
	 * Returns a {@link Map} with all supported {@link ExternalMediaType}s and
	 * their mapped <i>ID</i> {@code String}s.
	 * 
	 * @return A {@code Map} with all supported {@code ExternalMediaType}s
	 */
	public Map<String, ExternalMediaType> getSupportedExternalMediaTypes()
	{
		return getDataFactory().getSupportedExternalMediaTypes();
	}

	/**
	 * Returns an {@link ArrayList} containing a {@link Map} with all supported
	 * {@link PlatformMediaType}s and their mapped <i>ID</i> {@code String}s as
	 * well as a {@code Map} with all supported {@link ExternalMediaType}s and
	 * their mapped <i>ID</i> {@code String}s. <br/><br/>
	 * 
	 * <i>Annotation:</i> The {@code Map} with the {@code PlatformMediaType}s is
	 * located at index {@code 0} and the {@code Map} with all
	 * {@code ExternalMediaType}s is located at index {@code 1}.
	 * 
	 * @return An {@code ArrayList} containing two {@code Map}s with all
	 *         {@code MediaType}s.
	 */
	public ArrayList<Map<String, ? extends MediaType>> getSupportedMediaTypes()
	{
		return getDataFactory().getSupportedMediaTypes();
	}

	/**
	 * Returns a {@link Map} with all supported {@link PlatformMediaType}s and
	 * their mapped <i>ID</i> {@code String}s.
	 * 
	 * @return A {@code Map} with all supported {@code PlatformMediaType}s
	 */
	public Map<String, PlatformMediaType> getSupportedPlatformMediaTypes()
	{
		return getDataFactory().getSupportedPlatformMediaTypes();
	}

	/**
	 * Removes the {@link Document} with the specified {@link DocumentID} from
	 * the {@link Collection} with the specified {@link CollectionID} if the
	 * {@code Collection} contains the {@code Document}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param collectionID The corresponding {@code CollectionID}
	 * @param documentID A {@code DocumentID}
	 * @return {@code true} if the removal was successful; otherwise
	 *         {@code false}
	 */
	public boolean removeDocumentFromCollection(UserSession userSession, CollectionID collectionID, DocumentID documentID)
	{
		if(userSession == null || collectionID == null || documentID == null)
		{
			throw new NullPointerException();
		}

		return getDataFactory().removeDocumentFromCollection(userSession, collectionID, documentID);
	}

	/**
	 * Removes the {@link KeyValue} with the specified {@link KeyValueID} from
	 * the {@link Document} if the {@link User} of the given {@link UserSession}
	 * has created the {@code KeyValue}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param documentID The corresponding {@code DocumentID}
	 * @param keyValueID The {@code KeyValueID}
	 * @return {@code true} if the removal was successful; otherwise
	 *         {@code false}
	 */
	public boolean removeKeyValueFromDocument(UserSession userSession, DocumentID documentID, KeyValueID keyValueID)
	{
		if(userSession == null || documentID == null || keyValueID == null)
		{
			throw new NullPointerException();
		}

		boolean wasSuccessful = getDataFactory().removeKeyValueFromDocument(userSession, keyValueID);

		if(cacheActivated)
		{
			getDocument(documentID, LoadType.COMPLETE, true);
		}

		return wasSuccessful;
	}

	/**
	 * Checks whether the {@code Cache} is activated and in case it is
	 * activated, all {@link Document}s of the given {@link ResultSet}, which
	 * are loaded for the present time, will be put in the {@code Cache}.
	 * 
	 * @param resultSet The {@code ResultSet} with the {@code Document}s
	 */
	private void putDocumentsIntoCache(ResultSetImpl<DocumentID, Document> resultSet)
	{
		if(cacheActivated)
		{
			for(Document document : resultSet.getLoadedData())
			{
				cache.put(new Element(document.getID(), document));
			}
		}
	}

	/**
	 * This <i>member</i> class implements the {@link ResultSetManager}
	 * interface and is responsible for the {@link ResultSet} managing aspects
	 * in respect of {@link Comment}s.
	 */
	class CommentResultSetManager implements ResultSetManager<CommentID, Comment>
	{
		/**
		 * Constructs a {@link CommentResultSetManager}.
		 */
		private CommentResultSetManager()
		{
		}

		@Override
		public LinkedHashMap<CommentID, Comment> getNextPageData(ResultSetImpl<CommentID, Comment> resultSet)
		{
			if(resultSet == null)
			{
				throw new NullPointerException();
			}

			return getDataFactory().getNextCommentPageData(resultSet);
		}
	}

	/**
	 * This <i>member</i> class implements the {@link ResultSetManager}
	 * interface and is responsible for the {@link ResultSet} managing aspects
	 * in respect of {@link Document}s.
	 */
	class DocumentResultSetManager implements ResultSetManager<DocumentID, Document>
	{
		/**
		 * Constructs a {@link DocumentResultSetManager}.
		 */
		private DocumentResultSetManager()
		{
		}

		@Override
		public LinkedHashMap<DocumentID, Document> getNextPageData(ResultSetImpl<DocumentID, Document> resultSet)
		{
			if(resultSet == null)
			{
				throw new NullPointerException();
			}

			LinkedHashMap<DocumentID, Document> resultMap = getDataFactory().getNextDocumentPageData(resultSet);

			if(cacheActivated)
			{
				for(Document document : resultMap.values())
				{
					cache.put(new Element(document.getID(), document));
				}
			}

			return resultMap;
		}
	}

	/**
	 * This <i>member</i> class implements the {@link ResultSetManager}
	 * interface and is responsible for the {@link ResultSet} managing aspects
	 * in respect of {@link Link}s.
	 */
	class LinkResultSetManager implements ResultSetManager<LinkID, Link>
	{
		/**
		 * Constructs a {@link LinkResultSetManager}.
		 */
		private LinkResultSetManager()
		{
		}

		@Override
		public LinkedHashMap<LinkID, Link> getNextPageData(ResultSetImpl<LinkID, Link> resultSet)
		{
			if(resultSet == null)
			{
				throw new NullPointerException();
			}

			return getDataFactory().getNextLinkPageData(resultSet);
		}
	}

	/**
	 * This <i>member</i> class implements the {@link ResultSetManager}
	 * interface and is responsible for the {@link ResultSet} managing aspects
	 * in respect of {@link Message}s.
	 */
	class MessageResultSetManager implements ResultSetManager<MessageID, Message>
	{
		/**
		 * Constructs a {@link MessageResultSetManager}.
		 */
		private MessageResultSetManager()
		{
		}

		@Override
		public LinkedHashMap<MessageID, Message> getNextPageData(ResultSetImpl<MessageID, Message> resultSet)
		{
			if(resultSet == null)
			{
				throw new NullPointerException();
			}

			return getDataFactory().getNextMessagePageData(resultSet);
		}
	}

	/**
	 * This <i>member</i> class implements the {@link ResultSetManager}
	 * interface and is responsible for the {@link ResultSet} managing aspects
	 * in respect of {@link Tag}s.
	 */
	class TagResultSetManager implements ResultSetManager<TagID, Tag>
	{
		/**
		 * Constructs a {@link TagResultSetManager}.
		 */
		private TagResultSetManager()
		{
		}

		@Override
		public LinkedHashMap<TagID, Tag> getNextPageData(ResultSetImpl<TagID, Tag> resultSet)
		{
			if(resultSet == null)
			{
				throw new NullPointerException();
			}

			return getDataFactory().getNextTagPageData(resultSet);
		}
	}

	/**
	 * This <i>member</i> class implements the {@link ResultSetManager}
	 * interface and is responsible for the {@link ResultSet} managing aspects
	 * in respect of {@link UserInfo}s.
	 */
	class UserInfoResultSetManager implements ResultSetManager<UserID, UserInfo>
	{
		/**
		 * Constructs a {@link UserInfoResultSetManager}.
		 */
		private UserInfoResultSetManager()
		{
		}

		@Override
		public LinkedHashMap<UserID, UserInfo> getNextPageData(ResultSetImpl<UserID, UserInfo> resultSet)
		{
			if(resultSet == null)
			{
				throw new NullPointerException();
			}

			return getDataFactory().getNextUserInfoPageData(resultSet);
		}
	}
}
