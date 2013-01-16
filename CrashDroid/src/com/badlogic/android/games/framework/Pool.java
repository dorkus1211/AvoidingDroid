package com.badlogic.android.games.framework;

import java.util.ArrayList;
import java.util.List;

public class Pool<T> {//�W�F�l���b�N�^��Pool�N���X��錾

	public interface PoolObjectFactory<T> {
		public T createObject();
	}

	private final List<T> freeObjects;
	private final PoolObjectFactory<T> factory;
	private final int maxSize;

	public Pool(PoolObjectFactory<T> factory, int maxSize) {
		this.factory = factory;
		this.maxSize = maxSize;
		this.freeObjects = new ArrayList<T>(maxSize);//ArrayList(int)�����Ɏw�肵���e�ʂ̋��ArrayList���쐬���܂��B
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
