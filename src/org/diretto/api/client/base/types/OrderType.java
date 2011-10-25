package org.diretto.api.client.base.types;

/**
 * An {@code OrderType} defines the order according to which the corresponding
 * data are arranged.
 * 
 * @author Tobias Schlecht
 */
public enum OrderType
{
	/**
	 * The <i>newest</i> data will come first.
	 */
	NEWEST("newest"),

	/**
	 * The <i>unattended</i> data will come first.
	 */
	UNATTENDED("unattended"),

	/**
	 * The <i>expiring</i> data will come first.
	 */
	EXPIRING("expiring"),

	/**
	 * The <i>popular</i> data will come first.
	 */
	POPULAR("popular");

	private final String urlParameter;

	/**
	 * Constructs a {@link OrderType}.
	 * 
	 * @param urlParameter The API {@code URL} parameter
	 */
	OrderType(String urlParameter)
	{
		this.urlParameter = urlParameter;
	}

	/**
	 * Returns the API {@code URL} parameter.
	 * 
	 * @return The API {@code URL} parameter
	 */
	public String getURLParameter()
	{
		return urlParameter;
	}
}
