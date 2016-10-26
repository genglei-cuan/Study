package cn.steve.dateCalendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.steve.dateCalendar.basic.BaseDatePriceAdapter;
import cn.steve.dateCalendar.basic.DatePickerView;
import cn.steve.study.R;
import rx.Subscriber;

/**
 * Created by yantinggeng on 2016/10/20.
 */

public class DatePickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datecalendar);
        final DatePickerView datePickerView = (DatePickerView) findViewById(R.id.daypicker);
        ArrayMap<String, DatePriceVO> datas = new ArrayMap<>();

        Calendar cal = Calendar.getInstance();

        for (int i = 0; i < 100; i++) {
            DatePriceVO vo = new DatePriceVO();
            String date = formatDate(cal);
            vo.setDate(date);
            vo.setPrice("¥" + i);
            vo.setStock(i);
            datas.put(date, vo);
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        DatePriceAdapterBuilder builder = new DatePriceAdapterBuilder();
        builder.withSelected("2016-11-11").getDayAdapter(datas, new Subscriber<BaseDatePriceAdapter>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseDatePriceAdapter baseDatePriceAdapter) {
                datePickerView.setAdapter(baseDatePriceAdapter);
            }
        });

    }


    private String formatDate(Calendar cal) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = cal.getTime();
        return sdf.format(date);
    }
}
