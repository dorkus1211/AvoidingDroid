package com.badlogic.android.games.crashdroid;

public class Time {
	public float updateTime = 0;
	private final float updateTick = 0.00001f;
	public float GameOverTime = 0;
	public final float GameOverTick = 3f;

	public Time() {
	}

	//�A�b�v�f�[�g���Ԃ��X�V�B
	public void updateTime(float deltaTime) {
		updateTime += deltaTime;
	}
	
	public void updateGame(float deltaTime){
		GameOverTime += deltaTime;
		
	}
	
	//updateTick���Ԍo�߂���������B
	public boolean availableTime(){
		if (updateTime > updateTick){
			updateTime -= updateTick;
			return true;
		}else{
			return false;}
	}
	
	public boolean GameOverTime(){
		if(GameOverTime > GameOverTick){
			GameOverTime -= GameOverTick;
			return true;
		}else{
			return false;
		}
		
	}

}
