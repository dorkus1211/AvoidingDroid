package com.badlogic.android.games.crashdroid;

import com.badlogic.android.games.framework.Game;
import com.badlogic.android.games.framework.Graphics;
import com.badlogic.android.games.framework.Graphics.PixmapFormat;
import com.badlogic.android.games.framework.Screen;

public class LoadingScreen extends Screen {

	public LoadingScreen(Game game) {
		super(game);

	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();

		Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
		Assets.car = g.newPixmap("car.png", PixmapFormat.ARGB4444);
		Assets.ready = g.newPixmap("Ready.png",PixmapFormat.ARGB4444);
		Assets.droid = g.newPixmap("droidkun3.png", PixmapFormat.ARGB4444);
		Assets.policecar = g.newPixmap("policecar.png", PixmapFormat.ARGB4444);
		Assets.tyuui = g.newPixmap("bikkurima-ku.png", PixmapFormat.ARGB4444);	
		Assets.blooddroid = g.newPixmap("droidkun4.png", PixmapFormat.ARGB4444);	
		Assets.result = g.newPixmap("Result1.png", PixmapFormat.ARGB4444);
		Assets.gameover = g.newPixmap("gameover1.png", PixmapFormat.ARGB4444);
		Assets.retry = g.newPixmap("RETRY.png", PixmapFormat.ARGB4444);
		Assets.title = g.newPixmap("TITLE.png", PixmapFormat.ARGB4444);
		Assets.bomber = g.newPixmap("itembomber.png", PixmapFormat.ARGB4444);
		Assets.cardown = g.newPixmap("itemdown.png", PixmapFormat.ARGB4444);
		Assets.droidup = g.newPixmap("itemspeed.png", PixmapFormat.ARGB4444);
		Assets.Logo = g.newPixmap("AvoidingDroid.png", PixmapFormat.ARGB4444);
		Assets.Play = g.newPixmap("PLAY.png", PixmapFormat.ARGB4444);
		Settings.load(game.getFileIO());
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void present(float deltaTime) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
