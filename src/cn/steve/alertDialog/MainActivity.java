package cn.steve.alertDialog;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import cn.steve.study.R;


/**
 * ¶Ô»°¿ò
 * @author steve.yan
 *
 */
public class MainActivity extends Activity {
	String[] allDivisionNames = { "one", "two", "three" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new AlertDialog.Builder(MainActivity.this)
						.setTitle("title")
						.setSingleChoiceItems(allDivisionNames, 0,
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										try {
											Toast.makeText(
													getApplicationContext(),
													allDivisionNames[which],
													Toast.LENGTH_SHORT).show();

										} finally {
											dialog.dismiss();
										}
									}
								}).show();
			}
		});

	}

	public String[] getData() {
		return this.allDivisionNames;
	}

}
