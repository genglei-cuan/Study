package cn.steve.customview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.steve.signature.Utils;
import cn.steve.study.R;

public class CustomerPaintActivity extends Activity {
	private Button mClearButton;
	private Button mSaveButton;
	private CustomerPaintView mPaintView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 将自定义的控件类作为整个布局
		// setContentView(new MyPaintView(this));
		// 使用布局文件
		setContentView(R.layout.activity_customer_paint);
		mPaintView = (CustomerPaintView) findViewById(R.id.view_paint);
		mSaveButton = (Button) findViewById(R.id.btn_save);

		mClearButton = (Button) findViewById(R.id.btn_clear);
		mClearButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mPaintView.clear();
			}
		});

		mSaveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Utils.storeInSD(mPaintView.save_img());

			}
		});

	}
}