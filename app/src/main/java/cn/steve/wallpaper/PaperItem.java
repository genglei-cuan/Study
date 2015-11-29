package cn.steve.wallpaper;

import java.util.ArrayList;

/**
 * Created by Steve on 2015/8/21.
 */
public class PaperItem {

    private ArrayList<ImageInfo> datas;

    public PaperItem(ArrayList<ImageInfo> datas) {
        this.datas = datas;
    }

    public ArrayList<ImageInfo> getDatas() {
        return datas;
    }
}
