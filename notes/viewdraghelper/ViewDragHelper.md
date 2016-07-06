# ViewDragHelper

## ViewDragHelper.Callback 回调方法介绍

1. clampViewPositionHorizontal();
此方法是用来控制水平方向移动的范围，每次调用
    拿到当前的left值跟DragLayout的根布局的paddingleft值做比较，取两者中大的值，这样做是为了防止子view左边滑出根布局的左边界。
    拿left和padding left中的较大值跟可滑动范围的右边界（根布局宽度减去子view的宽度）作比较，取较小值，这样做是为了防止子view右边滑出根布局右边界。
clampViewPositionVertical方法与此类似。

2. getViewVerticalDragRange
范围值在最初测量摆放子view时，在onLayout方法就已经确定了。

3. tryCaptureView
确定当前子view是否可拖动

4. onViewPositionChanged
该方法在子view位置发生改变时都会被调用，可以在这个方法中做一些拖动过程中渐变的动画等操作

5. onViewReleased
该方法在手势拖动释放的时候被调用，可以在这里设置子View预期到达的位置，如果人为的手势拖动没有到达预期位置，
我们可以让子View在人为的拖动结束后，再自动关的滑动到指定位置.

6. onEdgeTouched
触碰到边界时就会被调用.

7. onEdgeDragStarted
当人为的从边界拖动（此时并没有拖动子view）时，可以选择关联某一个子view，实现只要从边界拖动，
不管是否触碰到子view，都能控制子view一起拖动的效果

8.




[鸿洋](http://blog.csdn.net/lmj623565791/article/details/46858663)