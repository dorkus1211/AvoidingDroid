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
		SensorManager manager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);//SensorManager型のmanagerにセンサーサービスを格納
		if(manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0){//managerに格納された加速センサーがあるか判定
			Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);//accelerometerにTYPE_ACCELEROMETERのセンサーを格納
			manager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_GAME);//センサーを登録？？SENSOR_DELAY_GAMEはセンサー更新頻度の定数ゲーム用
		}
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor,int accuracy){
		
	}
	
	@Override
	public void onSensorChanged(SensorEvent event){//加速センサーが変わったら
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