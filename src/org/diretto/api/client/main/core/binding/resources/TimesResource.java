package org.diretto.api.client.main.core.binding.resources;

import java.util.ArrayList;

import org.diretto.api.client.main.core.binding.entities.TimeResource;

/**
 * This class represents a POJO based {@code TimesResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class TimesResource
{
	private HyperLinkResource link;
	private ArrayList<ListEntry> list;

	public HyperLinkResource getLink()
	{
		return link;
	}

	public void setLink(HyperLinkResource link)
	{
		this.link = link;
	}

	public ArrayList<ListEntry> getList()
	{
		return list;
	}

	public void setList(ArrayList<ListEntry> list)
	{
		this.list = list;
	}

	public static class ListEntry
	{
		private TimeResource time;

		public TimeResource getTime()
		{
			return time;
		}

		public void setTime(TimeResource time)
		{
			this.time = time;
		}
	}
}
