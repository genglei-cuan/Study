package cn.steve.retrofit;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * 定义请求的服务
 *
 *
 * Created by yantinggeng on 2015/12/29.
 */
public interface GitHubService {

  /**
   * 动态Url
   *
   * GET 里是个URL，花括号里的类似于占位符，到时候进行替换就行
   *
   * @param owners 第一个参数
   * @param repo   第二个参数
   * @return 将请求的数据封装成list形式返回
   */
  @GET("/repos/{owner}/{repo}/contributors")
  Call<List<Contributor>> contributors(
      @Path("owner") String owners,
      @Path("repo") String repo
  );


  @GET("/repos/{owner}/{repo}/contributors")
  Observable<List<Contributor>> contributorsObservable(
      @Path("owner") String owners,
      @Path("repo") String repo
  );


}
