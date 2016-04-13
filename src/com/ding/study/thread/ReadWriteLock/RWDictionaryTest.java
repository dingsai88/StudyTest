package com.ding.study.thread.ReadWriteLock;

import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;
 
import java.util.Collections;

import java.util.concurrent.locks.Lock;

import java.util.concurrent.locks.ReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import java.util.TreeMap;

import java.util.Map;

class RWDictionary {

	private final Map<String, String> map = new TreeMap<String, String>();
	private final ReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock readLock = rwl.readLock();
	private final Lock writeLock = rwl.writeLock();

	{
	 	map.put("EN", "英国");
    	map.put("CA", "加拿大");
    	map.put("FR", "法国");
    	map.put("CN", "中国");
	}
	
	
	public String get(String key) {
		readLock.lock();
		try {
			return map.get(key);
		} finally {
			readLock.unlock();
		}
	}

	public String[] allKeys() {
		readLock.lock();
		try {
			return (String[]) map.keySet().toArray();
		} finally {
			readLock.unlock();
		}
	}

	public String put(String key, String value) {
		writeLock.lock();
		try {
			return map.put(key, value);
		} finally {
			writeLock.unlock();
		}
	}

	public void clear() {
		writeLock.lock();
		try {
			map.clear();
		} finally {
			writeLock.unlock();
		}
	}
}



public class RWDictionaryTest {
    public static void main(String[] args) {
    	
	    	RWDictionary rw=new RWDictionary();
	    	String value =rw.get("CN");
	    	System.out.println("value ="+value );
    	
    	
	    	Map<String, String> map = new TreeMap<String, String>();
	    	map.put("EN", "英国");
	    	map.put("CA", "加拿大");
	    	map.put("FR", "法国");
	    	map.put("CN", "中国");
    	//Collections.synchronizedMap用法如下
	      Map<String, String> m = Collections.synchronizedMap(map);
	      Set<String> s = m.keySet();  // Needn't be in synchronized block
	       synchronized(m) {  // Synchronizing on m, not s!
	    	   Iterator <String> i = s.iterator(); // Must be in synchronized block
	           while (i.hasNext()){
	        	   RWDictionaryTest.foo(i.next());
	           }
	       }
	}
    
    public static void foo(String str){
    	System.out.println(" "+ str+" ");
    }
}
