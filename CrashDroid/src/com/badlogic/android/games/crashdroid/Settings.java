package com.badlogic.android.games.crashdroid;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.badlogic.android.games.framework.FileIO;

public class Settings {
	public static boolean soundEnabled = true;// サウンド再生するかどうか。初期値はtrue
	public static int[] highscores = new int[] { 0, 0, 0, 0, 0 };// highscoreを5段階いれる
	public static final int Width = 320;
	public static final int Height = 480;

	public static void load(FileIO files) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					files.readFile(".crashdroid")));// mrnomの絶対パスを返してそこからInputStreamReaderバイトストリームから文字ストリームへの橋渡しの役目を持つそしてバッファリーダーでもじへ
			soundEnabled = Boolean.parseBoolean(in.readLine());// BufferedReader型のinからテキスト行をよみこむそしてブーリアン型へ変換
			for (int i = 0; i < 5; i++) {
				highscores[i] = Integer.parseInt(in.readLine());// ハイスコアを読み込む
			}
		} catch (IOException e) {

		} catch (NumberFormatException e) {

		} finally {
			try {
				if (in != null)// inに何かはいってたらストリームを閉じて、それに関連するすべてのシステムリソースを解放します。
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
