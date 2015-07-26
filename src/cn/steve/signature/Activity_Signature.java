package cn.steve.signature;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.steve.study.R;



/**
 * 手写签名
 * 
 * @author steve.yan
 *
 */
public class Activity_Signature extends Activity {
  private HandWritingView mHandWritingView = null;
  private Button mSig_button_clear = null;
  private Button mSig_button_save = null;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signature);
    this.mHandWritingView = (HandWritingView) findViewById(R.id.handwriteview);
    this.mSig_button_clear = (Button) findViewById(R.id.sig_button_clear);
    this.mSig_button_save = (Button) findViewById(R.id.sig_button_saveimg);

    // 显示出手写画板
    mHandWritingView.setVisibility(View.VISIBLE);
    // the button for clearing
    mSig_button_clear.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        mHandWritingView.clear();
      }
    });

    // the button for saving image
    mSig_button_save.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        HandWritingView.saveImage =
            Bitmap.createBitmap(mHandWritingView.HandWriting(HandWritingView.new1Bitmap));
        Utils.storeInSD(mHandWritingView.saveImage());
      }
    });
  }
}
