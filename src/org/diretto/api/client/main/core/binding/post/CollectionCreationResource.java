package org.diretto.api.client.main.core.binding.post;

/**
 * This class represents a POJO based {@code CollectionCreationResource}.
 * <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class CollectionCreationResource
{
	private String title;
	private String description;
	private boolean nonpublic;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public boolean getNonpublic()
	{
		return nonpublic;
	}

	public void setNonpublic(boolean nonpublic)
	{
		this.nonpublic = nonpublic;
	}
}
