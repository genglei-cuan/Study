package cn.steve.ipc.bundle;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yantinggeng on 2016/8/22.
 */
public class ShuaiGe implements Parcelable {

    public static final Creator<ShuaiGe> CREATOR = new Creator<ShuaiGe>() {
        @Override
        public ShuaiGe createFromParcel(Parcel in) {
            return new ShuaiGe(in);
        }

        @Override
        public ShuaiGe[] newArray(int size) {
            return new ShuaiGe[size];
        }
    };
    public String name;
    public String title;

    public ShuaiGe(String name, String title) {
        this.name = name;
        this.title = title;
    }

    protected ShuaiGe(Parcel in) {
        name = in.readString();
        title = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(title);
    }
}
