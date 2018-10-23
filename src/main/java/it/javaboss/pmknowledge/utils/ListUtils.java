package it.javaboss.pmknowledge.utils;

import java.util.List;

public class ListUtils {
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List list) {
		return list == null || list.isEmpty();
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(List list) {
		return !isEmpty(list);
	}
}
