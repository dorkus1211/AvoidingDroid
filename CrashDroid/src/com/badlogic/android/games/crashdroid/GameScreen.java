package com.badlogic.android.games.crashdroid;

import java.util.List;

import com.badlogic.android.games.crashdroid.Item.ItemList;
import com.badlogic.android.games.framework.Game;
import com.badlogic.android.games.framework.Graphics;
import com.badlogic.android.games.framework.Input.TouchEvent;
import com.badlogic.android.games.framework.Screen;

public class GameScreen extends Screen {
	enum GAME_STATE {
		Ready, Running, Paused, GameOver
	}

	RunningObject runObje;
	GAME_STATE state = GAME_STATE.Ready;

	public GameScreen(Game game) {
		super(game);
		runObje = new RunningObject();

	}

	@Override
	public void update(float deltaTime) {

		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		if (state == GAME_STATE.Ready)
			ReadyUpdate(touchEvents);
		if (state == GAME_STATE.Running)
			RunningUpdate(touchEvents, deltaTime);
		if (state == GAME_STATE.Paused)
			PausedUpdate(touchEvents);
		if (state == GAME_STATE.GameOver)
			GameOverUpdate(touchEvents);

	}

	@Override
	public void present(float deltaTime) {

		drawWorld();

		if (state == GAME_STATE.Ready)
			ReadyPresent();
		if (state == GAME_STATE.Running)
			RunningPresent(deltaTime);
		if (state == GAME_STATE.Paused)
			;
		if (state == GAME_STATE.GameOver)
			game.setScreen(new ResultScreen(game,runObje.point.Point));
	}

	private void drawWorld() {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);
	}

	private void ReadyPresent() {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.ready, 64, 100);
	}

	private void RunningPresent(float deltaTime) {
		Graphics g = game.getGraphics();
		runObje.present(deltaTime, g);
	}

	private void ReadyUpdate(List<TouchEvent> touchEvents) {
		if (touchEvents.size() > 0)
			state = GAME_STATE.Running;
	}

	private void RunningUpdate(List<TouchEvent> touchEvents, float deltaTime) {
		if(runObje.judged(deltaTime))
			state = GAME_STATE.GameOver;
		else
			runObje.update(deltaTime,game);
	}

	// 3ë‰ÇÃé‘Ç™èdÇ»ÇÁÇ»Ç≠Ç»ÇÈÇ‹Ç≈é‘ÇÃà íuÇÇ©Ç¶ÇÈÅB

	private void PausedUpdate(List<TouchEvent> touchEvents) {

	}

	private void GameOverUpdate(List<TouchEvent> touchEvents) {
		game.setScreen(new ResultScreen(game,runObje.point.Point));
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		Speed.POLICESPEED = 3;
		Speed.SPEED = 3;
		Item.item = ItemList.no;
		Speed.droidspeed = 2.3;
		Item.updateTime = 0;
		runObje.gatherCar.dispose();
		runObje.PC.dispose();
	}

}
