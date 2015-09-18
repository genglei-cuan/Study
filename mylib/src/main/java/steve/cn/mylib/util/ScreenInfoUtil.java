package steve.cn.mylib.util;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by Steve on 2015/8/3.
 */
public class ScreenInfoUtil {

    public static String getScreenDetailInfo(Activity context) {
        // 获取设备信息
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        StringBuffer sb = new StringBuffer();
        sb.append("宽度方向上的像素(widthPixels):" + displayMetrics.widthPixels + "px");
        sb.append("\n");
        sb.append("高度方向上的像素(heightPixels):" + displayMetrics.heightPixels + "px");
        sb.append("\n");
        sb.append("宽度:" + displayMetrics.widthPixels / displayMetrics.density + "dp");
        sb.append("\n");
        sb.append("高度:" + displayMetrics.heightPixels / displayMetrics.density + "dp");
        sb.append("\n");
        sb.append("宽度方向上的每英寸的像素值(xdpi):" + displayMetrics.xdpi + "px");
        sb.append("\n");
        sb.append("高度方向上的每英寸的像素值(ydpi):" + displayMetrics.ydpi + "px");
        sb.append("\n");
        sb.append("像素密度(density):" + displayMetrics.density);
        sb.append("\n");
        sb.append("像素密度值，每英寸的像素值(densityDpi):" + displayMetrics.densityDpi + "px");
        sb.append("\n");
        sb.append("像素比例,与density一样，唯一可能受手机的字体影响(scaledDensity):" + displayMetrics.scaledDensity);
        sb.append("\n");
        sb.append("屏幕尺寸(ScreenSize):" + getInch(context));
        sb.append("\n");
        return sb.toString();
    }


    //屏幕尺寸
    private static double getInch(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int dens = dm.densityDpi;
        double wi = (double) width / (double) dens;
        double hi = (double) height / (double) dens;
        double x = Math.pow(wi, 2);
        double y = Math.pow(hi, 2);
        double screenInches = Math.sqrt(x + y);
        return screenInches;
    }
}
