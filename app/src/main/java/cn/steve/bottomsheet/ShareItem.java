package cn.steve.bottomsheet;

import android.support.annotation.DrawableRes;

/**
 * Created by yantinggeng on 2016/5/5.
 */
public class ShareItem {

    @DrawableRes
    private int drawableRes;
    private String title;

    public ShareItem(@DrawableRes int drawableRes, String title) {
        this.drawableRes = drawableRes;
        this.title = title;
    }

    public int getDrawableRes() {
        return drawableRes;
    }

    public void setDrawableRes(int drawableRes) {
        this.drawableRes = drawableRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
