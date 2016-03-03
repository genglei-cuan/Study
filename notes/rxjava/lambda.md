# Lambda表达式的认识

## Why 为何出现这种"奇葩的表示方式"

### 匿名内部类

说到Lambda产生的背景得说说匿名内部类。
匿名内部类的产生又牵扯到接口和抽象类。
Java是一门面向对象编程语言。面向对象编程语言使用带有方法的对象封装行为，函数式编程语言使用函数封装行为。但这个相同点并不明显，因为Java的对象往往比较“重量级”：实例化一个类型往往会涉及不同的类，并需要初始化类里的字段和方法。
不过有些Java对象只是对单个函数的封装。例如下面这个典型用例：Java API中定义了一个接口（典型的是回调接口），用户通过提供这个接口的实例来传入指定行为。

一般我们使用匿名内部类的原因就是，我们并不需要专门定义一个类来实现一个接口，因为它只会在调用处被使用一次。


虽然匿名内部类可以减少一些不必要的类继承，但是它带来了另外一个问题，“高度太高了”。太多的模板代码，对于一个接口，我们其实想要使用的，并且真正起到作用的也就是他的方法实现。

所以就出现了匿名函数。

## 什么是Lambda 表达式

lambda表达式就是匿名函数，简化了匿名委托的使用，让代码更加简洁,降低代码的"高度"。



## How 如何使用

### 对比

- 传统的匿名内部类的方式实现

```java
public class LambdaTest {
    interface IPerson {
        void walk();
    }
    public static void main(String[] args) {
        LambdaTest test = new LambdaTest();
        test.get(new IPerson() {
            @Override
            public void walk() {
                System.out.println("I'm walking!");
            }
        });
    }
    void get(IPerson person) {
        person.walk();
    }
}
```
- Lambda方式
```java
public class LambdaTest {
    interface IPerson {
        void walk();
    }
    public static void main(String[] args) {
        LambdaTest test = new LambdaTest();
        test.get(()-> System.out.println("HHHH"));
    }
    void get(IPerson person) {
        person.walk();
    }
}
```
以上的对比的是接口内的参数是无参的，我们再看看有参数的情况
- 传统方式
```java
public class LambdaTest {

    interface IPerson {
        void walk(String name);
    }

    public static void main(String[] args) {
        simple();
    }

    private static void simple() {
        LambdaTest test = new LambdaTest();
        test.get(new IPerson() {
            @Override
            public void walk(String s) {
                System.out.println(s + "walking!");
            }
        });
    }

}
```

- Lambda

```java
public class LambdaTest {

    interface IPerson {
        void walk(String name);
    }

    public static void main(String[] args) {
        lambda();
    }

    private static void lambda() {
        LambdaTest test = new LambdaTest();
        test.get((s) -> System.out.println(s + " is walking "));
    }
}
```

以上对比，明显lambda的表达式更加简洁，但同时也增加了可理解性的难度。





## lambda表达式的返回值
```java
(s) -> System.out.println(s + " is walking ")
name -> name + "55"
```
左侧是表示形参的名字，右边是这个函数的值，返回值，对于第一个是一个命令，也是一种返回值。
对于第二个返回值是name + "55"






## 致谢
* [内部类的介绍](http://www.cnblogs.com/figure9/archive/2014/10/24/4048421.html)
