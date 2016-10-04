package cn.steve.share;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

/**
 * Created by yantinggeng on 2016/5/5.
 */
public class ShareItem implements Parcelable {

    public static final int WECHAT = 11;
    public static final int WECHAT_TIMELINE = 22;
    public static final int WECHAT_FAVOURITE = 33;
    public static final int WEIBO = 44;
    public static final int QQ = 55;
    public static final int MESSAGE = 66;
    public static final int LINK = 77;
    public static final Creator<ShareItem> CREATOR = new Creator<ShareItem>() {
        @Override
        public ShareItem createFromParcel(Parcel in) {
            return new ShareItem(in);
        }

        @Override
        public ShareItem[] newArray(int size) {
            return new ShareItem[size];
        }
    };
    @DrawableRes
    private int drawableRes;
    private int type;
    private String title;

    protected ShareItem(Parcel in) {
        drawableRes = in.readInt();
        type = in.readInt();
        title = in.readString();
    }

    public ShareItem(@DrawableRes int drawableRes, String title) {
        this.drawableRes = drawableRes;
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(drawableRes);
        dest.writeInt(type);
        dest.writeString(title);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
