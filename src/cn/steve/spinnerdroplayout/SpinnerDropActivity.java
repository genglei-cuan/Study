package cn.steve.spinnerdroplayout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;
import cn.steve.study.R;

public class SpinnerDropActivity extends Activity {
	/** Called when the activity is first created. */

	Spinner spinner = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_spinner);

		spinner = (Spinner) findViewById(R.id.spinner);
		
		SpinnerDropAdapter adapter = new SpinnerDropAdapter(this);

		spinner.setAdapter(adapter);

	}
}