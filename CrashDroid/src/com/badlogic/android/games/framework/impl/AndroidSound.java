package com.badlogic.android.games.framework.impl;

import android.media.SoundPool;

import com.badlogic.android.games.framework.Sound;

public class AndroidSound implements Sound{
	int soundId;
	SoundPool soundPool;
	
	public AndroidSound(SoundPool soundPool,int soundId){
		this.soundId = soundId;//メンバのsoundIDにsoundIdを格納
		this.soundPool = soundPool;//メンバのsoundPoolにsoundpoolを格納
	}
	
	@Override
	public void play(float volume){
		soundPool.play(soundId, volume, volume, 0, 0, 1);//soundIdと対応するサウンドをvolumeの大きさで再生。
	}

	@Override
	public void dispose(){
		soundPool.unload(soundId);//使わなくなったサウンドのリソースを開放。
	}
}
