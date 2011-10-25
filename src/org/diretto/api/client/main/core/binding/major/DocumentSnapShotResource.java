package org.diretto.api.client.main.core.binding.major;

import org.diretto.api.client.main.core.binding.entities.AttachmentResource;
import org.diretto.api.client.main.core.binding.entities.PositionResource;
import org.diretto.api.client.main.core.binding.entities.TimeResource;
import org.diretto.api.client.main.core.binding.resources.HyperLinkResource;
import org.diretto.api.client.main.core.binding.resources.HyperLinkResourceWrapper;
import org.diretto.api.client.main.core.binding.resources.TagsResource;

/**
 * This class represents a POJO based {@code DocumentSnapShotResource}.
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
public final class DocumentSnapShotResource
{
	private DocumentResource document;

	public DocumentResource getDocument()
	{
		return document;
	}

	public void setDocument(DocumentResource document)
	{
		this.document = document;
	}

	public static class DocumentResource
	{
		private HyperLinkResource link;
		private String mediaType;
		private String publishedTime;
		private HyperLinkResourceWrapper publisher;
		private String title;
		private String description;
		private SnapShotResource snapshot;
		private HyperLinkResourceWrapper comments;
		private TagsResource tags;
		private HyperLinkResourceWrapper attachments;
		private HyperLinkResourceWrapper locations;
		private HyperLinkResourceWrapper times;
		private HyperLinkResourceWrapper values;
		private HyperLinkResourceWrapper documentLinks;

		public HyperLinkResource getLink()
		{
			return link;
		}

		public void setLink(HyperLinkResource link)
		{
			this.link = link;
		}

		public String getMediaType()
		{
			return mediaType;
		}

		public void setMediaType(String mediaType)
		{
			this.mediaType = mediaType;
		}

		public String getPublishedTime()
		{
			return publishedTime;
		}

		public void setPublishedTime(String publishedTime)
		{
			this.publishedTime = publishedTime;
		}

		public HyperLinkResourceWrapper getPublisher()
		{
			return publisher;
		}

		public void setPublisher(HyperLinkResourceWrapper publisher)
		{
			this.publisher = publisher;
		}

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

		public SnapShotResource getSnapshot()
		{
			return snapshot;
		}

		public void setSnapshot(SnapShotResource snapshot)
		{
			this.snapshot = snapshot;
		}

		public HyperLinkResourceWrapper getComments()
		{
			return comments;
		}

		public void setComments(HyperLinkResourceWrapper comments)
		{
			this.comments = comments;
		}

		public TagsResource getTags()
		{
			return tags;
		}

		public void setTags(TagsResource tags)
		{
			this.tags = tags;
		}

		public HyperLinkResourceWrapper getAttachments()
		{
			return attachments;
		}

		public void setAttachments(HyperLinkResourceWrapper attachments)
		{
			this.attachments = attachments;
		}

		public HyperLinkResourceWrapper getLocations()
		{
			return locations;
		}

		public void setLocations(HyperLinkResourceWrapper locations)
		{
			this.locations = locations;
		}

		public HyperLinkResourceWrapper getTimes()
		{
			return times;
		}

		public void setTimes(HyperLinkResourceWrapper times)
		{
			this.times = times;
		}

		public HyperLinkResourceWrapper getValues()
		{
			return values;
		}

		public void setValues(HyperLinkResourceWrapper values)
		{
			this.values = values;
		}

		public HyperLinkResourceWrapper getDocumentLinks()
		{
			return documentLinks;
		}

		public void setDocumentLinks(HyperLinkResourceWrapper documentLinks)
		{
			this.documentLinks = documentLinks;
		}
	}

	public static class SnapShotResource
	{
		private AttachmentResource attachment;
		private PositionResource location;
		private TimeResource time;

		public AttachmentResource getAttachment()
		{
			return attachment;
		}

		public void setAttachment(AttachmentResource attachment)
		{
			this.attachment = attachment;
		}

		public PositionResource getLocation()
		{
			return location;
		}

		public void setLocation(PositionResource location)
		{
			this.location = location;
		}

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
