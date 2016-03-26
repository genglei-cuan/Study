package cn.steve.zxing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import cn.steve.study.R;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import steve.cn.mylib.qrcode.QRCodeUtil;

/**
 * Created by yantinggeng on 2016/3/16.
 */
public class ZxingActivity extends AppCompatActivity {

  private ImageView prescaleiamgeview;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_imageview);
    this.prescaleiamgeview = (ImageView) findViewById(R.id.prescale_iamgeview);
    getCode();
  }


  void getCode() {
    Observable.create(new Observable.OnSubscribe<Bitmap>() {
      @Override
      public void call(Subscriber<? super Bitmap> subscriber) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.codelogo);
        subscriber.onNext(QRCodeUtil.createQRImage("http://www.baidu.com", 800, 800, bitmap));
        subscriber.onCompleted();
      }
    }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<Bitmap>() {
          @Override
          public void call(Bitmap bitmap) {
            prescaleiamgeview.setImageBitmap(bitmap);
          }
        });
  }


}
