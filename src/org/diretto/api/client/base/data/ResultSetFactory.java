package org.diretto.api.client.base.data;

import java.net.URL;
import java.util.LinkedHashMap;

import org.diretto.api.client.base.characteristic.ResultSetManager;
import org.diretto.api.client.base.entities.Entity;
import org.diretto.api.client.base.entities.EntityID;
import org.diretto.api.client.base.types.LoadType;
import org.diretto.api.client.session.Session;

/**
 * The {@code ResultSetFactory} is a noninstantiable factory class and is
 * responsible for creating instances of the {@link ResultSet} interface.
 * 
 * @author Tobias Schlecht
 */
public final class ResultSetFactory
{
	/**
	 * The constructor is {@code private} to suppress the default constructor
	 * for noninstantiability.
	 */
	private ResultSetFactory()
	{
		throw new AssertionError();
	}

	/**
	 * Returns an instance of the requested {@link ResultSet}. <br/><br/>
	 * 
	 * <i>Annotation:</i> If there is no next page, the {@link URL} must be set
	 * to {@code null}.
	 * 
	 * @param resultSetManager The corresponding {@code ResultSetManager}
	 * @param session The corresponding {@code Session}
	 * @param isQueryResultSet {@code true} if the {@code ResultSet} derives
	 *        from a query result page; otherwise {@code false}
	 * @param loadType The {@code LoadType} of the {@code ResultSet}
	 * @param linkedHashMap The {@code LinkedHashMap} whose mappings are to be
	 *        placed in this {@code ResultSet}
	 * @param nextPageURL The {@code URL} of the next page
	 * @return An instance of the requested {@code ResultSet}
	 */
	public static <I extends EntityID, E extends Entity<I>> ResultSet<I, E> getResultSetInstance(ResultSetManager<I, E> resultSetManager, Session session, boolean isQueryResultSet, LoadType loadType, LinkedHashMap<I, E> linkedHashMap, URL nextPageURL)
	{
		return new ResultSetImpl<I, E>(resultSetManager, session, isQueryResultSet, loadType, linkedHashMap, nextPageURL);
	}
}
