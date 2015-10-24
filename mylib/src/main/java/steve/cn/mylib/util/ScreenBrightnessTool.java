package steve.cn.mylib.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.view.Window;
import android.view.WindowManager.LayoutParams;


/**
 * 通过设置当前的Window的亮度来达到修改了系统的手机亮度的目的，但是唯一需要注意的是，
 * 当切换回自动调节的时候，需要还原系统的标志位的。
 */
public class ScreenBrightnessTool {

    /**
     * Activty自动调节亮度模式
     */
    public static final int ACTIVITY_BRIGHTNESS_AUTOMATIC = -1;
    /**
     * 自动调节模式
     */
    public static final int
            SCREEN_BRIGHTNESS_MODE_AUTOMATIC =
            Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
    /**
     * 手动调节模式
     */
    public static final int
            SCREEN_BRIGHTNESS_MODE_MANUAL =
            Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL;
    /**
     * 默认亮度
     */
    public static final int SCREEN_BRIGHTNESS_DEFAULT = 75;
    /**
     * 最大亮度
     */
    public static final int MAX_BRIGHTNESS = 100;
    /**
     * 最小亮度
     */
    public static final int MIN_BRIGHTNESS = 0;

    private static final int mMaxBrighrness = 255;
    private static final int mMinBrighrness = 120;

    // 当前系统调节模式
    private boolean sysAutomaticMode;
    // 当前系统亮度值
    private int sysBrightness;

    private Context context;
    private Window window;

    private ScreenBrightnessTool(Context context, int sysBrightness,
                                 boolean sysAutomaticMode) {
        this.context = context;
        this.sysBrightness = sysBrightness;
        this.sysAutomaticMode = sysAutomaticMode;
        window = ((Activity) context).getWindow();
    }

    /**
     * 创建屏幕亮度工具
     */
    public static ScreenBrightnessTool Builder(Context context) {
        int brightness;
        boolean automaticMode;
        try {
            // 获取当前系统亮度值
            brightness = Settings.System.getInt(context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
            // 获取当前系统调节模式
            automaticMode = Settings.System.getInt(
                    context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE) == SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        } catch (SettingNotFoundException e) {
            return null;
        }

        return new ScreenBrightnessTool(context, brightness, automaticMode);
    }

    /**
     * 亮度预览
     *
     * @param activity   预览activity
     * @param brightness 亮度值（0.47~1）
     */
    public static void brightnessPreview(Activity activity, float brightness) {
        Window window = activity.getWindow();
        LayoutParams lp = window.getAttributes();
        lp.screenBrightness = brightness;
        window.setAttributes(lp);
    }

    /**
     * 亮度预览
     *
     * @param activity 预览activity
     * @param percent  百分比（0.0~1.00）
     */
    public static void brightnessPreviewFromPercent(Activity activity,
                                                    float percent) {
        float brightness = percent + (1.0f - percent)
                * (((float) mMinBrighrness) / mMaxBrighrness);
        brightnessPreview(activity, brightness);
    }

    // 获取当前系统允许的最小亮度值
    public static int getMinimumScreenBrightnessSetting(Context context) {
        final Resources res = Resources.getSystem();
        int
                id =
                res.getIdentifier("config_screenBrightnessSettingMinimum", "integer",
                        "android"); // API17+
        if (id == 0) {
            id = res.getIdentifier("config_screenBrightnessDim", "integer", "android"); // lower
        }
        // API
        // levels
        if (id != 0) {
            try {
                return res.getInteger(id);
            } catch (Resources.NotFoundException e) {
                // ignore
            }
        }
        return 0;
    }

    // 当前最大亮度值
    public static int getMaximumScreenBrightnessSetting(Context context) {
        final Resources res = Resources.getSystem();
        final int id = res.getIdentifier("config_screenBrightnessSettingMaximum", "integer",
                "android"); // API17+
        if (id != 0) {
            try {
                return res.getInteger(id);
            } catch (Resources.NotFoundException e) {
                // ignore
            }
        }
        return 255;
    }

    /**
     * 返回当前系统亮度调节模式
     */
    public boolean getSystemAutomaticMode() {
        return sysAutomaticMode;
    }

    /**
     * 返回当前系统亮度值
     */
    public int getSystemBrightness() {
        return sysBrightness;
    }

    /**
     * 设置调节模式
     *
     * @param mode 调节模式
     */
    public void setMode(int mode) {
        if (mode != SCREEN_BRIGHTNESS_MODE_AUTOMATIC
                && mode != SCREEN_BRIGHTNESS_MODE_MANUAL) {
            return;
        }

        sysAutomaticMode = mode == SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        Settings.System.putInt(context.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE, mode);
    }

    /**
     * 设置屏幕亮度
     *
     * @param brightness 亮度值,值为0至100
     */
    public void setBrightness(int brightness) {
        int mid = mMaxBrighrness - mMinBrighrness;
        int bri = (int) (mMinBrighrness + mid * ((float) brightness)
                / MAX_BRIGHTNESS);
        ContentResolver resolver = context.getContentResolver();
        Settings.System
                .putInt(resolver, Settings.System.SCREEN_BRIGHTNESS, bri);

        // Get the current window attributes
        LayoutParams layoutpars = window.getAttributes();
        // Set the brightness of this window
        layoutpars.screenBrightness = brightness / (float) 255;
        // Apply attribute changes to this window
        window.setAttributes(layoutpars);
    }

}
