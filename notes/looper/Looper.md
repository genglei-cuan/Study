# Looper

## 背景

由于android是线程安全的,只能在主线程中更新UI.看到一个要想在子线程中更新UI需要在操作UI之前执行.

```java
 Looper.prepare();
 Looper.loop();
```

进而发现如子线程之在主线程中直接启动的,是可以更新UI的,而如果是在按钮的监听器中启动的,则会报错.

```java
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_button);
        textViewMain = (TextView) findViewById(R.id.textView);
        Button buttonMain = (Button) findViewById(R.id.buttonMain);
        assert buttonMain != null;
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newLooper4Thread();
            }
        });


        //newLooper4Thread();  //start directly

    }

    private void newLooper4Thread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: " + Thread.currentThread().toString());
                textViewMain.setText("大写的服!!!!!!");
            }
        }).start();
    }
```