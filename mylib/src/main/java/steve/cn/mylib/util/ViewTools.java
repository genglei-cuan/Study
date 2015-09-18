package steve.cn.mylib.util;

/**
 * Created by Steve on 2015/7/29.
 */

import android.view.View;

/**
 * view的工具类
 */
public class ViewTools {

    /**
     * 判断点击区域是否在指定的view内
     *
     * @param x    点击的X
     * @param y    点击的Y
     * @param view 需要判断的区域view
     * @return 是否在
     */
    public static boolean isPointInsideView(float x, float y, View view) {
        if (view == null) {
            return false;
        }
        int location[] = new int[2];
        view.getLocationOnScreen(location);
        int viewX = location[0];
        int viewY = location[1];

        // point is inside view bounds
        if ((x > viewX && x < (viewX + view.getWidth())) &&
            (y > viewY && y < (viewY + view.getHeight()))) {
            return true;
        } else {
            return false;
        }
    }
}
