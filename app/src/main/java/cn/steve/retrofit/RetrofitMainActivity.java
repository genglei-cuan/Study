package cn.steve.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.util.List;

import cn.steve.study.R;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yantinggeng on 2015/12/28.
 */
public class RetrofitMainActivity extends AppCompatActivity {

    static final String API = "https://api.github.com";
    private static final String TAG = "RetrofitMainActivity";
    private android.widget.TextView retrofitTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        this.retrofitTextView = (TextView) findViewById(R.id.retrofitTextView);
        //1. 创建Retrofit2的实例，并设置BaseUrl和Gson转换。
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(new OkHttpClient())
            .build();
        //2. 创建请求服务，并为网络请求方法设置参数
        GitHubService gitHubService = retrofit.create(GitHubService.class);
        // Call是Retrofit中重要的一个概念，代表被封装成单个请求/响应的交互行为
        final Call<List<Contributor>> call = gitHubService.contributors("square", "retrofit");
        //3. 请求网络，并获取响应

        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    //execute（同步）或者enqueue（异步）方法，发送请求到网络服务器，并返回一个响应（Response）。
                    Response<List<Contributor>> response = call.execute();
                    subscriber.onNext(response.body().toString());
                    //因为call只能被执行一次，可以clone一个，再重新执行
                    Call<List<Contributor>> cloneCall = call.clone();
                    cloneCall.enqueue(new Callback<List<Contributor>>() {
                        @Override
                        public void onResponse(Response<List<Contributor>> response,
                                               Retrofit retrofit) {
                            Log.e(TAG, "onResponse:"+ response.body().toString());
                        }
                        @Override
                        public void onFailure(Throwable t) {
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Action1 onNext = new Action1<String>() {
            @Override
            public void call(String o) {
                retrofitTextView.setText(o);
            }
        };

        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onNext);
    }
}
