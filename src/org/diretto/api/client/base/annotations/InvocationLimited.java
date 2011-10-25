package org.diretto.api.client.base.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a method should not be invoked by any class and defines a list
 * of classes, which have the explicit permission to invoke the annotated
 * method.
 * 
 * @author Tobias Schlecht
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface InvocationLimited
{
	Class<?>[] legitimateInvocationClasses();
}
