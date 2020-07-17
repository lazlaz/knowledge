#Java NIO Transfer零拷贝与普通复制测试对比

在 NIO 中,如果其中一个Channel是FileChannel,则可以利用transferTo()和transferFrom()两个API进行高效的数据传输.

其底层原理是利用DMA技术减少内核缓冲区和应用程序缓冲区的数据拷贝来提高效率.

注意这两个API的具体实现是不做完全保证的,也就是说没有规定是否用DMA技术.具体参见API注释.