package cn.steve.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import cn.steve.study.R;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by
 *
 * @author Steve on 2016/03/20.
 */
public class RetrofitRxJavaActivity extends AppCompatActivity {

  static final String API = "https://api.github.com";
  private TextView retrofitTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_retrofit);
    this.retrofitTextView = (TextView) findViewById(R.id.retrofitTextView);
    //1. 创建Retrofit2的实例，并设置BaseUrl和Gson转换。
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(API)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(new OkHttpClient())
        .build();
    //2. 创建请求服务，并为网络请求方法设置参数
    GitHubService gitHubService = retrofit.create(GitHubService.class);
    // Call是Retrofit中重要的一个概念，代表被封装成单个请求/响应的交互行为
    Observable<List<Contributor>> listObservable = gitHubService.contributorsObservable("square", "retrofit");
    listObservable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<List<Contributor>>() {
          @Override
          public void call(List<Contributor> contributors) {
            retrofitTextView.setText(contributors.get(0).toString());
          }
        });

  }
}
