# Annotation

## Annotation 分类

1. 标准 Annotation
   包括 Override, Deprecated, SuppressWarnings，是java自带的几个注解，他们由编译器来识别，不会进行编译，不影响代码运行。
2. 元 Annotation
   @Retention, @Target, @Inherited, @Documented，它们是用来定义 Annotation 的 Annotation。也就是当我们要自定义注解时，需要使用它们。
3. 自定义 Annotation
   自定义的Annotation。

### 自定义的注解也分为三类，通过元Annotation - @Retention 定义：

>* @Retention(RetentionPolicy.SOURCE)

    源码时注解，一般用来作为编译器标记。如Override, Deprecated, SuppressWarnings。

>* @Retention(RetentionPolicy.RUNTIME)

    运行时注解，在运行时通过反射去识别的注解，这种注解最大的缺点就是反射消耗性能。

>* @Retention(RetentionPolicy.CLASS)

    编译时注解，在编译时被识别并处理的注解，相当于自动生成代码，没有反射，和正常的手写代码无二。

## Annotation 的工作原理

APT(Annotation Processing Tool)
