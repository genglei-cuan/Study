package cn.steve.annotations;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
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

    @MainThread
    private void workInMainThread() {
        this.textViewMain.setText("在主线程");
    }

    @WorkerThread
    private void workInWorkThread() {
        for (int i = 0; i < 100; i++) {
            // 更新UI会报错，一般是后台处理一些复杂的工作，执行一些耗时的工作
        }
    }

    @BinderThread
    private void workInBindThread() {
        // 更新UI报错 Denotes that the annotated method should only be called on the binder thread. If the annotated element is a class, then all methods in the class should be called on the binder thread.
    }

    //5. 值的范围约束,@Size, @IntRange, @FloatRange
    private void setIntValueRange(@IntRange(from = 1, to = 200) int value) {
        //设置以后，参数的范围之内是1到200

    }

    private void setValueArray(@Size(value = 2) int[] array) {
        //设置完之后，参数的长度必须为2，否则报错
    }

    //6. 权限要求,@RequiresPermission 其中的参数anyOf表示至少需要一个权限，allOf 表示所有权限都要有
    @RequiresPermission(anyOf = {Manifest.permission.LOCATION_HARDWARE,
                                 Manifest.permission.INTERNET})
    private void requirePermission() {
        //表名至少需要其中的一个权限
    }

    //7. 表示需要被子类继承的时候调用@CallSuper
    @CallSuper
    protected void mustBecalledInSubclass() {

    }

    // 8.提示返回值检测：@CheckResult,suggest的#之后的是表示替代的方法,当被直接调用的时候，会提示调用下面的方法
    @CheckResult(suggest = "#mustCheckResult(String)")
    private String mustCheckResult() {
        return "must be checked value";
    }

    private void mustCheckResult(String s) {

    }


    // 3.IntDef/StringDef，用以替代枚举类型的值; 还可以以@IntDef(flag = true,value = {TYPE1, TYPE2, TYPE3})的形式表示是否可以作为标志位，也就是使用与或非进行操作
    @IntDef(flag = true, value = {TYPE1, TYPE2, TYPE3})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPES {

    }

}
