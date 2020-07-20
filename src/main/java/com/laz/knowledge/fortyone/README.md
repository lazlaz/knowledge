# Java如何实现协程

协程（Coroutine）这个词其实有很多叫法，比如有的人喜欢称为纤程（Fiber），或者绿色线程（GreenThread）。其实究其本质，对于协程最直观的解释是线程的线程。虽然读上去有点拗口，但本质上就是这样。

协程的核心在于调度那块由他来负责解决，遇到阻塞操作，立刻放弃掉，并且记录当前栈上的数据，阻塞完后立刻再找一个线程恢复栈并把阻塞的结果放到这个线程上去跑，这样看上去好像跟写同步代码没有任何差别，这整个流程可以称为coroutine，而跑在由coroutine负责调度的线程称为Fiber。

参考

[https://www.w3cschool.cn/java/java-x3pi2oso.html](https://www.w3cschool.cn/java/java-x3pi2oso.html)

[https://www.cnblogs.com/tohxyblog/p/10712798.html](https://www.cnblogs.com/tohxyblog/p/10712798.html)