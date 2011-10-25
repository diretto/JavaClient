package org.diretto.api.client.main.core.binding.resources;

/**
 * This class represents a POJO based {@code DocumentResultsResource}.
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
public final class DocumentResultsResource
{
	private int count;
	private DocumentPageResource page;

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public DocumentPageResource getPage()
	{
		return page;
	}

	public void setPage(DocumentPageResource page)
	{
		this.page = page;
	}
}
