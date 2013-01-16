package com.badlogic.android.games.framework;

import java.util.ArrayList;
import java.util.List;

public class Pool<T> {//ジェネリック型のPoolクラスを宣言

	public interface PoolObjectFactory<T> {
		public T createObject();
	}

	private final List<T> freeObjects;
	private final PoolObjectFactory<T> factory;
	private final int maxSize;

	public Pool(PoolObjectFactory<T> factory, int maxSize) {
		this.factory = factory;
		this.maxSize = maxSize;
		this.freeObjects = new ArrayList<T>(maxSize);//ArrayList(int)引数に指定した容量の空のArrayListを作成します。
	}
	
	public T newObject(){
		T object = null;
		
		if(freeObjects.size() == 0)
			object = factory.createObject();
		else
			object = freeObjects.remove(freeObjects.size() -1);
		
		return object;
	}
	
	public void free(T object){
		if(freeObjects.size() < maxSize)
			freeObjects.add(object);
	}
	
	

}
