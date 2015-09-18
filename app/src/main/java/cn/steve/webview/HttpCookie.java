
package cn.steve.webview;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.Handler;
import android.os.Message;

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
