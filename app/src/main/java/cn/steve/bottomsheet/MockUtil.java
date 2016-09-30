package cn.steve.bottomsheet;

import java.util.ArrayList;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/9/30.
 */

public class MockUtil {

    public static ArrayList<ShareItem> createItems() {
        ArrayList<ShareItem> items = new ArrayList<>();
        items.add(new ShareItem(R.drawable.share_icon_wechat, "Preview"));
        items.add(new ShareItem(R.drawable.share_icon_wechatfriends, "Share"));
        items.add(new ShareItem(R.drawable.share_icon_copy, "Get link"));
        items.add(new ShareItem(R.drawable.share_icon_qq, "Copy"));

        items.add(new ShareItem(R.drawable.ic_share_24dp, "Share"));
        items.add(new ShareItem(R.drawable.ic_content_copy_24dp, "Copy"));
        items.add(new ShareItem(R.drawable.ic_preview_24dp, "Preview"));
        items.add(new ShareItem(R.drawable.ic_link_24dp, "Get link"));

        items.add(new ShareItem(R.drawable.ic_share_24dp, "Share"));
        items.add(new ShareItem(R.drawable.ic_link_24dp, "Get link"));
        items.add(new ShareItem(R.drawable.ic_content_copy_24dp, "Copy"));
        items.add(new ShareItem(R.drawable.share_icon_more, "Preview"));

        return items;
    }
}
