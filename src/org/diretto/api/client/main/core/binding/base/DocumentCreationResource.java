package org.diretto.api.client.main.core.binding.base;

import org.diretto.api.client.main.core.binding.resources.BetweenRangeResource;
import org.diretto.api.client.main.core.binding.resources.PositionCoordinatesResource;

/**
 * This {@code abstract} class serves as base class for all
 * {@code DocumentCreationResource}s, to minimize the effort required to
 * implement a specific {@code DocumentCreationResource}.
 * 
 * @author Tobias Schlecht
 */
public abstract class DocumentCreationResource extends AttachmentCreationResource
{
	private PositionCoordinatesResource.LocationResource location;
	private BetweenRangeResource createdBetween;

	public PositionCoordinatesResource.LocationResource getLocation()
	{
		return location;
	}

	public void setLocation(PositionCoordinatesResource.LocationResource location)
	{
		this.location = location;
	}

	public BetweenRangeResource getCreatedBetween()
	{
		return createdBetween;
	}

	public void setCreatedBetween(BetweenRangeResource createdBetween)
	{
		this.createdBetween = createdBetween;
	}
}
