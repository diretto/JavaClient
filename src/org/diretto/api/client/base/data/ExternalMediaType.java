package org.diretto.api.client.base.data;

/**
 * This class represents an {@code ExternalMediaType}. <br/><br/>
 * 
 * <i>Annotation:</i> It is an immutable class.
 * 
 * @author Tobias Schlecht
 */
public final class ExternalMediaType extends AbstractMediaType
{
	private final String name;

	/**
	 * Constructs an {@link ExternalMediaType}. <br/><br/>
	 * 
	 * <i>Annotation:</i> An example for an <i>ID</i> {@code String} is
	 * {@code "video/x-diretto-youtube+url"}.
	 * 
	 * @param id The <i>ID</i> {@code String}
	 * @param mediaMainType The {@code MediaMainType}
	 * @param name The name
	 */
	ExternalMediaType(String id, MediaMainType mediaMainType, String name)
	{
		super(id, mediaMainType);

		if(name == null)
		{
			this.name = "";
		}
		else
		{
			this.name = name;
		}
	}

	/**
	 * Returns the name.
	 * 
	 * @return The name
	 */
	public String getName()
	{
		return name;
	}
}
