package org.diretto.api.client.main.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.diretto.api.client.JavaClient;
import org.diretto.api.client.JavaClientImpl;
import org.diretto.api.client.base.annotations.InvocationLimited;
import org.diretto.api.client.base.data.BoundingBox;
import org.diretto.api.client.base.data.ExternalMediaType;
import org.diretto.api.client.base.data.MediaMainType;
import org.diretto.api.client.base.data.MediaType;
import org.diretto.api.client.base.data.PlatformMediaType;
import org.diretto.api.client.base.data.ResultSet;
import org.diretto.api.client.base.data.TimeRange;
import org.diretto.api.client.base.data.TopographicPoint;
import org.diretto.api.client.base.data.UploadInfo;
import org.diretto.api.client.base.types.LoadType;
import org.diretto.api.client.base.types.MessageBoxType;
import org.diretto.api.client.main.core.binding.major.CoreServiceInstanceDataResource;
import org.diretto.api.client.main.core.entities.Collection;
import org.diretto.api.client.main.core.entities.CollectionID;
import org.diretto.api.client.main.core.entities.Comment;
import org.diretto.api.client.main.core.entities.CommentID;
import org.diretto.api.client.main.core.entities.Document;
import org.diretto.api.client.main.core.entities.DocumentID;
import org.diretto.api.client.main.core.entities.Link;
import org.diretto.api.client.main.core.entities.LinkID;
import org.diretto.api.client.main.core.entities.Message;
import org.diretto.api.client.main.core.entities.MessageID;
import org.diretto.api.client.main.core.entities.Tag;
import org.diretto.api.client.main.core.entities.TagID;
import org.diretto.api.client.main.core.entities.creation.ExternalAttachmentCreationData;
import org.diretto.api.client.main.core.entities.creation.PlatformAttachmentCreationData;
import org.diretto.api.client.service.AbstractService;
import org.diretto.api.client.session.UserSession;
import org.diretto.api.client.user.User;
import org.diretto.api.client.user.UserFactory;
import org.diretto.api.client.user.UserID;
import org.diretto.api.client.user.UserInfo;
import org.diretto.api.client.util.InvocationUtils;
import org.joda.time.DateTime;
import org.restlet.Client;
import org.restlet.resource.ClientResource;

/**
 * This class is the implementation class of the {@link CoreService} interface.
 * 
 * @author Tobias Schlecht
 */
public final class CoreServiceImpl extends AbstractService implements CoreService
{
	private final JavaClient javaClient;
	private final Client restletClient;
	private final int maxEntityRequestSize;
	private final int paginationSize;

	private final Map<String, PlatformMediaType> platformMediaTypes = new ConcurrentHashMap<String, PlatformMediaType>();
	private final Map<String, ExternalMediaType> externalMediaTypes = new ConcurrentHashMap<String, ExternalMediaType>();
	private final Map<String, MediaMainType> mediaMainTypes = new ConcurrentHashMap<String, MediaMainType>();

	private boolean mediaTypeMapsInitialized = false;

	private DataManagerImpl dataManager = null;

	/**
	 * The constructor is {@code private} to have strict control what instances
	 * exist at any time. Instead of the constructor the {@code public}
	 * <i>static factory method</i> {@link #getInstance(JavaClient)} returns the
	 * instances of the class.
	 * 
	 * @param javaClient The corresponding {@code JavaClient}
	 */
	private CoreServiceImpl(JavaClient javaClient)
	{
		super(CoreServiceID.INSTANCE, javaClient.getAPIBaseURL(), javaClient);

		this.javaClient = javaClient;

		restletClient = ((JavaClientImpl) javaClient).getRestletClient();

		ClientResource clientResource = new ClientResource(serviceURL.toExternalForm());
		clientResource.setNext(restletClient);
		CoreServiceInstanceDataResource coreServiceInstanceDataResource = clientResource.get(CoreServiceInstanceDataResource.class);

		System.out.println("[CoreService CoreServiceImpl] " + serviceURL.toExternalForm());

		maxEntityRequestSize = coreServiceInstanceDataResource.getParameters().getBatchLimit();
		paginationSize = coreServiceInstanceDataResource.getParameters().getPaginationSize();
	}

	/**
	 * Returns a {@link CoreService} instance for the corresponding
	 * {@link JavaClient}.
	 * 
	 * @param javaClient The corresponding {@code JavaClient}
	 * @return A {@code CoreService} instance
	 */
	@InvocationLimited(legitimateInvocationClasses = {JavaClientImpl.class})
	public static synchronized CoreService getInstance(JavaClient javaClient)
	{
		String warningMessage = "The method invocation \"" + CoreServiceImpl.class.getCanonicalName() + ".getInstance(JavaClient)\" is not intended for this usage. Use the method \"" + JavaClient.class.getCanonicalName() + ".getCoreService()\" instead.";
		InvocationUtils.checkMethodInvocation(warningMessage, "getInstance", JavaClient.class);

		return new CoreServiceImpl(javaClient);
	}

	/**
	 * Initializes the {@link MediaType} {@link Map}s.
	 */
	@SuppressWarnings("unchecked")
	private synchronized void initializeMediaTypeMaps()
	{
		ArrayList<Map<String, ? extends MediaType>> mediaTypes = ((DataManagerImpl) getDataManager()).getSupportedMediaTypes();

		platformMediaTypes.putAll((Map<String, PlatformMediaType>) mediaTypes.get(0));
		externalMediaTypes.putAll((Map<String, ExternalMediaType>) mediaTypes.get(1));

		for(PlatformMediaType platformMediaType : platformMediaTypes.values())
		{
			mediaMainTypes.put(platformMediaType.getMediaMainType().TYPE, platformMediaType.getMediaMainType());
		}

		for(ExternalMediaType externalMediaType : externalMediaTypes.values())
		{
			mediaMainTypes.put(externalMediaType.getMediaMainType().TYPE, externalMediaType.getMediaMainType());
		}

		mediaTypeMapsInitialized = true;
	}

	/**
	 * Returns the corresponding {@link DataManager}.
	 * 
	 * @return The corresponding {@code DataManager}
	 */
	private DataManager getDataManager()
	{
		if(dataManager == null)
		{
			dataManager = (DataManagerImpl) DataManagerImpl.getInstance(this, serviceURL, restletClient, javaClient.getSystemSession());

			UserFactory.setDataManager(javaClient.getSystemSession().getUser(), dataManager);
		}

		return dataManager;
	}

	@Override
	public CollectionID createCollection(UserSession userSession, String title, String description, boolean privateSetting)
	{
		return getDataManager().createCollection(userSession, title, description, privateSetting);
	}

	@Override
	public DocumentID createDocument(UserSession userSession, ExternalAttachmentCreationData externalAttachmentCreationData, TopographicPoint topographicPoint, TimeRange timeRange)
	{
		return getDataManager().createDocument(userSession, externalAttachmentCreationData, topographicPoint, timeRange);
	}

	@Override
	public UploadInfo createDocument(UserSession userSession, PlatformAttachmentCreationData platformAttachmentCreationData, TopographicPoint topographicPoint, TimeRange timeRange)
	{
		return getDataManager().createDocument(userSession, platformAttachmentCreationData, topographicPoint, timeRange);
	}

	@Override
	public DocumentID createDocument(UserSession userSession, UUID uuid, ExternalAttachmentCreationData externalAttachmentCreationData, TopographicPoint topographicPoint, TimeRange timeRange)
	{
		return getDataManager().createDocument(userSession, uuid, externalAttachmentCreationData, topographicPoint, timeRange);
	}

	@Override
	public UploadInfo createDocument(UserSession userSession, UUID uuid, PlatformAttachmentCreationData platformAttachmentCreationData, TopographicPoint topographicPoint, TimeRange timeRange)
	{
		return getDataManager().createDocument(userSession, uuid, platformAttachmentCreationData, topographicPoint, timeRange);
	}

	@Override
	public UserID createUser(String emailAddress, String password, String userName)
	{
		return getDataManager().createUser(emailAddress, password, userName);
	}

	@Override
	public ResultSet<DocumentID, Document> getAllCachedDocuments()
	{
		return getDataManager().getAllCachedDocuments();
	}

	@Override
	public ResultSet<DocumentID, Document> getAllDocuments()
	{
		return getDataManager().getAllDocuments();
	}

	@Override
	public ResultSet<DocumentID, Document> getAllDocuments(LoadType loadType)
	{
		return getDataManager().getAllDocuments(loadType);
	}

	@Override
	public ResultSet<LinkID, Link> getAllLinks()
	{
		return getDataManager().getAllLinks();
	}

	@Override
	public ResultSet<TagID, Tag> getAllTags()
	{
		return getDataManager().getAllTags();
	}

	@Override
	public ResultSet<UserID, UserInfo> getAllUserInfos()
	{
		return getDataManager().getAllUserInfos();
	}

	@Override
	public Collection getCollection(UserSession userSession, CollectionID collectionID)
	{
		return getDataManager().getCollection(userSession, collectionID);
	}

	@Override
	public ResultSet<CollectionID, Collection> getCollectionsByUser(UserSession userSession, UserID creator)
	{
		return getDataManager().getCollectionsByUser(userSession, creator);
	}

	@Override
	public ResultSet<CollectionID, Collection> getCollectionsWithDocumentsByUser(UserSession userSession, UserID userID)
	{
		return getDataManager().getCollectionsWithDocumentsByUser(userSession, userID);
	}

	@Override
	public ResultSet<CollectionID, Collection> getCollectionsWithDocumentsByUser(UserSession userSession, UserID userID, LoadType loadType)
	{
		return getDataManager().getCollectionsWithDocumentsByUser(userSession, userID, loadType);
	}

	@Override
	public Collection getCollectionWithDocuments(UserSession userSession, CollectionID collectionID)
	{
		return getDataManager().getCollectionWithDocuments(userSession, collectionID);
	}

	@Override
	public Collection getCollectionWithDocuments(UserSession userSession, CollectionID collectionID, LoadType loadType)
	{
		return getDataManager().getCollectionWithDocuments(userSession, collectionID, loadType);
	}

	@Override
	public ResultSet<CommentID, Comment> getCommentsByUser(UserID userID)
	{
		return getDataManager().getCommentsByUser(userID);
	}

	@Override
	public Document getDocument(DocumentID documentID)
	{
		return getDataManager().getDocument(documentID);
	}

	@Override
	public Document getDocument(DocumentID documentID, LoadType loadType, boolean forceAPICall)
	{
		return getDataManager().getDocument(documentID, loadType, forceAPICall);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocuments(List<String> tags, BoundingBox boundingBox, TimeRange timeRange)
	{
		return getDataManager().getDocuments(tags, boundingBox, timeRange);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocuments(List<String> tags, BoundingBox boundingBox, TimeRange timeRange, LoadType loadType)
	{
		return getDataManager().getDocuments(tags, boundingBox, timeRange, loadType);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocuments(List<String> tags, BoundingBox boundingBox, TimeRange timeRange, TimeRange publishingTimeRange)
	{
		return getDataManager().getDocuments(tags, boundingBox, timeRange, publishingTimeRange);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocuments(List<String> tags, BoundingBox boundingBox, TimeRange timeRange, TimeRange publishingTimeRange, LoadType loadType)
	{
		return getDataManager().getDocuments(tags, boundingBox, timeRange, publishingTimeRange, loadType);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocumentsAfter(DateTime time)
	{
		return getDataManager().getDocumentsAfter(time);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocumentsAfter(DateTime time, LoadType loadType)
	{
		return getDataManager().getDocumentsAfter(time, loadType);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocumentsByIDs(List<DocumentID> documentIDs)
	{
		return getDataManager().getDocumentsByIDs(documentIDs);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocumentsByIDs(List<DocumentID> documentIDs, LoadType loadType, boolean forceAPICall)
	{
		return getDataManager().getDocumentsByIDs(documentIDs, loadType, forceAPICall);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocumentsByTag(String value)
	{
		return getDataManager().getDocumentsByTag(value);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocumentsByTag(String value, LoadType loadType)
	{
		return getDataManager().getDocumentsByTag(value, loadType);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocumentsByUser(UserID userID)
	{
		return getDataManager().getDocumentsByUser(userID);
	}

	@Override
	public ResultSet<DocumentID, Document> getDocumentsByUser(UserID userID, LoadType loadType)
	{
		return getDataManager().getDocumentsByUser(userID, loadType);
	}

	@Override
	public synchronized ExternalMediaType getExternalMediaType(String id)
	{
		if(!mediaTypeMapsInitialized)
		{
			initializeMediaTypeMaps();
		}

		return externalMediaTypes.get(id);
	}

	@Override
	public ResultSet<LinkID, Link> getLinksAfter(DateTime time)
	{
		return getDataManager().getLinksAfter(time);
	}

	@Override
	public ResultSet<LinkID, Link> getLinksByTag(String value)
	{
		return getDataManager().getLinksByTag(value);
	}

	@Override
	public int getMaxEntityRequestSize()
	{
		return maxEntityRequestSize;
	}

	@Override
	public MediaMainType getMediaMainType(String type)
	{
		if(!mediaTypeMapsInitialized)
		{
			initializeMediaTypeMaps();
		}

		return mediaMainTypes.get(type);
	}

	@Override
	public Message getMessage(UserSession userSession, MessageID messageID)
	{
		return getDataManager().getMessage(userSession, messageID);
	}

	@Override
	public ResultSet<MessageID, Message> getMessages(UserSession userSession, MessageBoxType messageBoxType)
	{
		return getDataManager().getMessages(userSession, messageBoxType);
	}

	@Override
	public ResultSet<MessageID, Message> getMessagesAfter(UserSession userSession, MessageBoxType messageBoxType, DateTime time)
	{
		return getDataManager().getMessagesAfter(userSession, messageBoxType, time);
	}

	@Override
	public int getPaginationSize()
	{
		return paginationSize;
	}

	@Override
	public synchronized PlatformMediaType getPlatformMediaType(String id)
	{
		if(!mediaTypeMapsInitialized)
		{
			initializeMediaTypeMaps();
		}

		return platformMediaTypes.get(id);
	}

	@Override
	public synchronized Map<String, ExternalMediaType> getSupportedExternalMediaTypes()
	{
		if(!mediaTypeMapsInitialized)
		{
			initializeMediaTypeMaps();
		}

		return Collections.unmodifiableMap(externalMediaTypes);
	}

	@Override
	public Map<String, MediaMainType> getSupportedMediaMainTypes()
	{
		if(!mediaTypeMapsInitialized)
		{
			initializeMediaTypeMaps();
		}

		return Collections.unmodifiableMap(mediaMainTypes);
	}

	@Override
	public synchronized Map<String, PlatformMediaType> getSupportedPlatformMediaTypes()
	{
		if(!mediaTypeMapsInitialized)
		{
			initializeMediaTypeMaps();
		}

		return Collections.unmodifiableMap(platformMediaTypes);
	}

	@Override
	public User getUser(String emailAddress, String password)
	{
		return getDataManager().getUser(emailAddress, password);
	}

	@Override
	public UserInfo getUserInfo(UserID userID)
	{
		return getDataManager().getUserInfo(userID);
	}

	@Override
	public ResultSet<UserID, UserInfo> getUserInfosByIDs(List<UserID> userIDs)
	{
		return getDataManager().getUserInfosByIDs(userIDs);
	}

	@Override
	public UserSession getUserSession(User user)
	{
		return getDataManager().getUserSession(user);
	}

	@Override
	public boolean hasUser(String emailAddress)
	{
		return getDataManager().hasUser(emailAddress);
	}

	@Override
	public boolean hasUser(String emailAddress, String password)
	{
		return getDataManager().hasUser(emailAddress, password);
	}

	@Override
	public boolean isCacheActivated()
	{
		return getDataManager().isCacheActivated();
	}

	@Override
	public boolean removeCollection(UserSession userSession, CollectionID collectionID)
	{
		return getDataManager().removeCollection(userSession, collectionID);
	}

	@Override
	public boolean removeMessage(UserSession userSession, MessageID messageID)
	{
		return getDataManager().removeMessage(userSession, messageID);
	}

	@Override
	public MessageID sendMessage(UserSession userSession, UserID receiver, String title, String content)
	{
		return getDataManager().sendMessage(userSession, receiver, title, content);
	}
}
