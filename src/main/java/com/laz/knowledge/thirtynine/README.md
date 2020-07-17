#Netty bytebuf使用示例

ByteBuf本质是: 一个由不同的索引分别控制读访问和写访问的字节数组。请记住这句话。ByteBuf共有三种模式:   
堆缓冲区模式(Heap Buffer)、直接缓冲区模式(Direct Buffer)和复合缓冲区模式(Composite Buffer)
###堆缓冲区模式(Heap Buffer)
堆缓冲区模式又称为：支撑数组(backing array)。将数据存放在JVM的堆空间，通过将数据存储在数组中实现

堆缓冲的优点: 由于数据存储在Jvm堆中可以快速创建和快速释放，并且提供了数组直接快速访问的方法
堆缓冲的缺点: 每次数据与I/O进行传输时，都需要将数据拷贝到直接缓冲区

###直接缓冲区模式(Direct Buffer)
Direct Buffer属于堆外分配的直接内存，不会占用堆的容量。适用于套接字传输过程，避免了数据从内部缓冲区拷贝到直接缓冲区的过程，性能较好

Direct Buffer的优点: 使用Socket传递数据时性能很好，避免了数据从Jvm堆内存拷贝到直接缓冲区的过程。提高了性能  
Direct Buffer的缺点: 相对于堆缓冲区而言，Direct Buffer分配内存空间和释放更为昂贵  
对于涉及大量I/O的数据读写，建议使用Direct Buffer。而对于用于后端的业务消息编解码模块建议使用Heap Buffer  

###复合缓冲区模式(Composite Buffer)
Composite Buffer是Netty特有的缓冲区。本质上类似于提供一个或多个ByteBuf的组合视图，可以根据需要添加和删除不同类型的ByteBuf。

想要理解Composite Buffer，请记住：它是一个组合视图。它提供一种访问方式让使用者自由的组合多个ByteBuf，避免了拷贝和分配新的缓冲区。  
Composite Buffer不支持访问其支撑数组。因此如果要访问，需要先将内容拷贝到堆内存中，再进行访问

[Netty专栏 （ 三）——— Netty的ByteBuf](https://blog.csdn.net/thinking_fioa/article/details/80795673)
