#Java 指令重排

[指令重排](https://www.cnblogs.com/tuhooo/p/7921651.html)

[深入理解happens-before和as-if-serial语义](https://www.jb51.net/article/161668.htm)
as-if-serial语义的意思指：
不管怎么重排序（编译器和处理器为了提高并行度），（单线程）程序的执行结果不能被改变。编译器，runtime 和处理器都必须遵守as-if-serial语义。为了遵守as-if-serial语义，编译器和处理器不会对存在数据依赖关系的操作做重排序，因为这种重排序会改变执行结果。但是，如果操作之间不存在数据依赖关系，