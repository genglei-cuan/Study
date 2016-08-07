# Gradle 笔记

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


