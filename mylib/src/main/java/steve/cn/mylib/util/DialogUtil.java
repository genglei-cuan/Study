package steve.cn.mylib.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import steve.cn.mylib.R;


/**
 * loading Dialog工具类
 */
public class DialogUtil {

    // 渐变退出的时间
    private static final int CANCEL_LOADING_TIME = 500;
    // 每旋转一圈的时间
    private static final int PER_REVOLUTION_TIME = 700;
    private SafeProgressDialog loadingDialog;
    private Context mContext;
    private LinearLayout ll_layout;
    private ImageView spaceshipImage;
    private TextView tipTextView;
    private int mDefaultTextSize = 20;

    public DialogUtil(Context context) {
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
        ll_layout = (LinearLayout) view.findViewById(R.id.dialog_view);// 加载布局
        spaceshipImage = (ImageView) view.findViewById(R.id.img_dialog);// loading_dialog中的ImageView
        tipTextView = (TextView) view.findViewById(R.id.tipTextView);// 提示文字
    }

    public boolean isShowing() {
        if (loadingDialog != null) {
            return loadingDialog.isShowing();
        }
        return false;
    }

    /**
     * 显示自定义的progressDialog 默认无文字显示，可点击空白处或者back键使Loading dialog消失
     */
    public void showLoadingDialog() {
        showLoadingDialog("", mDefaultTextSize, true);
    }

    /**
     * 显示自定义的progressDialog 默认无文字显示
     *
     * @param setCancelable 是否可以点击空白处或者back键使Loading dialog消失
     */
    public void showLoadingDialog(boolean setCancelable) {
        showLoadingDialog("", mDefaultTextSize, setCancelable);
    }

    /**
     * 显示自定义的progressDialog
     *
     * @param msg           提示消息
     * @param textSize      提示消息的字体大小
     * @param setCancelable 是否可以点击空白处或者back键使Loading dialog消失
     */
    public void showLoadingDialog(String msg, int textSize,
                                  boolean setCancelable) {
        tipTextView.setTextSize(textSize);
        ObjectAnimator
                animator =
                ObjectAnimator
                        .ofFloat(spaceshipImage, "rotation", 0.0f, 360.0f);// 给spaceshipImage添加旋转动画
        animator.setDuration(PER_REVOLUTION_TIME);
        animator.setRepeatCount(Integer.MAX_VALUE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator);
        animatorSet.start();// 开始动画
        tipTextView.setText(msg);// 设置加载信息
        loadingDialog = new SafeProgressDialog(mContext, R.style.loading_dialog);// 创建自定义样式dialog
        loadingDialog.setCancelable(setCancelable);// 是否可以用“返回键”取消
        loadingDialog.setContentView(ll_layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        loadingDialog.show();
    }

    /**
     * 显示自定义的progressDialog
     *
     * @param msg           提示消息
     * @param textSize      提示消息的字体大小
     * @param setCancelable 是否可以点击空白处或者back键使Loading dialog消失
     */
    public void showLoadingDialogWithOutsizeCancelable(String msg, int textSize,
                                                       boolean setCancelable) {
        tipTextView.setTextSize(textSize);
        ObjectAnimator
                animator =
                ObjectAnimator
                        .ofFloat(spaceshipImage, "rotation", 0.0f, 360.0f);// 给spaceshipImage添加旋转动画
        animator.setDuration(PER_REVOLUTION_TIME);
        animator.setRepeatCount(Integer.MAX_VALUE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator);
        animatorSet.start();// 开始动画
        tipTextView.setText(msg);// 设置加载信息
        loadingDialog = new SafeProgressDialog(mContext, R.style.loading_dialog);// 创建自定义样式dialog
        loadingDialog.setCanceledOnTouchOutside(setCancelable);  //表示是否点击屏幕会消失
        loadingDialog.setContentView(ll_layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        loadingDialog.show();
    }

    /**
     * 取消加载动画带渐变动画退出
     */
    public void cancelLoadingDialog() {
        spaceshipImage.clearAnimation();
        ObjectAnimator animator = ObjectAnimator.ofFloat(ll_layout, "alpha", 1.0f, 0.0f);
        animator.setDuration(CANCEL_LOADING_TIME);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                    loadingDialog = null;
                }
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator);
        animatorSet.start();
    }

    /**
     * 取消加载动画不带渐变动画退出
     */
    public void cancelLoadingDialogNoAnimation() {
        spaceshipImage.clearAnimation();
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    class SafeProgressDialog extends Dialog {

        Activity mParentActivity;

        public SafeProgressDialog(Context context) {
            super(context);
            mParentActivity = (Activity) context;
        }

        public SafeProgressDialog(Context context, int id) {
            super(context, id);
            mParentActivity = (Activity) context;
        }

        @Override
        public void dismiss() {
            if (mParentActivity != null && !mParentActivity.isFinishing()) {
                super.dismiss(); // 调用超类对应方法
            }
        }
    }
}
