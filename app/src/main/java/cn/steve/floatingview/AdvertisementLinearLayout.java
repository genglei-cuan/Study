package cn.steve.floatingview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.steve.study.R;
import steve.cn.mylib.commonutil.ScreenUtils;


/**
 * Created by Steve on 2015/8/27.
 */
public class AdvertisementLinearLayout extends LinearLayout implements View.OnClickListener {

    MoveListener listener;
    private Context mContext;
    private ImageView mImageView = null;
    private LinearLayout mLinearLayout = null;
    private LinearLayout mLinearLayoutGroup = null;
    //imageView的坐标系
    private int startImageView;
    private int topImageView;
    private int bottomImageView;
    //linearLayout的坐标系
    private int startLinearLayout;
    private int topLinearLayout;
    private int bottomLinearLayout;
    //imageView的宽高
    private int imageHeight;

    private WebView mWebView;
    private int imageWidth;
    //整个屏幕的宽高
    private int mWidth;
    private int mHeight;
    //linear的宽高
    private int linearWidth = 0;
    private int linearHeight = 0;

    public AdvertisementLinearLayout(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public AdvertisementLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getmWidth() {
        return mWidth;
    }

    void init() {
        setOrientation(HORIZONTAL);
        mImageView = new ImageView(mContext);
        LayoutParams imageViewParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                        ViewGroup.LayoutParams.WRAP_CONTENT);
        mImageView.setLayoutParams(imageViewParams);
        mImageView.setOnClickListener(this);

        mImageView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.circle));

        int strokeWidth = 5; // 3px not dp
        int roundRadius = 15; // 8px not dp
        int strokeColor = Color.parseColor("#EAEAEA");
        int fillColor = Color.parseColor("#DFDFE0");
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(fillColor);

        float[] cors = new float[]{roundRadius, roundRadius, 0, 0, 0, 0, roundRadius, roundRadius};
        gd.setCornerRadii(cors);
        gd.setStroke(strokeWidth, strokeColor);

        mLinearLayoutGroup = new LinearLayout(mContext);

        mLinearLayout = new LinearLayout(mContext);
        mLinearLayout.setBackgroundDrawable(gd);

        LayoutParams
            linearLayoutParams =
            new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                             ViewGroup.LayoutParams.MATCH_PARENT);
        LayoutParams
            linearLayoutGroupParams =
            new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                             ViewGroup.LayoutParams.MATCH_PARENT);
        mLinearLayoutGroup.setLayoutParams(linearLayoutGroupParams);
        mLinearLayout.setLayoutParams(linearLayoutParams);
        mLinearLayoutGroup.setOrientation(HORIZONTAL);
        mLinearLayout.setOrientation(HORIZONTAL);

        mWebView = new WebView(mContext);
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
        mLinearLayout.addView(mWebView);
        mLinearLayoutGroup.addView(mImageView);
        mLinearLayoutGroup.addView(mLinearLayout);
        addView(mLinearLayoutGroup);
    }


    public void loadURL(String url) {
        mWebView.loadUrl(url);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        imageHeight = mImageView.getMeasuredHeight();
        imageWidth = mImageView.getMeasuredWidth();

        linearWidth = mLinearLayout.getMeasuredWidth();
        linearHeight = mLinearLayout.getMeasuredHeight();

        startImageView = getLeft();

        topImageView = mHeight / 2;
        topLinearLayout = ScreenUtils.dpToPxInt(mContext, 50f);

        bottomLinearLayout = mHeight - topLinearLayout;

        startLinearLayout = startImageView + imageWidth;

        bottomImageView = topImageView + imageHeight;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mImageView.layout(startImageView, topImageView, startLinearLayout, bottomImageView);
        mLinearLayout.layout(startLinearLayout, topLinearLayout, mWidth, bottomLinearLayout);
    }

    @Override
    public void onClick(View v) {
        ValueAnimator animator = ValueAnimator.ofInt(0, mWidth - imageWidth);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            int pre = 0;

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                int delta = animatedValue - pre;
                pre = animatedValue;
                //更新window的位置
                listener.onClick(delta);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                listener.setShowingStatus();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.setDuration(1000);
        animator.start();
    }

    public void setMoveListener(MoveListener listener) {
        this.listener = listener;
    }

    interface MoveListener {

        public void onClick(int deltawidth);

        public void setShowingStatus();
    }

}
