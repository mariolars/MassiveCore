package com.massivecraft.massivecore.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtil
{
	public static Object getField(Class<?> clazz, String fieldName, Object object)
	{
		try
		{
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(object);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	public static boolean setField(Class<?> clazz, String fieldName, Object object, Object value)
	{
		try
		{
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(object, value);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public static boolean transferField(Class<?> clazz, Object from, Object to, String fieldName)
	{
		try
		{
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			Object value = field.get(from);
			field.set(to, value);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public static boolean transferFields(Class<?> clazz, Object from, Object to, List<String> fieldNames)
	{
		if (fieldNames == null)
		{
			fieldNames = new ArrayList<String>();
			for (Field field : clazz.getDeclaredFields())
			{
				fieldNames.add(field.getName());
			}
		}
		
		for (String fieldName : fieldNames)
		{
			if ( ! transferField(clazz, from, to, fieldName)) return false;
		}
		
		return true;
	}
	
	public static boolean transferFields(Class<?> clazz, Object from, Object to)
	{
		return transferFields(clazz, from, to, null);
	}
	
}
