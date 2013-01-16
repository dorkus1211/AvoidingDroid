package com.badlogic.android.games.crashdroid;

import android.graphics.Rect;

import com.badlogic.android.games.framework.Game;
import com.badlogic.android.games.framework.Graphics;
import com.badlogic.android.games.framework.Input;

public class Droid {
	float x;
	float y;
	Rect droidRect;
	
	

	public Droid() {
		int DroidWidth = Assets.droid.getWidth();
		int DroidHeight = Assets.droid.getHeight();
		x = Settings.Width / 2 + DroidWidth / 2 - 1;
		y = Settings.Height / 2 + DroidHeight / 2 - 1;
		droidRect = new Rect((int)x,(int)y,(int)(x+Assets.droid.getWidth()),(int)(y+Assets.droid.getHeight()));


	}

	// ドロイドくんの位置を更新
	public void moveDroid(float x, float y) {
		int DroidWidth = Assets.droid.getWidth();
		int DroidHeight = Assets.droid.getHeight();
		this.x += x < 0 ? x * Speed.droidspeed : x * Speed.droidspeed;
		this.y += y < 0 ? y * Speed.droidspeed : y * Speed.droidspeed;
		if (this.x < 0)
			this.x = 0;
		if (this.x > Settings.Width - DroidWidth)
			this.x = Settings.Width - DroidWidth;
		if (this.y < 0)
			this.y = 0;
		if (this.y > Settings.Height - DroidHeight)
			this.y = Settings.Height - DroidHeight;
		
		droidRect.set((int)x,(int)y,(int)(x+Assets.droid.getWidth()),(int)(y+Assets.droid.getHeight()));

	}

	public void updateDroid(Game game) {
		Input input = game.getInput();
		float accelX = -input.getAccelX();// 加速センサーのX
		float accelY = input.getAccelY();// y取得
		if(!RunningObject.gameOver)
		moveDroid(accelX, accelY); // ドロイドくんの位置を更新
	}

	public void presentDroid(Graphics g) {
		if(RunningObject.gameOver){
			g.drawPixmap(Assets.blooddroid, x, y);
		}else{
		g.drawPixmap(Assets.droid, x, y);// ドロイドくんを描画}
	}

}
}
