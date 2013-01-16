package com.badlogic.android.games.crashdroid;

import java.util.Random;

import android.util.Log;

import com.badlogic.android.games.crashdroid.Item.ItemList;
import com.badlogic.android.games.framework.Game;
import com.badlogic.android.games.framework.Graphics;

public class RunningObject {

	public PoliceCar PC;
	private Droid dr;
	public Point point;
	private Time time;
	public GatherCar gatherCar;
	private Speed speed;

	private boolean item = false;
	private boolean showItem = false;
	private boolean getItem = false;
	private Random random = new Random();
	private boolean Police = false;
	public static boolean gameOver = false;

	public RunningObject() {
		gameOver = false;
		dr = new Droid();//
		PC = new PoliceCar(dr);
		point = new Point();
		time = new Time();
		gatherCar = new GatherCar(dr);
		speed = new Speed();
	}

	public boolean judged(float deltaTime) {
		if (gatherCar.attackCar() || PC.attackPolice())
			gameOver = true;

		if (gameOver) {
			time.updateGame(deltaTime);
			if (time.GameOverTime()){
					Speed.SPEED = 3;	
				Item.item = ItemList.no;
				Speed.droidspeed = 2.3;
				Item.updateTime = 0;
				Speed.POLICESPEED = 3;
				return true;
				
			}
		}

		return false;

	}

	public void update(float deltaTime, Game game) {
		Log.d("SPEED",""+Speed.SPEED);
		time.updateTime(deltaTime);
		point.updatePoint(deltaTime);// 得点を更新。
		speed.updateSpeed(deltaTime);// スピードを更新。

		if (time.availableTime()) {

			dr.updateDroid(game);

			if (!item) {
				if (random.nextInt(500) == 1) {
					showItem = true;
					item = true;
					Item.selectItem();
				}
			}

			if (item&&getItem==false) {
				if (Item.nokoriTime(deltaTime)){
					item = false;
					Item.item = ItemList.no;
				}

				if (Item.getItem(dr)) {
					showItem = false;
					Item.update(gatherCar,PC);
					getItem = true;
					Item.updateTime = 0;
				}


			}
			
			if(getItem){
				if(Item.nokoriTime(deltaTime)){
					item = false;
					Item.end();
					getItem = false;
				}
			}

			if (!Police) {// パトカーが出現してないか判断
				if (random.nextInt(1000) == 1) {// 乱数がパトカーを出現する値になっているかチェック
					Police = true;// もしそうならパトカーのいろいろやるやつをtrue
					gatherCar.initFalse();// 車を初期化しないように
				}
			}

			if (Police) {// パトカーやるかしらべる。
				if (gatherCar.CarOut()) {// 車が画面外にあるかしらべる。
					if (PC.end()) {
						gatherCar.initTrue();
						gatherCar.updateCar();
						PC.Clear();
						Police = false;
					} else {
						PC.update();
					}
				} else {
					gatherCar.updateCar();
				}
			} else {

				gatherCar.updateCar();
			}

		}
	}

	public void present(float deltaTime, Graphics g) {
		if (showItem)
			Item.present(g);
		dr.presentDroid(g);
		PC.present(g);
		if (!gatherCar.CarOut() && Police)
			if ((int) point.sumTime % 2 == 0)
				g.drawPixmap(Assets.tyuui, Settings.Width/2-Assets.tyuui.getWidth()/2, Settings.Height/2-Assets.tyuui.getHeight()/2);

		gatherCar.presentCar(g);
		point.presentPoint(g);

		if (gameOver)
			g.drawPixmap(Assets.gameover, 35, 150);
	}

}
