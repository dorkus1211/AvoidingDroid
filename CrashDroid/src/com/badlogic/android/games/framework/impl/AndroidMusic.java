package com.badlogic.android.games.framework.impl;

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;


import com.badlogic.android.games.framework.Music;

public class AndroidMusic implements Music, OnCompletionListener{
	MediaPlayer mediaPlayer;
	boolean isPrepared = false;//mediaPlayerが準備される前に再生するメソッドが呼ばれるのを防ぐためにfalseに設定。

	public AndroidMusic(AssetFileDescriptor assetDescriptor){
		mediaPlayer = new MediaPlayer();//MediaPlayer型のmediaPlayerオブジェクトを宣言。
		try{
			mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),assetDescriptor.getStartOffset(),assetDescriptor.getLength());//再生するファイルをmediaPlayerに設定
			mediaPlayer.prepare();//mediaPlayerの準備。
			isPrepared = true;//mediaPlayer.prepare()の後にtrueへ
			mediaPlayer.setOnCompletionListener(this);//mediaPlayerにOnCompletionListenerを登録。
		}catch (Exception e){
			throw new RuntimeException("Couldnt load music");
		}
	}
	
	@Override
	public void dispose(){
		if(mediaPlayer.isPlaying()){//mediaPlayerが再生中か確認
			mediaPlayer.stop();//再生中ならstop
			mediaPlayer.release();//リソース解放
		}
	}
	
	@Override
	public boolean isLooping(){
		return mediaPlayer.isLooping();//ループ再生中かを返す。
	}
	
	@Override
	public boolean isPlaying(){
		return mediaPlayer.isPlaying();//再生中かを返す。
	}
	
	@Override
	public boolean isStopped(){
		return !isPrepared;//メディアプレイヤーが停止してるかどうかをかえす。
	}
	
	@Override
	public void pause(){
		if(mediaPlayer.isPlaying())//再生中ならpause
			mediaPlayer.pause();
	}
	
	@Override
	public void play(){
		if(mediaPlayer.isPlaying())//再生中なら終了
			return;
			
			try{
				synchronized(this){//synchronizedは指定された処理は、処理を実行しているオブジェクトを他の処理からアクセスされないようロックします。
					if(!isPrepared)//準備されてなかったら
						mediaPlayer.prepare();//準備
					mediaPlayer.start();//再生開始
				}
			}catch (IllegalStateException e){
				e.printStackTrace();//エラーを出力
			}catch (IOException e){
				e.printStackTrace();
			}
		
	}
	
	@Override
	public void setLooping(boolean isLooping){//ループするのかい？
		mediaPlayer.setLooping(isLooping);
	}
	
	@Override
	public void setVolume(float volume){//音の大きさをセット。
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
