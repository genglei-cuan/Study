package cn.steve.deviceinfo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import cn.steve.study.R;
import steve.cn.mylib.util.DeviceInfoUtils;
import steve.cn.mylib.util.ScreenInfoUtil;

/**
 * 展示一些设备的基本信息，一些硬件信息
 */
public class DeviceInfoActivity extends Activity {

    private TextView textViewDeviceInfo;
    private StringBuffer sb = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_textview);

        textViewDeviceInfo = (TextView) findViewById(R.id.textViewMain);
        String text = "getDeviceName():" + DeviceInfoUtils.getDeviceName() + "\n Brand:"
                + DeviceInfoUtils.getDeviceBrand() + "\n Model:" + DeviceInfoUtils
                .getDeviceModel()
                + "\n MANUFACTURER:" + DeviceInfoUtils.getDeviceManufacturer()
                + "\n BOOTLOADER:"
                + DeviceInfoUtils.getDeviceBootloader();
        sb.append(text);
        String getDeviceUUID = DeviceInfoUtils.getDeviceUUID(this).toString();
        sb.append("\n UUID:" + getDeviceUUID);
        String cpu = DeviceInfoUtils.getCpuType();
        sb.append("\n cpu:" + cpu);
        String sdk = String.valueOf(DeviceInfoUtils.getAndroidSDKVersion());
        sb.append("\n sdk" + sdk);
        sb.append("\n 屏幕信息：");
        sb.append(ScreenInfoUtil.getScreenDetailInfo(this));
        textViewDeviceInfo.setText(sb);
    }

}
