package com.bteteam.bteLite.util.registry;

import java.util.List;
import java.util.Map.Entry;

public interface IRegistryHolder<K, V> {
	
	public Entry<K, V> get(int index);
	
	public List<Entry<K, V>> getAll();

	public void add(K k,V v);
}
