package com.badlogic.android.games.framework.impl;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerHandler implements SensorEventListener{
	float accelX;
	float accelY;
	float accelZ;
	
	public AccelerometerHandler(Context context){
		SensorManager manager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);//SensorManager�^��manager�ɃZ���T�[�T�[�r�X���i�[
		if(manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0){//manager�Ɋi�[���ꂽ�����Z���T�[�����邩����
			Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);//accelerometer��TYPE_ACCELEROMETER�̃Z���T�[���i�[
			manager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_GAME);//�Z���T�[��o�^�H�HSENSOR_DELAY_GAME�̓Z���T�[�X�V�p�x�̒萔�Q�[���p
		}
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor,int accuracy){
		
	}
	
	@Override
	public void onSensorChanged(SensorEvent event){//�����Z���T�[���ς������
		accelX = event.values[0];
		accelY = event.values[1];
		accelZ = event.values[2];
	}
	
	public float getAccelX(){
		return accelX;
	}
	
	public float getAccelY(){
		return accelY;
	}
	
	public float getAccelZ(){
		return accelZ;
	}
	
	
	
	
	
}