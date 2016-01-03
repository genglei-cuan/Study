package cn.steve.webview.simple;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.impl.client.AbstractHttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * 发送cookie实现免登陆状态
 *
 * @author Steve
 */
public class HttpCookie extends Thread {

    HttpPost post = new HttpPost();
    List<NameValuePair> list = new ArrayList<NameValuePair>();
    HttpClient client = new DefaultHttpClient();

    private Handler mHandler = null;

    public HttpCookie(Handler mHandler) {
        this.mHandler = mHandler;
    }

    @Override
    public void run() {

        list.add(new BasicNameValuePair("name", "steve"));
        list.add(new BasicNameValuePair("age", "12"));
        try {
            post.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                AbstractHttpClient absclient = (AbstractHttpClient) client;
                List<Cookie> cookies = absclient.getCookieStore().getCookies();
                for (Cookie cookie : cookies) {
                    System.out.println("name==" + cookie.getName());
                    System.out.println("value==" + cookie.getValue());
                    Message message = new Message();
                    message.obj = cookie;
                    mHandler.sendMessage(message);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
