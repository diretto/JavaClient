package org.diretto.api.client.base.characteristic;

import java.net.URL;
import java.util.LinkedHashMap;

import org.diretto.api.client.base.data.ResultSet;
import org.diretto.api.client.base.data.ResultSetImpl;
import org.diretto.api.client.base.entities.Entity;
import org.diretto.api.client.base.entities.EntityID;

/**
 * A class implements the {@code ResultSetManager} interface to indicate that
 * the class has got the necessary methods for managing {@link ResultSet}s with
 * the corresponding {@link Entity} type.
 * 
 * @author Tobias Schlecht
 * 
 * @param <I> The type of the corresponding {@code EntityID}
 * @param <E> The type of the corresponding {@code Entity}
 */
public interface ResultSetManager<I extends EntityID, E extends Entity<I>>
{
	/**
	 * Returns a {@link LinkedHashMap} with the data of the next page and
	 * updates the {@link URL} for the next page of the given {@link ResultSet}.
	 * 
	 * @param resultSet The corresponding {@code ResultSet}
	 * @return A {@code LinkedHashMap} with the data of the next page
	 */
	LinkedHashMap<I, E> getNextPageData(ResultSetImpl<I, E> resultSet);
}
