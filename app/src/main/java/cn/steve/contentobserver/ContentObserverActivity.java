package cn.steve.contentobserver;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import cn.steve.study.R;
import steve.cn.mylib.util.FileUtil;


/**
 * 列出存储器中所有的截图文件
 *
 *
 * Created by yantinggeng on 2015/11/5.
 */
public class ContentObserverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        getAllScreenShot();
    }


    //取出所有截图看看大小
    private void getAllScreenShot() {
        Cursor query;
        String str = "( " + "_data" + " like ? or " + "_data" + " like ? or " + "_data" + " like ? )";
        //查询数据库的内容
        query = this.getContentResolver()
            .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, str, new String[]{"%Screenshot%", "%screenshot%", "%\u622a\u5c4f%"}, MediaStore.Images.Media.DATE_ADDED);
        if (query != null) {
            try {
                if (query.getCount() > 0) {
                    query.moveToFirst();
                    while (query.moveToNext()) {
                        String realPath = query.getString(query.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                        String type = query.getString(query.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE));
                        long size = query.getLong(query.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE));
                        boolean hasType = !TextUtils.isEmpty(type);
                        boolean isImage = type.contains("image") || type.contains("Image");
                        //打印图片的详细信息
                        System.out.println(realPath + "大小:" + size);
                        System.out.println("真实大小:" + FileUtil.getFileSize(realPath));
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
