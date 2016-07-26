package cn.steve.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/7/22.
 */
public class ContentProviderActivity extends AppCompatActivity {

    private Button buttonMain;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        this.buttonMain = (Button) findViewById(R.id.buttonMain);
        this.textView = (TextView) findViewById(R.id.textView);
        this.buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer stringBuffer = new StringBuffer();
                Uri bookUri = Uri.parse("content://cn.steve.config.provider/book");
                ContentValues values = new ContentValues();
                values.put("_id", 6);
                values.put("name", "程序设计的艺术");
                getContentResolver().insert(bookUri, values);
                Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);
                while (bookCursor.moveToNext()) {
                    stringBuffer.append(bookCursor.getInt(0));
                    stringBuffer.append("\n");
                    stringBuffer.append(bookCursor.getString(1));
                    stringBuffer.append("\n");
                }
                bookCursor.close();

                Uri userUri = Uri.parse("content://cn.steve.config.provider/user");
                String[] projection = {"_id", "name", "sex"};
                String selection = "name=?";
                String[] selectionArgs = {"jake"};
                Cursor userCursor = getContentResolver().query(userUri, projection, selection, selectionArgs, null);
                while (userCursor.moveToNext()) {
                    stringBuffer.append(userCursor.getInt(0));
                    stringBuffer.append("\n");
                    stringBuffer.append(userCursor.getString(1));
                    stringBuffer.append("\n");
                    boolean b = userCursor.getInt(2) == 1;
                    stringBuffer.append("\n");
                }
                userCursor.close();
                textView.setText(stringBuffer.toString());
                getContentResolver().delete(userUri, null, null);
            }
        });
    }
}
