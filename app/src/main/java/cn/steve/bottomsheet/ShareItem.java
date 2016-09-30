package cn.steve.bottomsheet;

import android.support.annotation.DrawableRes;
import android.view.View;

/**
 * Created by yantinggeng on 2016/5/5.
 */
public class ShareItem {

    @DrawableRes
    private int drawableRes;
    private String title;
    private View.OnClickListener onClickListener;

    public ShareItem(@DrawableRes int drawableRes, String title) {
        this.drawableRes = drawableRes;
        this.title = title;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
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
