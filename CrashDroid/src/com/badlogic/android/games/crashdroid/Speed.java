package com.badlogic.android.games.crashdroid;

public class Speed {
	public static int SPEED = 3;// 車のスピード
	public final int MAXSPEED = 10;
	public static int POLICESPEED = 3;
	public static double droidspeed = 2.3;

	private float updateSpeedTime = 0;
	private final float updateSpeedTimeTick = 30f;

	public void updateSpeed(float deltaTime) {
		updateSpeedTime += deltaTime;
		if (updateSpeedTime > updateSpeedTimeTick) {
			updateSpeedTime -= updateSpeedTimeTick;
					speedUp();
					maxSpeed();
				}
		}
	
	private void speedUp(){
		SPEED += 1;
	}
	
	private void maxSpeed(){
		if (SPEED > MAXSPEED)
			SPEED = MAXSPEED;
	}
		
		
	}

