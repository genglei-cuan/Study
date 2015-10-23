package cn.steve.audiomanager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.steve.study.R;

/**
 * 切换系统的音量模式的demo
 * 1.设置有声音、震动、静音模式
 */
public class AudioManagerActivity extends Activity {

    private AudioManager mAudioManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audiomanager);
        ButterKnife.bind(this);
        mAudioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
    }

    @OnClick(R.id.button_reduceVolume)
    public void reduceVolume() {
        mAudioManager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_LOWER,
                AudioManager.FLAG_PLAY_SOUND);
        mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, 0, 0);
    }

    @OnClick(R.id.button_getCurrentModel)
    public void getSoundModel() {
        String s = "";
        int soundMode = getSoundMode();
        switch (soundMode) {
            case 0:
                s = "静音";
                break;
            case 1:
                s = "震动";
                break;
            case 2:
                s = "有声";
                break;
        }
        System.out.println("当前的Model:" + s);
    }

    @OnClick(R.id.button_getVolumeFixed)
    @SuppressLint("NewApi")
    public void getIsVolumeFixed() {
        System.out.println("isVolumeFixed " + mAudioManager.isVolumeFixed());
    }

    //获取当前的模式
    public int getSoundMode() {
        if (mAudioManager == null) {
            return AudioManager.RINGER_MODE_NORMAL;
        }
        return mAudioManager.getRingerMode();
    }

    //切换模式
    @OnClick(R.id.button_switchModel)
    public void clickSound() {
        if (mAudioManager == null) {
            return;
        }
        int soundMode = getSoundMode();
        switch (soundMode) {
            case AudioManager.RINGER_MODE_NORMAL:
                mAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Log.i("steve", "由有声转向震动");
                break;
            case AudioManager.RINGER_MODE_VIBRATE:
                mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Log.i("steve", "由震动转向静音");
                break;
            case AudioManager.RINGER_MODE_SILENT:
                mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Log.i("steve", "由静音转向有声");
                break;
        }
    }
}
