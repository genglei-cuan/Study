package cn.steve.uicommunicate;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import cn.steve.study.R;

public class UIActivity extends Activity {

  private TextView tv;
  private Button button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_uicommunicate);
    tv = (TextView) findViewById(R.id.textView1);
    button = (Button) findViewById(R.id.uibutton);
    button.requestLayout();
    tv.post(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 100; i++) {
          UIActivity.this.button.setText(String.valueOf(i));
        }
      }
    });

    // this.runOnUiThread(new Runnable() {
    // @Override
    // public void run() {
    // for (int i = 0; i < 1001; i++) {
    // UIActivity.this.tv.setText(String.valueOf(i));
    // }
    // }
    // });
  }

}
