package com.badlogic.android.games.crashdroid;

import com.badlogic.android.games.framework.Graphics;

public class Point {
	public int Point;// 得点
	public float sumTime = 0;// 合計時間

	public Point() {
	}

	public void updatePoint(float deltaTime) {
		if(!RunningObject.gameOver){
		sumTime += deltaTime;// 合計時間を更新。
		Point = ((int) sumTime) / 2;}
	}

	public void presentPoint(Graphics g) {
		g.drawText(String.valueOf(Point), 50, 50, 50);// 得点を描画
	}

	// 得点を返す。
	public int getPoint() {
		Point = ((int) sumTime) / 2;
		return Point;
	}

}
