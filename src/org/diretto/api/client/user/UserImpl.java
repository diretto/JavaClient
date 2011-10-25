package org.diretto.api.client.user;

import java.net.URL;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.diretto.api.client.base.entities.AbstractEntity;
import org.diretto.api.client.main.core.DataManager;
import org.diretto.api.client.main.core.DataManagerImpl;
import org.diretto.api.client.main.core.binding.post.UserCreationResource;
import org.diretto.api.client.session.UserSession;
import org.diretto.api.client.util.HashUtils;
import org.restlet.data.ChallengeScheme;
import org.restlet.resource.ClientResource;

/**
 * This class is the implementation class of the {@link User} interface.
 * 
 * @author Tobias Schlecht
 */
final class UserImpl extends AbstractEntity<UserID> implements User
{
	private DataManagerImpl dataManager;

	private final UserInfo userInfo;
	private final String emailAddress;
	private final String authID;

	private String password;

	/**
	 * Constructs an object of the {@link User} interface.
	 * 
	 * @param dataManager The corresponding {@code DataManager}
	 * @param userInfo The {@code UserInfo}
	 * @param apiBaseURL The API base {@code URL}
	 * @param emailAddress The Email address of the {@code User}
	 * @param password The password of the {@code User}
	 */
	UserImpl(DataManagerImpl dataManager, UserInfo userInfo, URL apiBaseURL, String emailAddress, String password)
	{
		super(UserFactory.getUserIDInstance(apiBaseURL, emailAddress));

		this.userInfo = userInfo;
		this.dataManager = dataManager;
		this.emailAddress = emailAddress;
		this.authID = HashUtils.generateMD5Hash(emailAddress);
		this.password = password;
	}

	@Override
	public UserInfo getUserInfo()
	{
		return userInfo;
	}

	@Override
	public String getEmailAddress()
	{
		return emailAddress;
	}

	@Override
	public String getAuthID()
	{
		return authID;
	}

	@Override
	public boolean changeUserName(UserSession userSession, String userName)
	{
		boolean wasSuccessful = dataManager.changeUserName(userSession, userName);

		if(wasSuccessful)
		{
			((UserInfoImpl) userInfo).setUserName(userName);
		}

		return wasSuccessful;
	}

	@Override
	public boolean changePassword(UserSession userSession, String password)
	{
		boolean wasSuccessful = dataManager.changePassword(userSession, password);

		if(wasSuccessful)
		{
			this.password = password;
		}

		return wasSuccessful;
	}

	@Override
	public boolean verifyPassword(String password)
	{
		return password.equals(this.password);
	}

	/**
	 * Sets the corresponding {@link DataManager}.
	 * 
	 * @param dataManager The corresponding {@code DataManager}
	 */
	void setDataManager(DataManagerImpl dataManager)
	{
		this.dataManager = dataManager;
	}

	/**
	 * Sets the mandatory credentials of the {@link User} to the given
	 * {@link ClientResource}. This is necessary to execute the authentication.
	 * 
	 * @param clientResource The {@code ClientResource}
	 */
	void authenticateClientResource(ClientResource clientResource)
	{
		clientResource.setChallengeResponse(ChallengeScheme.HTTP_BASIC, authID, password);
	}

	/**
	 * Sets the password of the {@link User} to the given
	 * {@link UserCreationResource}.
	 * 
	 * @param userCreationResource The {@code UserCreationResource}
	 */
	void setPassword(UserCreationResource userCreationResource)
	{
		userCreationResource.setPassword(password);
	}

	/**
	 * Sets the mandatory credentials of the {@link User} and the given
	 * {@link AuthScope} to the {@link CredentialsProvider}.
	 * 
	 * @param authScope The {@code AuthScope}
	 * @param credentialsProvider The {@code CredentialsProvider}
	 */
	void setCredentials(AuthScope authScope, CredentialsProvider credentialsProvider)
	{
		credentialsProvider.setCredentials(authScope, new UsernamePasswordCredentials(authID, password));
	}
}
