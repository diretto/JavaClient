package org.diretto.api.client.user;

import org.diretto.api.client.base.entities.AbstractEntity;

/**
 * This class is the implementation class of the {@link UserInfo} interface.
 * 
 * @author Tobias Schlecht
 */
final class UserInfoImpl extends AbstractEntity<UserID> implements UserInfo
{
	private String userName;

	/**
	 * Constructs an object of the {@link UserInfo} interface.
	 * 
	 * @param userID The corresponding {@code UserID}
	 * @param userName The name of the {@code User}
	 */
	UserInfoImpl(UserID userID, String userName)
	{
		super(userID);

		this.userName = userName;
	}

	@Override
	public String getUserName()
	{
		return userName;
	}

	/**
	 * Sets the name of the {@link User}.
	 * 
	 * @param userName The name of the {@code User}
	 */
	void setUserName(String userName)
	{
		this.userName = userName;
	}
}
