package com.badlogic.android.games.framework.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AndroidFastRenderView extends SurfaceView implements Runnable{
	AndroidGame game;
	Bitmap framebuffer;
	Thread renderThread = null;
	SurfaceHolder holder;
	
	volatile boolean running = false;
	
	public AndroidFastRenderView(AndroidGame game,Bitmap framebuffer){
		super(game);
		this.game = game;
		this.framebuffer = framebuffer;
		this.holder = getHolder();
	}
	
	public void resume(){
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}
	
	public void run(){
		Rect dstRect = new Rect();
		long startTime = System.nanoTime();
		while(running){
			if(!holder.getSurface().isValid())//�T�[�t�F�C�X���쐬���ꂽ���m�F�B
				continue;
			
			float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
			
			startTime = System.nanoTime();
			
			game.getCurrentScreen().update(deltaTime);
			game.getCurrentScreen().present(deltaTime);
			
			Canvas canvas = holder.lockCanvas();//canvas�����b�N�B
			canvas.getClipBounds(dstRect);//SurfaceView�𕢂��Z�`�̈���擾�BdstRect��top�����o��left�����o��0�ɐݒ肵�Abottom�����o��right�����o�Ɏ��ۂ̃X�N���[���T�C�Y��ݒ肷��B
			canvas.drawBitmap(framebuffer, null, dstRect,null);//framebuffer��dstRect�̑傫���ŕ`�悷��B
			holder.unlockCanvasAndPost(canvas);//�A�����b�N�I�I
		}
	}
	
	public void pause(){
		running = false;//running��true����false�ɂ��A�`����~�߂�B
		while(true){
			try{
				renderThread.join();//�X���b�h���I������܂ő҂B��[��
				break;
			}catch(InterruptedException e){
				
			}
		}
	}
	
	

}
