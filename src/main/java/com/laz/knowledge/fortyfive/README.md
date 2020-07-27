# Java 对象的四种引用：强引用、软引用、弱引用和虚引用
### 强引用（Strong Reference）
强引用表示一个对象处在【有用，必须】的状态，是使用最普遍的引用。如果一个对象具有强引用，那么垃圾回收器绝不会回收它。就算在内存空间不足的情况下，Java虚拟机宁可抛出OutOfMemoryError错误，使程序异常终止，也不会通过回收具有强引用的对象来解决内存不足的问题。  

	Student student = new Student(); // 这就是强引用  

后面的软引用、弱引用和虚引用都是基于强引用的，都需要强引用的对象。意思就是说，必须先有强引用的对象，然后在这个强引用的对象上做文章，通过一定操作把它变成软引用、弱引用或虚引用的对象。
### 软引用（Soft Reference）
软引用表示一个对象处在【有用，但非必须】的状态。在内存空间足够的情况下，如果一个对象只具有软引用，那么垃圾回收器就不会回收它，但是如果内存空间不足，垃圾回收器就会回收这个对象（回收发生在OutOfMemoryError错误之前）。只要垃圾回收器没有回收它，这个对象就能被程序使用。  

软引用可以用来实现内存敏感的高速缓存。

软引用可以和一个引用队列（Reference Queue）联合使用，如果软引用所引用的对象被垃圾回收器回收，Java虚拟机就会把这个软引用加入到与之关联的引用队列中，以便在恰当的时机将该软引用回收。但是由于GC线程的优先级较低，通常手动调用System.gc()并不能立即执行GC，因此软引用所引用的对象并不一定会被马上回收。

	Student student = new Student();
	SoftReference softReference = new SoftReference(student);

### 弱引用（Weak Reference）
弱引用表示一个对象处在【可能有用，但非必须】的状态。类似于软引用，但是强度比软引用更弱一些：只具有弱引用的对象拥有更短暂的生命周期。GC线程在扫描它所管辖的内存区域的过程中，一旦发现只具有弱引用的对象，就会回收掉这些被弱引用关联的对象。也就是说，无论当前内存是否紧缺，GC都会回收被弱引用关联的对象。不过，由于GC是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。

弱引用同样可以和一个引用队列（Reference Queue）联合使用，如果弱引用所引用的对象被垃圾回收器回收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。

使用场景：
>>ThreadLocal中，获取到线程私有对象是通过线程持有的一个threadLocalMap，然后传入ThreadLocal当做key获取到对象的，这时候就有个问题，如果你在使用完ThreadLocal之后，将其置为null，这时候这个对象并不能被回收，因为他还有 ThreadLocalMap->entry->key的引用，直到该线程被销毁，但是这个线程很可能会被放到线程池中不会被销毁，这就产生了内存泄露，jdk是通过弱引用来解决的这个问题的，entry中对key的引用是弱引用，当你取消了ThreadLocal的强引用之后，他就只剩下一个弱引用了，所以也会被回收。

	Student student = new Student();
	WeakReference weakReference = new WeakReference(student);
	
### 虚引用（Phantom Reference）
虚引用表示一个对象处在【无用】的状态。这意味着虚引用等同于没有引用，在任何时候都可能被GC回收。设置虚引用的目的是为了被虚引用关联的对象在被垃圾回收器回收的时候，能够收到一个系统通知（用来跟踪对象被GC回收的活动）。

虚引用和弱引用的区别在于：虚引用的使用必须和引用队列（Reference Queue）联合使用。

	ReferenceQueue referenceQueue = new ReferenceQueue();
	PhantomReference phantomReference = new PhantomReference(object， queue);
	
这意味着，GC在回收一个对象时，如果发现该对象具有虚引用，那么在回收之前就会首先将该对象的虚引用加入到与之关联的引用队列中。程序可以通过判断引用队列中是否已经加入虚引用来了解被引用的对象是否将要被GC回收。如果程序发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对象的内存被回收之前采取必要的行动。

参考：
[https://www.cnblogs.com/yanggb/p/10386175.html](https://www.cnblogs.com/yanggb/p/10386175.html)