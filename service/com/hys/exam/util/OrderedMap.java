package com.hys.exam.util;

/**
 * 
 * ���⣺ҽ������̨
 * 
 * ���ߣ���������2009 4 9
 * 
 * ������
 * 
 * ˵��:
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class OrderedMap<K, V> extends HashMap<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -706259030644989276L;

	private List<K> orderedKeys = new LinkedList<K>();

	/**
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public V put(K key, V value) {
		if (!orderedKeys.contains(key))
			orderedKeys.add(key);
		return super.put(key, value);
	}

	/**
	 * @see java.util.Map#clear()
	 */
	public void clear() {
		super.clear();
		orderedKeys.clear();
	}

	/**
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public V remove(Object key) {
		if (orderedKeys.contains(key))
			orderedKeys.remove(key);
		return super.remove(key);
	}

	public Object[] getKeyArray() {
		return orderedKeys.toArray();
	}

	public Object[] getKeyArray(Object[] a) {
		return orderedKeys.toArray(a);
	}

	public List<V> toList() {

		List<V> list = new ArrayList<V>(orderedKeys.size());

		for (int i = 0; i < orderedKeys.size(); ++i) {
			list.add(get(orderedKeys.get(i)));
		}

		return list;
	}
}