package org.diretto.api.client.util;

import java.lang.reflect.Method;

import org.diretto.api.client.base.annotations.InvocationLimited;

/**
 * {@code InvocationUtils} is a noninstantiable utility class and provides
 * miscellaneous methods in respect of invocations.
 * 
 * @author Tobias Schlecht
 */
public final class InvocationUtils
{
	/**
	 * The constructor is {@code private} to suppress the default constructor
	 * for noninstantiability.
	 */
	private InvocationUtils()
	{
		throw new AssertionError();
	}

	/**
	 * Checks whether the method invocation of the specified method is
	 * legitimate and returns the result. Furthermore the provided warning
	 * message will be printed to the "standard" error output stream with the
	 * preceded {@code String} "WARNING: ". If the value of the delivered String
	 * is {@code null} no message will be printed (neither the preceded
	 * {@code String} "WARNING: ").
	 * 
	 * @param warningMessage The {@code String} to be printed
	 * @param methodName The name of the method
	 * @param parameterTypes The parameter array of the method
	 * @return {@code true} if the method invocation is legitimate;
	 *         {@code false} otherwise
	 */
	public static boolean checkMethodInvocation(String warningMessage, String methodName, Class<?>... parameterTypes)
	{
		// See also: http://stackoverflow.com/questions/421280

		final Class<?>[] executionStack = new SecurityManager()
		{
			public Class<?>[] getExecutionStack()
			{
				return getClassContext();
			}
		}.getExecutionStack();

		boolean legitimateInvocation = false;

		try
		{
			Method m = executionStack[2].getDeclaredMethod(methodName, parameterTypes);

			if(m.isAnnotationPresent(InvocationLimited.class))
			{
				Class<?>[] legitimateInvocationClasses = m.getAnnotation(InvocationLimited.class).legitimateInvocationClasses();

				for(Class<?> c : legitimateInvocationClasses)
				{
					if(c.equals(executionStack[3]))
					{
						legitimateInvocation = true;
						break;
					}
				}

				if(!legitimateInvocation && warningMessage != null)
				{
					System.err.println("WARNING: " + warningMessage);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return legitimateInvocation;
	}
}
