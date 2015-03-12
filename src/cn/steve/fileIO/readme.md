所有的Android设备都有两个文件存储区域："internal" 与 "external" 存储。
 那两个名称来自与早先的Android系统中，那个时候大多数的设备都内置了不可变的内存（internal storage)，
 然后再加上一个类似SD card（external storage）这样可以卸载的存储部件。
 后来有一些设备把"internal" 与 "external" 的部分都做成不可卸载的内置存储了，
 虽然如此，但是这一整块还是从逻辑上有被划分为"internal"与"external"的。
 只是现在不再以是否可以卸载来区分了。 
 下面列出了两者的区别：Internal storage:总是可用的这里的文件默认是只能被你的app所访问的。
 当用户卸载你的app的时候，系统会把internal里面的相关文件都清除干净。
 Internal是在你想确保不被用户与其他app所访问的最佳存储区域。
 External storage:并不总是可用的，因为用户可以选择把这部分作为USB存储模式，这样就不可以访问了。
 是大家都可以访问的，因此保存到这里的文件是失去访问控制权限的。
 当用户卸载你的app时，系统仅仅会删除external根目录（getExternalFilesDir()）下的相关文件。
 External是在你不需要严格的访问权限并且你希望这些文件能够被其他app所共享或者是允许用户通过电脑访问时的最佳存储区域。
 Tip:尽管app是默认被安装到internal storage的，你还是可以通过在程序的manifest文件中声明android:installLocation属性来指定程序也可以被安装到external storage。当某个程序的安装文件很大，用户会倾向这个程序能够提供安装到external storage的选项。
 更多安装信息，请参考App Install Location。