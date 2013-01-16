package com.badlogic.android.games.framework.impl;

import android.media.SoundPool;

import com.badlogic.android.games.framework.Sound;

public class AndroidSound implements Sound{
	int soundId;
	SoundPool soundPool;
	
	public AndroidSound(SoundPool soundPool,int soundId){
		this.soundId = soundId;//�����o��soundID��soundId���i�[
		this.soundPool = soundPool;//�����o��soundPool��soundpool���i�[
	}
	
	@Override
	public void play(float volume){
		soundPool.play(soundId, volume, volume, 0, 0, 1);//soundId�ƑΉ�����T�E���h��volume�̑傫���ōĐ��B
	}

	@Override
	public void dispose(){
		soundPool.unload(soundId);//�g��Ȃ��Ȃ����T�E���h�̃��\�[�X���J���B
	}
}
