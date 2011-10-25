package org.diretto.api.client.base.data;

import org.diretto.api.client.user.UserID;

/**
 * This class is one of the implementation classes of the {@link Contributor}
 * interface and represents an {@code PlatformContributor}. <br/><br/>
 * 
 * <i>Annotation:</i> It is an immutable class.
 * 
 * @author Tobias Schlecht
 */
public class PlatformContributor implements Contributor
{
	private final UserID userID;

	/**
	 * Constructs a {@link PlatformContributor}.
	 * 
	 * @param userID The {@code UserID} of the {@code PlatformContributor}.
	 */
	public PlatformContributor(UserID userID)
	{
		this.userID = userID;
	}

	@Override
	public boolean isExternalContributor()
	{
		return false;
	}

	/**
	 * Returns the {@link UserID} of the {@link PlatformContributor}.
	 * 
	 * @return The {@code UserID}
	 */
	public UserID getUserID()
	{
		return userID;
	}
}
