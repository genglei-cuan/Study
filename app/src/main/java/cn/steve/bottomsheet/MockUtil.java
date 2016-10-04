package cn.steve.bottomsheet;

import java.util.ArrayList;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/9/30.
 */

public class MockUtil {

    public static ArrayList<ShareItem> createItems() {
        ArrayList<ShareItem> items = new ArrayList<>();

        ShareItem previewItem = new ShareItem(R.drawable.share_icon_wechat, "Preview");
        setOnClickListener(previewItem);
        items.add(previewItem);

        ShareItem share = new ShareItem(R.drawable.share_icon_wechatfriends, "Share");
        setOnClickListener(share);
        items.add(share);

        ShareItem link = new ShareItem(R.drawable.share_icon_copy, "Get link");
        setOnClickListener(link);
        items.add(link);

        ShareItem copy = new ShareItem(R.drawable.share_icon_qq, "Copy");
        setOnClickListener(copy);
        items.add(copy);

        items.add(previewItem);
        items.add(share);
        items.add(link);
        items.add(copy);

        return items;
    }


    private static void setOnClickListener(final ShareItem item) {
    }


}
