#Fork/Join框架基本使用

------------------------------
ForkJoinPool由Java大师Doug Lea主持编写，它可以将一个大的任务拆分成多个子任务进行并行处理，最后将子任务结果合并成最后的计算结果，并进行输出。本文中对Fork/Join框架的讲解，基于JDK1.8+中的Fork/Join框架实现，参考的Fork/Join框架主要源代码也基于JDK1.8+。