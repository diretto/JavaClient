package org.diretto.api.client.main.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.diretto.api.client.base.data.BoundingBox;
import org.diretto.api.client.base.data.Contributor;
import org.diretto.api.client.base.data.ExternalContributor;
import org.diretto.api.client.base.data.ExternalMediaType;
import org.diretto.api.client.base.data.MediaMainType;
import org.diretto.api.client.base.data.MediaType;
import org.diretto.api.client.base.data.MediaTypeFactory;
import org.diretto.api.client.base.data.PlatformContributor;
import org.diretto.api.client.base.data.PlatformMediaType;
import org.diretto.api.client.base.data.ResultSet;
import org.diretto.api.client.base.data.ResultSetFactory;
import org.diretto.api.client.base.data.ResultSetImpl;
import org.diretto.api.client.base.data.TimeRange;
import org.diretto.api.client.base.data.TopographicPoint;
import org.diretto.api.client.base.data.UploadInfo;
import org.diretto.api.client.base.data.Votes;
import org.diretto.api.client.base.entities.Entity;
import org.diretto.api.client.base.entities.EntityID;
import org.diretto.api.client.base.exceptions.NoResultsException;
import org.diretto.api.client.base.types.LoadType;
import org.diretto.api.client.base.types.MessageBoxType;
import org.diretto.api.client.base.types.VoteType;
import org.diretto.api.client.main.core.binding.base.AttachmentCreationResource;
import org.diretto.api.client.main.core.binding.base.DocumentCreationResource;
import org.diretto.api.client.main.core.binding.entities.AnonymousTagResource;
import org.diretto.api.client.main.core.binding.entities.AttachmentResource;
import org.diretto.api.client.main.core.binding.entities.CollectionResource;
import org.diretto.api.client.main.core.binding.entities.CommentResource;
import org.diretto.api.client.main.core.binding.entities.KeyValueResource;
import org.diretto.api.client.main.core.binding.entities.LinkResource;
import org.diretto.api.client.main.core.binding.entities.MessageResource;
import org.diretto.api.client.main.core.binding.entities.PositionResource;
import org.diretto.api.client.main.core.binding.entities.TagResource;
import org.diretto.api.client.main.core.binding.entities.TimeResource;
import org.diretto.api.client.main.core.binding.major.AnonymousTagResultPageResource;
import org.diretto.api.client.main.core.binding.major.BaseTagResource;
import org.diretto.api.client.main.core.binding.major.CollectionReceptionResource;
import org.diretto.api.client.main.core.binding.major.CommentResultPageResource;
import org.diretto.api.client.main.core.binding.major.DocumentFullResource;
import org.diretto.api.client.main.core.binding.major.DocumentMetaDataResource;
import org.diretto.api.client.main.core.binding.major.DocumentQueryResultPageResource;
import org.diretto.api.client.main.core.binding.major.DocumentResultPageResource;
import org.diretto.api.client.main.core.binding.major.DocumentSnapShotResource;
import org.diretto.api.client.main.core.binding.major.LinkResultPageResource;
import org.diretto.api.client.main.core.binding.major.MessageReceptionResource;
import org.diretto.api.client.main.core.binding.major.MessageResultPageResource;
import org.diretto.api.client.main.core.binding.major.MultipleTagsResource;
import org.diretto.api.client.main.core.binding.major.SupportedMediaTypesResource;
import org.diretto.api.client.main.core.binding.major.UserInfoResource;
import org.diretto.api.client.main.core.binding.major.UserInfoResultPageResource;
import org.diretto.api.client.main.core.binding.major.UserVoteResource;
import org.diretto.api.client.main.core.binding.post.CollectionCreationResource;
import org.diretto.api.client.main.core.binding.post.CommentCreationResource;
import org.diretto.api.client.main.core.binding.post.DispatchDocumentQueryResource;
import org.diretto.api.client.main.core.binding.post.DocumentHyperLinkResource;
import org.diretto.api.client.main.core.binding.post.ExternalAttachmentCreationResource;
import org.diretto.api.client.main.core.binding.post.ExternalDocumentCreationResource;
import org.diretto.api.client.main.core.binding.post.LinkCreationResource;
import org.diretto.api.client.main.core.binding.post.MessageCreationResource;
import org.diretto.api.client.main.core.binding.post.MultipleDocumentsRequestResource;
import org.diretto.api.client.main.core.binding.post.MultipleUsersRequestResource;
import org.diretto.api.client.main.core.binding.post.MultipleValuesRequestResource;
import org.diretto.api.client.main.core.binding.post.PlatformAttachmentCreationResource;
import org.diretto.api.client.main.core.binding.post.PlatformDocumentCreationResource;
import org.diretto.api.client.main.core.binding.post.TagCreationResource;
import org.diretto.api.client.main.core.binding.post.UserCreationResource;
import org.diretto.api.client.main.core.binding.post.ValueRequestResource;
import org.diretto.api.client.main.core.binding.resources.AttachmentsResource;
import org.diretto.api.client.main.core.binding.resources.BetweenRangeResource;
import org.diretto.api.client.main.core.binding.resources.CommentsResource;
import org.diretto.api.client.main.core.binding.resources.ContributorResource;
import org.diretto.api.client.main.core.binding.resources.ContributorResource.ContributorEntry;
import org.diretto.api.client.main.core.binding.resources.ContributorResource.External;
import org.diretto.api.client.main.core.binding.resources.ContributorResource.ExternalEntry;
import org.diretto.api.client.main.core.binding.resources.ContributorResource.PlatformEntry;
import org.diretto.api.client.main.core.binding.resources.DocumentLinkResource;
import org.diretto.api.client.main.core.binding.resources.DocumentQueryResource;
import org.diretto.api.client.main.core.binding.resources.HyperLinkResource;
import org.diretto.api.client.main.core.binding.resources.HyperLinkResourceWrapper;
import org.diretto.api.client.main.core.binding.resources.BoundingBoxResource;
import org.diretto.api.client.main.core.binding.resources.KeyValuesResource;
import org.diretto.api.client.main.core.binding.resources.PositionsResource;
import org.diretto.api.client.main.core.binding.resources.TagsResource;
import org.diretto.api.client.main.core.binding.resources.TimesResource;
import org.diretto.api.client.main.core.binding.resources.UserResource;
import org.diretto.api.client.main.core.binding.resources.MediaTypesResource.ExternalResource;
import org.diretto.api.client.main.core.binding.resources.MediaTypesResource.StoredResource;
import org.diretto.api.client.main.core.binding.resources.PositionCoordinatesResource;
import org.diretto.api.client.main.core.binding.resources.PositionCoordinatesResource.LocationResource;
import org.diretto.api.client.main.core.binding.resources.TimeRangeResource;
import org.diretto.api.client.main.core.binding.resources.UserHyperLinkResource;
import org.diretto.api.client.main.core.entities.AnonymousTagBuilder;
import org.diretto.api.client.main.core.entities.Attachment;
import org.diretto.api.client.main.core.entities.AttachmentBuilder;
import org.diretto.api.client.main.core.entities.AttachmentID;
import org.diretto.api.client.main.core.entities.Collection;
import org.diretto.api.client.main.core.entities.CollectionBuilder;
import org.diretto.api.client.main.core.entities.CollectionID;
import org.diretto.api.client.main.core.entities.Comment;
import org.diretto.api.client.main.core.entities.CommentBuilder;
import org.diretto.api.client.main.core.entities.CommentID;
import org.diretto.api.client.main.core.entities.CoreServiceEntityIDFactory;
import org.diretto.api.client.main.core.entities.Document;
import org.diretto.api.client.main.core.entities.DocumentBuilder;
import org.diretto.api.client.main.core.entities.DocumentID;
import org.diretto.api.client.main.core.entities.KeyValue;
import org.diretto.api.client.main.core.entities.KeyValueBuilder;
import org.diretto.api.client.main.core.entities.KeyValueID;
import org.diretto.api.client.main.core.entities.Link;
import org.diretto.api.client.main.core.entities.LinkBuilder;
import org.diretto.api.client.main.core.entities.LinkID;
import org.diretto.api.client.main.core.entities.Message;
import org.diretto.api.client.main.core.entities.MessageBuilder;
import org.diretto.api.client.main.core.entities.MessageID;
import org.diretto.api.client.main.core.entities.Position;
import org.diretto.api.client.main.core.entities.PositionBuilder;
import org.diretto.api.client.main.core.entities.PositionID;
import org.diretto.api.client.main.core.entities.Tag;
import org.diretto.api.client.main.core.entities.TagBuilder;
import org.diretto.api.client.main.core.entities.TagID;
import org.diretto.api.client.main.core.entities.Time;
import org.diretto.api.client.main.core.entities.TimeBuilder;
import org.diretto.api.client.main.core.entities.TimeID;
import org.diretto.api.client.main.core.entities.creation.AttachmentCreationData;
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

/**
 * The {@code DataFactory} is a manager class and particularly responsible for
 * the transformation between {@link Entity} objects and the POJO based
 * {@code Resource} classes. Thus it serves as interface between the
 * {@link DataManager} and the {@link ResourceManager}.
 * 
 * @author Tobias Schlecht
 */
final class DataFactory
{
	private final URL apiBaseURL;
	private final SystemSession systemSession;

	private final DataManagerImpl dataManager;
	private final CoreService coreService;
	private final ResourceManager resourceManager;

	/**
	 * The constructor is {@code private} to have strict control what instances
	 * exist at any time. Instead of the constructor the {@code public}
	 * <i>static factory method</i> {@link #getInstance(DataManagerImpl)}
	 * returns the instances of the class.
	 * 
	 * @param dataManager The corresponding {@code DataManager}
	 */
	private DataFactory(DataManagerImpl dataManager)
	{
		this.dataManager = dataManager;
		apiBaseURL = dataManager.getAPIBaseURL();
		coreService = dataManager.getCoreService();
		resourceManager = dataManager.getResourceManager();
		systemSession = dataManager.getSystemSession();
	}

	/**
	 * Returns a {@link DataFactory} instance for the corresponding
	 * {@link DataManager}.
	 * 
	 * @param dataManager The corresponding {@code DataManager}
	 * @return A {@code DataFactory} instance
	 */
	static synchronized DataFactory getInstance(DataManagerImpl dataManager)
	{
		return new DataFactory(dataManager);
	}

	/**
	 * @see DataManagerImpl#addAlternativePositionToDocument(UserSession,
	 *      DocumentID, TopographicPoint)
	 */
	PositionID addAlternativePositionToDocument(UserSession userSession, DocumentID documentID, TopographicPoint topographicPoint)
	{
		return resourceManager.addAlternativePositionToDocument(userSession, documentID, topographicPoint);
	}

	/**
	 * @see DataManagerImpl#addAlternativeTimeToDocument(UserSession,
	 *      DocumentID, TimeRange)
	 */
	TimeID addAlternativeTimeToDocument(UserSession userSession, DocumentID documentID, TimeRange timeRange)
	{
		return resourceManager.addAlternativeTimeToDocument(userSession, documentID, timeRange);
	}

	/**
	 * @see DataManagerImpl#addCommentToDocument(UserSession, DocumentID,
	 *      String)
	 */
	CommentID addCommentToDocument(UserSession userSession, DocumentID documentID, String content)
	{
		CommentCreationResource commentCreationResource = new CommentCreationResource();

		commentCreationResource.setContent(content);

		return resourceManager.addCommentToDocument(userSession, documentID, commentCreationResource);
	}

	/**
	 * @see DataManagerImpl#addDocumentToCollection(UserSession, CollectionID,
	 *      DocumentID)
	 */
	boolean addDocumentToCollection(UserSession userSession, CollectionID collectionID, DocumentID documentID)
	{
		DocumentHyperLinkResource documentHyperLinkResource = new DocumentHyperLinkResource();
		HyperLinkResourceWrapper document = new HyperLinkResourceWrapper();
		HyperLinkResource link = new HyperLinkResource();

		link.setRel("self");
		link.setHref(documentID.getUniqueResourceURL().toExternalForm());

		document.setLink(link);
		documentHyperLinkResource.setDocument(document);

		return resourceManager.addDocumentToCollection(userSession, collectionID, documentHyperLinkResource);
	}

	/**
	 * @see DataManagerImpl#addExternalAttachmentToDocument(UserSession,
	 *      DocumentID, ExternalAttachmentCreationData)
	 */
	AttachmentID addExternalAttachmentToDocument(UserSession userSession, DocumentID documentID, ExternalAttachmentCreationData externalAttachmentCreationData)
	{
		ExternalAttachmentCreationResource externalAttachmentCreationResource = new ExternalAttachmentCreationResource();

		setAttachmentCreationData(externalAttachmentCreationResource, externalAttachmentCreationData);

		externalAttachmentCreationResource.setMimeType(externalAttachmentCreationData.getExternalMediaType().getID());
		externalAttachmentCreationResource.setExternal(externalAttachmentCreationData.getFileURL().toExternalForm());

		return resourceManager.addExternalAttachmentToDocument(userSession, documentID, externalAttachmentCreationResource);
	}

	/**
	 * @see DataManagerImpl#addKeyValueToDocument(UserSession, DocumentID,
	 *      String, String)
	 */
	KeyValueID addKeyValueToDocument(UserSession userSession, DocumentID documentID, String key, String value)
	{
		ValueRequestResource valueRequestResource = new ValueRequestResource();

		valueRequestResource.setValue(value);

		return resourceManager.addKeyValueToDocument(userSession, documentID, key, valueRequestResource);
	}

	/**
	 * @see DataManagerImpl#addLinkBetweenDocuments(UserSession, DocumentID,
	 *      DocumentID, String, String)
	 */
	LinkID addLinkBetweenDocuments(UserSession userSession, DocumentID sourceDocumentID, DocumentID destinationDocumentID, String title, String description)
	{
		LinkCreationResource linkCreationResource = new LinkCreationResource();
		DocumentHyperLinkResource source = new DocumentHyperLinkResource();
		DocumentHyperLinkResource destination = new DocumentHyperLinkResource();
		HyperLinkResourceWrapper sourceDocument = new HyperLinkResourceWrapper();
		HyperLinkResourceWrapper destinationDocument = new HyperLinkResourceWrapper();
		HyperLinkResource sourceLink = new HyperLinkResource();
		HyperLinkResource destinationLink = new HyperLinkResource();

		sourceLink.setRel("self");
		sourceLink.setHref(sourceDocumentID.getUniqueResourceURL().toExternalForm());
		destinationLink.setRel("self");
		destinationLink.setHref(destinationDocumentID.getUniqueResourceURL().toExternalForm());

		sourceDocument.setLink(sourceLink);
		destinationDocument.setLink(destinationLink);
		source.setDocument(sourceDocument);
		destination.setDocument(destinationDocument);

		linkCreationResource.setTitle(title);
		linkCreationResource.setDescription(description);
		linkCreationResource.setSource(source);
		linkCreationResource.setDestination(destination);

		return resourceManager.addLinkBetweenDocuments(userSession, sourceDocumentID, linkCreationResource);
	}

	/**
	 * @see DataManagerImpl#addPlatformAttachmentToDocument(UserSession,
	 *      DocumentID, PlatformAttachmentCreationData)
	 */
	UploadInfo addPlatformAttachmentToDocument(UserSession userSession, DocumentID documentID, PlatformAttachmentCreationData platformAttachmentCreationData)
	{
		PlatformAttachmentCreationResource platformAttachmentCreationResource = new PlatformAttachmentCreationResource();

		setAttachmentCreationData(platformAttachmentCreationResource, platformAttachmentCreationData);

		platformAttachmentCreationResource.setMimeType(platformAttachmentCreationData.getPlatformMediaType().getID());
		platformAttachmentCreationResource.setFileSize(platformAttachmentCreationData.getFileSize());

		return resourceManager.addPlatformAttachmentToDocument(userSession, documentID, platformAttachmentCreationData.getPlatformMediaType(), platformAttachmentCreationResource);
	}

	/**
	 * @see DataManagerImpl#addTagToEntity(UserSession, EntityID, String)
	 */
	TagID addTagToEntity(UserSession userSession, EntityID entityID, String value)
	{
		TagCreationResource tagCreationResource = new TagCreationResource();

		tagCreationResource.setValue(value);

		return resourceManager.addTagToEntity(userSession, entityID, tagCreationResource);
	}

	/**
	 * @see DataManagerImpl#changeCollection(UserSession, CollectionID, String,
	 *      String, boolean)
	 */
	boolean changeCollection(UserSession userSession, CollectionID collectionID, String title, String description, boolean privateSetting)
	{
		CollectionCreationResource collectionChangeResource = new CollectionCreationResource();

		collectionChangeResource.setTitle(title);
		collectionChangeResource.setDescription(description);
		collectionChangeResource.setNonpublic(privateSetting);

		return resourceManager.changeCollection(userSession, collectionID, collectionChangeResource);
	}

	/**
	 * @see DataManagerImpl#changePassword(UserSession, String)
	 */
	boolean changePassword(UserSession userSession, String password)
	{
		UserCreationResource userCreationResource = new UserCreationResource();

		userCreationResource.setEmail(userSession.getUser().getEmailAddress());
		userCreationResource.setPassword(password);
		userCreationResource.setUsername(userSession.getUser().getUserInfo().getUserName());

		return resourceManager.changeUserData(userSession, userCreationResource);
	}

	/**
	 * @see DataManagerImpl#changeUserName(UserSession, String)
	 */
	boolean changeUserName(UserSession userSession, String userName)
	{
		UserCreationResource userCreationResource = new UserCreationResource();

		userCreationResource.setEmail(userSession.getUser().getEmailAddress());
		UserFactory.setPassword(userSession.getUser(), userCreationResource);
		userCreationResource.setUsername(userName);

		return resourceManager.changeUserData(userSession, userCreationResource);
	}

	/**
	 * @see DataManagerImpl#createCollection(UserSession, String, String,
	 *      boolean)
	 */
	CollectionID createCollection(UserSession userSession, String title, String description, boolean privateSetting)
	{
		CollectionCreationResource collectionCreationResource = new CollectionCreationResource();

		collectionCreationResource.setTitle(title);
		collectionCreationResource.setDescription(description);
		collectionCreationResource.setNonpublic(privateSetting);

		return resourceManager.createCollection(userSession, collectionCreationResource);
	}

	/**
	 * @see DataManagerImpl#createDocument(UserSession, UUID,
	 *      ExternalAttachmentCreationData, TopographicPoint, TimeRange)
	 */
	DocumentID createDocument(UserSession userSession, UUID uuid, ExternalAttachmentCreationData externalAttachmentCreationData, TopographicPoint topographicPoint, TimeRange timeRange)
	{
		ExternalDocumentCreationResource externalDocumentCreationResource = new ExternalDocumentCreationResource();

		setDocumentCreationData(externalDocumentCreationResource, externalAttachmentCreationData, topographicPoint, timeRange);

		externalDocumentCreationResource.setMimeType(externalAttachmentCreationData.getExternalMediaType().getID());
		externalDocumentCreationResource.setExternal(externalAttachmentCreationData.getFileURL().toExternalForm());

		return resourceManager.createDocument(userSession, uuid, externalDocumentCreationResource);
	}

	/**
	 * @see DataManagerImpl#createDocument(UserSession, UUID,
	 *      PlatformAttachmentCreationData, TopographicPoint, TimeRange)
	 */
	UploadInfo createDocument(UserSession userSession, UUID uuid, PlatformAttachmentCreationData platformAttachmentCreationData, TopographicPoint topographicPoint, TimeRange timeRange)
	{
		PlatformDocumentCreationResource platformDocumentCreationResource = new PlatformDocumentCreationResource();

		setDocumentCreationData(platformDocumentCreationResource, platformAttachmentCreationData, topographicPoint, timeRange);

		platformDocumentCreationResource.setMimeType(platformAttachmentCreationData.getPlatformMediaType().getID());
		platformDocumentCreationResource.setFileSize(platformAttachmentCreationData.getFileSize());

		return resourceManager.createDocument(userSession, uuid, platformAttachmentCreationData.getPlatformMediaType(), platformDocumentCreationResource);
	}

	/**
	 * @see DataManagerImpl#createUser(String, String, String)
	 */
	UserID createUser(String emailAddress, String password, String userName)
	{
		UserCreationResource userCreationResource = new UserCreationResource();

		userCreationResource.setEmail(emailAddress);
		userCreationResource.setPassword(password);
		userCreationResource.setUsername(userName);

		return resourceManager.createUser(userCreationResource);
	}

	/**
	 * @see DataManagerImpl#getAllDocuments(LoadType)
	 */
	ResultSetImpl<DocumentID, Document> getAllDocuments(LoadType loadType)
	{
		try
		{
			return createDocumentResultSet(resourceManager.getAllDocuments(), loadType);
		}
		catch(NoResultsException e)
		{
			return createEmptyDocumentResultSet(false, loadType);
		}
	}

	/**
	 * @see DataManagerImpl#getAllLinks()
	 */
	ResultSet<LinkID, Link> getAllLinks()
	{
		try
		{
			return createLinkResultSet(resourceManager.getAllLinks());
		}
		catch(NoResultsException e)
		{
			return createEmptyLinkResultSet();
		}
	}

	/**
	 * @see DataManagerImpl#getAllTags()
	 */
	ResultSet<TagID, Tag> getAllTags()
	{
		try
		{
			AnonymousTagResultPageResource anonymousTagResultPageResource = resourceManager.getAllTags();

			LinkedHashMap<TagID, Tag> resultMap = createAnonymousTagMap(anonymousTagResultPageResource);

			URL url = createNextPageURL(anonymousTagResultPageResource.getRelated());

			return ResultSetFactory.getResultSetInstance(dataManager.TAG_RESULT_SET_MANAGER, systemSession, false, LoadType.COMPLETE, resultMap, url);
		}
		catch(NoResultsException e)
		{
			return ResultSetFactory.getResultSetInstance(dataManager.TAG_RESULT_SET_MANAGER, systemSession, false, LoadType.COMPLETE, new LinkedHashMap<TagID, Tag>(), null);
		}
	}

	/**
	 * @see DataManagerImpl#getAllUserInfos()
	 */
	ResultSet<UserID, UserInfo> getAllUserInfos()
	{
		try
		{
			UserInfoResultPageResource userInfoResultPageResource = resourceManager.getAllUserInfos();

			LinkedHashMap<UserID, UserInfo> resultMap = createUserInfoMap(userInfoResultPageResource);

			URL url = createNextPageURL(userInfoResultPageResource.getRelated());

			return ResultSetFactory.getResultSetInstance(dataManager.USER_INFO_RESULT_SET_MANAGER, systemSession, false, LoadType.COMPLETE, resultMap, url);
		}
		catch(NoResultsException e)
		{
			return ResultSetFactory.getResultSetInstance(dataManager.USER_INFO_RESULT_SET_MANAGER, systemSession, false, LoadType.COMPLETE, new LinkedHashMap<UserID, UserInfo>(), null);
		}
	}

	/**
	 * @see DataManagerImpl#getCollection(UserSession, CollectionID)
	 */
	Collection getCollection(UserSession userSession, CollectionID collectionID)
	{
		return getCollectionWithDocuments(userSession, collectionID, null);
	}

	/**
	 * @see DataManagerImpl#getCollectionsByUser(UserSession, UserID)
	 */
	ResultSet<CollectionID, Collection> getCollectionsByUser(UserSession userSession, UserID creator)
	{
		return getCollectionsWithDocumentsByUser(userSession, creator, null);
	}

	/**
	 * @see DataManagerImpl#getCollectionsWithDocumentsByUser(UserSession,
	 *      UserID, LoadType)
	 */
	ResultSet<CollectionID, Collection> getCollectionsWithDocumentsByUser(UserSession userSession, UserID creator, LoadType loadType)
	{
		LinkedHashMap<CollectionID, Collection> collections = new LinkedHashMap<CollectionID, Collection>();

		ArrayList<CollectionReceptionResource> results = resourceManager.getCollectionsByUser(userSession, creator).getCollections();

		Collection collection;
		CollectionResource collectionResource;
		CollectionID collectionID;

		for(CollectionReceptionResource collectionReceptionResource : results)
		{
			collectionResource = collectionReceptionResource.getCollection();

			collectionID = CoreServiceEntityIDFactory.getCollectionIDInstance(collectionResource.getLink().getHref());

			collection = createCollection(userSession, collectionID, collectionResource, loadType);

			collections.put(collectionID, collection);
		}

		return ResultSetFactory.getResultSetInstance(null, userSession, false, LoadType.COMPLETE, collections, null);
	}

	/**
	 * @see DataManagerImpl#getCollectionWithDocuments(UserSession,
	 *      CollectionID, LoadType)
	 */
	Collection getCollectionWithDocuments(UserSession userSession, CollectionID collectionID, LoadType loadType)
	{
		CollectionReceptionResource collectionReceptionResource = resourceManager.getCollection(userSession, collectionID);

		if(collectionReceptionResource == null)
		{
			return null;
		}

		return createCollection(userSession, collectionID, collectionReceptionResource.getCollection(), loadType);
	}

	/**
	 * @see DataManagerImpl#getCommentsByUser(UserID)
	 */
	ResultSet<CommentID, Comment> getCommentsByUser(UserID userID)
	{
		try
		{
			return createCommentResultSet(resourceManager.getCommentsByUser(userID));
		}
		catch(NoResultsException e)
		{
			return ResultSetFactory.getResultSetInstance(dataManager.COMMENT_RESULT_SET_MANAGER, systemSession, false, LoadType.COMPLETE, new LinkedHashMap<CommentID, Comment>(), null);
		}
	}

	/**
	 * {@code forceAPICall} = {@code true}
	 * 
	 * @see DataManagerImpl#getDocument(DocumentID, LoadType, boolean)
	 */
	Document getDocument(DocumentID documentID, LoadType loadType)
	{
		if(loadType == LoadType.METADATA)
		{
			return createMetaDataDocument(documentID, resourceManager.getMetaDataDocument(documentID));
		}
		else if(loadType == LoadType.SNAPSHOT)
		{
			return createSnapShotDocument(documentID, resourceManager.getSnapShotDocument(documentID));
		}
		else
		{
			return createCompleteDocument(documentID, resourceManager.getFullDocument(documentID));
		}
	}

	/**
	 * @see DataManagerImpl#getDocuments(List, BoundingBox, TimeRange, LoadType)
	 */
	ResultSetImpl<DocumentID, Document> getDocuments(List<String> tags, BoundingBox boundingBox, TimeRange timeRange, LoadType loadType)
	{
		return getDocuments(tags, boundingBox, timeRange, null, loadType);
	}

	/**
	 * @see DataManagerImpl#getDocuments(List, BoundingBox, TimeRange,
	 *      TimeRange, LoadType)
	 */
	ResultSetImpl<DocumentID, Document> getDocuments(List<String> tags, BoundingBox boundingBox, TimeRange timeRange, TimeRange publishingTimeRange, LoadType loadType)
	{
		ArrayList<String> tagIDs = new ArrayList<String>();

		if(tags.size() > 0)
		{
			MultipleValuesRequestResource multipleValuesRequestResource = new MultipleValuesRequestResource();

			multipleValuesRequestResource.setValues(new ArrayList<String>(tags));

			MultipleTagsResource multipleTagsResource = resourceManager.getMultipleTags(multipleValuesRequestResource);
			LinkedHashMap<String, BaseTagResource> results = multipleTagsResource.getResults();

			for(String tag : tags)
			{
				tagIDs.add(results.get(tag).getBaseTag().getLink().getHref());
			}
		}

		DispatchDocumentQueryResource dispatchDocumentQueryResource = new DispatchDocumentQueryResource();
		DocumentQueryResource documentQueryResource = new DocumentQueryResource();
		TimeRangeResource timeRangeResource = new TimeRangeResource();
		BoundingBoxResource boundingBoxResource = new BoundingBoxResource();

		timeRangeResource.setStart(timeRange.getStartDateTime().toString(Time.ISO_UTC_DATE_TIME_FORMATTER));
		timeRangeResource.setEnd(timeRange.getEndDateTime().toString(Time.ISO_UTC_DATE_TIME_FORMATTER));
		boundingBoxResource.setBbox(boundingBox.getArrayList(1, 0, 3, 2));

		documentQueryResource.setTime(timeRangeResource);
		documentQueryResource.setLocation(boundingBoxResource);
		documentQueryResource.setTags(tagIDs);

		if(publishingTimeRange != null)
		{
			TimeRangeResource publishedBetweenResource = new TimeRangeResource();

			publishedBetweenResource.setStart(publishingTimeRange.getStartDateTime().toString(Time.ISO_UTC_DATE_TIME_FORMATTER));
			publishedBetweenResource.setEnd(publishingTimeRange.getEndDateTime().toString(Time.ISO_UTC_DATE_TIME_FORMATTER));

			documentQueryResource.setPublishedBetween(publishedBetweenResource);
		}
		else
		{
			documentQueryResource.setPublishedBetween(null);
		}

		dispatchDocumentQueryResource.setQuery(documentQueryResource);

		try
		{
			return createDocumentResultSet(resourceManager.executeQuery(dispatchDocumentQueryResource), loadType);
		}
		catch(NoResultsException e)
		{
			return createEmptyDocumentResultSet(true, loadType);
		}
	}

	/**
	 * @see DataManagerImpl#getDocumentsAfter(DateTime, LoadType)
	 */
	ResultSetImpl<DocumentID, Document> getDocumentsAfter(DateTime time, LoadType loadType)
	{
		try
		{
			return createDocumentResultSet(resourceManager.getDocumentsAfter(time), loadType);
		}
		catch(NoResultsException e)
		{
			return createEmptyDocumentResultSet(false, loadType);
		}
	}

	/**
	 * {@code forceAPICall} = {@code true}
	 * 
	 * @see DataManagerImpl#getDocumentsByIDs(List, LoadType, boolean)
	 */
	LinkedHashMap<DocumentID, Document> getDocumentsByIDs(List<DocumentID> documentIDs, LoadType loadType)
	{
		MultipleDocumentsRequestResource multipleDocumentsRequestResource = new MultipleDocumentsRequestResource();
		ArrayList<String> documentsList = new ArrayList<String>();

		for(DocumentID documentID : documentIDs)
		{
			documentsList.add(documentID.getUniqueResourceURL().toExternalForm());
		}

		multipleDocumentsRequestResource.setDocuments(documentsList);

		LinkedHashMap<DocumentID, Document> documents = new LinkedHashMap<DocumentID, Document>();

		if(loadType == LoadType.METADATA)
		{
			LinkedHashMap<String, DocumentMetaDataResource> results = resourceManager.getMultipleMetaDataDocuments(multipleDocumentsRequestResource).getResults();

			Document document;

			for(DocumentID documentID : documentIDs)
			{
				document = createMetaDataDocument(documentID, results.get(documentID.getUniqueResourceURL().toExternalForm()));

				documents.put(documentID, document);
			}
		}
		else if(loadType == LoadType.SNAPSHOT)
		{
			LinkedHashMap<String, DocumentSnapShotResource> results = resourceManager.getMultipleSnapShotDocuments(multipleDocumentsRequestResource).getResults();

			Document document;

			for(DocumentID documentID : documentIDs)
			{
				document = createSnapShotDocument(documentID, results.get(documentID.getUniqueResourceURL().toExternalForm()));

				documents.put(documentID, document);
			}
		}
		else
		{
			LinkedHashMap<String, DocumentFullResource> results = resourceManager.getMultipleFullDocuments(multipleDocumentsRequestResource).getResults();

			Document document;

			for(DocumentID documentID : documentIDs)
			{
				document = createCompleteDocument(documentID, results.get(documentID.getUniqueResourceURL().toExternalForm()));

				documents.put(documentID, document);
			}
		}

		return documents;
	}

	/**
	 * @see DataManagerImpl#getDocumentsByTag(String, LoadType)
	 */
	ResultSetImpl<DocumentID, Document> getDocumentsByTag(String value, LoadType loadType)
	{
		TagCreationResource tagCreationResource = new TagCreationResource();

		tagCreationResource.setValue(value);

		try
		{
			return createDocumentResultSet(resourceManager.getDocumentsByTag(tagCreationResource), loadType);
		}
		catch(NoResultsException e)
		{
			return createEmptyDocumentResultSet(false, loadType);
		}
	}

	/**
	 * @see DataManagerImpl#getDocumentsByUser(UserID, LoadType)
	 */
	ResultSetImpl<DocumentID, Document> getDocumentsByUser(UserID publisher, LoadType loadType)
	{
		try
		{
			return createDocumentResultSet(resourceManager.getDocumentsByUser(publisher), loadType);
		}
		catch(NoResultsException e)
		{
			return createEmptyDocumentResultSet(false, loadType);
		}
	}

	/**
	 * @see DataManagerImpl#getLinksAfter(DateTime)
	 */
	ResultSet<LinkID, Link> getLinksAfter(DateTime time)
	{
		try
		{
			return createLinkResultSet(resourceManager.getLinksAfter(time));
		}
		catch(NoResultsException e)
		{
			return createEmptyLinkResultSet();
		}
	}

	/**
	 * @see DataManagerImpl#getLinksByTag(String)
	 */
	ResultSet<LinkID, Link> getLinksByTag(String value)
	{
		TagCreationResource tagCreationResource = new TagCreationResource();

		tagCreationResource.setValue(value);

		try
		{
			return createLinkResultSet(resourceManager.getLinksByTag(tagCreationResource));
		}
		catch(NoResultsException e)
		{
			return createEmptyLinkResultSet();
		}
	}

	/**
	 * @see DataManagerImpl#getMessage(UserSession, MessageID)
	 */
	Message getMessage(UserSession userSession, MessageID messageID)
	{
		MessageReceptionResource messageReceptionResource = resourceManager.getMessage(userSession, messageID);

		if(messageReceptionResource == null)
		{
			return null;
		}

		return createMessage(messageID, messageReceptionResource.getMessage());
	}

	/**
	 * @see DataManagerImpl#getMessages(UserSession, MessageBoxType)
	 */
	ResultSet<MessageID, Message> getMessages(UserSession userSession, MessageBoxType messageBoxType)
	{
		try
		{
			return createMessageResultSet(resourceManager.getMessages(userSession, messageBoxType), userSession, messageBoxType);
		}
		catch(NoResultsException e)
		{
			return ResultSetFactory.getResultSetInstance(dataManager.MESSAGE_RESULT_SET_MANAGER, userSession, false, LoadType.COMPLETE, new LinkedHashMap<MessageID, Message>(), null);
		}
	}

	/**
	 * @see DataManagerImpl#getMessagesAfter(UserSession, MessageBoxType,
	 *      DateTime)
	 */
	ResultSet<MessageID, Message> getMessagesAfter(UserSession userSession, MessageBoxType messageBoxType, DateTime time)
	{
		try
		{
			return createMessageResultSet(resourceManager.getMessagesAfter(userSession, messageBoxType, time), userSession, messageBoxType);
		}
		catch(NoResultsException e)
		{
			return ResultSetFactory.getResultSetInstance(dataManager.MESSAGE_RESULT_SET_MANAGER, userSession, false, LoadType.COMPLETE, new LinkedHashMap<MessageID, Message>(), null);
		}
	}

	/**
	 * @see DataManagerImpl.CommentResultSetManager#getNextPageData(ResultSetImpl)
	 */
	LinkedHashMap<CommentID, Comment> getNextCommentPageData(ResultSetImpl<CommentID, Comment> resultSet)
	{
		CommentResultPageResource commentResultPageResource = resourceManager.getResultPage(CommentResultPageResource.class, resultSet.getNextPageURL());

		resultSet.setNextPageURL(createNextPageURL(commentResultPageResource.getRelated()));

		return createCommentMap(commentResultPageResource);
	}

	/**
	 * @see DataManagerImpl.DocumentResultSetManager#getNextPageData(ResultSetImpl)
	 */
	LinkedHashMap<DocumentID, Document> getNextDocumentPageData(ResultSetImpl<DocumentID, Document> resultSet)
	{
		if(resultSet.isQueryResultSet())
		{
			DocumentQueryResultPageResource documentQueryResultPageResource = resourceManager.getQueryResultPageResource(DocumentQueryResultPageResource.class, resultSet.getNextPageURL());

			resultSet.setNextPageURL(createNextPageURL(documentQueryResultPageResource.getResults().getPage().getRelated()));

			return getDocumentsByIDs(createDocumentIDs(documentQueryResultPageResource.getResults().getPage().getList()), resultSet.getLoadType());
		}
		else
		{
			DocumentResultPageResource documentResultPageResource = resourceManager.getResultPage(DocumentResultPageResource.class, resultSet.getNextPageURL());

			resultSet.setNextPageURL(createNextPageURL(documentResultPageResource.getRelated()));

			return getDocumentsByIDs(createDocumentIDs(documentResultPageResource.getList()), resultSet.getLoadType());
		}
	}

	/**
	 * @see DataManagerImpl.LinkResultSetManager#getNextPageData(ResultSetImpl)
	 */
	LinkedHashMap<LinkID, Link> getNextLinkPageData(ResultSetImpl<LinkID, Link> resultSet)
	{
		LinkResultPageResource linkResultPageResource = resourceManager.getResultPage(LinkResultPageResource.class, resultSet.getNextPageURL());

		resultSet.setNextPageURL(createNextPageURL(linkResultPageResource.getRelated()));

		return createLinkMap(linkResultPageResource);
	}

	/**
	 * @see DataManagerImpl.MessageResultSetManager#getNextPageData(ResultSetImpl)
	 */
	LinkedHashMap<MessageID, Message> getNextMessagePageData(ResultSetImpl<MessageID, Message> resultSet)
	{
		MessageResultPageResource messageResultPageResource = resourceManager.getResultPage(MessageResultPageResource.class, resultSet.getNextPageURL());

		resultSet.setNextPageURL(createNextPageURL(messageResultPageResource.getRelated()));

		String pageID = messageResultPageResource.getPage().getLink().getHref();
		String userID = resultSet.getSession().getUser().getID().getUniqueResourceURL().toExternalForm();

		String subString = pageID.trim().replaceFirst(userID.trim() + "/", "");

		MessageBoxType messageBoxType;

		if(subString.startsWith(MessageBoxType.OUTBOX.getURLParameter()))
		{
			messageBoxType = MessageBoxType.OUTBOX;
		}
		else
		{
			messageBoxType = MessageBoxType.INBOX;
		}

		return createMessageMap(messageResultPageResource, messageBoxType);
	}

	/**
	 * @see DataManagerImpl.TagResultSetManager#getNextPageData(ResultSetImpl)
	 */
	LinkedHashMap<TagID, Tag> getNextTagPageData(ResultSetImpl<TagID, Tag> resultSet)
	{
		AnonymousTagResultPageResource anonymousTagResultPageResource = resourceManager.getResultPage(AnonymousTagResultPageResource.class, resultSet.getNextPageURL());

		resultSet.setNextPageURL(createNextPageURL(anonymousTagResultPageResource.getRelated()));

		return createAnonymousTagMap(anonymousTagResultPageResource);
	}

	/**
	 * @see DataManagerImpl.UserInfoResultSetManager#getNextPageData(ResultSetImpl)
	 */
	LinkedHashMap<UserID, UserInfo> getNextUserInfoPageData(ResultSetImpl<UserID, UserInfo> resultSet)
	{
		UserInfoResultPageResource userInfoResultPageResource = resourceManager.getResultPage(UserInfoResultPageResource.class, resultSet.getNextPageURL());

		resultSet.setNextPageURL(createNextPageURL(userInfoResultPageResource.getRelated()));

		return createUserInfoMap(userInfoResultPageResource);
	}

	/**
	 * @see DataManagerImpl#getSupportedExternalMediaTypes()
	 */
	Map<String, ExternalMediaType> getSupportedExternalMediaTypes()
	{
		SupportedMediaTypesResource supportedMediaTypesResource = resourceManager.getSupportedMediaTypes();

		return createSupportedExternalMediaTypes(supportedMediaTypesResource.getMediatypes().getExternal());
	}

	/**
	 * @see DataManagerImpl#getSupportedMediaTypes()
	 */
	ArrayList<Map<String, ? extends MediaType>> getSupportedMediaTypes()
	{
		SupportedMediaTypesResource supportedMediaTypesResource = resourceManager.getSupportedMediaTypes();

		ArrayList<Map<String, ? extends MediaType>> supportedMediaTypes = new ArrayList<Map<String, ? extends MediaType>>();

		Map<String, PlatformMediaType> supportedPlatformMediaTypes = createSupportedPlatformMediaTypes(supportedMediaTypesResource.getMediatypes().getStored());
		Map<String, ExternalMediaType> supportedExternalMediaTypes = createSupportedExternalMediaTypes(supportedMediaTypesResource.getMediatypes().getExternal());

		supportedMediaTypes.add(0, supportedPlatformMediaTypes);
		supportedMediaTypes.add(1, supportedExternalMediaTypes);

		return supportedMediaTypes;
	}

	/**
	 * @see DataManagerImpl#getSupportedPlatformMediaTypes()
	 */
	Map<String, PlatformMediaType> getSupportedPlatformMediaTypes()
	{
		SupportedMediaTypesResource supportedMediaTypesResource = resourceManager.getSupportedMediaTypes();

		return createSupportedPlatformMediaTypes(supportedMediaTypesResource.getMediatypes().getStored());
	}

	/**
	 * @see DataManagerImpl#getUserInfo(UserID)
	 */
	UserInfo getUserInfo(UserID userID)
	{
		UserInfoResource userInfoResource = resourceManager.getUserInfo(userID);

		if(userInfoResource == null)
		{
			return null;
		}

		return UserFactory.getUserInfoInstance(userID, userInfoResource.getUser().getUsername());
	}

	/**
	 * Determines whether there is a {@link User} with the given data or not. If
	 * there is a {@code User} with the given data, the corresponding
	 * {@link UserInfo} will be returned; otherwise {@code null} will be
	 * returned.
	 * 
	 * @param emailAddress The Email address of the {@code User}
	 * @param password The password of the {@code User}
	 * @return The corresponding {@code UserInfo} or {@code null} if there is no
	 *         {@code User} with the given data
	 */
	UserInfo getUserInfo(String emailAddress, String password)
	{
		User user = UserFactory.getUserInstance(null, null, apiBaseURL, emailAddress, password);

		UserSession userSession = SessionFactory.getUserSessionInstance(user);

		UserInfoResource userInfoResource = resourceManager.getUserInfo(userSession);

		if(userInfoResource == null || userInfoResource.getUser().getEmail() == null)
		{
			return null;
		}

		UserID userID = UserFactory.getUserIDInstance(apiBaseURL, emailAddress);

		return UserFactory.getUserInfoInstance(userID, userInfoResource.getUser().getUsername());
	}

	/**
	 * @see DataManagerImpl#getUserInfosByIDs(List)
	 */
	ResultSet<UserID, UserInfo> getUserInfosByIDs(List<UserID> userIDs)
	{
		MultipleUsersRequestResource multipleUsersRequestResource = new MultipleUsersRequestResource();
		ArrayList<String> userInfosList = new ArrayList<String>();

		for(UserID userID : userIDs)
		{
			userInfosList.add(userID.getUniqueResourceURL().toExternalForm());
		}

		multipleUsersRequestResource.setUsers(userInfosList);

		LinkedHashMap<UserID, UserInfo> userInfos = new LinkedHashMap<UserID, UserInfo>();

		LinkedHashMap<String, UserInfoResource> results = resourceManager.getMultipleUserInfos(multipleUsersRequestResource).getResults();

		UserInfo userInfo;
		String userName;

		for(UserID userID : userIDs)
		{
			userName = results.get(userID.getUniqueResourceURL().toExternalForm()).getUser().getUsername();

			userInfo = UserFactory.getUserInfoInstance(userID, userName);

			userInfos.put(userID, userInfo);
		}

		return ResultSetFactory.getResultSetInstance(dataManager.USER_INFO_RESULT_SET_MANAGER, systemSession, false, LoadType.COMPLETE, userInfos, null);
	}

	/**
	 * @see DataManagerImpl#getUserVote(UserSession, EntityID)
	 */
	VoteType getUserVote(UserSession userSession, EntityID entityID)
	{
		UserVoteResource userVoteResource = resourceManager.getUserVote(userSession, entityID);

		return VoteType.getValue(userVoteResource.getVote());
	}

	/**
	 * @see DataManagerImpl#hasUser(String)
	 */
	boolean hasUser(String emailAddress)
	{
		UserID userID = UserFactory.getUserIDInstance(apiBaseURL, emailAddress);

		UserInfoResource userInfoResource = resourceManager.getUserInfo(userID);

		if(userInfoResource == null)
		{
			return false;
		}

		return true;
	}

	/**
	 * @see DataManagerImpl#hasUser(String, String)
	 */
	boolean hasUser(String emailAddress, String password)
	{
		if(getUserInfo(emailAddress, password) == null)
		{
			return false;
		}

		return true;
	}

	/**
	 * @see DataManagerImpl#removeCollection(UserSession, CollectionID)
	 */
	boolean removeCollection(UserSession userSession, CollectionID collectionID)
	{
		return resourceManager.removeCollection(userSession, collectionID);
	}

	/**
	 * @see DataManagerImpl#removeDocumentFromCollection(UserSession,
	 *      CollectionID, DocumentID)
	 */
	boolean removeDocumentFromCollection(UserSession userSession, CollectionID collectionID, DocumentID documentID)
	{
		return resourceManager.removeDocumentFromCollection(userSession, collectionID, documentID);
	}

	/**
	 * @see DataManagerImpl#removeKeyValueFromDocument(UserSession, DocumentID,
	 *      KeyValueID)
	 */
	boolean removeKeyValueFromDocument(UserSession userSession, KeyValueID keyValueID)
	{
		return resourceManager.removeKeyValueFromDocument(userSession, keyValueID);
	}

	/**
	 * @see DataManagerImpl#removeMessage(UserSession, MessageID)
	 */
	boolean removeMessage(UserSession userSession, MessageID messageID)
	{
		return resourceManager.removeMessage(userSession, messageID);
	}

	/**
	 * @see DataManagerImpl#removeUserVote(UserSession, EntityID)
	 */
	boolean removeUserVote(UserSession userSession, EntityID entityID)
	{
		return resourceManager.removeUserVote(userSession, entityID);
	}

	/**
	 * @see DataManagerImpl#sendMessage(UserSession, UserID, String, String)
	 */
	MessageID sendMessage(UserSession userSession, UserID receiver, String title, String content)
	{
		MessageCreationResource messageCreationResource = new MessageCreationResource();

		messageCreationResource.setTitle(title);
		messageCreationResource.setContent(content);

		return resourceManager.sendMessage(userSession, receiver, messageCreationResource);
	}

	/**
	 * @see DataManagerImpl#setUserVote(UserSession, VoteType, EntityID)
	 */
	boolean setUserVote(UserSession userSession, VoteType voteType, EntityID entityID)
	{
		return resourceManager.setUserVote(userSession, voteType, entityID);
	}

	/**
	 * Creates a {@link LinkedHashMap} from the given
	 * {@link AnonymousTagResultPageResource} and returns the
	 * {@code LinkedHashMap}.
	 * 
	 * @param anonymousTagResultPageResource The
	 *        {@code AnonymousTagResultPageResource}
	 * @return The created {@code LinkedHashMap}
	 */
	private LinkedHashMap<TagID, Tag> createAnonymousTagMap(AnonymousTagResultPageResource anonymousTagResultPageResource)
	{
		LinkedHashMap<TagID, Tag> resultMap = new LinkedHashMap<TagID, Tag>();

		ArrayList<AnonymousTagResultPageResource.ListEntry> list = anonymousTagResultPageResource.getList();

		AnonymousTagResource anonymousTagResource;
		TagID tagID;
		String value;
		DateTime creationTime;
		UserID creator;
		Tag tag;

		for(AnonymousTagResultPageResource.ListEntry listEntry : list)
		{
			anonymousTagResource = listEntry.getBaseTag();

			tagID = CoreServiceEntityIDFactory.getAnonymousTagIDInstance(anonymousTagResource.getLink().getHref());

			value = anonymousTagResource.getValue();
			creationTime = new DateTime(anonymousTagResource.getCreationTime());
			creator = UserFactory.getUserIDInstance(anonymousTagResource.getCreator().getLink().getHref());

			tag = new AnonymousTagBuilder(tagID).value(value).creationTime(creationTime).creator(creator).build();

			resultMap.put(tagID, tag);
		}

		return resultMap;
	}

	/**
	 * Creates an {@link Attachment} from the given {@link AttachmentResource}
	 * and returns the {@code Attachment}.
	 * 
	 * @param attachmentID The corresponding {@code AttachmentID}
	 * @param attachmentResource The {@code AttachmentResource}
	 * @return The created {@code Attachment}
	 */
	private Attachment createAttachment(AttachmentID attachmentID, AttachmentResource attachmentResource)
	{
		boolean isExternal;
		MediaType mediaType;
		long fileSize;
		URL fileURL;
		String fileURLString;

		if(attachmentResource.getExternal() == null)
		{
			isExternal = false;
			mediaType = coreService.getPlatformMediaType(attachmentResource.getMimeType());
			fileSize = attachmentResource.getFile().getSize();
			fileURLString = attachmentResource.getFile().getLink().getHref();
		}
		else
		{
			isExternal = true;
			mediaType = coreService.getExternalMediaType(attachmentResource.getMimeType());
			fileSize = 0;
			fileURLString = attachmentResource.getExternal().getLink().getHref();
		}

		try
		{
			fileURL = new URL(fileURLString);
		}
		catch(MalformedURLException e)
		{
			fileURL = null;
			e.printStackTrace();
		}

		String title = attachmentResource.getTitle();
		String description = attachmentResource.getDescription();
		DateTime publishingTime = new DateTime(attachmentResource.getPublishedTime());
		UserID publisher = UserFactory.getUserIDInstance(attachmentResource.getPublisher().getLink().getHref());
		Votes votes = new Votes(attachmentResource.getVotes().getUp(), attachmentResource.getVotes().getDown());
		String license = attachmentResource.getLicense();
		List<Contributor> contributors = createContributorsArray(attachmentResource.getContributors());

		List<UserID> creators = new ArrayList<UserID>();

		for(UserHyperLinkResource user : attachmentResource.getCreators())
		{
			creators.add(UserFactory.getUserIDInstance(user.getUser().getLink().getHref()));
		}

		return new AttachmentBuilder(attachmentID, dataManager, isExternal).title(title).description(description).publishingTime(publishingTime).publisher(publisher).mediaType(mediaType).fileSize(fileSize).fileURL(fileURL).votes(votes).license(license).contributors(contributors).creators(creators).build();
	}

	/**
	 * Converts an {@link AttachmentsResource} to a {@link ResultSet} of
	 * {@link Attachment}s and their mapped {@link AttachmentID}s.
	 * 
	 * @param attachmentsResource An {@code AttachmentsResource}
	 * @param documentID The corresponding {@code DocumentID}
	 * @return A {@code ResultSet} with the {@code Attachment}s
	 */
	private ResultSet<AttachmentID, Attachment> createAttachmentResultSet(AttachmentsResource attachmentsResource, DocumentID documentID)
	{
		LinkedHashMap<AttachmentID, Attachment> attachments = new LinkedHashMap<AttachmentID, Attachment>();

		AttachmentResource attachmentResource;
		AttachmentID attachmentID;
		Attachment attachment;

		for(AttachmentsResource.ListEntry listEntry : attachmentsResource.getList())
		{
			attachmentResource = listEntry.getAttachment();

			attachmentID = CoreServiceEntityIDFactory.getAttachmentIDInstance(attachmentResource.getLink().getHref(), documentID, documentID);

			attachment = createAttachment(attachmentID, attachmentResource);

			attachments.put(attachmentID, attachment);
		}

		return ResultSetFactory.getResultSetInstance(null, systemSession, false, LoadType.COMPLETE, attachments, null);
	}

	/**
	 * Creates a {@link Collection} from the given {@link CollectionResource}
	 * and returns the {@code Collection}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param collectionID The corresponding {@code CollectionID}
	 * @param collectionResource The {@code CollectionResource}
	 * @param loadType The desired {@code LoadType} of the {@code Document}s
	 *        within the {@code Collection}
	 * @return The created {@code Collection}
	 */
	private Collection createCollection(UserSession userSession, CollectionID collectionID, CollectionResource collectionResource, LoadType loadType)
	{
		String title = collectionResource.getTitle();
		String description = collectionResource.getDescription();
		DateTime creationTime = new DateTime(collectionResource.getCreationTime());
		UserID creator = UserFactory.getUserIDInstance(collectionResource.getCreator().getLink().getHref());
		boolean privateSetting = collectionResource.getNonpublic();

		ResultSet<DocumentID, Document> documents = null;
		DocumentResultPageResource documentResultPageResource = null;

		try
		{
			documentResultPageResource = resourceManager.getDocumentsByCollection(userSession, collectionID);

			if(documentResultPageResource == null)
			{
				throw new NoResultsException();
			}

			if(loadType == null)
			{
				try
				{
					URL nextPageURL = new URL(documentResultPageResource.getPage().getLink().getHref());

					documents = ResultSetFactory.getResultSetInstance(dataManager.DOCUMENT_RESULT_SET_MANAGER, userSession, false, loadType, new LinkedHashMap<DocumentID, Document>(), nextPageURL);
				}
				catch(MalformedURLException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				documents = createDocumentResultSet(documentResultPageResource, loadType);
			}
		}
		catch(NoResultsException e)
		{
			documents = ResultSetFactory.getResultSetInstance(dataManager.DOCUMENT_RESULT_SET_MANAGER, userSession, false, loadType, new LinkedHashMap<DocumentID, Document>(), null);
		}

		return new CollectionBuilder(collectionID, dataManager, documents).title(title).description(description).creationTime(creationTime).creator(creator).privateSetting(privateSetting).build();
	}

	/**
	 * Creates a {@link Comment} from the given {@link CommentResource} and
	 * returns the {@code Comment}.
	 * 
	 * @param commentID The corresponding {@code CommentID}
	 * @param commentResource The {@code CommentResource}
	 * @return The created {@code Comment}
	 */
	private Comment createComment(CommentID commentID, CommentResource commentResource)
	{
		String content = commentResource.getContent();
		DateTime creationTime = new DateTime(commentResource.getCreationTime());
		UserID creator = UserFactory.getUserIDInstance(commentResource.getCreator().getLink().getHref());
		Votes votes = new Votes(commentResource.getVotes().getUp(), commentResource.getVotes().getDown());

		return new CommentBuilder(commentID, dataManager).content(content).creationTime(creationTime).creator(creator).votes(votes).build();
	}

	/**
	 * Creates a {@link LinkedHashMap} from the given
	 * {@link CommentResultPageResource} and returns the {@code LinkedHashMap}.
	 * 
	 * @param commentResultPageResource The {@code CommentResultPageResource}
	 * @return The created {@code LinkedHashMap}
	 */
	private LinkedHashMap<CommentID, Comment> createCommentMap(CommentResultPageResource commentResultPageResource)
	{
		LinkedHashMap<CommentID, Comment> resultMap = new LinkedHashMap<CommentID, Comment>();

		CommentResource commentResource;
		String href;
		String documentIDString;
		DocumentID documentID;
		CommentID commentID;
		Comment comment;

		for(CommentResultPageResource.ListEntry listEntry : commentResultPageResource.getList())
		{
			commentResource = listEntry.getComment();

			href = commentResource.getLink().getHref().trim();
			documentIDString = href;

			if(documentIDString.endsWith("/"))
			{
				documentIDString = documentIDString.substring(0, documentIDString.length() - 1);
			}

			documentIDString = documentIDString.substring(0, documentIDString.lastIndexOf("/"));
			documentIDString = documentIDString.substring(0, documentIDString.lastIndexOf("/"));

			documentID = CoreServiceEntityIDFactory.getDocumentIDInstance(documentIDString);
			commentID = CoreServiceEntityIDFactory.getCommentIDInstance(href, documentID, documentID);

			comment = createComment(commentID, commentResource);

			resultMap.put(commentID, comment);
		}

		return resultMap;
	}

	/**
	 * Creates a {@link ResultSet} from the given
	 * {@link CommentResultPageResource} and returns the created
	 * {@code ResultSet}.
	 * 
	 * @param commentResultPageResource The {@code CommentResultPageResource}
	 * @return The created {@code ResultSet}
	 */
	private ResultSet<CommentID, Comment> createCommentResultSet(CommentResultPageResource commentResultPageResource)
	{
		LinkedHashMap<CommentID, Comment> resultMap = createCommentMap(commentResultPageResource);

		URL url = createNextPageURL(commentResultPageResource.getRelated());

		return ResultSetFactory.getResultSetInstance(dataManager.COMMENT_RESULT_SET_MANAGER, systemSession, false, LoadType.COMPLETE, resultMap, url);
	}

	/**
	 * Converts a {@link CommentsResource} to a {@link ResultSet} of
	 * {@link Comment}s and their mapped {@link CommentID}s.
	 * 
	 * @param commentsResource A {@code CommentsResource}
	 * @param documentID The corresponding {@code DocumentID}
	 * @return A {@code ResultSet} with the {@code Comment}s
	 */
	private ResultSet<CommentID, Comment> createCommentResultSet(CommentsResource commentsResource, DocumentID documentID)
	{
		LinkedHashMap<CommentID, Comment> comments = new LinkedHashMap<CommentID, Comment>();

		CommentResource commentResource;
		CommentID commentID;
		Comment comment;

		for(CommentsResource.ListEntry listEntry : commentsResource.getList())
		{
			commentResource = listEntry.getComment();

			commentID = CoreServiceEntityIDFactory.getCommentIDInstance(commentResource.getLink().getHref(), documentID, documentID);

			comment = createComment(commentID, commentResource);

			comments.put(commentID, comment);
		}

		return ResultSetFactory.getResultSetInstance(null, systemSession, false, LoadType.COMPLETE, comments, null);
	}

	/**
	 * Creates a {@link Document} from the given {@link DocumentFullResource}
	 * and returns the {@code Document}.
	 * 
	 * @param documentID The corresponding {@code DocumentID}
	 * @param documentFullResource The {@code DocumentFullResource}
	 * @return The created {@code Document}
	 */
	private Document createCompleteDocument(DocumentID documentID, DocumentFullResource documentFullResource)
	{
		DocumentFullResource.DocumentResource documentResource = documentFullResource.getDocument();

		MediaMainType mediaMainType = MediaTypeFactory.getMediaMainType(documentResource.getMediaType());
		DateTime publishingTime = new DateTime(documentResource.getPublishedTime());
		UserID publisher = UserFactory.getUserIDInstance(documentResource.getPublisher().getLink().getHref());
		String title = documentResource.getTitle();
		String description = documentResource.getDescription();

		ResultSet<CommentID, Comment> comments = createCommentResultSet(documentResource.getComments(), documentID);
		ResultSet<TagID, Tag> tags = createTagResultSet(documentResource.getTags(), documentID, documentID);
		ResultSet<AttachmentID, Attachment> attachments = createAttachmentResultSet(documentResource.getAttachments(), documentID);
		ResultSet<PositionID, Position> positions = createPositionResultSet(documentResource.getLocations(), documentID);
		ResultSet<TimeID, Time> times = createTimeResultSet(documentResource.getTimes(), documentID);
		ResultSet<KeyValueID, KeyValue> keyValues = createKeyValueResultSet(documentResource.getValues(), documentID);
		ResultSet<LinkID, Link> incomingLinks = createLinkResultSet(documentResource.getDocumentLinks().getIn(), documentID);
		ResultSet<LinkID, Link> outgoingLinks = createLinkResultSet(documentResource.getDocumentLinks().getOut(), documentID);

		return new DocumentBuilder(documentID, dataManager, LoadType.COMPLETE).mediaMainType(mediaMainType).publishingTime(publishingTime).publisher(publisher).title(title).description(description).comments(comments).tags(tags).attachments(attachments).positions(positions).times(times).keyValues(keyValues).incomingLinks(incomingLinks).outgoingLinks(outgoingLinks).build();
	}

	/**
	 * Transforms the given {@link ArrayList} of {@link ContributorResource}s to
	 * an {@code ArrayList} of {@link Contributor}s.
	 * 
	 * @param contributorResources The {@code ArrayList} of
	 *        {@code ContributorResource}s
	 * @return The {@code ArrayList} of {@code Contributor}s
	 */
	private ArrayList<Contributor> createContributorsArray(ArrayList<ContributorResource> contributorResources)
	{
		ArrayList<Contributor> contributors = new ArrayList<Contributor>();

		Contributor contributor;
		URL externalContributorLink;

		for(ContributorResource contributorResource : contributorResources)
		{
			if(contributorResource.getExternal() == null)
			{
				contributor = new PlatformContributor(UserFactory.getUserIDInstance(contributorResource.getUser().getLink().getHref()));
			}
			else
			{
				if(contributorResource.getExternal().getLink() == null)
				{
					externalContributorLink = null;
				}
				else
				{
					try
					{
						externalContributorLink = new URL(contributorResource.getExternal().getLink().getHref());
					}
					catch(MalformedURLException e)
					{
						externalContributorLink = null;
						e.printStackTrace();
					}
				}

				contributor = new ExternalContributor(contributorResource.getExternal().getName(), externalContributorLink);
			}

			contributors.add(contributor);
		}

		return contributors;
	}

	/**
	 * Transforms the given {@link ArrayList} of {@link Contributor}s to an
	 * {@code ArrayList} of {@link ContributorEntry}s.
	 * 
	 * @param contributors The {@code ArrayList} of {@code Contributor}s
	 * @return The {@code ArrayList} of {@code ContributorEntry}s
	 */
	private ArrayList<ContributorEntry> createContributorsEntryArray(ArrayList<Contributor> contributors)
	{
		ArrayList<ContributorEntry> contributorsResourceArray = new ArrayList<ContributorEntry>();

		ExternalContributor externalContributor;
		ExternalEntry externalEntry;
		External external;
		HyperLinkResource link;
		PlatformContributor platformContributor;
		PlatformEntry platformEntry;
		HyperLinkResourceWrapper user;

		for(Contributor contributor : contributors)
		{
			if(contributor.isExternalContributor())
			{
				externalContributor = (ExternalContributor) contributor;

				externalEntry = new ExternalEntry();
				external = new External();
				link = new HyperLinkResource();

				external.setName(externalContributor.getName());

				if(externalContributor.getSite() != null)
				{
					link.setRel("self");
					link.setHref(externalContributor.getSite().toExternalForm());
				}
				else
				{
					link = null;
				}

				external.setLink(link);

				externalEntry.setExternal(external);

				contributorsResourceArray.add(externalEntry);
			}
			else
			{
				platformContributor = (PlatformContributor) contributor;

				platformEntry = new PlatformEntry();
				user = new HyperLinkResourceWrapper();
				link = new HyperLinkResource();

				link.setRel("self");
				link.setHref(platformContributor.getUserID().getUniqueResourceURL().toExternalForm());

				user.setLink(link);

				platformEntry.setUser(user);

				contributorsResourceArray.add(platformEntry);
			}
		}

		return contributorsResourceArray;
	}

	/**
	 * Extracts the {@link List} of {@link DocumentID}s from the given
	 * {@link ArrayList} of {@link DocumentHyperLinkResource}s.
	 * 
	 * @param documentHyperLinkResources The {@code ArrayList} of
	 *        {@code DocumentHyperLinkResource}s
	 * @return The {@code List} of {@code DocumentID}s
	 */
	private List<DocumentID> createDocumentIDs(ArrayList<DocumentHyperLinkResource> documentHyperLinkResources)
	{
		List<DocumentID> documentIDs = new ArrayList<DocumentID>();

		for(DocumentHyperLinkResource documentHyperLink : documentHyperLinkResources)
		{
			documentIDs.add(CoreServiceEntityIDFactory.getDocumentIDInstance(documentHyperLink.getDocument().getLink().getHref()));
		}

		return documentIDs;
	}

	/**
	 * Creates a {@link ResultSet} from the given
	 * {@link DocumentQueryResultPageResource} and returns the created
	 * {@code ResultSet}.
	 * 
	 * @param documentQueryResultPageResource The
	 *        {@code DocumentQueryResultPageResource}
	 * @param loadType The desired {@code LoadType}
	 * @return The created {@code ResultSet}
	 */
	private ResultSetImpl<DocumentID, Document> createDocumentResultSet(DocumentQueryResultPageResource documentQueryResultPageResource, LoadType loadType)
	{
		List<DocumentID> documentIDs = createDocumentIDs(documentQueryResultPageResource.getResults().getPage().getList());
		URL url = createNextPageURL(documentQueryResultPageResource.getResults().getPage().getRelated());

		return (ResultSetImpl<DocumentID, Document>) ResultSetFactory.getResultSetInstance(dataManager.DOCUMENT_RESULT_SET_MANAGER, systemSession, true, loadType, getDocumentsByIDs(documentIDs, loadType), url);
	}

	/**
	 * Creates a {@link ResultSet} from the given
	 * {@link DocumentResultPageResource} and returns the created
	 * {@code ResultSet}.
	 * 
	 * @param documentResultPageResource The {@code DocumentResultPageResource}
	 * @param loadType The desired {@code LoadType}
	 * @return The created {@code ResultSet}
	 */
	private ResultSetImpl<DocumentID, Document> createDocumentResultSet(DocumentResultPageResource documentResultPageResource, LoadType loadType)
	{
		List<DocumentID> documentIDs = createDocumentIDs(documentResultPageResource.getList());
		URL url = createNextPageURL(documentResultPageResource.getRelated());

		return (ResultSetImpl<DocumentID, Document>) ResultSetFactory.getResultSetInstance(dataManager.DOCUMENT_RESULT_SET_MANAGER, systemSession, false, loadType, getDocumentsByIDs(documentIDs, loadType), url);
	}

	/**
	 * Creates and returns an empty {@link Document} {@link ResultSet}.
	 * 
	 * @param isQueryResultSet {@code true} if the {@code ResultSet} derives
	 *        from a query result page; otherwise {@code false}
	 * @param loadType The desired {@code LoadType}
	 * @return An empty {@code ResultSet}
	 */
	private ResultSetImpl<DocumentID, Document> createEmptyDocumentResultSet(boolean isQueryResultSet, LoadType loadType)
	{
		return (ResultSetImpl<DocumentID, Document>) ResultSetFactory.getResultSetInstance(dataManager.DOCUMENT_RESULT_SET_MANAGER, systemSession, isQueryResultSet, loadType, new LinkedHashMap<DocumentID, Document>(), null);
	}

	/**
	 * Creates and returns an empty {@link Link} {@link ResultSet}.
	 * 
	 * @return An empty {@code ResultSet}
	 */
	private ResultSetImpl<LinkID, Link> createEmptyLinkResultSet()
	{
		return (ResultSetImpl<LinkID, Link>) ResultSetFactory.getResultSetInstance(dataManager.LINK_RESULT_SET_MANAGER, systemSession, false, LoadType.COMPLETE, new LinkedHashMap<LinkID, Link>(), null);
	}

	/**
	 * Creates a {@link KeyValue} from the given {@link KeyValueResource} and
	 * returns the {@code KeyValue}.
	 * 
	 * @param keyValueID The corresponding {@code KeyValueID}
	 * @param keyValueResource The {@code KeyValueResource}
	 * @return The created {@code KeyValue}
	 */
	private KeyValue createKeyValue(KeyValueID keyValueID, KeyValueResource keyValueResource)
	{
		String keyValueIDString = keyValueID.getUniqueResourceURL().toExternalForm();
		String nameSpace = keyValueResource.getKey().getNamespace();

		String userIDString = keyValueIDString.trim();

		if(userIDString.endsWith("/"))
		{
			userIDString = userIDString.substring(0, userIDString.length() - 1);
		}

		for(int i = 0; i < 5; i++)
		{
			userIDString = userIDString.substring(0, userIDString.lastIndexOf("/"));
		}

		userIDString = userIDString + "/user/" + nameSpace.trim();

		UserID creator = UserFactory.getUserIDInstance(userIDString);
		String key = keyValueResource.getKey().getField();
		String value = keyValueResource.getValue();

		return new KeyValueBuilder(keyValueID).creator(creator).key(key).value(value).build();
	}

	/**
	 * Converts a {@link KeyValuesResource} to a {@link ResultSet} of
	 * {@link KeyValue}s and their mapped {@link KeyValueID}s.
	 * 
	 * @param keyValuesResource A {@code KeyValuesResource}
	 * @param documentID The corresponding {@code DocumentID}
	 * @return A {@code ResultSet} with the {@code KeyValue}s
	 */
	private ResultSet<KeyValueID, KeyValue> createKeyValueResultSet(KeyValuesResource keyValuesResource, DocumentID documentID)
	{
		LinkedHashMap<KeyValueID, KeyValue> keyValues = new LinkedHashMap<KeyValueID, KeyValue>();

		KeyValueID keyValueID;
		KeyValue keyValue;

		for(KeyValueResource keyValueResource : keyValuesResource.getList())
		{
			keyValueID = CoreServiceEntityIDFactory.getKeyValueIDInstance(keyValueResource.getLink().getHref(), documentID, documentID);

			keyValue = createKeyValue(keyValueID, keyValueResource);

			keyValues.put(keyValueID, keyValue);
		}

		return ResultSetFactory.getResultSetInstance(null, systemSession, false, LoadType.COMPLETE, keyValues, null);
	}

	/**
	 * Creates a {@link Link} from the given {@link LinkResource} and returns
	 * the {@code Link}.
	 * 
	 * @param linkID The corresponding {@code LinkID}
	 * @param linkResource The {@code LinkResource}
	 * @return The created {@code Link}
	 */
	private Link createLink(LinkID linkID, LinkResource linkResource)
	{
		DocumentID sourceDocumentID = CoreServiceEntityIDFactory.getDocumentIDInstance(linkResource.getSource().getDocument().getLink().getHref());
		DocumentID destinationDocumentID = CoreServiceEntityIDFactory.getDocumentIDInstance(linkResource.getDestination().getDocument().getLink().getHref());
		String title = linkResource.getTitle();
		String description = linkResource.getDescription();
		DateTime creationTime = new DateTime(linkResource.getCreationTime());
		UserID creator = UserFactory.getUserIDInstance(linkResource.getCreator().getLink().getHref());
		Votes votes = new Votes(linkResource.getVotes().getUp(), linkResource.getVotes().getDown());

		ResultSet<TagID, Tag> tags = createTagResultSet(linkResource.getTags(), linkID.getRootID(), linkID);

		return new LinkBuilder(linkID, dataManager, dataManager, sourceDocumentID, destinationDocumentID).title(title).description(description).creationTime(creationTime).creator(creator).votes(votes).tags(tags).build();
	}

	/**
	 * Creates a {@link LinkedHashMap} from the given
	 * {@link LinkResultPageResource} and returns the {@code LinkedHashMap}.
	 * 
	 * @param linkResultPageResource The {@code LinkResultPageResource}
	 * @return The created {@code LinkedHashMap}
	 */
	private LinkedHashMap<LinkID, Link> createLinkMap(LinkResultPageResource linkResultPageResource)
	{
		LinkedHashMap<LinkID, Link> resultMap = new LinkedHashMap<LinkID, Link>();

		LinkResource linkResource;
		DocumentID sourceDocumentID;
		LinkID linkID;
		Link link;

		for(DocumentLinkResource documentLinkResource : linkResultPageResource.getList())
		{
			linkResource = documentLinkResource.getDocumentLink();

			sourceDocumentID = CoreServiceEntityIDFactory.getDocumentIDInstance(linkResource.getSource().getDocument().getLink().getHref());

			linkID = CoreServiceEntityIDFactory.getLinkIDInstance(linkResource.getLink().getHref(), sourceDocumentID, sourceDocumentID);

			link = createLink(linkID, linkResource);

			resultMap.put(linkID, link);
		}

		return resultMap;
	}

	/**
	 * Converts the given {@link ArrayList} of {@link DocumentLinkResource}s to
	 * a {@link ResultSet} of {@link Link}s and their mapped {@link LinkID}s.
	 * 
	 * @param documentLinkResources An {@code ArrayList} of
	 *        {@code DocumentLinkResource}s
	 * @param documentID The corresponding {@code DocumentID}
	 * @return A {@code ResultSet} with the {@code Link}s
	 */
	private ResultSet<LinkID, Link> createLinkResultSet(ArrayList<DocumentLinkResource> documentLinkResources, DocumentID documentID)
	{
		LinkedHashMap<LinkID, Link> links = new LinkedHashMap<LinkID, Link>();

		LinkResource linkResource;
		LinkID linkID;
		Link link;

		for(DocumentLinkResource documentLinkResource : documentLinkResources)
		{
			linkResource = documentLinkResource.getDocumentLink();

			linkID = CoreServiceEntityIDFactory.getLinkIDInstance(linkResource.getLink().getHref(), documentID, documentID);

			link = createLink(linkID, linkResource);

			links.put(linkID, link);
		}

		return ResultSetFactory.getResultSetInstance(null, systemSession, false, LoadType.COMPLETE, links, null);
	}

	/**
	 * Creates a {@link ResultSet} from the given {@link LinkResultPageResource}
	 * and returns the created {@code ResultSet}.
	 * 
	 * @param linkResultPageResource The {@code LinkResultPageResource}
	 * @return The created {@code ResultSet}
	 */
	private ResultSet<LinkID, Link> createLinkResultSet(LinkResultPageResource linkResultPageResource)
	{
		LinkedHashMap<LinkID, Link> resultMap = createLinkMap(linkResultPageResource);

		URL url = createNextPageURL(linkResultPageResource.getRelated());

		return ResultSetFactory.getResultSetInstance(dataManager.LINK_RESULT_SET_MANAGER, systemSession, false, LoadType.COMPLETE, resultMap, url);
	}

	/**
	 * Creates a {@link Message} from the given {@link MessageResource} and
	 * returns the {@code Message}.
	 * 
	 * @param messageID The corresponding {@code MessageID}
	 * @param messageResource The {@code MessageResource}
	 * @return The created {@code Message}
	 */
	private Message createMessage(MessageID messageID, MessageResource messageResource)
	{
		DateTime creationTime = new DateTime(messageResource.getCreationTime());
		UserID sender = UserFactory.getUserIDInstance(messageResource.getSender().getLink().getHref());
		UserID receiver = UserFactory.getUserIDInstance(messageResource.getReceiver().getLink().getHref());
		String title = messageResource.getTitle();
		String content = messageResource.getContent();

		return new MessageBuilder(messageID).creationTime(creationTime).sender(sender).receiver(receiver).title(title).content(content).build();
	}

	/**
	 * Creates a {@link LinkedHashMap} from the given
	 * {@link MessageResultPageResource} and returns the {@code LinkedHashMap}.
	 * 
	 * @param messageResultPageResource The {@code MessageResultPageResource}
	 * @param messageBoxType The {@code MessageBoxType}
	 * @return The created {@code LinkedHashMap}
	 */
	private LinkedHashMap<MessageID, Message> createMessageMap(MessageResultPageResource messageResultPageResource, MessageBoxType messageBoxType)
	{
		LinkedHashMap<MessageID, Message> resultMap = new LinkedHashMap<MessageID, Message>();

		MessageResource messageResource;
		MessageID messageID;
		Message message;

		for(MessageReceptionResource messageReceptionResource : messageResultPageResource.getList())
		{
			messageResource = messageReceptionResource.getMessage();

			if(messageBoxType == MessageBoxType.OUTBOX)
			{
				messageID = CoreServiceEntityIDFactory.getMessageIDInstance(messageResource.getLinks().get(0).getLink().getHref());
			}
			else
			{
				messageID = CoreServiceEntityIDFactory.getMessageIDInstance(messageResource.getLinks().get(1).getLink().getHref());
			}

			message = createMessage(messageID, messageResource);

			resultMap.put(messageID, message);
		}

		return resultMap;
	}

	/**
	 * Creates a {@link ResultSet} from the given
	 * {@link MessageResultPageResource} and returns the created
	 * {@code ResultSet}.
	 * 
	 * @param messageResultPageResource The {@code MessageResultPageResource}
	 * @param userSession The corresponding {@code UserSession}
	 * @param messageBoxType The {@code MessageBoxType}
	 * @return The created {@code ResultSet}
	 */
	private ResultSet<MessageID, Message> createMessageResultSet(MessageResultPageResource messageResultPageResource, UserSession userSession, MessageBoxType messageBoxType)
	{
		LinkedHashMap<MessageID, Message> resultMap = createMessageMap(messageResultPageResource, messageBoxType);

		URL url = createNextPageURL(messageResultPageResource.getRelated());

		return ResultSetFactory.getResultSetInstance(dataManager.MESSAGE_RESULT_SET_MANAGER, userSession, false, LoadType.COMPLETE, resultMap, url);
	}

	/**
	 * Creates a {@link Document} from the given
	 * {@link DocumentMetaDataResource} and returns the {@code Document}.
	 * 
	 * @param documentID The corresponding {@code DocumentID}
	 * @param documentMetaDataResource The {@code DocumentMetaDataResource}
	 * @return The created {@code Document}
	 */
	private Document createMetaDataDocument(DocumentID documentID, DocumentMetaDataResource documentMetaDataResource)
	{
		DocumentMetaDataResource.DocumentResource documentResource = documentMetaDataResource.getDocument();

		MediaMainType mediaMainType = MediaTypeFactory.getMediaMainType(documentResource.getMediaType());
		DateTime publishingTime = new DateTime(documentResource.getPublishedTime());
		UserID publisher = UserFactory.getUserIDInstance(documentResource.getPublisher().getLink().getHref());
		String title = documentResource.getTitle();
		String description = documentResource.getDescription();

		return new DocumentBuilder(documentID, dataManager, LoadType.METADATA).mediaMainType(mediaMainType).publishingTime(publishingTime).publisher(publisher).title(title).description(description).build();
	}

	/**
	 * Extracts the {@link URL} of the next page from the given {@code related}
	 * resource.
	 * 
	 * @param related The {@code related} list resource
	 * @return The {@code URL} of the next page
	 */
	private URL createNextPageURL(ArrayList<HyperLinkResourceWrapper> related)
	{
		URL url = null;

		for(HyperLinkResourceWrapper linkResourceWrapper : related)
		{
			if(linkResourceWrapper.getLink().getRel().equals("next"))
			{
				try
				{
					return new URL(linkResourceWrapper.getLink().getHref());
				}
				catch(MalformedURLException e)
				{
					e.printStackTrace();
				}
			}
		}

		return url;
	}

	/**
	 * Creates a {@link Position} from the given {@link PositionResource} and
	 * returns the {@code Position}.
	 * 
	 * @param positionID The corresponding {@code PositionID}
	 * @param positionResource The {@code PositionResource}
	 * @return The created {@code Position}
	 */
	private Position createPosition(PositionID positionID, PositionResource positionResource)
	{
		double latitude = positionResource.getPosition().getCoordinates().get(1);
		double longitude = positionResource.getPosition().getCoordinates().get(0);
		int variance = positionResource.getVariance();
		DateTime creationTime = new DateTime(positionResource.getCreationTime());
		UserID creator = UserFactory.getUserIDInstance(positionResource.getCreator().getLink().getHref());
		Votes votes = new Votes(positionResource.getVotes().getUp(), positionResource.getVotes().getDown());

		return new PositionBuilder(positionID, dataManager).latitude(latitude).longitude(longitude).variance(variance).creationTime(creationTime).creator(creator).votes(votes).build();
	}

	/**
	 * Converts a {@link PositionsResource} to a {@link ResultSet} of
	 * {@link Position}s and their mapped {@link PositionID}s.
	 * 
	 * @param positionsResource A {@code PositionsResource}
	 * @param documentID The corresponding {@code DocumentID}
	 * @return A {@code ResultSet} with the {@code Position}s
	 */
	private ResultSet<PositionID, Position> createPositionResultSet(PositionsResource positionsResource, DocumentID documentID)
	{
		LinkedHashMap<PositionID, Position> positions = new LinkedHashMap<PositionID, Position>();

		PositionResource positionResource;
		PositionID positionID;
		Position position;

		for(PositionsResource.ListEntry listEntry : positionsResource.getList())
		{
			positionResource = listEntry.getLocation();

			positionID = CoreServiceEntityIDFactory.getPositionIDInstance(positionResource.getLink().getHref(), documentID, documentID);

			position = createPosition(positionID, positionResource);

			positions.put(positionID, position);
		}

		return ResultSetFactory.getResultSetInstance(null, systemSession, false, LoadType.COMPLETE, positions, null);
	}

	/**
	 * Creates a {@link Document} from the given
	 * {@link DocumentSnapShotResource} and returns the {@code Document}.
	 * 
	 * @param documentID The corresponding {@code DocumentID}
	 * @param documentSnapShotResource The {@code DocumentSnapShotResource}
	 * @return The created {@code Document}
	 */
	private Document createSnapShotDocument(DocumentID documentID, DocumentSnapShotResource documentSnapShotResource)
	{
		DocumentSnapShotResource.DocumentResource documentResource = documentSnapShotResource.getDocument();

		MediaMainType mediaMainType = MediaTypeFactory.getMediaMainType(documentResource.getMediaType());
		DateTime publishingTime = new DateTime(documentResource.getPublishedTime());
		UserID publisher = UserFactory.getUserIDInstance(documentResource.getPublisher().getLink().getHref());
		String title = documentResource.getTitle();
		String description = documentResource.getDescription();

		ResultSet<TagID, Tag> tags = createTagResultSet(documentResource.getTags(), documentID, documentID);

		LinkedHashMap<AttachmentID, Attachment> attachmentMap = new LinkedHashMap<AttachmentID, Attachment>();
		AttachmentID attachmentID = CoreServiceEntityIDFactory.getAttachmentIDInstance(documentResource.getSnapshot().getAttachment().getLink().getHref(), documentID, documentID);
		attachmentMap.put(attachmentID, createAttachment(attachmentID, documentResource.getSnapshot().getAttachment()));

		ResultSet<AttachmentID, Attachment> attachments = ResultSetFactory.getResultSetInstance(null, systemSession, false, LoadType.COMPLETE, attachmentMap, null);

		LinkedHashMap<PositionID, Position> positionMap = new LinkedHashMap<PositionID, Position>();
		PositionID positionID = CoreServiceEntityIDFactory.getPositionIDInstance(documentResource.getSnapshot().getLocation().getLink().getHref(), documentID, documentID);
		positionMap.put(positionID, createPosition(positionID, documentResource.getSnapshot().getLocation()));

		ResultSet<PositionID, Position> positions = ResultSetFactory.getResultSetInstance(null, systemSession, false, LoadType.COMPLETE, positionMap, null);

		LinkedHashMap<TimeID, Time> timeMap = new LinkedHashMap<TimeID, Time>();
		TimeID timeID = CoreServiceEntityIDFactory.getTimeIDInstance(documentResource.getSnapshot().getTime().getLink().getHref(), documentID, documentID);
		timeMap.put(timeID, createTime(timeID, documentResource.getSnapshot().getTime()));

		ResultSet<TimeID, Time> times = ResultSetFactory.getResultSetInstance(null, systemSession, false, LoadType.COMPLETE, timeMap, null);

		return new DocumentBuilder(documentID, dataManager, LoadType.SNAPSHOT).mediaMainType(mediaMainType).publishingTime(publishingTime).publisher(publisher).title(title).description(description).tags(tags).attachments(attachments).positions(positions).times(times).build();
	}

	/**
	 * Creates a {@link Map} of {@link ExternalMediaType}s from the given
	 * {@link LinkedHashMap} containing the {@link ExternalResource}s.
	 * 
	 * @param external The {@code LinkedHashMap} with the
	 *        {@code ExternalResource}s
	 * @return The created {@code Map} of {@code ExternalMediaType}s
	 */
	private Map<String, ExternalMediaType> createSupportedExternalMediaTypes(LinkedHashMap<String, ExternalResource> external)
	{
		Map<String, ExternalMediaType> supportedExternalMediaTypes = new HashMap<String, ExternalMediaType>();

		ExternalResource externalResource;

		for(String id : external.keySet())
		{
			externalResource = external.get(id);

			MediaMainType mediaMainType = MediaTypeFactory.getMediaMainType(externalResource.getType());

			supportedExternalMediaTypes.put(id, MediaTypeFactory.getExternalMediaTypeInstance(id, mediaMainType, externalResource.getName()));
		}

		return supportedExternalMediaTypes;
	}

	/**
	 * Creates a {@link Map} of {@link PlatformMediaType}s from the given
	 * {@link LinkedHashMap} containing the {@link StoredResource}s.
	 * 
	 * @param stored The {@code LinkedHashMap} with the {@code StoredResource}s
	 * @return The created {@code Map} of {@code PlatformMediaType}s
	 */
	private Map<String, PlatformMediaType> createSupportedPlatformMediaTypes(LinkedHashMap<String, StoredResource> stored)
	{
		Map<String, PlatformMediaType> supportedPlatformMediaTypes = new HashMap<String, PlatformMediaType>();

		StoredResource storedResource;

		for(String id : stored.keySet())
		{
			storedResource = stored.get(id);

			MediaMainType mediaMainType = MediaTypeFactory.getMediaMainType(storedResource.getType());

			supportedPlatformMediaTypes.put(id, MediaTypeFactory.getPlatformMediaTypeInstance(id, mediaMainType, storedResource.getExtension(), storedResource.getMaxSize()));
		}

		return supportedPlatformMediaTypes;
	}

	/**
	 * Converts a {@link TagsResource} to a {@link ResultSet} of {@link Tag}s
	 * and their mapped {@link TagID}s.
	 * 
	 * @param tagsResource The {@code TagsResource}
	 * @param rootEntityID The corresponding root {@code EntityID}
	 * @param parentEntityID The corresponding parent {@code EntityID}
	 * @return A {@code ResultSet} with the {@code Tag}s
	 */
	private ResultSet<TagID, Tag> createTagResultSet(TagsResource tagsResource, EntityID rootEntityID, EntityID parentEntityID)
	{
		LinkedHashMap<TagID, Tag> tags = new LinkedHashMap<TagID, Tag>();

		TagID tagID;
		Tag tag;
		String value;
		DateTime creationTime;
		UserID creator;
		Votes votes;
		TagResource tagResource;

		for(TagsResource.ListEntry listEntry : tagsResource.getList())
		{
			tagResource = listEntry.getTag();

			tagID = CoreServiceEntityIDFactory.getTagIDInstance(tagResource.getLink().getHref(), rootEntityID, parentEntityID);

			value = tagResource.getValue();
			creationTime = new DateTime(tagResource.getCreationTime());
			creator = UserFactory.getUserIDInstance(tagResource.getCreator().getLink().getHref());
			votes = new Votes(tagResource.getVotes().getUp(), tagResource.getVotes().getDown());

			tag = new TagBuilder(tagID, dataManager).value(value).creationTime(creationTime).creator(creator).votes(votes).build();

			tags.put(tagID, tag);
		}

		return ResultSetFactory.getResultSetInstance(null, systemSession, false, LoadType.COMPLETE, tags, null);
	}

	/**
	 * Creates a {@link Time} from the given {@link TimeResource} and returns
	 * the {@code Time}.
	 * 
	 * @param timeID The corresponding {@code TimeID}
	 * @param timeResource The {@code TimeResource}
	 * @return The created {@code Time}
	 */
	private Time createTime(TimeID timeID, TimeResource timeResource)
	{
		TimeRange timeRange = new TimeRange(new DateTime(timeResource.getBetween().getAfter()), new DateTime(timeResource.getBetween().getBefore()));
		DateTime creationTime = new DateTime(timeResource.getCreationTime());
		UserID creator = UserFactory.getUserIDInstance(timeResource.getCreator().getLink().getHref());
		Votes votes = new Votes(timeResource.getVotes().getUp(), timeResource.getVotes().getDown());

		return new TimeBuilder(timeID, dataManager).timeRange(timeRange).creationTime(creationTime).creator(creator).votes(votes).build();
	}

	/**
	 * Converts a {@link TimesResource} to a {@link ResultSet} of {@link Time}s
	 * and their mapped {@link TimeID}s.
	 * 
	 * @param timesResource A {@code TimesResource}
	 * @param documentID The corresponding {@code DocumentID}
	 * @return A {@code ResultSet} with the {@code Time}s
	 */
	private ResultSet<TimeID, Time> createTimeResultSet(TimesResource timesResource, DocumentID documentID)
	{
		LinkedHashMap<TimeID, Time> times = new LinkedHashMap<TimeID, Time>();

		TimeResource timeResource;
		TimeID timeID;
		Time time;

		for(TimesResource.ListEntry listEntry : timesResource.getList())
		{
			timeResource = listEntry.getTime();

			timeID = CoreServiceEntityIDFactory.getTimeIDInstance(timeResource.getLink().getHref(), documentID, documentID);

			time = createTime(timeID, timeResource);

			times.put(timeID, time);
		}

		return ResultSetFactory.getResultSetInstance(null, systemSession, false, LoadType.COMPLETE, times, null);
	}

	/**
	 * Transforms the given {@link ArrayList} of {@link UserID}s to an
	 * {@code ArrayList} of {@link UserHyperLinkResource}s.
	 * 
	 * @param userIDs The {@code ArrayList} of {@code UserID}s
	 * @return The {@code ArrayList} of {@code UserHyperLinkResource}s
	 */
	private ArrayList<UserHyperLinkResource> createUserHyperLinkResourceArray(ArrayList<UserID> userIDs)
	{
		ArrayList<UserHyperLinkResource> getUserHyperLinkResourceArray = new ArrayList<UserHyperLinkResource>();

		UserHyperLinkResource listEntry;
		HyperLinkResourceWrapper user;
		HyperLinkResource link;

		for(UserID userID : userIDs)
		{
			listEntry = new UserHyperLinkResource();
			user = new HyperLinkResourceWrapper();
			link = new HyperLinkResource();

			link.setRel("self");
			link.setHref(userID.getUniqueResourceURL().toExternalForm());

			user.setLink(link);

			listEntry.setUser(user);

			getUserHyperLinkResourceArray.add(listEntry);
		}

		return getUserHyperLinkResourceArray;
	}

	/**
	 * Creates a {@link LinkedHashMap} from the given
	 * {@link UserInfoResultPageResource} and returns the {@code LinkedHashMap}.
	 * 
	 * @param userInfoResultPageResource The {@code UserInfoResultPageResource}
	 * @return The created {@code LinkedHashMap}
	 */
	private LinkedHashMap<UserID, UserInfo> createUserInfoMap(UserInfoResultPageResource userInfoResultPageResource)
	{
		LinkedHashMap<UserID, UserInfo> resultMap = new LinkedHashMap<UserID, UserInfo>();

		ArrayList<UserInfoResource> list = userInfoResultPageResource.getList();

		UserResource userResource;
		UserID userID;
		String userName;
		UserInfo userInfo;

		for(UserInfoResource userInfoResource : list)
		{
			userResource = userInfoResource.getUser();

			userID = UserFactory.getUserIDInstance(userResource.getLink().getHref());

			userName = userResource.getUsername();

			userInfo = UserFactory.getUserInfoInstance(userID, userName);

			resultMap.put(userID, userInfo);
		}

		return resultMap;
	}

	/**
	 * Sets the common {@link Attachment} creation data to the given
	 * {@link AttachmentCreationResource} regardless of whether the
	 * {@code Attachment} is an external {@code Attachment} or a platform
	 * {@code Attachment}.
	 * 
	 * @param attachmentCreationResource The {@code AttachmentCreationResource}
	 * @param attachmentCreationData The {@code AttachmentCreationData}
	 */
	private void setAttachmentCreationData(AttachmentCreationResource attachmentCreationResource, AttachmentCreationData attachmentCreationData)
	{
		ArrayList<ContributorEntry> contributors = createContributorsEntryArray(attachmentCreationData.getContributors());
		ArrayList<UserHyperLinkResource> creators = createUserHyperLinkResourceArray(attachmentCreationData.getCreators());

		attachmentCreationResource.setTitle(attachmentCreationData.getTitle());
		attachmentCreationResource.setDescription(attachmentCreationData.getDescription());
		attachmentCreationResource.setLicense(attachmentCreationData.getLicense());
		attachmentCreationResource.setContributors(contributors);
		attachmentCreationResource.setCreators(creators);
	}

	/**
	 * Sets the common {@link Document} creation data to the given
	 * {@link DocumentCreationResource} regardless of whether the initial
	 * {@link Attachment} is an external {@code Attachment} or a platform
	 * {@code Attachment}.
	 * 
	 * @param documentCreationResource The {@code DocumentCreationResource}
	 * @param attachmentCreationData The {@code AttachmentCreationData}
	 * @param topographicPoint The {@code TopographicPoint} of the location
	 *        where the {@code Document} has been created
	 * @param timeRange The {@code TimeRange} when the {@code Document} has been
	 *        created
	 */
	private void setDocumentCreationData(DocumentCreationResource documentCreationResource, AttachmentCreationData attachmentCreationData, TopographicPoint topographicPoint, TimeRange timeRange)
	{
		LocationResource location = new LocationResource();
		PositionCoordinatesResource position = new PositionCoordinatesResource();
		ArrayList<Double> coordinates = new ArrayList<Double>();
		BetweenRangeResource createdBetween = new BetweenRangeResource();

		position.setType("Point");
		coordinates.add(topographicPoint.getLongitude());
		coordinates.add(topographicPoint.getLatitude());
		position.setCoordinates(coordinates);
		location.setPosition(position);
		location.setVariance(topographicPoint.getVariance());

		createdBetween.setAfter(timeRange.getStartDateTime().toString(Time.ISO_UTC_DATE_TIME_FORMATTER));
		createdBetween.setBefore(timeRange.getEndDateTime().toString(Time.ISO_UTC_DATE_TIME_FORMATTER));

		documentCreationResource.setLocation(location);
		documentCreationResource.setCreatedBetween(createdBetween);

		setAttachmentCreationData(documentCreationResource, attachmentCreationData);
	}
}
