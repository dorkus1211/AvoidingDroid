package com.badlogic.android.games.crashdroid;

import com.badlogic.android.games.framework.Graphics;

public class Point {
	public int Point;// ���_
	public float sumTime = 0;// ���v����

	public Point() {
	}

	public void updatePoint(float deltaTime) {
		if(!RunningObject.gameOver){
		sumTime += deltaTime;// ���v���Ԃ��X�V�B
		Point = ((int) sumTime) / 2;}
	}

	public void presentPoint(Graphics g) {
		g.drawText(String.valueOf(Point), 50, 50, 50);// ���_��`��
	}

	// ���_��Ԃ��B
	public int getPoint() {
		Point = ((int) sumTime) / 2;
		return Point;
	}

}
