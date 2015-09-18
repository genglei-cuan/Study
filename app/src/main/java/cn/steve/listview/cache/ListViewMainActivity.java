package cn.steve.listview.cache;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import cn.steve.study.R;

/**
 * 
 * @author 了解更多：http://blog.csdn.net/finddreams
 *
 */
public class ListViewMainActivity extends Activity {

  ListView list;
  LazyAdapter adapter;
  Button b;

  /**
   * 异步加载图片基本思想： 1. 先从内存缓存中获取图片显示（内存缓冲） 2. 获取不到的话从SD卡里获取（SD卡缓冲） 3. 都获取不到的话从网络下载图片并保存到SD卡同时加入内存并显示 4、
   * 采用线程池 5、 内存缓存+文件缓存 6、 内存缓存中网上很多是采用SoftReference来防止堆溢出，这儿严格限制只能使用最大JVM内存的1/4 7、
   * 对下载的图片进行按比例缩放，以减少内存的消耗
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activitylazylist_main);
    list = (ListView) findViewById(R.id.list);
    adapter = new LazyAdapter(this, mStrings);
    list.setAdapter(adapter);
    b = (Button) findViewById(R.id.button1);
    b.setOnClickListener(listener);
  }

  @Override
  public void onDestroy() {
    list.setAdapter(null);
    super.onDestroy();
  }

  public OnClickListener listener = new OnClickListener() {
    @Override
    public void onClick(View arg0) {
      adapter.imageLoader.clearCache();
      adapter.notifyDataSetChanged();
    }
  };


  private String[] mStrings =
      {
          "http://f.hiphotos.baidu.com/image/w%3D230/sign=13c6412ffffaaf5184e386bcbc5594ed/94cad1c8a786c917a2e3bb30cb3d70cf3ac757d3.jpg",
          "http://a.hiphotos.baidu.com/image/pic/item/96dda144ad34598222690c9b0ef431adcaef84dc.jpg",
          "http://g.hiphotos.baidu.com/image/w%3D230/sign=dcf4f1cce2fe9925cb0c6e5304a95ee4/9e3df8dcd100baa12bccaeb24510b912c9fc2ed3.jpg",
          "http://c.hiphotos.baidu.com/image/pic/item/b17eca8065380cd703ba4166a044ad3458828191.jpg",
          "http://e.hiphotos.baidu.com/image/pic/item/4610b912c8fcc3ce2f20203a9045d688d53f20d3.jpg",
          "http://d.hiphotos.baidu.com/image/pic/item/b90e7bec54e736d18cf0650199504fc2d46269dc.jpg",
          "http://d.hiphotos.baidu.com/image/pic/item/ac6eddc451da81cbc2066ac45066d016082431d3.jpg",
          "http://e.hiphotos.baidu.com/image/pic/item/aa18972bd40735fae213a7b09c510fb30e2408d3.jpg",
          "http://e.hiphotos.baidu.com/image/pic/item/aa18972bd40735fae213a7b09c510fb30e2408d3.jpg",
          "http://e.hiphotos.baidu.com/image/pic/item/aa18972bd40735fae213a7b09c510fb30e2408d3.jpg",
          "http://e.hiphotos.baidu.com/image/pic/item/aa18972bd40735fae213a7b09c510fb30e2408d3.jpg",
          "http://e.hiphotos.baidu.com/image/pic/item/aa18972bd40735fae213a7b09c510fb30e2408d3.jpg",
          "http://e.hiphotos.baidu.com/image/pic/item/aa18972bd40735fae213a7b09c510fb30e2408d3.jpg",
          "http://e.hiphotos.baidu.com/image/pic/item/aa18972bd40735fae213a7b09c510fb30e2408d3.jpg",
          "http://e.hiphotos.baidu.com/image/pic/item/aa18972bd40735fae213a7b09c510fb30e2408d3.jpg",
          "http://e.hiphotos.baidu.com/image/pic/item/aa18972bd40735fae213a7b09c510fb30e2408d3.jpg",
          "http://e.hiphotos.baidu.com/image/pic/item/aa18972bd40735fae213a7b09c510fb30e2408d3.jpg",};
}
