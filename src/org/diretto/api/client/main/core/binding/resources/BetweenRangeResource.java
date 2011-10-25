package org.diretto.api.client.main.core.binding.resources;

/**
 * This class represents a POJO based {@code BetweenRangeResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class BetweenRangeResource
{
	private String after;
	private String before;

	public String getAfter()
	{
		return after;
	}

	public void setAfter(String after)
	{
		this.after = after;
	}

	public String getBefore()
	{
		return before;
	}

	public void setBefore(String before)
	{
		this.before = before;
	}
}
