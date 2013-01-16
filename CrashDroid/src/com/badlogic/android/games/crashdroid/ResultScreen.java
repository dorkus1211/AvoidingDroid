package com.badlogic.android.games.crashdroid;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.badlogic.android.games.framework.Game;
import com.badlogic.android.games.framework.Graphics;
import com.badlogic.android.games.framework.Input.TouchEvent;
import com.badlogic.android.games.framework.Screen;

public class ResultScreen extends Screen {
	int Point;

	public ResultScreen(Game game,int Point) {
		super(game);
		this.Point = Point;
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		for (int i = 0; i < touchEvents.size(); i++) {
			TouchEvent event = touchEvents.get(i);

			if (event.type == TouchEvent.TOUCH_UP) {

				if (inBounds(event, 15, 340, 140, 60)) {
					game.setScreen(new GameScreen(game));
					// if(Settings.soundEnabled)
					// Assets.click.play(1);
				}

				if (inBounds(event, 140+10+15, 340, 140, 60)) {
					game.setScreen(new MainMenuScreen(game));
					// if(Settings.soundEnabled)
					// Assets.click.play(1);
					return;
				}

			}

	}
	}
	
	public boolean inBounds(TouchEvent event, int x, int y, int w, int h) {

		if (event.x > x && event.x < x + w - 1 && event.y > y
				&& event.y < y + w - 1)
			return true;
		else
			return false;

	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		Paint paint = new Paint();
		paint.setTextAlign(Paint.Align.RIGHT);
		paint.setColor(Color.WHITE);
		//g.drawText(String.valueOf(Point),35+125,260,50,paint);
		g.drawPixmap(Assets.result,35,100);
		g.drawText(String.valueOf(Point),35+125,260,65,paint);
		g.drawPixmap(Assets.retry,15,340);
		g.drawPixmap(Assets.title,140+10+15,340);
	}

	@Override
	public void pause() {
		Settings.save(game.getFileIO());
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
