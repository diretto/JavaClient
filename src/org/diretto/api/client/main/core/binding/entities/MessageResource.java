package org.diretto.api.client.main.core.binding.entities;

import java.util.ArrayList;

import org.diretto.api.client.main.core.binding.resources.HyperLinkResourceWrapper;

/**
 * This class represents a POJO based {@code MessageResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class MessageResource
{
	private String creationTime;
	private ArrayList<HyperLinkResourceWrapper> links;
	private HyperLinkResourceWrapper sender;
	private HyperLinkResourceWrapper receiver;
	private String title;
	private String content;

	public String getCreationTime()
	{
		return creationTime;
	}

	public void setCreationTime(String creationTime)
	{
		this.creationTime = creationTime;
	}

	public ArrayList<HyperLinkResourceWrapper> getLinks()
	{
		return links;
	}

	public void setLinks(ArrayList<HyperLinkResourceWrapper> links)
	{
		this.links = links;
	}

	public HyperLinkResourceWrapper getSender()
	{
		return sender;
	}

	public void setSender(HyperLinkResourceWrapper sender)
	{
		this.sender = sender;
	}

	public HyperLinkResourceWrapper getReceiver()
	{
		return receiver;
	}

	public void setReceiver(HyperLinkResourceWrapper receiver)
	{
		this.receiver = receiver;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}
}
