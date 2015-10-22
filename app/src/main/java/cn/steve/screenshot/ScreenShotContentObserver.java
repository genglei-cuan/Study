package cn.steve.screenshot;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;

/**
 * 监听截屏产生的图片变化
 *
 * Created by yantinggeng on 2015/10/22.
 */
public class ScreenShotContentObserver extends ContentObserver {

    private Context mContext;
    private Handler mHandler;

    public ScreenShotContentObserver(Context context, Handler handler) {
        super(handler);
        this.mContext = context;
        this.mHandler = handler;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Cursor query;
        //检查四秒以内的变化
        long currentTimeMillis = System.currentTimeMillis() / 1000;

        String str = "date_added > " + (currentTimeMillis - 4)
                     + " and " + "date_added" + " < "
                     + (currentTimeMillis + 4) +
                     " and ( " + "_data" + " like ? or " + "_data" + " like ? or " + "_data"
                     + " like ? )";
        //查询数据库的内容
        query = this.mContext.getContentResolver().query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, str,
            new String[]{"%Screenshot%", "%screenshot%", "%\u622a\u5c4f%"}, "date_added");
        if (query != null) {
            try {
                if (query.getCount() > 0) {
                    query.moveToLast();
                    String realPath = query.getString(query.getColumnIndexOrThrow("_data"));
                    String type = query.getString(query.getColumnIndexOrThrow("mime_type"));
                    if (query.getLong(query.getColumnIndexOrThrow("_size")) > 1
                        && !TextUtils.isEmpty(type) && (type.contains("image")
                                                        || type.contains("Image"))) {
                        mHandler.obtainMessage(1,realPath).sendToTarget();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (query != null) {
            query.close();
        }
    }
}
