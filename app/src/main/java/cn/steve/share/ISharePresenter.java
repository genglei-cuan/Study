package cn.steve.share;

import static cn.steve.share.ShareItem.LINK;
import static cn.steve.share.ShareItem.MESSAGE;
import static cn.steve.share.ShareItem.QQ;
import static cn.steve.share.ShareItem.WECHAT;
import static cn.steve.share.ShareItem.WECHAT_FAVOURITE;
import static cn.steve.share.ShareItem.WECHAT_TIMELINE;
import static cn.steve.share.ShareItem.WEIBO;

/**
 * Created by steveyan on 16-10-4.
 */

public abstract class ISharePresenter {

    void dealOnclick(ShareItem shareItem) {
        switch (shareItem.getType()) {
            case WECHAT:
                shareByWechat();
                break;
            case WECHAT_TIMELINE:
                shareByWechatTimeLine();
                break;
            case WECHAT_FAVOURITE:
                shareByWechatFavourite();
                break;
            case WEIBO:
                shareByWeibo();
                break;
            case QQ:
                shareByQQ();
                break;
            case MESSAGE:
                shareByMessage();
                break;
            case LINK:
                shareByLink();
                break;
        }
    }

    abstract void shareByQQ();

    abstract void shareByWechat();

    abstract void shareByWechatTimeLine();

    abstract void shareByWechatFavourite();

    abstract void shareByWeibo();

    abstract void shareByMessage();

    abstract void shareByLink();
}
