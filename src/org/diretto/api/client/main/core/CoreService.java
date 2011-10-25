package org.diretto.api.client.main.core;

import java.util.Map;

import org.diretto.api.client.base.data.ExternalMediaType;
import org.diretto.api.client.base.data.MediaMainType;
import org.diretto.api.client.base.data.MediaType;
import org.diretto.api.client.base.data.PlatformMediaType;
import org.diretto.api.client.base.entities.Entity;
import org.diretto.api.client.service.Service;

/**
 * This interface represents a {@code CoreService}. <br/><br/>
 * 
 * The {@code CoreService} provides the bulk of the platform functionalities in
 * respect of the {@code Core API}.
 * 
 * @author Tobias Schlecht
 */
public interface CoreService extends Service, DataManager
{
	/**
	 * Returns the maximum value of {@link Entity}s which can be requested with
	 * one method invocation.
	 * 
	 * @return The maximum value of {@code Entity}s which can be requested
	 */
	int getMaxEntityRequestSize();

	/**
	 * Returns the pagination size of the API.
	 * 
	 * @return The pagination size of the API
	 */
	int getPaginationSize();

	/**
	 * Returns the requested {@link PlatformMediaType} or {@code null} if no
	 * {@code PlatformMediaType} was found for the given <i>ID</i>
	 * {@code String} and thus the requested {@code PlatformMediaType} is not
	 * supported by the platform.
	 * 
	 * @param id The corresponding <i>ID</i> {@code String}
	 * @return The requested {@code PlatformMediaType}
	 */
	PlatformMediaType getPlatformMediaType(String id);

	/**
	 * Returns a {@link Map} with all supported {@link PlatformMediaType}s and
	 * their mapped <i>ID</i> {@code String}s. <br/><br/>
	 * 
	 * <i>Annotation:</i> An example for an <i>ID</i> {@code String} is
	 * {@code "image/jpeg"}.
	 * 
	 * @return A {@code Map} with all supported {@code PlatformMediaType}s
	 */
	Map<String, PlatformMediaType> getSupportedPlatformMediaTypes();

	/**
	 * Returns the requested {@link ExternalMediaType} or {@code null} if no
	 * {@code ExternalMediaType} was found for the given <i>ID</i>
	 * {@code String} and thus the requested {@code ExternalMediaType} is not
	 * supported by the platform.
	 * 
	 * @param id The corresponding <i>ID</i> {@code String}
	 * @return The requested {@code ExternalMediaType}
	 */
	ExternalMediaType getExternalMediaType(String id);

	/**
	 * Returns a {@link Map} with all supported {@link ExternalMediaType}s and
	 * their mapped <i>ID</i> {@code String}s. <br/><br/>
	 * 
	 * <i>Annotation:</i> An example for an <i>ID</i> {@code String} is
	 * {@code "video/x-diretto-youtube+url"}.
	 * 
	 * @return A {@code Map} with all supported {@code ExternalMediaType}s
	 */
	Map<String, ExternalMediaType> getSupportedExternalMediaTypes();

	/**
	 * Returns the requested {@link MediaMainType} or {@code null} if no
	 * {@code MediaMainType} was found for the given {@code String}
	 * representation and thus the requested {@code MediaMainType} is not
	 * supported by the platform. <br/><br/>
	 * 
	 * <i>Attention:</i> Supporting a {@code MediaMainType} does not mean that
	 * the platform supports all {@link MediaType}s which have this
	 * {@code MediaMainType}, but this means only that there are one or more
	 * supported {@code MediaType}s which have this {@code MediaMainType}.
	 * Hence, for instance supporting the {@code MediaMainType} {@code "image"}
	 * ({@code String} representation) can involve that the {@code MediaType}
	 * {@code "image/jpeg"} ({@code String} representation) is supported, but
	 * the {@code MediaType} {@code "image/bmp"} ({@code String} representation)
	 * is not supported.
	 * 
	 * @param type The {@code String} representation of the main type
	 * @return The requested {@code MediaMainType}
	 */
	MediaMainType getMediaMainType(String type);

	/**
	 * Returns a {@link Map} with all supported {@link MediaMainType}s and their
	 * mapped {@code String} representation. <br/><br/>
	 * 
	 * <i>Annotation:</i> An example for the {@code String} representation of a
	 * {@code MediaMainType} is {@code "image"}. <br/><br/>
	 * 
	 * <i>Attention:</i> Supporting a {@code MediaMainType} does not mean that
	 * the platform supports all {@link MediaType}s which have this
	 * {@code MediaMainType}, but this means only that there are one or more
	 * supported {@code MediaType}s which have this {@code MediaMainType}.
	 * Hence, for instance supporting the {@code MediaMainType} {@code "image"}
	 * ({@code String} representation) can involve that the {@code MediaType}
	 * {@code "image/jpeg"} ({@code String} representation) is supported, but
	 * the {@code MediaType} {@code "image/bmp"} ({@code String} representation)
	 * is not supported.
	 * 
	 * @return A {@code Map} with all supported {@code MediaMainType}s
	 */
	Map<String, MediaMainType> getSupportedMediaMainTypes();
}
