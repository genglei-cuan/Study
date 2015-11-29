package cn.steve.wallpaper;


/**
 * Created by harmy on 2015/7/13 0013.
 */
public class ImageInfo {

    public String path;
    public float height;
    public float width;

    public ImageInfo(String path, float width, float height) {
        this.height = height;
        this.width = width;
        this.path = path;
    }
}