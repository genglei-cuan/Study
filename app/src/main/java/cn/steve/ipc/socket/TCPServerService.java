package cn.steve.ipc.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServerService extends Service {

    private static final String TAG = "TCPServerService";
    private boolean isDestroyed = false;
    private String[] tips = new String[]{"A", "B", "C", "D", "E"};

    public TCPServerService() {
    }

    @Override
    public void onCreate() {
        new Thread(new TCPServer()).start();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        isDestroyed = true;
        super.onDestroy();
    }

    private void responseClient(Socket client) throws Exception {
        //用于接收客户端消息
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        //用于向客户端发送消息
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);

        out.println("Welcom to chat room ");

        while (!isDestroyed) {
            String str = in.readLine();
            if (str == null) {
                //断开
                break;
            }
            Log.i(TAG, "responseClient: " + str);
            int i = new Random().nextInt(tips.length);
            String msg = tips[i];
            out.println("server:" + msg);
            Log.i(TAG, "responseClient: " + msg);
        }
        Log.i(TAG, "responseClient: quit");
        out.close();
        in.close();
        client.close();
    }

    // TCP 服务
    private class TCPServer implements Runnable {

        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            while (!isDestroyed) {
                try {
                    final Socket client = serverSocket.accept();
                    Log.i(TAG, "accept");
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                responseClient(client);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
