package com.badlogic.android.games.framework.impl;

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;


import com.badlogic.android.games.framework.Music;

public class AndroidMusic implements Music, OnCompletionListener{
	MediaPlayer mediaPlayer;
	boolean isPrepared = false;//mediaPlayer�����������O�ɍĐ����郁�\�b�h���Ă΂��̂�h�����߂�false�ɐݒ�B

	public AndroidMusic(AssetFileDescriptor assetDescriptor){
		mediaPlayer = new MediaPlayer();//MediaPlayer�^��mediaPlayer�I�u�W�F�N�g��錾�B
		try{
			mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),assetDescriptor.getStartOffset(),assetDescriptor.getLength());//�Đ�����t�@�C����mediaPlayer�ɐݒ�
			mediaPlayer.prepare();//mediaPlayer�̏����B
			isPrepared = true;//mediaPlayer.prepare()�̌��true��
			mediaPlayer.setOnCompletionListener(this);//mediaPlayer��OnCompletionListener��o�^�B
		}catch (Exception e){
			throw new RuntimeException("Couldnt load music");
		}
	}
	
	@Override
	public void dispose(){
		if(mediaPlayer.isPlaying()){//mediaPlayer���Đ������m�F
			mediaPlayer.stop();//�Đ����Ȃ�stop
			mediaPlayer.release();//���\�[�X���
		}
	}
	
	@Override
	public boolean isLooping(){
		return mediaPlayer.isLooping();//���[�v�Đ�������Ԃ��B
	}
	
	@Override
	public boolean isPlaying(){
		return mediaPlayer.isPlaying();//�Đ�������Ԃ��B
	}
	
	@Override
	public boolean isStopped(){
		return !isPrepared;//���f�B�A�v���C���[����~���Ă邩�ǂ������������B
	}
	
	@Override
	public void pause(){
		if(mediaPlayer.isPlaying())//�Đ����Ȃ�pause
			mediaPlayer.pause();
	}
	
	@Override
	public void play(){
		if(mediaPlayer.isPlaying())//�Đ����Ȃ�I��
			return;
			
			try{
				synchronized(this){//synchronized�͎w�肳�ꂽ�����́A���������s���Ă���I�u�W�F�N�g�𑼂̏�������A�N�Z�X����Ȃ��悤���b�N���܂��B
					if(!isPrepared)//��������ĂȂ�������
						mediaPlayer.prepare();//����
					mediaPlayer.start();//�Đ��J�n
				}
			}catch (IllegalStateException e){
				e.printStackTrace();//�G���[���o��
			}catch (IOException e){
				e.printStackTrace();
			}
		
	}
	
	@Override
	public void setLooping(boolean isLooping){//���[�v����̂����H
		mediaPlayer.setLooping(isLooping);
	}
	
	@Override
	public void setVolume(float volume){//���̑傫�����Z�b�g�B
		mediaPlayer.setVolume(volume,volume);
	}
	
	@Override
	public void stop(){
		mediaPlayer.stop();
		synchronized(this){
			isPrepared = false;
		}
	}
	
	@Override
	public void onCompletion(MediaPlayer player){
		synchronized (this){
			isPrepared = false;
		}
	}
}
