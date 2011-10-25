/**
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: LGPL 3.0 or LGPL 2.1 or CDDL 1.0 or EPL 1.0 (the
 * "Licenses"). You can select the license that you prefer but you may not use
 * this file except in compliance with one of these Licenses.
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.opensource.org/licenses/lgpl-3.0.html
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.opensource.org/licenses/lgpl-2.1.php
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.opensource.org/licenses/cddl1.php
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0.php
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 */

package org.diretto.api.client.base.external;

import java.io.IOException;
import java.util.List;

import org.restlet.data.MediaType;
import org.restlet.data.Preference;
import org.restlet.engine.resource.VariantInfo;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.UniformResource;

/**
 * This class extends the {@link org.restlet.ext.jackson.JacksonConverter} and
 * enables the processing of messages with {@code Content-Type} values which are
 * not {@code application/json}.
 * 
 * @author Jerome Louvel
 * @author Tobias Schlecht
 */
public class JacksonConverter extends org.restlet.ext.jackson.JacksonConverter
{
	private static final VariantInfo VARIANT_JSON = new VariantInfo(MediaType.APPLICATION_ALL);

	@Override
	protected <T> JacksonRepresentation<T> create(MediaType mediaType, T source)
	{
		return super.create(mediaType, source);
	}

	@Override
	protected <T> JacksonRepresentation<T> create(Representation source, Class<T> objectClass)
	{
		return super.create(source, objectClass);
	}

	@Override
	public List<Class<?>> getObjectClasses(Variant source)
	{
		List<Class<?>> result = null;

		if(VARIANT_JSON.isCompatible(source))
		{
			result = addObjectClass(result, Object.class);
			result = addObjectClass(result, JacksonRepresentation.class);
		}

		return result;
	}

	@Override
	public List<VariantInfo> getVariants(Class<?> source)
	{
		List<VariantInfo> result = null;

		if(source != null)
		{
			result = addVariant(result, VARIANT_JSON);
		}

		return result;
	}

	@Override
	public float score(Object source, Variant target, UniformResource resource)
	{
		float result = -1.0F;

		if(source instanceof JacksonRepresentation<?>)
		{
			result = 1.0F;
		}
		else
		{
			if(target == null)
			{
				result = 0.5F;
			}
			else if(VARIANT_JSON.isCompatible(target))
			{
				result = 0.8F;
			}
			else
			{
				result = 0.5F;
			}
		}

		return result;
	}

	@Override
	public <T> float score(Representation source, Class<T> target, UniformResource resource)
	{
		float result = -1.0F;

		if(source instanceof JacksonRepresentation<?>)
		{
			result = 1.0F;
		}
		else if((target != null) && JacksonRepresentation.class.isAssignableFrom(target))
		{
			result = 1.0F;
		}
		else if(VARIANT_JSON.isCompatible(source))
		{
			result = 0.8F;
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T toObject(Representation source, Class<T> target, UniformResource resource) throws IOException
	{
		Object result = null;
		JacksonRepresentation<?> jacksonSource = null;

		if(source instanceof JacksonRepresentation)
		{
			jacksonSource = (JacksonRepresentation<?>) source;
		}
		else if(VARIANT_JSON.isCompatible(source))
		{
			jacksonSource = create(source, target);
		}

		if(jacksonSource != null)
		{
			if((target != null) && JacksonRepresentation.class.isAssignableFrom(target))
			{
				result = jacksonSource;
			}
			else
			{
				result = jacksonSource.getObject();
			}
		}

		return (T) result;
	}

	@Override
	public Representation toRepresentation(Object source, Variant target, UniformResource resource)
	{
		Representation result = null;

		if(source instanceof JacksonRepresentation)
		{
			result = (JacksonRepresentation<?>) source;
		}
		else
		{
			target.setMediaType(MediaType.APPLICATION_JSON);

			if(VARIANT_JSON.isCompatible(target))
			{
				JacksonRepresentation<Object> jacksonRepresentation = create(target.getMediaType(), source);
				result = jacksonRepresentation;
			}
		}

		return result;
	}

	@Override
	public <T> void updatePreferences(List<Preference<MediaType>> preferences, Class<T> entity)
	{
		super.updatePreferences(preferences, entity);
	}
}
