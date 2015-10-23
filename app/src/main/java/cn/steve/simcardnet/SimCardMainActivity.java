package cn.steve.simcardnet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import cn.steve.study.R;
import steve.cn.mylib.util.SIMCardUtil;


public class SimCardMainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_textview);

    SIMCardUtil telephonyInfo = SIMCardUtil.getInstance(this);

    String imsiSIM1 = telephonyInfo.getImsiSIM1();
    String imsiSIM2 = telephonyInfo.getImsiSIM2();

    boolean isSIM1Ready = telephonyInfo.isSIM1Ready();
    boolean isSIM2Ready = telephonyInfo.isSIM2Ready();

    boolean isDualSIM = telephonyInfo.isDualSIM();

    TextView tv = (TextView) findViewById(R.id.textViewMain);
    tv.setText(" IMEI1 : " + imsiSIM1 + "\n" +
               " IMEI2 : " + imsiSIM2 + "\n" +
               " IS DUAL SIM : " + isDualSIM + "\n" +
               " IS SIM1 READY : " + isSIM1Ready + "\n" +
               " IS SIM2 READY : " + isSIM2Ready + "\n");
    telephonyInfo.printTelephonyManagerMethodNamesForThisDevice(this);
  }
}