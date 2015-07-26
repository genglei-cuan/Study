package cn.steve.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import cn.steve.study.R;


/**
 * AsyncTask的一个demo，仿造下载的进度条
 * 
 * @author steve.yan
 *
 */
public class AsyncTaskMainActivity extends Activity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    super.setContentView(R.layout.activity_synctask);

    new AsyncTask<Void, Integer, Void>() {
      ProgressDialog progressdialog = null;

      @Override
      protected void onPreExecute() {
        super.onPreExecute();
        progressdialog = new ProgressDialog(AsyncTaskMainActivity.this, 0);
        progressdialog.setMax(100);
        progressdialog.setTitle("Downloading");
        progressdialog.setMessage("");
        progressdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressdialog.show();
      }

      @Override
      protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressdialog.setProgress(values[0]);
        progressdialog.setMessage("当前下载进度：" + values[0] + "%");
      }


      @Override
      protected Void doInBackground(Void... params) {
        try {
          while (true) {
            for (int i = 0; i < 101; i++) {
              Thread.sleep(100);
              publishProgress(i);
            }
            return null;
          }
        } catch (Exception e) {
          return null;
        }
      }

      @Override
      protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        progressdialog.dismiss();
      }
    }.execute();
  }
}
