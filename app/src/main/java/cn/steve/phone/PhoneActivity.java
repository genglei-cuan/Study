package cn.steve.phone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.steve.study.R;
import steve.cn.mylib.commonutil.ToastUtil;

/**
 * Created by steveyan on 16-10-4.
 */

public class PhoneActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        assignViews();
    }

    private void assignViews() {
        Button buttonMain = (Button) findViewById(R.id.buttonMain);
        assert buttonMain != null;
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCall();
            }
        });
    }


    private void callPhone() {
        String mobile = "15221318313";
        // 使用系统的电话拨号服务，必须去声明权限，在AndroidManifest.xml中进行声明
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
        this.startActivity(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0x11) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            } else {
                System.out.println("Permission dined");
            }
        }
    }


    private boolean hasPermission() {
        boolean b = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
        return b;
    }


    private void askForPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 0x11);
    }


    private void onClickCall() {
        if (hasPermission()) {
            // 有权限
            callPhone();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                ToastUtil.show(this, "打电话需要拨号的权限", Toast.LENGTH_SHORT);
            } else {
                // 没有权限,需要动态申请
                askForPermission();
            }
        }
    }


}
