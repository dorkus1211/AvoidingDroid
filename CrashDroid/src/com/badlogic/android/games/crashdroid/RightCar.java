package com.badlogic.android.games.crashdroid;


public class RightCar extends Car {

	public RightCar() {
		x = random.nextInt(111);
		y = random.nextInt(500) + Settings.Height;
	}

	@Override
	public void init() {
		x = random.nextInt(121) + 150;
		y = -random.nextInt(450) - Assets.car.getHeight();
	}

	@Override
	public void update() {
		if (y > 480 && Init) {
			init();
		} else {
			speed = Speed.SPEED;
			y += speed;
		}
	}

}
