# Java 几种OutOfMemory错误示例


### HeapOutOfMemory 
堆溢出 情况多见于对象过多，存在多余引用，使对象未及时释放

### Young OutOfMemory
设置XX：MaxTenuringThreshold为一个很大的值  
使对象无法及时的移动到年老代中，导致年轻代内存溢出

### MethodArea OutOfMemory
在经常动态生成大量Class的应用中，需要特别注意类的回收状况。这类场景除了上面提到的程序使用了CGLib字节码增强和动态语言  
之外，常见的还有：大量JSP或动态产生JSP文件的应用（JSP第一次运行时需要编译为Java类）、基于OSGi的应用（即使是同一个类文  
件，被不同的加载器加载也会视为不同的类）等。

### ConstantPool OutOfMemory
一般来说是不可能的，只有项目启动方法区内存很小或者项目中的静态变量极其多时才会发生

### DirectMemory OutOfMemory
堆外内存溢出 一般与nio有关

### Stack OutOfMemory Stack OverFlow



参考：

[https://blog.csdn.net/youanyyou/article/details/90624308](https://blog.csdn.net/youanyyou/article/details/90624308)

[https://blog.csdn.net/junranhuigu/article/details/50625161](https://blog.csdn.net/junranhuigu/article/details/50625161)