package cn.steve.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/7/22.
 */
public class ContentProviderActivity extends AppCompatActivity {

    private Button buttonMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        this.buttonMain = (Button) findViewById(R.id.buttonMain);
        this.buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri bookUri = Uri.parse("content://cn.steve.config.provider/book");
                ContentValues values = new ContentValues();
                values.put("_id", 6);
                values.put("name", "程序设计的艺术");
                getContentResolver().insert(bookUri, values);
                Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);
                while (bookCursor.moveToNext()) {
                    bookCursor.getInt(0);
                    bookCursor.getString(1);
                }
                bookCursor.close();

                Uri userUri = Uri.parse("content://cn.steve.config.provider/user");
                Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "name", "sex"}, null, null, null);
                while (userCursor.moveToNext()) {
                    userCursor.getInt(0);
                    userCursor.getString(1);
                    boolean b = userCursor.getInt(2) == 1;
                }
                userCursor.close();
            }
        });
    }
}
