package com.badlogic.android.games.crashdroid;

import java.util.Random;

public class LeftPolice {
	int x;
	int y;
	int flag;
	int num = 0;
	Random random;

	public LeftPolice(int n) {
		random = new Random();
		//x = 0 - Assets.policecar.getWidth() - 1;
		flag = n;
		setXY(flag);
	}
	
	
	public void clear(int n){
		num = 0;
		flag = n;
		setXY(flag);
	}

	public void setXY(int flag) {
		x = 0 - Assets.policecar.getWidth() - 1;
		switch (flag) {
		case 0:
			y = 68;
			break;
		case 1:
			y = 186;
			break;
		case 2:
			y = 304;
			break;
		case 3:
			y = 422;
			break;
		}

	}

	public void init() {
		num++;
		if (flag <= 1)
			setXY(random.nextInt(2));
		else
			setXY(random.nextInt(2) + 2);
	}

	public boolean end() {
		if (num == 3)
			return true;
		else
			return false;
	}

	public void move() {
		x += Speed.POLICESPEED;

		if (x > Settings.Width)
			init();

	}

}
