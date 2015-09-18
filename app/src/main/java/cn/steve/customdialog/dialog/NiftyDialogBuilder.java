package cn.steve.customdialog.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.steve.customdialog.dialog.effects.BaseEffects;
import cn.steve.study.R;

/**
 * Created by lee on 2014/7/30.
 */
public class NiftyDialogBuilder extends Dialog implements DialogInterface {

  private final String defTextColor = "#FFFFFFFF";

  private final String defDividerColor = "#11000000";

  private final String defMsgColor = "#FFFFFFFF";

  private final String defDialogColor = "#FFE74C3C";

  private Effectstype type = null;

  private LinearLayout mLinearLayoutView;

  private RelativeLayout mRelativeLayoutView;

  private LinearLayout mLinearLayoutMsgView;

  private LinearLayout mLinearLayoutTopView;

  private FrameLayout mFrameLayoutCustomView;

  private View mDialogView;

  private View mDivider;

  private TextView mTitle;

  private TextView mMessage;

  private ImageView mIcon;

  private Button mButton1;

  private Button mButton2;

  private int mDuration = -1;

  private static int mOrientation = 1;

  private boolean isCancelable = true;

  private volatile static NiftyDialogBuilder instance;

  public NiftyDialogBuilder(Context context) {
    super(context);
    init(context);

  }

  public NiftyDialogBuilder(Context context, int theme) {
    super(context, theme);
    init(context);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // 设置当前为全屏有这些设置之后，将会导致对话框不能取消
    WindowManager.LayoutParams params = getWindow().getAttributes();
    params.height = ViewGroup.LayoutParams.MATCH_PARENT;
    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
    getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
  }

  /**
   * 
   * 单例模式
   * 
   * @param context
   * @return 返回一个对话框实例
   * 
   */
  public static NiftyDialogBuilder getInstance(Context context) {
    int ort = context.getResources().getConfiguration().orientation;
    if (mOrientation != ort) {
      mOrientation = ort;
      instance = null;
    }
    if (instance == null || ((Activity) context).isFinishing()) {
      synchronized (NiftyDialogBuilder.class) {
        if (instance == null) {
          instance = new NiftyDialogBuilder(context, R.style.dialog_untran);
        }
      }
    }
    return instance;
  }

  // 初始化控件
  private void init(Context context) {
    // 整个对话框的整体布局
    mDialogView = View.inflate(context, R.layout.dialog_layout, null);
    // 获取对话框中的组件
    mLinearLayoutView = (LinearLayout) mDialogView.findViewById(R.id.parentPanel);
    mRelativeLayoutView = (RelativeLayout) mDialogView.findViewById(R.id.main);
    mLinearLayoutTopView = (LinearLayout) mDialogView.findViewById(R.id.topPanel);
    mLinearLayoutMsgView = (LinearLayout) mDialogView.findViewById(R.id.contentPanel);
    mFrameLayoutCustomView = (FrameLayout) mDialogView.findViewById(R.id.customPanel);
    mTitle = (TextView) mDialogView.findViewById(R.id.alertTitle);
    mMessage = (TextView) mDialogView.findViewById(R.id.message);
    mIcon = (ImageView) mDialogView.findViewById(R.id.icon);
    mDivider = mDialogView.findViewById(R.id.titleDivider);
    mButton1 = (Button) mDialogView.findViewById(R.id.button1);
    mButton2 = (Button) mDialogView.findViewById(R.id.button2);

    // 设置当前的对话框的布局
    setContentView(mDialogView);
    // 设置对话框在显示的时候的监听器
    this.setOnShowListener(new OnShowListener() {
      @Override
      public void onShow(DialogInterface dialogInterface) {
        mLinearLayoutView.setVisibility(View.VISIBLE);
        if (type == null) {
          type = Effectstype.Slidetop;
        }
        start(type);
      }
    });

    // 有这些设置之后，将导致所有的点击都会导致对话框消失
    mRelativeLayoutView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (isCancelable) dismiss();
      }
    });
  }


  // 对话框的默认设置,暂未发现有何用处
  public void toDefault() {
    mTitle.setTextColor(Color.parseColor(defTextColor));
    mDivider.setBackgroundColor(Color.parseColor(defDividerColor));
    mMessage.setTextColor(Color.parseColor(defMsgColor));
    mLinearLayoutView.setBackgroundColor(Color.parseColor(defDialogColor));
  }

  // //以下为动态设置对话框中的数据的方法
  public NiftyDialogBuilder withDividerColor(String colorString) {
    mDivider.setBackgroundColor(Color.parseColor(colorString));
    return this;
  }


  public NiftyDialogBuilder withTitle(CharSequence title) {
    toggleView(mLinearLayoutTopView, title);
    mTitle.setText(title);
    return this;
  }

  public NiftyDialogBuilder withTitleColor(String colorString) {
    mTitle.setTextColor(Color.parseColor(colorString));
    return this;
  }

  public NiftyDialogBuilder withMessage(int textResId) {
    toggleView(mLinearLayoutMsgView, textResId);
    mMessage.setText(textResId);
    return this;
  }

  public NiftyDialogBuilder withMessage(CharSequence msg) {
    toggleView(mLinearLayoutMsgView, msg);
    mMessage.setText(msg);
    return this;
  }

  public NiftyDialogBuilder withMessageColor(String colorString) {
    mMessage.setTextColor(Color.parseColor(colorString));
    return this;
  }

  public NiftyDialogBuilder withIcon(int drawableResId) {
    mIcon.setImageResource(drawableResId);
    return this;
  }

  public NiftyDialogBuilder withIcon(Drawable icon) {
    mIcon.setImageDrawable(icon);
    return this;
  }

  public NiftyDialogBuilder withDuration(int duration) {
    this.mDuration = duration;
    return this;
  }

  public NiftyDialogBuilder withEffect(Effectstype type) {
    this.type = type;
    return this;
  }

  public NiftyDialogBuilder withButtonDrawable(int resid) {
    mButton1.setBackgroundResource(resid);
    mButton2.setBackgroundResource(resid);
    return this;
  }

  public NiftyDialogBuilder withButton1Text(CharSequence text) {
    mButton1.setVisibility(View.VISIBLE);
    mButton1.setText(text);
    return this;
  }

  public NiftyDialogBuilder withButton2Text(CharSequence text) {
    mButton2.setVisibility(View.VISIBLE);
    mButton2.setText(text);
    return this;
  }

  public NiftyDialogBuilder setButton1Click(View.OnClickListener click) {
    mButton1.setOnClickListener(click);
    return this;
  }

  public NiftyDialogBuilder setButton2Click(View.OnClickListener click) {
    mButton2.setOnClickListener(click);
    return this;
  }

  // 设置对话框内的自定义布局
  public NiftyDialogBuilder setCustomView(int resId, Context context) {
    View customView = View.inflate(context, resId, null);
    if (mFrameLayoutCustomView.getChildCount() > 0) {
      mFrameLayoutCustomView.removeAllViews();
    }
    mFrameLayoutCustomView.addView(customView);
    return this;
  }

  // 设置对话框内的自定义布局
  public NiftyDialogBuilder setCustomView(View view, Context context) {
    if (mFrameLayoutCustomView.getChildCount() > 0) {
      mFrameLayoutCustomView.removeAllViews();
    }
    mFrameLayoutCustomView.addView(view);
    return this;
  }

  public NiftyDialogBuilder isCancelableOnTouchOutside(boolean cancelable) {
    this.isCancelable = cancelable;
    this.setCanceledOnTouchOutside(cancelable);
    return this;
  }

  public NiftyDialogBuilder isCancelable(boolean cancelable) {
    this.isCancelable = cancelable;
    this.setCancelable(cancelable);
    return this;
  }

  /**
   * 转换器
   * 
   * @param view 需要显示或者隐藏的view界面
   * @param obj 参考的标准
   */
  private void toggleView(View view, Object obj) {
    if (obj == null) {
      view.setVisibility(View.GONE);
    } else {
      view.setVisibility(View.VISIBLE);
    }
  }

  @Override
  public void show() {
    super.show();
  }

  // 启动动画消失对话框
  private void start(Effectstype type) {
    BaseEffects animator = type.getAnimator();
    if (mDuration != -1) {
      animator.setDuration(Math.abs(mDuration));
    }
    animator.start(mRelativeLayoutView);
  }

  // 取消显示
  @Override
  public void dismiss() {
    super.dismiss();
    mButton1.setVisibility(View.GONE);
    mButton2.setVisibility(View.GONE);
  }
}
