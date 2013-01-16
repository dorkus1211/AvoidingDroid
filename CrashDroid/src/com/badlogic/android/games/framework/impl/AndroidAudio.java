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
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);//���ʒ��߃{�^����STERAM_MUSIC�Ɋ֘A�t����B
        this.assets = activity.getAssets();//�A�Z�b�g���擾�B
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);//SoundPool�̃I�u�W�F�N�g���쐬�B�ő哯���Đ���20�ASTREAM_MUSIC�֏o�͂���ݒ�
    }

    @Override
    public Music newMusic(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);//�A�Z�b�g����t�@�C����ǂݏo��assetDescriptor�֊i�[
            return new AndroidMusic(assetDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music '" + filename + "'");
        }
    }

    @Override
    public Sound newSound(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);//�A�Z�b�g����t�@�C����ǂݏo��assetDescriptor�֊i�[
            int soundId = soundPool.load(assetDescriptor, 0);//soundPool�փT�E���h��ǂݏo��soundId�����蓖�Ă�B
            return new AndroidSound(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load sound '" + filename + "'");
        }
    }
}