package com.badlogic.android.games.crashdroid;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.badlogic.android.games.framework.FileIO;

public class Settings {
	public static boolean soundEnabled = true;// �T�E���h�Đ����邩�ǂ����B�����l��true
	public static int[] highscores = new int[] { 0, 0, 0, 0, 0 };// highscore��5�i�K�����
	public static final int Width = 320;
	public static final int Height = 480;

	public static void load(FileIO files) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					files.readFile(".crashdroid")));// mrnom�̐�΃p�X��Ԃ��Ă�������InputStreamReader�o�C�g�X�g���[�����當���X�g���[���ւ̋��n���̖�ڂ��������ăo�b�t�@���[�_�[�ł�����
			soundEnabled = Boolean.parseBoolean(in.readLine());// BufferedReader�^��in����e�L�X�g�s����݂��ނ����ău�[���A���^�֕ϊ�
			for (int i = 0; i < 5; i++) {
				highscores[i] = Integer.parseInt(in.readLine());// �n�C�X�R�A��ǂݍ���
			}
		} catch (IOException e) {

		} catch (NumberFormatException e) {

		} finally {
			try {
				if (in != null)// in�ɉ����͂����Ă���X�g���[������āA����Ɋ֘A���邷�ׂẴV�X�e�����\�[�X��������܂��B
					in.close();
			} catch (IOException e) {
			}
		}
	}

	public static void save(FileIO files){
		BufferedWriter out = null;
		try{
			out = new BufferedWriter(new OutputStreamWriter(files.writeFile(".mrnom")));
			
			out.write(Boolean.toString(soundEnabled));
			for(int i = 0;i<5;i++){
				out.write(Integer.toString(highscores[i]));
			}
		}catch(IOException e){
		}finally{
			try{
				if(out != null)
					out.close();
			}catch(IOException e){
				
			}
		}
	}

	public static void addScore(int score) {
		for (int i = 0; i < 5; i++) {
			if (highscores[i] < score) {
				for (int j = 4; j < i; j--)
					highscores[j] = highscores[j - 1];
				highscores[i] = score;
				break;
			}
		}
	}
	

}
