package com.ztasks.hashmap.task;

import com.generalutils.GeneralUtils;
import com.exception.InvalidArgumentException;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class HashMapTask{
	
	public <K,V> Map<K,V> createMap(){
		return new HashMap<>();
	}
	
	public <K,V> void put(Map<K,V> map,K key,V value) throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(map);
		map.put(key,value);
	}
	
	public <K,V> boolean containsKey(Map<K,V> map,K key)throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(map);
		return map.containsKey(key);
	}
	
	public <K,V> boolean containsValue(Map<K,V> map,V value)throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(map);
		return map.containsValue(value);
	}
	
	public <K,V> V get(Map<K,V> map,K key) throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(map);
		return map.get(key);
	}
	
	public <K,V> V getOrDefault(Map<K,V> map,K key,V defaultVal) throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(map);
		//GeneralUtils.checkObjArgIsNull(defaultVal);
		return map.getOrDefault(key,defaultVal);
	}
	
	public <K,V> V remove(Map<K,V> map,K key) throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(map);
		return map.remove(key);
	}
	
	public <K,V> boolean remove(Map<K,V> map,K key,V value) throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(map);
		return map.remove(key,value);
	}
	
	public <K,V> V replace(Map<K,V> map,K key,V value) throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(map);
		return map.replace(key,value);
	}
	
	public <K,V> boolean replace(Map<K,V> map,K key,V oldValue,V newValue) 
	throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(map);
		return map.replace(key,oldValue,newValue);
	}
	
	public <K,V> void putAll(Map<K,V>map1,Map<K,V> map2)throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(map1);
		GeneralUtils.checkObjArgIsNull(map2);
		map1.putAll(map2);
	}
	
	public <K,V> Set<Map.Entry<K,V>> entrySet(Map<K,V> map)throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(map);
		return map.entrySet();
	}
	
	public <K,V> void clear (Map<K,V> map)throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(map);
		map.clear();
	} 
}