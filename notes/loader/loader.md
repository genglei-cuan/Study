#[Android动态加载基础 ClassLoader工作机制](http://segmentfault.com/a/1190000004062880)

##加载器的个数
一个运行中的APP不仅只有一个类加载器。其实，在Android系统启动的时候会创建一个Boot类型的ClassLoader实例，用于加载一些系统Framework层级需要的类，
我们的Android应用里也需要用到一些系统的类，所以APP启动的时候也会把这个Boot类型的ClassLoader传进来。
此外，APP也有自己的类，这些类保存在APK的dex文件里面，所以APP启动的时候，也会创建一个自己的ClassLoader实例，用于加载自己dex文件中的类。
所以，一个APP运行的APP内至少有两个类加载器。

##ClassLoader双亲代理模型加载类的特点和作用
创建一个ClassLoader实例的时候，需要使用一个现有的ClassLoader实例作为新创建的实例的Parent。这样一来，
一个Android应用，甚至整个Android系统里所有的ClassLoader实例都会被一棵树关联起来，这也是ClassLoader的双亲代理模型（Parent-Delegation Model）的特点。
###特点
从源码中我们也可以看出，loadClass方法在加载一个类的实例的时候，

1. 会先查询当前ClassLoader实例是否加载过此类，有就返回；
1. 如果没有。查询Parent是否已经加载过此类，如果已经加载过，就直接返回Parent加载的类；
1. 如果继承路线上的ClassLoader都没有加载，才由Child执行类的加载工作；

这样做有个明显的特点，如果一个类被位于树根的ClassLoader加载过，那么在以后整个系统的生命周期内，这个类永远不会被重新加载。

### 作用
首先是共享功能，一些Framework层级的类一旦被顶层的ClassLoader加载过就缓存在内存里面，以后任何地方用到都不需要重新加载。
除此之外还有隔离功能，不同继承路线上的ClassLoader加载的类肯定不是同一个类，
这样的限制避免了用户自己的代码冒充核心类库的类访问核心类库包可见成员的情况。
这也好理解，一些系统层级的类会在系统初始化的时候被加载，比如java.lang.String，
如果在一个应用里面能够简单地用自定义的String类把这个系统的String类给替换掉，那将会有严重的安全问题。

##使用ClassLoader一些需要注意的问题
如果你希望通过动态加载的方式，加载一个新版本的dex文件，使用里面的新类替换原有的旧类，从而修复原有类的BUG，
那么你必须保证在加载新类的时候，旧类还没有被加载，因为如果已经加载过旧类，那么ClassLoader会一直优先使用旧类。
在Java中，只有当两个实例的类名、包名以及加载其的ClassLoader都相同，才会被认为是同一种类型。上面分别加载的新类和旧类，
虽然包名和类名都完全一样，但是由于加载的ClassLoader不同，所以并不是同一种类型，在实际使用中可能会出现类型不符异常。
-- 同一个Class = 相同的 ClassName + PackageName + ClassLoader

##DexClassLoader 和 PathClassLoader
ClassLoader是一个抽象类，实际开发过程中，我们一般是使用其具体的子类DexClassLoader、PathClassLoader这些类加载器来加载类的，
它们的不同之处是：
1. DexClassLoader可以加载jar/apk/dex，可以从SD卡中加载未安装的apk；
1. PathClassLoader只能加载系统中已经安装过的apk；


