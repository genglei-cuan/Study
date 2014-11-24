package cn.steve.drawcircle;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import cn.steve.study.R;

public class CircleActivity extends Activity {

	private EditText mET;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_circle);
		
		mET = (EditText) findViewById(R.id.ET);




		// 内容改变监听器
		mET.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

				
				
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

				
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

				System.out.println(s);
				
			}
			
		});

		// 焦点状态发生变化的时候
		mET.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub

				if (hasFocus) {
					
					
				}
				
				
			}
		});

	}

}
