# View 的工作原理(读艺术探索第四章)

## 初识view和DecorView

## 理解 MeasureSpec
测量的规格描述，记录测量的模式和大小。
生成一个 MeasureSpec 依赖与两个因素，自身的布局和父ViewGroup自身的MeasureSpec。共同生成子view的 MeasureSpec。

## View的工作流程
measure，layout，draw

### measure
onMeasure 中只是调用了 setMeasuredDimension 设置测量出来的大小，而大小一般情况下都是测量的大小，view大小的最终确定是在layout阶段，
几乎所有情况下，测量大小就是最终的大小。
```java
public static int getDefaultSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
        case MeasureSpec.UNSPECIFIED:
            result = size;
            break;
        case MeasureSpec.AT_MOST:
        case MeasureSpec.EXACTLY:
            result = specSize;
            break;
        }
        return result;
    }
```
从这里的代码分析，可以看出。对于直接继承 view 的自定义 view，在自己做测量的时候，需要覆写 onMeasure ，并且定义在 WRAP_CONTENT 下的情况。
因为在 WRAP_CONTENT 的情况下，直接返回的就是测量的结果。而从之前生成测量 MeasureSpec 的过程中，我们可以看出，除了父 ViewGroup 是 UNSPECIFIED 的情况下，
其他的时候，只要子View是 WRAP_CONTENT ，返回的测量模式都是AT_MOST，并且大小就是父 ViewGroup 的大小。


