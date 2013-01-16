package com.badlogic.android.games.framework.impl;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.badlogic.android.games.framework.Audio;
import com.badlogic.android.games.framework.Music;
import com.badlogic.android.games.framework.Sound;

public class AndroidAudio implements Audio {
    AssetManager assets;
    SoundPool soundPool;

    public AndroidAudio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);//音量調節ボタンをSTERAM_MUSICに関連付ける。
        this.assets = activity.getAssets();//アセットを取得。
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);//SoundPoolのオブジェクトを作成。最大同時再生数20、STREAM_MUSICへ出力する設定
    }

    @Override
    public Music newMusic(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);//アセットからファイルを読み出しassetDescriptorへ格納
            return new AndroidMusic(assetDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music '" + filename + "'");
        }
    }

    @Override
    public Sound newSound(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);//アセットからファイルを読み出しassetDescriptorへ格納
            int soundId = soundPool.load(assetDescriptor, 0);//soundPoolへサウンドを読み出しsoundIdを割り当てる。
            return new AndroidSound(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load sound '" + filename + "'");
        }
    }
}