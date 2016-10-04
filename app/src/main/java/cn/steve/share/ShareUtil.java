package cn.steve.share;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import cn.steve.study.R;

/**
 * Created by steveyan on 16-10-4.
 */

public class ShareUtil {

    private AppCompatActivity context;
    private ArrayList<ShareItem> items = new ArrayList<>();

    public ShareUtil(AppCompatActivity context) {
        this.context = context;
    }

    public void share() {
        ArrayList<ShareItem> shareData;
        if (items == null || items.size() < 1) {
            shareData = createAll();
        } else {
            shareData = items;
        }
        BottomSheetShareFragment shareFragment = new BottomSheetShareFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(BottomSheetShareFragment.SHAREITEMS, shareData);
        shareFragment.setArguments(args);
        shareFragment.setPresenter(new SharePresenter(context));
        shareFragment.show(context.getSupportFragmentManager(), "shareFragment");
    }


    public ArrayList<ShareItem> createAll() {

        weChat();

        timeLine();

        favourite();

        sinaWeibo();

        qq();

        sms();

        copy();

        return items;
    }

    public ShareUtil copy() {
        ShareItem copy = new ShareItem(R.drawable.share_copylink, "复制链接");
        copy.setType(ShareItem.LINK);
        items.add(copy);
        return this;

    }

    public ShareUtil sms() {
        ShareItem sms = new ShareItem(R.drawable.share_sms, "短信");
        sms.setType(ShareItem.MESSAGE);
        items.add(sms);
        return this;

    }

    public ShareUtil sinaWeibo() {
        ShareItem sinaWeibo = new ShareItem(R.drawable.share_sina, "新浪微博");
        sinaWeibo.setType(ShareItem.WEIBO);
        items.add(sinaWeibo);
        return this;

    }

    public ShareUtil favourite() {
        ShareItem favourite = new ShareItem(R.drawable.share_wx_favourite, "微信收藏");
        favourite.setType(ShareItem.WECHAT_FAVOURITE);
        items.add(favourite);
        return this;

    }

    public ShareUtil timeLine() {
        ShareItem timeTine = new ShareItem(R.drawable.share_wx_timeline, "微信朋友圈");
        timeTine.setType(ShareItem.WECHAT_TIMELINE);
        items.add(timeTine);
        return this;

    }

    public ShareUtil weChat() {
        ShareItem weChat = new ShareItem(R.drawable.share_wx_friend, "微信好友");
        weChat.setType(ShareItem.WECHAT);
        items.add(weChat);
        return this;
    }


    public ShareUtil qq() {
        ShareItem qq = new ShareItem(R.drawable.share_qq, "QQ");
        qq.setType(ShareItem.QQ);
        items.add(qq);
        return this;
    }


}
