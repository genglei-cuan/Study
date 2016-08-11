package cn.steve.ipc.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/8/11.
 */
public class MessengerActivity extends AppCompatActivity {

    private static final String TAG = "MessengerActivity";
    private Messenger handleFromServer = new Messenger(new ClientMessageHandler());

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //负责发送消息
            Messenger messenger = new Messenger(iBinder);

            Message msg = Message.obtain(null, MessengerService.MSG_FROM_CLIENT);
            Bundle data = new Bundle();
            data.putString("msg", "hello ,this is from client ");
            //指定消息回执的处理者
            msg.replyTo = handleFromServer;

            msg.setData(data);
            try {
                for (int i = 0; i < 10; i++) {
                    //向服务端发送消息
                    messenger.send(msg);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);

        Button buttonMain = (Button) findViewById(R.id.buttonMain);

        assert buttonMain != null;
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MessengerActivity.this, MessengerService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
            }
        });
    }


    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }

    private static class ClientMessageHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessengerService.MSG_FROM_SERVER:
                    Log.i(TAG, "receive from server" + msg.getData().getString("reply"));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

}
