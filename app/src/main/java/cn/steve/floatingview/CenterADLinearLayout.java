package cn.steve.floatingview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import steve.cn.mylib.commonutil.ScreenUtils;


/**
 * Created by Steve on 2015/8/27.
 */
public class CenterADLinearLayout extends LinearLayout {

    private Context mContext;
    private LinearLayout mLinearLayoutGroup = null;
    private LinearLayout mLinearLayoutAppGroup = null;
    private LinearLayout mLinearLayout = null;
    private ImageView imageViewIcon;
    private ImageView imageViewSafe;
    private TextView textViewAppName;
    private TextView textViewAppPopular;
    private TextView textViewAppDesc;

    private WebView mWebView;


    private int mWidth;
    private int mHeight;


    public CenterADLinearLayout(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public CenterADLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    void init() {

        setOrientation(VERTICAL);

        //设置圆形背景
        int strokeWidth = 5; // 3px not dp
        int roundRadius = 15; // 8px not dp
        int strokeColor = Color.parseColor("#21b7b3");
        int fillColor = Color.parseColor("#21b7b3");
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(fillColor);
        float[]
            cors =
            new float[]{roundRadius, roundRadius, roundRadius, roundRadius, roundRadius,
                        roundRadius, roundRadius, roundRadius};
        gd.setCornerRadii(cors);
        gd.setStroke(strokeWidth, strokeColor);

        imageViewIcon = new ImageView(mContext);
        textViewAppDesc = new TextView(mContext);
        textViewAppName = new TextView(mContext);
        textViewAppPopular = new TextView(mContext);
        imageViewSafe = new ImageView(mContext);
        mLinearLayoutAppGroup = new LinearLayout(mContext);
        mLinearLayoutGroup = new LinearLayout(mContext);
        mWebView = new WebView(mContext);
        mLinearLayout = new LinearLayout(mContext);

        LayoutParams
            linearLayoutParamsWebView =
            new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                             ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(linearLayoutParamsWebView);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        //保持比例
        mWebView.setInitialScale(1);
        mWebView.setWebViewClient(new WebViewClient());

        LayoutParams paramsImageViewIcon = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                            ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsImageViewIcon.leftMargin = ScreenUtils.dpToPxInt(mContext, 20f);

        LayoutParams paramsTextViewAppName = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                              ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutParams
            paramsTextViewAppPopular =
            new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                             ViewGroup.LayoutParams.WRAP_CONTENT);

        LayoutParams paramsTextViewAppDesc = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                              ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutParams paramsImageViewSafe = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                            ViewGroup.LayoutParams.WRAP_CONTENT);

        paramsImageViewSafe.leftMargin = ScreenUtils.dpToPxInt(mContext, 30f);
        LayoutParams paramsLinearLayoutApp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                              ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsLinearLayoutApp.leftMargin = ScreenUtils.dpToPxInt(mContext, 20f);
        LayoutParams paramsLinearLayoutGroup = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutParams paramsLinearLayout = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                           ViewGroup.LayoutParams.MATCH_PARENT);

        imageViewIcon.setLayoutParams(paramsImageViewIcon);
        imageViewSafe.setLayoutParams(paramsImageViewSafe);
        textViewAppName.setLayoutParams(paramsTextViewAppName);
        textViewAppPopular.setLayoutParams(paramsTextViewAppPopular);
        textViewAppDesc.setLayoutParams(paramsTextViewAppDesc);
        mLinearLayoutAppGroup.setLayoutParams(paramsLinearLayoutApp);
        mLinearLayoutAppGroup.setOrientation(VERTICAL);
        mLinearLayoutGroup.setLayoutParams(paramsLinearLayoutGroup);
        mLinearLayoutGroup.setOrientation(HORIZONTAL);

        mLinearLayoutAppGroup.addView(textViewAppName);
        mLinearLayoutAppGroup.addView(textViewAppPopular);
        mLinearLayoutAppGroup.addView(textViewAppDesc);

        mLinearLayoutGroup.addView(imageViewIcon);
        mLinearLayoutGroup.addView(mLinearLayoutAppGroup);
        mLinearLayoutGroup.addView(imageViewSafe);

        mLinearLayout.setOrientation(VERTICAL);
        mLinearLayout.setLayoutParams(paramsLinearLayout);
        mLinearLayout.addView(mLinearLayoutGroup);
        mLinearLayout.addView(mWebView);

        mLinearLayout.setBackgroundDrawable(gd);
        addView(mLinearLayout);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public void setImageViewIconBitmap(BitmapDrawable bitmapDrawable) {
        imageViewIcon.setImageDrawable(bitmapDrawable);
    }

    public void setTextViewAppName(String appName) {
        textViewAppName.setText(appName);
    }

    public void setTextViewAppPopular(String appPopular) {
        textViewAppPopular.setText(appPopular);
    }

    public void setTextViewAppDesc(String appDesc) {
        textViewAppDesc.setText(appDesc);
    }

    public void setImageViewSafe(BitmapDrawable bitmapDrawable) {
        imageViewSafe.setImageDrawable(bitmapDrawable);
    }

    public void loadURL(String url) {
        mWebView.loadUrl(url);
    }

}
