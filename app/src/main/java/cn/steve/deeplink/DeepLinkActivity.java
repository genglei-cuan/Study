package cn.steve.deeplink;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.steve.study.R;

public class DeepLinkActivity extends AppCompatActivity {

    private TextView textviewDeepLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link);
        this.textviewDeepLink = (TextView) findViewById(R.id.textviewDeepLink);
        onNewIntent(getIntent());
    }

    /**
     * 检验该intent是否是deep link的intent。如果是则从intent数据从接触recipe的URI并调用 showRecipe()来展示菜谱。
     **/
    protected void onNewIntent(Intent intent) {
        String action = intent.getAction();
        String data = intent.getDataString();
        if (Intent.ACTION_VIEW.equals(action) && data != null) {
            String recipeId = data.substring(data.lastIndexOf("/") + 1);
            textviewDeepLink.setText(data.toString());
        }
    }
}
