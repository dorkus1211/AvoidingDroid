package com.badlogic.android.games.framework.impl;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import com.badlogic.android.games.framework.Graphics;
import com.badlogic.android.games.framework.Pixmap;

public class AndroidGraphics implements Graphics {
	AssetManager assets;
	Bitmap frameBuffer;
	Canvas canvas;
	Paint paint;
	Rect srcRect = new Rect();
	Rect dstRect = new Rect();

	public AndroidGraphics(AssetManager assets, Bitmap frameBuffer) {
		this.assets = assets;
		this.frameBuffer = frameBuffer;
		this.canvas = new Canvas(frameBuffer);
		this.paint = new Paint();
	}

	@Override
	public Pixmap newPixmap(String fileName, PixmapFormat format) {
		Config config = null;
		if (format == PixmapFormat.RGB565)//�s�N�Z���t�H�[�}�b�g��RGB_565�Ȃ�confing�ɂ�����i�[
			config = Config.RGB_565;
		else if (format == PixmapFormat.ARGB4444)
			config = Config.ARGB_4444;
		else
			config = Config.ARGB_8888;

		Options options = new Options();//option�I�u�W�F�N�g��錾
		options.inPreferredConfig = config;//options.inPreferredConfig�ɓǂݍ��ރt�@�C���t�H�[�}�b�g���w��B

		InputStream in = null;
		Bitmap bitmap = null;
		try {
			in = assets.open(fileName);//�A�Z�b�g����J���t�@�C����Inputsteream��
			bitmap = BitmapFactory.decodeStream(in);//inputstream�ɂ���bitmap��ϊ���bitmap�֊i�[
			if (bitmap == null)//bitmap�������͂����ĂȂ������炦��[��
				throw new RuntimeException("Couldnt load bitmap from asset "
						+ fileName + "");
		} catch (IOException e) {
			throw new RuntimeException("Coludnt load bitmap from asset "
					+ fileName + "");
		} finally {//��΂Ƃ����
			if (in != null) {//inputstream��null�łȂ�������
				try {
					in.close();//inputstream�����
				} catch (IOException e) {
				}
			}
		}
		if (bitmap.getConfig() == Config.RGB_565)//bitmap�̃t�H�[�}�b�g��RGB_565�܂�format��RGB_565���i�[
			format = PixmapFormat.RGB565;
		else if (bitmap.getConfig() == Config.ARGB_4444)
			format = PixmapFormat.ARGB4444;
		else
			format = PixmapFormat.ARGB8888;

		return new AndroidPixmap(bitmap, format);//AndroidPixmap�^�̃I�u�W�F�N�g��Ԃ��B
	}
	
	
	
	@Override
	public void drawText(String string,int x,int y,int textsize,Paint paint){
		paint.setTextSize(textsize);
		canvas.drawText(string, x, y, paint);
	}
	
	@Override
	public void drawText(String string,int x,int y,int textsize){
		paint.setTextSize(textsize);
		canvas.drawText(string, x, y, paint);
	}

	@Override
	public void clear(int color) {
		canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,//������color����Ԑ΂̐��������o���`��B
				(color & 0xff));
	}

	@Override
	public void drawPixel(int x, int y, int color) {//�����Ŏw�肳�ꂽcolor�Ńs�N�Z����`��
		paint.setColor(color);
		canvas.drawPoint(x, y, paint);
	}

	@Override
	public void drawLine(int x, int y, int x2, int y2, int color) {
		paint.setColor(color);
		canvas.drawLine(x, y, x2, y2, paint);
	}

	@Override
	public void drawRect(int x, int y, int width, int height, int color) {
		paint.setColor(color);
		paint.setStyle(Style.FILL);
		canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
	}
	
	@Override
	public void drawPixmap(Pixmap pixmap, int x,int y,int srcX,int srcY,int srcWidth,int srcHeight){
		srcRect.left = srcX;
		srcRect.top = srcY;
		srcRect.right= srcX + srcWidth-1;
		srcRect.bottom = srcY + srcHeight-1;
		
		dstRect.left = x;
		dstRect.top = y;
		dstRect.right= x + srcWidth-1;
		dstRect.bottom = y + srcHeight-1;	
		
		canvas.drawBitmap(((AndroidPixmap)pixmap).bitmap	, srcRect,dstRect, paint); 
	}
	
	@Override
	public void drawPixmap(Pixmap pixmap,int x,int y){
		canvas.drawBitmap(((AndroidPixmap)pixmap).bitmap,x,y,null);
	}
	
	@Override
	public void drawPixmap(Pixmap pixmap,float x,float y){
		canvas.drawBitmap(((AndroidPixmap)pixmap).bitmap,x,y,null);
	}
	
	@Override
	public int getWidth(){
		return frameBuffer.getWidth();}
	
	@Override
	public int getHeight(){
		return frameBuffer.getHeight();
	}
	
}
