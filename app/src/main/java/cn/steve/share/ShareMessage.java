package cn.steve.share;

/**
 * Created by yantinggeng on 2015/10/16.
 */
public abstract class ShareMessage {

    //通用的分享内容
    protected String shareTitle;
    protected String shareContent;
    protected String shareImageURL;
    protected String shareDirectURL;

    //内容的类型
    enum CONTENTTYPE {
        TEXT, IMAGE, MULTIL
    }

}
