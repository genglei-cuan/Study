package cn.steve.contentobserver;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;

import steve.cn.mylib.util.FileUtil;


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
            new String[]{"%Screenshot%", "%screenshot%", "%\u622a\u5c4f%"},
            MediaStore.Images.Media.DATE_ADDED);

        String[] columnNames = query.getColumnNames();
        for (String s : columnNames) {
            System.out.println("columnNames:" + s);
        }

        if (query != null) {
            try {
                if (query.getCount() > 0) {
                    query.moveToLast();
                    String
                        realPath =
                        query.getString(query.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                    String type = query.getString(
                        query.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE));
                    long size =
                        query.getLong(query.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE));
                    boolean hasType = !TextUtils.isEmpty(type);
                    boolean isImage = type.contains("image") || type.contains("Image");
                    if (size == 0) {
                        //部分机型显示的所有值都是0
                        size = FileUtil.getFileSize(realPath);
                    }
                    if (size > 1 && hasType && isImage) {
                        //此处将截屏图片的绝对路径传递到handler处理
                        mHandler.obtainMessage(1, realPath).sendToTarget();
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
