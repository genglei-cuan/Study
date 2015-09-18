
package cn.steve.webview;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Environment;

/**
 * 自定义的下载线程
 * 
 * @author Steve
 */
public class HttpDownload extends Thread {
    String mURL;

    public HttpDownload(String url) {
        this.mURL = url;
    }

    @Override
    public void run() {
        System.out.println("start download----");
        try {
            URL httpURL = new URL(mURL);
            HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);

            InputStream in = conn.getInputStream();
            File downloadFile;
            File sdFile;
            FileOutputStream out = null;
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                downloadFile = Environment.getExternalStorageDirectory();
                sdFile = new File(downloadFile, "test.apk");
                out = new FileOutputStream(sdFile);
            }
            byte[] b = new byte[6 * 1024];

            int len;
            while ((len = in.read(b)) != -1) {
                if (out != null) {
                    out.write(b, 0, len);
                }
            }

            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            System.out.println("download success");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
