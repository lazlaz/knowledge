# 进程同步问题

### 进程同步问题——生产者、消费者问题
>>生产者、消费者问题是操作系统中个著名的进程同步问题。一般是指: 有一群生产者进程在生产产品，并将此产品提供给消费者进程去消费。为使生产者进程和消费者进程能并发执行，在它们之间设置一个缓冲区， 生产者进程可将它所产的产品放入一个缓冲区中，消费者进程可从一个缓冲区取得一个产品消费。尽管所有的生产者进程和消费者进程都是以异步的方式运行的，但它们之间必须保持同步，即不允许消费者进程到一个空缓冲区去取产品，也不允许生产者进程向一个已装有消息、 但尚未取走产品的满缓冲区投放产品。这里将“进程”换成“线程”，问题仍然成立。下面要做由事情就是用线程来模拟这一过程。
