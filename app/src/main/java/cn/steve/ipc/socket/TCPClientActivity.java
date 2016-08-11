package cn.steve.ipc.socket;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/8/11.
 */
public class TCPClientActivity extends AppCompatActivity {


    private static final int MESSAGE_RECEIVE_NEW_MSG = 1;
    private static final int MESSAGE_SOCKET_CONNECTED = 2;
    private static final String TAG = "TCPClientActivity";
    private Socket clientSocket;
    private PrintWriter printWriter;
    private android.widget.Button buttonMain;
    private android.widget.TextView textView;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_RECEIVE_NEW_MSG:
                    textView.setText(textView.getText() + msg.obj.toString());
                    break;
                case MESSAGE_SOCKET_CONNECTED:
                    buttonMain.setEnabled(true);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        this.textView = (TextView) findViewById(R.id.textView);
        this.buttonMain = (Button) findViewById(R.id.buttonMain);
        Intent intent = new Intent(this, TCPServerService.class);
        startService(intent);

        new Thread() {
            @Override
            public void run() {
                super.run();
                connectTCPServer();
            }
        }.start();

        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String h = "Hello";
                printWriter.println(h);
                String msg = "self:" + h + formatDateTime(System.currentTimeMillis());
                String text = textView.getText().toString() + "\n" + msg;
                textView.setText(text);
            }
        });

    }

    @SuppressLint("SimpleDateFormat")
    private String formatDateTime(long time) {
        return new SimpleDateFormat("(HH:mm:ss)").format(new Date(time));
    }

    private void connectTCPServer() {
        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", 8688);
                clientSocket = socket;
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                handler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
                Log.i(TAG, "connectTCPServer: connect success");
            } catch (IOException e) {
                SystemClock.sleep(1000);
                Log.i(TAG, "connectTCPServer: connect tcp server failed ,retry...");
            }
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!TCPClientActivity.this.isFinishing()) {
                String msg = br.readLine();
                Log.i(TAG, "connectTCPServer: receive from server" + msg);
                if (msg != null) {
                    String time = formatDateTime(System.currentTimeMillis());
                    handler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG, "server:" + msg + time).sendToTarget();
                }

            }
            Log.i(TAG, "connectTCPServer: quit");
            printWriter.close();
            br.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
