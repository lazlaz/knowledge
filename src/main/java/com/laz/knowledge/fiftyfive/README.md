# java debug 体系-jdi 

  JDI属于JPDA中最上层接口。定义了调试器（Debugger）所需要的一些调试接口。基于这些接口，调试器可以及时地了解目标虚拟机的状态，例如查看目标虚拟机上有哪些类和实例等。另外，调试者还可以控制目标虚拟机的执行，例如挂起和恢复目标虚拟机上的线程，设置断点等。

运行模式：
切换到class目录下
>>java -classpath "%JAVA_HOME%/lib/tools.jar;." com.laz.knowledge.fiftyfive.SimpleDebugger com.laz.knowledge.fiftyfive.HelloWorld

>>
VM started  
Class com.laz.knowledge.fiftyfive.HelloWorld is already prepared  
Reach line 10 of HelloWorld  
The local variable str at line 10 is Hello world! of java.lang.String  
>>

参考：
[java debug 体系-jdi](https://www.jianshu.com/p/e641ea08a2fc)