package com.badlogic.android.games.crashdroid;

import java.util.Random;

import com.badlogic.android.games.framework.Graphics;

public class Item {

	enum ItemList {
		no, bomber, cardown, droidup
	}

	public static ItemList item = ItemList.no;
	public static float updateTime = 0;
	public final static float updateTick = 5.0f;
	public static int x, y;

	public static void selectItem() {
		Random random = new Random();
		int i = random.nextInt(3);
		x = random.nextInt(281);
		y = random.nextInt(441);
		switch (i) {
		case 0:
			item = ItemList.bomber;
			break;
		case 1:
			item = ItemList.cardown;
			break;
		case 2:
			item = ItemList.droidup;
			break;
		}
	}

	public static void update(GatherCar gatherCar,PoliceCar PC) {
		//Random random = new Random();
		if (item == ItemList.bomber) {
			int len = gatherCar.Cars.size();
			for (int i = 0; i < len; i++)
				gatherCar.Cars.get(i).init();
			PC.rightPcar.get(1).num=3;
		}

		if (item == ItemList.cardown) {
			Speed.SPEED -= 2;
			Speed.POLICESPEED -=2;
			
		}

		if (item == ItemList.droidup) {
			Speed.droidspeed = 4;
		}
	}

	public static void present(Graphics g){
		if(item == ItemList.bomber)
			g.drawPixmap(Assets.bomber,x,y);
		if(item == ItemList.cardown)
			g.drawPixmap(Assets.cardown,x,y);			
		if(item == ItemList.droidup)
			g.drawPixmap(Assets.droidup,x,y);
		
	}

	public static boolean nokoriTime(float deltaTime) {
		updateTime += deltaTime;
		if (updateTime > updateTick) {
			updateTime = 0;
			//item = ItemList.no;
			return true;
		}
		return false;
	}
	
	public static boolean getItem(Droid droid){
		int droidWidth = Assets.droid.getWidth();
		int droidHeight = Assets.droid.getHeight();
		if(AttackJudge.rectangleAttack(x,y,40,40,(int)droid.x,(int)droid.y,droidWidth,droidHeight))
			return true;
		else
			return false;
	
		
	}
	
	public static void end(){
		if(item == ItemList.bomber);
		if(item == ItemList.cardown){
			Speed.SPEED +=2;		
			Speed.POLICESPEED = 3;
		}
		if(item == ItemList.droidup)
			Speed.droidspeed = 2.3;
		item = ItemList.no;
		
	}

}
