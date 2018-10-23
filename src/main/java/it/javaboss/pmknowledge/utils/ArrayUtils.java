package it.javaboss.pmknowledge.utils;

import java.util.List;

public class ArrayUtils {
	
	public static boolean isNotEmpty( Object[] array ) {
		return array != null && array.length > 0;
	}
	
	public static boolean isEmpty( Object[] array ) {
		return !isNotEmpty(array);
	}
	
	@SuppressWarnings("rawtypes")
	public static Long[] asListOfLong(List list) {
		if ( list != null ) {
			Long[] array = new Long[list.size()];
			int i = 0;
			for ( Object element : list ) {
				array[i] = Long.parseLong( element.toString() );
				i++;
			}
			return array;
		}
		return new Long[0];
	}

}
