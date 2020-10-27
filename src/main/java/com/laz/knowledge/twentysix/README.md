# ThreadLocal 使用示例

什么是ThreadLocal变量

ThreadLoal 变量，线程局部变量，同一个 ThreadLocal 所包含的对象，在不同的 Thread 中有不同的副本。这里有几点需要注意：

因为每个 Thread 内有自己的实例副本，且该副本只能由当前 Thread 使用。这是也是 ThreadLocal 命名的由来。
既然每个 Thread 有自己的实例副本，且其它 Thread 不可访问，那就不存在多线程间共享的问题。

[ThreadLocal原理分析与使用场景](https://www.cnblogs.com/luxiaoxun/p/8744826.html)

# Threadlocal内存泄露示例
[内存泄露的原因找到了，罪魁祸首居然是Java TheadLocal](https://juejin.im/post/6887937212780904462?utm_source=gold_browser_extension)