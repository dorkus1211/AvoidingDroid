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
		this.assets = assets;//�����o��assets�Ɉ�����assets���i�[
		this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;//�X�g���[�W�p�X�̐�΃p�X�����B
	}
	
	@Override
	public InputStream readAsset(String fileName) throws IOException{
		return assets.open(fileName);//�A�Z�b�g����t�@�C�����J���B	
	}
	
	@Override
	public InputStream readFile(String fileName) throws IOException{
		return new FileInputStream(externalStoragePath + fileName);//�O���X�g���[�W�̃t�@�C����InPutSteram�ցB
	}
	
	@Override
	public OutputStream writeFile(String fileName) throws IOException{
		return new FileOutputStream(externalStoragePath + fileName);//�O���X�g���[�W�̃t�@�C����OutPutStream��
	}
	
}
