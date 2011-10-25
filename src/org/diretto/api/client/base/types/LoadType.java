package org.diretto.api.client.base.types;

import org.diretto.api.client.base.entities.Entity;

/**
 * A {@code LoadType} represents the load type of {@link Entity}s and declares
 * whether, and if so which, sub elements of the {@code Entity}s are loaded.
 * 
 * @author Tobias Schlecht
 */
public enum LoadType
{
	/**
	 * This type will induce that only the meta data of the requested objects
	 * will be loaded.
	 */
	METADATA("meta-data", 1),

	/**
	 * This type will induce that the meta data and several further important
	 * data and sub elements (but not all) of the requested objects will be
	 * loaded.
	 */
	SNAPSHOT("snapshot", 2),

	/**
	 * This type will induce that the complete data of the requested objects and
	 * all of their sub elements will be loaded. <br/><br/>
	 * 
	 * <i>Annotation:</i> The selection of this type can be <u>critical</u> in
	 * respect of performance aspects and should therefore only be invoked if it
	 * is really necessary.
	 */
	COMPLETE("full", 3);

	private final String apiName;
	private final int subsetSize;

	/**
	 * Constructs a {@link LoadType}.
	 * 
	 * @param apiName The API name of this {@code LoadType}
	 * @param subsetSize A value for the "size" of the subset (to determine
	 *        whether the data of this {@code LoadType} are also located in
	 *        another {@code LoadType})
	 */
	LoadType(String apiName, int subsetSize)
	{
		this.apiName = apiName;
		this.subsetSize = subsetSize;
	}

	/**
	 * Returns the API name of this {@link LoadType}.
	 * 
	 * @return The API name of this {@code LoadType}
	 */
	public String getAPIName()
	{
		return apiName;
	}

	/**
	 * Checks whether this {@link LoadType} represents a subset from the given
	 * {@code LoadType}. <br/><br/>
	 * 
	 * <i>Annotation:</i> If this {@code LoadType} is the same {@code LoadType}
	 * as the given {@code LoadType}, the returned value will be {@code true}.
	 * 
	 * @param loadType The {@code LoadType} to check against
	 * @return {@code true} if this {@code LoadType} is a subset from the given
	 *         {@code LoadType}; otherwise {@code false}
	 */
	public boolean isSubsetFrom(LoadType loadType)
	{
		if(subsetSize <= loadType.subsetSize)
		{
			return true;
		}

		return false;
	}
}
