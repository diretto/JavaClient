package org.diretto.api.client.user;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.auth.AuthScope;
import org.apache.http.client.CredentialsProvider;
import org.diretto.api.client.main.core.DataManager;
import org.diretto.api.client.main.core.DataManagerImpl;
import org.diretto.api.client.main.core.binding.post.UserCreationResource;
import org.diretto.api.client.util.HashUtils;
import org.diretto.api.client.util.URLTransformationUtils;
import org.restlet.resource.ClientResource;

/**
 * The {@code UserFactory} is a noninstantiable factory class. On the one hand
 * it is responsible for creating instances of all kinds of objects referring to
 * a {@link User} and on the other hand it manages some authentication related
 * functionalities.
 * 
 * @author Tobias Schlecht
 */
public final class UserFactory
{
	/**
	 * The constructor is {@code private} to suppress the default constructor
	 * for noninstantiability.
	 */
	private UserFactory()
	{
		throw new AssertionError();
	}

	/**
	 * Returns an instance of the requested {@link UserID}.
	 * 
	 * @param uniqueResourceURL The unique resource {@code URL}
	 * @return An instance of the requested {@code UserID}
	 */
	public static UserID getUserIDInstance(URL uniqueResourceURL)
	{
		return new UserIDImpl(uniqueResourceURL);
	}

	/**
	 * Returns an instance of the requested {@link UserID}. </br></br>
	 * 
	 * <i>Annotation:</i> An example for the {@code String} representation of an
	 * unique resource {@code URL} is
	 * {@code http://api.diretto.org/v2/user/098f6bcd4621d373cade4e832627b4f6}
	 * 
	 * @param uniqueResourceURL The {@code String} representation of the unique
	 *        resource {@code URL}
	 * @return An instance of the requested {@code UserID}
	 */
	public static UserID getUserIDInstance(String uniqueResourceURL)
	{
		UserID userID = null;

		try
		{
			userID = new UserIDImpl(new URL(uniqueResourceURL));
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}

		return userID;
	}

	/**
	 * Returns an instance of the requested {@link UserID}.
	 * 
	 * @param apiBaseURL The API base {@code URL}
	 * @param emailAddress The Email address of the {@code User}
	 * @return An instance of the requested {@code UserID}
	 */
	public static UserID getUserIDInstance(URL apiBaseURL, String emailAddress)
	{
		apiBaseURL = URLTransformationUtils.adjustAPIBaseURL(apiBaseURL);

		UserID userID = null;

		try
		{
			userID = new UserIDImpl(new URL(apiBaseURL.toExternalForm() + "/user/" + HashUtils.generateMD5Hash(emailAddress)));
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}

		return userID;
	}

	/**
	 * Returns an instance of the requested {@link User}.
	 * 
	 * @param dataManager The corresponding {@code DataManager}
	 * @param userInfo The {@code UserInfo}
	 * @param apiBaseURL The API base {@code URL}
	 * @param emailAddress The Email address of the {@code User}
	 * @param password The password of the {@code User}
	 * @return An instance of the requested {@code User}
	 */
	public static User getUserInstance(DataManagerImpl dataManager, UserInfo userInfo, URL apiBaseURL, String emailAddress, String password)
	{
		return new UserImpl(dataManager, userInfo, apiBaseURL, emailAddress, password);
	}

	/**
	 * Returns an instance of the requested {@link UserInfo}.
	 * 
	 * @param userID The corresponding {@code UserID}
	 * @param userName The name of the {@code User}
	 * @return An instance of the requested {@code UserInfo}
	 */
	public static UserInfo getUserInfoInstance(UserID userID, String userName)
	{
		return new UserInfoImpl(userID, userName);
	}

	/**
	 * Sets the given {@link DataManager} to the {@link User} object.
	 * 
	 * @param user The {@code User}
	 * @param dataManager The corresponding {@code DataManager}
	 */
	public static void setDataManager(User user, DataManagerImpl dataManager)
	{
		((UserImpl) user).setDataManager(dataManager);
	}

	/**
	 * Sets the mandatory credentials of the {@link User} to the given
	 * {@link ClientResource}. This is necessary to execute the authentication.
	 * 
	 * @param user The {@code User}
	 * @param clientResource The {@code ClientResource}
	 */
	public static void authenticateClientResource(User user, ClientResource clientResource)
	{
		((UserImpl) user).authenticateClientResource(clientResource);
	}

	/**
	 * Sets the password of the {@link User} to the given
	 * {@link UserCreationResource}.
	 * 
	 * @param user The {@code User}
	 * @param userCreationResource The {@code UserCreationResource}
	 */
	public static void setPassword(User user, UserCreationResource userCreationResource)
	{
		((UserImpl) user).setPassword(userCreationResource);
	}

	/**
	 * Sets the mandatory credentials of the {@link User} and the given
	 * {@link AuthScope} to the {@link CredentialsProvider}.
	 * 
	 * @param user The {@code User}
	 * @param authScope The {@code AuthScope}
	 * @param credentialsProvider The {@code CredentialsProvider}
	 */
	public static void setCredentials(User user, AuthScope authScope, CredentialsProvider credentialsProvider)
	{
		((UserImpl) user).setCredentials(authScope, credentialsProvider);
	}
}
