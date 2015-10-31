package cn.steve.newsImoocListView;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import cn.steve.study.R;


/**
 * Created by Steve on 2015/7/5.
 */
public class ImageLoader {

    //创建cache
    private LruCache<String, Bitmap> mCaches;
    private ListView mListview;
    private Set<NewsAsyncTask> mTask;
    //------------------------------------------------------------------------------------------------
    private ImageView mImageView;
    private String mURL;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mImageView.getTag().equals(mURL)) {
                mImageView.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };

    @TargetApi(14)
    public ImageLoader(ListView listView) {
        mListview = listView;
        mTask = new HashSet<>();
        //获取最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;
        mCaches = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //在每次存入缓存的时候被调用，将bitmap的实际大小返回
                return value.getByteCount();
            }
        };
    }

    //增加到缓存
    public void addBitmapToCache(String url, Bitmap bitmap) {
        if (getBitmapFromCache(url) == null) {
            mCaches.put(url, bitmap);
        }
    }

    //从缓存冲获取数据
    public Bitmap getBitmapFromCache(String url) {
        return mCaches.get(url);
    }

    public void showImageByThread(ImageView imageview, final String url) {
        mImageView = imageview;
        mURL = url;
        new Thread() {
            @Override
            public void run() {
                super.run();
                Bitmap bitmap = getBitmapFromURL(url);
                Message message = Message.obtain();
                message.obj = bitmap;
                mHandler.sendMessage(message);
            }
        }.start();
    }

    //以下是用异步任务完成--------------------------------------------------------------------------------
    public Bitmap getBitmapFromURL(String urlString) {
        Bitmap bitmap = null;
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void showImageByAsyncTask(ImageView imageview, String url) {
        //从缓存中取出对应的图片
        Bitmap bitmap = getBitmapFromCache(url);
        //假如缓存中没有，则必须去网络下载
        if (bitmap == null) {
//      new NewsAsyncTask(url).execute(url);
            imageview.setImageResource(R.drawable.ic_launcher);
        } else {
            imageview.setImageBitmap(bitmap);
        }
    }


    //用来加载从star有到end的所有图片
    public void loadImages(int start, int end) {
        for (int i = start; i < end; i++) {
            String url = NewsAdapter.URLS[i];
            //从缓存中取出对应的图片
            Bitmap bitmap = getBitmapFromCache(url);
            //假如缓存中没有，则必须去网络下载
            if (bitmap == null) {
                NewsAsyncTask task = new NewsAsyncTask(url);
                task.execute(url);
                mTask.add(task);
            } else {
                ImageView imageView = (ImageView) mListview.findViewWithTag(url);
                if (imageView == null) {
                    Log.i("steve", url);
                }
                if (imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }

    public void cancelAllTask() {
        if (mTask != null) {
            for (NewsAsyncTask task : mTask) {
                task.cancel(false);
            }
        }
    }

    private class NewsAsyncTask extends AsyncTask<String, Void, Bitmap> {

        //    private ImageView mImageView;
        private String mURL;

        public NewsAsyncTask(String url) {
//      mImageView = imageview;
            mURL = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            //从网络获取图片
            Bitmap bitmap = getBitmapFromURL(url);
            if (bitmap != null) {
                //将不在缓存中的图片加入缓存
                addBitmapToCache(url, bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imageView = (ImageView) mListview.findViewWithTag(mURL);
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            mTask.remove(this);
        }
    }

}
