
package cn.steve.fileIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import cn.steve.study.R;

public class MainActivity extends Activity {
    public static final int SET_RESULT = 1;
    public static final int DIALOG_SHOW = 2;
    public static final int DIALOG_DISMISS = 3;
    public static final int FILE_OUTPUT = 4;

    URL mURL = null;
    HttpURLConnection mHttpURLConnection = null;
    InputStreamReader mInputStreamReader = null;
    Button mButton = null;
    TextView mTextView = null;
    ProgressDialog mProgressDialog = null;
    MyHandler mHandler = null;
    Thread mThread = null;
    String content = null;

    static class MyHandler extends Handler {
        private WeakReference<MainActivity> mOuter;

        public MyHandler(MainActivity activity) {
            // 弱引用
            mOuter = new WeakReference<MainActivity>(activity);
        }

        public void handleMessage(Message msg) {
            MainActivity mq = mOuter.get();
            switch (msg.what) {
                case SET_RESULT:
                    String string = msg.obj.toString();
                    mq.content = string;
                    mq.mTextView.setText(string);
                    this.obtainMessage(FILE_OUTPUT).sendToTarget();
                    break;
                case DIALOG_SHOW:
                    if (mq.isFinishing()) {
                        break;
                    }
                    mq.mProgressDialog = new ProgressDialog(mq);
                    mq.mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    mq.mProgressDialog.setTitle("提示");
                    mq.mProgressDialog.setIcon(R.drawable.customer_icon);
                    mq.mProgressDialog.setCancelable(false);
                    mq.mProgressDialog.show();
                    break;
                case DIALOG_DISMISS:
                    mq.mProgressDialog.cancel();
                    break;
                case FILE_OUTPUT:
                    mq.mThread.start();
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_io);
        mHandler = new MyHandler(MainActivity.this);
        findviews();
    }

    private void findviews() {
        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.tv);
        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Integer, Integer, String>() {
                    protected void onPreExecute() {
                        MainActivity.this.mHandler.obtainMessage(DIALOG_SHOW).sendToTarget();
                    };

                    @Override
                    protected String doInBackground(Integer... params) {
                        MainActivity.this.mThread = new Thread(new BackgroundRunnable());
                        String s = getData(params[0]);
                        return s;
                    }

                    protected void onPostExecute(String result) {
                        MainActivity.this.mHandler.obtainMessage(SET_RESULT, result).sendToTarget();
                    };
                }.execute(1);
            }
        });
    }

    // 根据账号下载数据
    public String getData(int user_id) {
        String result = null;
        try {
            mURL = new URL("http://10.0.3.246:8080/bookstore/getbooks.jsp");
            mHttpURLConnection = (HttpURLConnection) mURL.openConnection();
            mInputStreamReader = new InputStreamReader(mHttpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(mInputStreamReader);
            StringBuffer strBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                strBuffer.append(line);
            }
            result = strBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mHttpURLConnection != null) {
                mHttpURLConnection.disconnect();
            }
            if (mInputStreamReader != null) {
                try {
                    mInputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    // 单独的一个线程可以处理额外的事情
    class BackgroundRunnable implements Runnable {
        public void run() {
            FileUtil fileUtil = new FileUtil(MainActivity.this);
            boolean is_out_success = fileUtil.outputFile("test.text", MainActivity.this.content,
                    true);
            try {
                System.out.println(fileUtil.readFile("test.text", true));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (is_out_success) {
                MainActivity.this.mHandler.obtainMessage(DIALOG_DISMISS).sendToTarget();
            }
        }
    }
}
