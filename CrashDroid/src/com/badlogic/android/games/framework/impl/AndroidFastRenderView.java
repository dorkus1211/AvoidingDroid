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
			if(!holder.getSurface().isValid())//サーフェイスが作成されたか確認。
				continue;
			
			float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
			
			startTime = System.nanoTime();
			
			game.getCurrentScreen().update(deltaTime);
			game.getCurrentScreen().present(deltaTime);
			
			Canvas canvas = holder.lockCanvas();//canvasをロック。
			canvas.getClipBounds(dstRect);//SurfaceViewを覆う短形領域を取得。dstRectのtopメンバとleftメンバを0に設定し、bottomメンバとrightメンバに実際のスクリーンサイズを設定する。
			canvas.drawBitmap(framebuffer, null, dstRect,null);//framebufferをdstRectの大きさで描画する。
			holder.unlockCanvasAndPost(canvas);//アンロック！！
		}
	}
	
	public void pause(){
		running = false;//runningをtrueからfalseにし、描画を止める。
		while(true){
			try{
				renderThread.join();//スレッドが終了するまで待つ。るーぷ
				break;
			}catch(InterruptedException e){
				
			}
		}
	}
	
	

}
