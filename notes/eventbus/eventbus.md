#EventBus note 
##Compare with the version 2.4
in the version 2.4 ,the name of evey method which can receive the event must begin with 'onEvent'.
in this version 3.0,that is unnessary ,you can specify the thread by inject and the name is by easy .
But the method must be public.


----
such as:

```java
@Subscribe(threadMode = ThreadMode.MainThread) //define the method run on the main thread
@Subscribe(threadMode = ThreadMode.BackgroundThread) //define this method run on the work thread
```