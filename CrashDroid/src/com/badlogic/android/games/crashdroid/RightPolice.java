package com.badlogic.android.games.crashdroid;

import java.util.Random;


public class RightPolice {
	int x;
	int y;
	int flag;
	int num=0;
	Random random;
	

	public RightPolice(int n) {
		random = new Random();
		//x = Settings.Width;
		
		flag = n;
		setXY(flag);
	}
	public void init(){
		num++;
		if(flag<=1)
			setXY(random.nextInt(2));
		else
			setXY(random.nextInt(2)+2);	
	}
	
	public void clear(int n){
		num = 0;
		flag = n;
		setXY(flag);
	}
	
	public boolean end(){
		if(num==3)
			return true;
		else
			return false;
	}
	
	public void setXY(int flag){
		x = Settings.Width;
		switch (flag) {
		case 0:
			y = 9;
			break;
		case 1:
			y = 127;
			break;
		case 2:
			y = 245;
			break;
		case 3:
			y = 363;
			break;
		}
		
		
	}
	
	public void move(){

		x -=Speed.POLICESPEED;
		if(x+Assets.policecar.getWidth()-1 <0)
			init();
	}
		
	}
