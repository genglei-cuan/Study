package cn.steve.brightness;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;

import cn.steve.study.R;

/**
 * 注意：有的手机有兼容性问题。设置完以后，需要熄屏以后才会生效，针对这个特点，所以采用修改Window的亮度这一途径，但是这个设置需要结合系统的特性。
 *
 * <p/>
 * 改变手机系统的亮度demo
 */
public class BrightnessActivity extends Activity implements OnClickListener {

    public static final String TAG = "TAG";
    Context context;
    int MANUAUL = Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL;
    int AUTO = Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;

    int bright = 0;

    private void setScreenMode(int mode) {
        try {
            Settings.System.putInt(this.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    mode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brightness);
        findViewById(R.id.button_add).setOnClickListener(this);
        findViewById(R.id.button_auto).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_auto:
                setScreenMode(AUTO);
                bright = 10;
                break;
            case R.id.button_add:
                setScreenMode(MANUAUL);
                ContentResolver contentResolver = this.getContentResolver();
                saveBrightness(contentResolver, 1);
                if (bright > 254) {
                    saveBrightness(contentResolver, 10);
                    bright = 10;
                } else {
                    bright = bright + 10;
                    saveBrightness(contentResolver, bright);
                    System.out.println("brightness->" + getScreenBrightness(this));
                }
                break;
            default:
                break;
        }

    }

    public void saveBrightness(ContentResolver resolver, int brightness) {
        Uri uri = Settings.System.getUriFor("screen_brightness");
        Settings.System.putInt(resolver, "screen_brightness", brightness);
        resolver.notifyChange(uri, null);
    }

    public int getScreenBrightness(Activity activity) {
        int nowBrightnessValue = 0;
        ContentResolver resolver = activity.getContentResolver();
        try {
            nowBrightnessValue = Settings.System.getInt(resolver,
                    Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nowBrightnessValue;
    }
}
