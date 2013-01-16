package com.badlogic.android.games.crashdroid;

import java.util.List;

import com.badlogic.android.games.framework.Game;
import com.badlogic.android.games.framework.Graphics;
import com.badlogic.android.games.framework.Input.TouchEvent;
import com.badlogic.android.games.framework.Screen;

public class MainMenuScreen extends Screen {

	public MainMenuScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();  

		for (int i = 0; i < touchEvents.size(); i++) {
			TouchEvent event = touchEvents.get(i);

			if (event.type == TouchEvent.TOUCH_UP) {



				if (inBounds(event, 64, 220, 192, 42)) {
					game.setScreen(new GameScreen(game));
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

		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.Logo, 32, 32);
		g.drawPixmap(Assets.Play, 64, 220);
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
