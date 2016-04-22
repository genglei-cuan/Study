# 介绍activity和fragment之间的关系

遇到一个问题，在fragment中定义的一些异步回调任务，可能在fragment已经消亡的时候被执行。
这时候，getactivity就是空。

- 尝试一：

由A->B,通过A跳转到B，B中只有一个fragment，在fragment中定义一个三秒延迟的任务，实验结果是正常运行，并未出现空指针异常。

```java
    private TextView relationTextView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println("hanlder");
            relationTextView.setText("Handler");
        }
    };
    public RelationFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_relation, container, false);
        relationTextView = (TextView) inflate.findViewById(R.id.relationTextView);
        handler.sendEmptyMessageDelayed(0, 3000);
        return inflate;
    }
```


- 尝试二:
```java
       private Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d(TAG, "handleMessage: " + String.valueOf(getActivity() == null));
                Log.d(TAG, "handler");
                relationTextView.setText("Handler");
            }
        };
```
输出的内容是true和handler，表明getActivity的确为空，但是这时候的任务还是被执行了，也未出现空指针异常。


- 尝试三:执行三秒延迟的异步任务

```java
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "handleMessage: " + String.valueOf(getActivity() == null));
                Log.d(TAG, "handler");
                relationTextView.setText("Handler");
            }
        }, 3000);
```

执行结果和以上一样。依旧没有任何问题


- 尝试四：不通过handler执行异步任务

```java
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_relation, container, false);
        relationTextView = (TextView) inflate.findViewById(R.id.relationTextView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "handleMessage: " + String.valueOf(getActivity() == null));
                Log.d(TAG, "handler");
                relationTextView.setText("Handler");
            }
        }).start();
        return inflate;
    }
```
执行结果和以上一样。依旧没有任何问题






# 额外记录原因
1. window通过activity，view等显示。
1. 当window进行attach的时候，会将window的信息记录。
1. window对view进行添加的时候会记录信息
1. 当删除的时候会制空view的AttachInfo
1. 当view需要进行invalidate的时候，就会不能通过
1.



# 待看
activity的destroy