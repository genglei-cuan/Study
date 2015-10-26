package cn.steve.floatingview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.steve.study.R;


/**
 * Created by Steve on 2015/9/2.
 */
public class InstallSuccessView extends LinearLayout {

    private Context mContext;
    private LinearLayout mLinearLayoutTop;
    private ImageView mImageViewIcon;
    private TextView mTextViewAppName;
    private TextView mTextViewSuccess;
    private LinearLayout mLinearLayoutBottom;
    private Button mButtonComplete, mButtonOpen;
    private LinearLayout mLinearLayoutWebView;
    private LayoutParams linearLayoutParamsTop;
    private LayoutParams linearLayoutParamsWebView;
    private LayoutParams linearLayoutParamsBottom;
    private LayoutParams paramsWebView;
    private LayoutParams paramsImageViewIcon;
    private LayoutParams paramsTextViewAppName;
    private LayoutParams paramsTextViewSuccess;
    private LayoutParams paramsButtonComplete;
    private LayoutParams paramsButtonOpen;

    public InstallSuccessView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public InstallSuccessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        mLinearLayoutTop = new LinearLayout(mContext);
        mImageViewIcon = new ImageView(mContext);
        mTextViewAppName = new TextView(mContext);
        mTextViewSuccess = new TextView(mContext);
        mLinearLayoutBottom = new LinearLayout(mContext);
        mButtonComplete = new Button(mContext);
        mButtonOpen = new Button(mContext);
        mLinearLayoutWebView = new LinearLayout(mContext);

        //
        linearLayoutParamsTop = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                 ViewGroup.LayoutParams.WRAP_CONTENT);

        linearLayoutParamsTop.weight = 3;
        mLinearLayoutTop.setOrientation(VERTICAL);
        mLinearLayoutTop.setLayoutParams(linearLayoutParamsTop);
        mLinearLayoutTop.setBackgroundColor(Color.parseColor("#14abb0"));
//        mLinearLayoutTop.setPadding(ScreenUtils.dpToPxInt(mContext,0),ScreenUtils.dpToPxInt(mContext,100),ScreenUtils.dpToPxInt(mContext, 0),ScreenUtils.dpToPxInt(mContext, 0));
        mLinearLayoutTop.setGravity(Gravity.CENTER);

        linearLayoutParamsWebView =
            new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        linearLayoutParamsWebView.weight = 6;
        mLinearLayoutWebView.setOrientation(VERTICAL);
        mLinearLayoutWebView.setLayoutParams(linearLayoutParamsWebView);

        linearLayoutParamsBottom =
            new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        linearLayoutParamsBottom.weight = 1;
        mLinearLayoutBottom.setOrientation(HORIZONTAL);
        mLinearLayoutBottom.setLayoutParams(linearLayoutParamsBottom);
        mLinearLayoutBottom.setBackgroundColor(Color.parseColor("#f7f7f7"));

        paramsWebView = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        paramsImageViewIcon =
            new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        paramsImageViewIcon.gravity = Gravity.CENTER;
        paramsTextViewAppName =
            new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        paramsTextViewAppName.gravity = Gravity.CENTER;
        paramsTextViewSuccess =
            new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        paramsTextViewSuccess.gravity = Gravity.CENTER;

        paramsButtonComplete =
            new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        paramsButtonComplete.weight = 1;
        paramsButtonComplete.gravity = Gravity.CENTER;

        paramsButtonOpen = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        paramsButtonOpen.weight = 1;
        paramsButtonOpen.gravity = Gravity.CENTER;

        mImageViewIcon.setLayoutParams(paramsImageViewIcon);
        mTextViewAppName.setLayoutParams(paramsTextViewAppName);
        mTextViewSuccess.setLayoutParams(paramsTextViewSuccess);

        mImageViewIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.circle));
        mTextViewSuccess.setText("应用安装成功");
        mTextViewAppName.setText("消灭星星美女版");

        mButtonComplete.setLayoutParams(paramsButtonComplete);
        mButtonOpen.setLayoutParams(paramsButtonOpen);
//        mButtonComplete.setText(mContext.getResources().getString(R.string.complete));
        mButtonComplete.setText("完成");
        mButtonOpen.setText("打开");
        mButtonComplete.setBackgroundColor(Color.TRANSPARENT);
        mButtonOpen.setBackgroundColor(Color.TRANSPARENT);

        mLinearLayoutTop.addView(mImageViewIcon);
        mLinearLayoutTop.addView(mTextViewAppName);
        mLinearLayoutTop.addView(mTextViewSuccess);

        mLinearLayoutBottom.addView(mButtonComplete);
        mLinearLayoutBottom.addView(mButtonOpen);

        setOrientation(VERTICAL);
        addView(mLinearLayoutTop);
        addView(mLinearLayoutWebView);
        addView(mLinearLayoutBottom);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }


    public void addWebView(WebView webView) {
        webView.setLayoutParams(paramsWebView);
        mLinearLayoutWebView.addView(webView);
    }
}
