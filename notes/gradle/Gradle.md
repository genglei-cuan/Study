# [Gradle 笔记](http://blog.csdn.net/lzyzsd/article/details/46935063)

## Gradle task

- assemble 
集成的意思，该任务下包含了所有的打包相关的任务，包括java中打的jar包，还是android打的apk。

- check
该任务是所有的验证相关的任务，比如运行测试的任务。

- build
该任务包含了assemble和check，就是说进行检查，然后进行打包集成。

- clean
该任务是清空项目中的所有输出，删除assemble任务打的包。

assemble，check，build，这三个任务其实并不做任何事情，只是一个钩子，真正的操作在插件里定义了。
这样，其实就不需要关心执行的是java构建任务还是android构建任务。




## Closure(闭包)

Closure,就是个代码块，可以接收参数，可以被赋值给变量。这段代码是在调用的生活执行，而不是在创建的时候。

## 把closure当做参数传递

1. 如果方法只接收一个参数，且参数是closure的方法： myMethod(myClosure)
2. 如果方法只接收一个参数，括号可以省略： myMethod myClosure
3. 可以使用内联的closure： myMethod {println ‘Hello World’}
4. 接收两个参数的方法： myMethod(arg1, myClosure)
5. 和4类似，单数closure是内联的： myMethod(arg1, { println ‘Hello World’ })
6. 如果最后一个参数是closure，它可以从小括号从拿出来： myMethod(arg1) { println ‘Hello World’ }


## project

` 构建脚本顶层的语句块都会被委托给project的实例。 `

` script block就是只接收closure作为参数的方法 `



