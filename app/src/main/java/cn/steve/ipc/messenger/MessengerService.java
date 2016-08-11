package cn.steve.ipc.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerService extends Service {

    public static final int MSG_FROM_CLIENT = 11;
    public static final int MSG_FROM_SERVER = 22;
    private static final String TAG = "MessengerService";
    private final Messenger messenger = new Messenger(new ServerMessengerHandler());

    public MessengerService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    private static class ServerMessengerHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FROM_CLIENT:
                    Bundle data = msg.getData();
                    Log.i(TAG, "handleMessage: receive from client" + data.getString("msg"));

                    //为了能够给客户端进行回应，获取发送该条消息的客户端
                    Messenger client = msg.replyTo;
                    Message replyMessage = Message.obtain(null, MSG_FROM_SERVER);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply", "hello , i have received your msg");
                    replyMessage.setData(bundle);
                    try {
                        client.send(replyMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}
