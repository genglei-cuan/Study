#函数式编程
函数可以当做参数来进行传递。

#Lambada表达式
Lambada产生的背景得说到匿名内部类。匿名内部类的产生原因是来自于接口和抽象类。



##传统的匿名内部类方式：

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

##用lambda简写：
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
## 用lambda含有参数的简写：
```java
LambdaTest test = new LambdaTest();
test.get((s) -> System.out.println(s + " is walking "));
```


