package com.badlogic.android.games.framework.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.res.AssetManager;
import android.os.Environment;

import com.badlogic.android.games.framework.FileIO;

public class AndroidFileIO implements FileIO{
	AssetManager assets;
	String externalStoragePath;
	
	public AndroidFileIO(AssetManager assets){
		this.assets = assets;//メンバのassetsに引数のassetsを格納
		this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;//ストレージパスの絶対パスを代入。
	}
	
	@Override
	public InputStream readAsset(String fileName) throws IOException{
		return assets.open(fileName);//アセットからファイルを開く。	
	}
	
	@Override
	public InputStream readFile(String fileName) throws IOException{
		return new FileInputStream(externalStoragePath + fileName);//外部ストレージのファイルをInPutSteramへ。
	}
	
	@Override
	public OutputStream writeFile(String fileName) throws IOException{
		return new FileOutputStream(externalStoragePath + fileName);//外部ストレージのファイルをOutPutStreamへ
	}
	
}
