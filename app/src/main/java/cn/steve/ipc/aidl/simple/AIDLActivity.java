package cn.steve.ipc.aidl.simple;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.steve.ipc.aidl.ICompute;
import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/8/1.
 */
public class AIDLActivity extends AppCompatActivity {

    private ICompute mService;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = ICompute.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mService = null;
        }
    };
    private Button buttonMain;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        this.textView = (TextView) findViewById(R.id.textView);
        this.buttonMain = (Button) findViewById(R.id.buttonMain);
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int add = mService.add(1, 2);
                    textView.setText("" + add);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle bundle = new Bundle();
        Intent intent = new Intent("cn.steve.ipc.aidl.simple.ComputeService");
        intent.putExtras(bundle);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
}
