package cn.steve.annotations;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import cn.steve.study.R;

/**
 * http://tools.android.com/tech-docs/support-annotations
 *
 * annotations的学习
 *
 *
 * Created by yantinggeng on 2015/10/29.
 */
public class AnnotationsActivity extends AppCompatActivity {

    public static final int TYPE1 = 1;
    public static final int TYPE2 = 2;
    public static final int TYPE3 = 3;


    private TextView textViewMain;

    // 1.@Nullable，表示可以为空;@NotNull表示不能为空
    private String getNullableContent(@NonNull String content) {
        return content;
    }

    // 2.@StringRes, @DrawableRes, @ColorRes, @InterpolatorRes,@AnyRes分别对应了各个资源类型的资源ID
    private void setResource2TextView(@StringRes int id) {
        this.textViewMain.setText(getResources().getString(id));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);
        this.textViewMain = (TextView) findViewById(R.id.textViewMain);
    }

    @TYPES
    private int getType() {
        return TYPE2;
    }

    private void setType(@TYPES int t) {
    }

    // 4.线程注解：@UiThread,@MainThread,@WorkerThread,@BinderThread
    @UiThread
    private void updateUI() {
        this.textViewMain.setText("在UI线程");
    }

    // 3.IntDef/StringDef，用以替代枚举类型的值; 还可以以@IntDef(flag = true,value = {TYPE1, TYPE2, TYPE3})的形式表示是否可以作为标志位，也就是使用与或非进行操作
    @IntDef(flag = true, value = {TYPE1, TYPE2, TYPE3})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPES {

    }

}
