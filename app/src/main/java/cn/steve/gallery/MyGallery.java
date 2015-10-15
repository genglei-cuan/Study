package cn.steve.gallery;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import cn.steve.study.R;

/**
 * Created by Steve on 2015/10/12.
 */
public class MyGallery extends LinearLayout {

    private Context mContext;
    private Gallery gallery;
    private Calendar mCalendar;
    private MyGalleryAdapter myGalleryAdapter;
    private TextView galleryTextViewYearMonth;
    private ArrayList<MyGalleryModel> datas = new ArrayList<MyGalleryModel>();

    //目前真实的时间
    private int realYear = 2015;
    private int realMonth = 10;
    private int realDayOfMonth = 13;
    private int realWeek = 2;
    private int realDayOfYear = 100;

    //当前传入的时间
    private int currentYear = 2015;
    private int currentMonth = 10;
    private int currentDayOfMonth = 13;
    private int currentYearDays = 365;
    private int currentDayOfYear = 100;


    private boolean isUp = true;
    private MyGalleryModel selectedModel = null;

    private int warningPosition = 0;
    //记录滑动选择的位置，目的是为了回滚
    private int warnSelectedPosition = 0;
    private boolean isWarn = false;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
                case 1:
                    //TODO 这里可以调用外部接口。给出超出范围的提示
                    MyGalleryAdapter adapter = (MyGalleryAdapter) gallery.getAdapter();
                    int po = warnSelectedPosition;
                    for (int i = 1; i <= warnSelectedPosition - warningPosition; i++) {
                        --po;
                        gallery.setSelection(po);
                        adapter.setSelectedPosition(po);
                        adapter.notifyDataSetChanged();
                    }
                    break;
            }

        }
    };


    private onSelectedListener monSelectedListener;

    private Runnable mDismissOnScreenControlRunner = new Runnable() {

        @Override
        public void run() {
            if (isUp) {
                if (!isWarn) {
                    monSelectedListener.onSelected(selectedModel);
                } else {
                    //跳转到今天
                    mHandler.obtainMessage(1).sendToTarget();
                }
            }
        }
    };

    public MyGallery(Context context) {
        super(context);
        this.mContext = context;
    }


    public MyGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mCalendar = Calendar.getInstance();

        realYear = mCalendar.get(Calendar.YEAR);
        realMonth = mCalendar.get(Calendar.MONTH);
        realDayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);
        realDayOfYear = mCalendar.get(Calendar.DAY_OF_YEAR);

        myGalleryAdapter = new MyGalleryAdapter(datas, mContext);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.gallery_layout, this);

        galleryTextViewYearMonth = (TextView) view.findViewById(R.id.galleryTextViewYearMonth);
        gallery = (Gallery) view.findViewById(R.id.myGallery);
        gallery.setAdapter(myGalleryAdapter);
        gallery.setUnselectedAlpha(0.3f);
        gallery.setSpacing(40);
        gallery.setSelection(myGalleryAdapter.getCount() - 1);

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > warningPosition) {
                    isWarn = true;
                    warnSelectedPosition = position;
                } else {
                    isWarn = false;
                }
                MyGalleryAdapter adapter = (MyGalleryAdapter) parent.getAdapter();
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetChanged();

                MyGalleryModel model = adapter.getItem(position);
                galleryTextViewYearMonth.setText(model.getYear() + "年" + model.getMonth() + "月");

                //更新前一年的信息
                if (model.getYear() == currentYear && model.getMonth() == 1
                    && model.getDay() == 10) {
                    getData(mCalendar, false);
                    gallery.setSelection(currentYearDays + position);
                }
                selectedModel = model;
                scheduleDismissOnScreenControls(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        gallery.setOnItemSelectedListener(listener);
        gallery.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    isUp = false;
                }
                if (event.getActionMasked() == MotionEvent.ACTION_UP) {
                    isUp = true;
                }
                return false;
            }
        });
        getData(mCalendar, true);
    }

    public void setmCalendar(Calendar mCalendar) {
        getData(mCalendar, true);
    }

    //从当天往前数出一年的数据来，当到达最前面10天的时候，进行上年的更新
    private void getData(Calendar calendar, boolean isClear) {
        if (isClear) {
            datas.clear();
        }

        ArrayList<MyGalleryModel> temps = new ArrayList<MyGalleryModel>();

        int year = 2015;
        int month = 10;
        int day = 12;
        int week = 1;
        int days = 0;

        days = calendar.getMaximum(Calendar.DAY_OF_YEAR);

        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);
        currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        currentDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        currentYearDays = days;

        //back
        //相差的年份
        int delta = realYear - currentYear;
        //当年的所有日期加入
        for (int i = 0; i < delta; i++) {
            int sum = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
            for (int j = currentDayOfYear; j < sum; j++) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH) + 1;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                week = calendar.get(Calendar.DAY_OF_WEEK);
                temps.add(new MyGalleryModel(year, month, day, week));
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
        if (delta > 0) {
            for (int i = 0; i < currentYearDays; i++) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH) + 1;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                week = calendar.get(Calendar.DAY_OF_WEEK);
                temps.add(new MyGalleryModel(year, month, day, week));
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        } else {
            for (int i = currentDayOfYear; i <= currentYearDays; i++) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH) + 1;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                week = calendar.get(Calendar.DAY_OF_WEEK);
                temps.add(new MyGalleryModel(year, month, day, week));
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        //添加进数据，已包含了当天的时间
        datas.addAll(temps);

        //还原至当天
        calendar.set(Calendar.YEAR, currentYear);
        calendar.set(Calendar.MONTH, currentMonth);
        calendar.set(Calendar.DAY_OF_MONTH, currentDayOfMonth);

        temps.clear();
        //pre用以记录下标，还原正确的位置
        int pre = 0;

        for (int i = 0; i < currentDayOfYear - 1; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DAY_OF_MONTH);
            week = calendar.get(Calendar.DAY_OF_WEEK);
            temps.add(new MyGalleryModel(year, month, day, week));
            pre += 1;
        }

        Collections.reverse(temps);

        datas.addAll(0, temps);

        myGalleryAdapter.notifyDataSetChanged();
        //设置应在的位置
        gallery.setSelection(pre);

        warningPosition = pre;

    }

    public void setMonSelectedListener(onSelectedListener monSelectedListener) {
        this.monSelectedListener = monSelectedListener;
    }

    private void scheduleDismissOnScreenControls(int position) {
        mHandler.removeCallbacks(mDismissOnScreenControlRunner);
        mHandler.postDelayed(mDismissOnScreenControlRunner, 500);
    }

    public interface onSelectedListener {

        void onSelected(MyGalleryModel model);
    }

}
