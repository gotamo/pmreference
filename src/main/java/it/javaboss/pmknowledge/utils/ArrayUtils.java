package it.javaboss.pmknowledge.utils;

import java.util.List;

import it.javaboss.pmknowledge.model.Identifiable;

public class ArrayUtils {
	
	public static boolean isNotEmpty( Object[] array ) {
		return array != null && array.length > 0;
	}
	
	public static boolean isEmpty( Object[] array ) {
		return !isNotEmpty(array);
	}
	
	public static <T extends Identifiable> Long[] asListOfId(List<T> list) {
		if ( list != null ) {
			Long[] array = new Long[list.size()];
			int i = 0;
			for ( Identifiable element : list ) {
				array[i] = element.getId();
				i++;
			}
			return array;
		}
		return new Long[0];
	}

}
