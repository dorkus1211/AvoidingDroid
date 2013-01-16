package com.badlogic.android.games.crashdroid;


public class LeftCar extends Car {

	public LeftCar() {
		x = random.nextInt(111);
		y = random.nextInt(500) + Settings.Height;
	}

	@Override
	public void init() {
		x = random.nextInt(111);
		y = random.nextInt(500) + Settings.Height;
	}

	@Override
	public void update() {
		if (y + Assets.car.getHeight() - 1 < 0 && Init) {
			init();
		} else {
			speed = Speed.SPEED;
			y -= speed;
		}
	}

}
