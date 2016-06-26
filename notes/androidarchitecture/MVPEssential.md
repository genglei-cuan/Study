# MVP

根据官方给出的 MVP demo做的笔记。

## View:
只是做些UI上的展示和用户之间进行的交互。一般activity和fragment就是充当view

## Model:
这里的model不是一个Javabean,他是数据源，数据的提供层。承担了数据的存储和读取操作，也应该承担网络请求等操作。由于这层的存在，对外屏蔽了数据源，也屏蔽了数据获取的细节。

## Presenter:
作为view层和model层之间的桥梁，将从model层获取的数据交给view层展示在界面上；
view层相应用户的操作，调用presenter做出相应，presenter处理完成之后，调用model进行存储或者另做他用。

## 目的,效果:
view层只负责了展示，model层只负责数据，presenter起桥梁作用，使得view和model解耦了。
view层应该不存在任何业务逻辑。

## 代码分析
以官方的TODO-MVP为例。

###　Model层
以接口的形式声明几个对外提供的方法，并且定义一些回调，而后这部分将由presenter层来实现，这样就实现了model层被presenter调用，而后，model层也能回调presenter层。

## 总结:
view最简单，只有数据显示。
presenter最主要是处理业务逻辑。
model提供数据操作的方法。
