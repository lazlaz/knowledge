#Java�̳߳��÷�
ͨ��ThreadPoolExecutor�����̴߳�������Ҫ����˵��

| ��� | ����            | ����                     | ����             |
| ---- | --------------- | ------------------------ | ---------------- |
| 1    | corePoolSize    | int                      | �����̳߳ش�С   |
| 2    | maximumPoolSize | int                      | ����̳߳ش�С   |
| 3    | keepAliveTime   | long                     | �߳�������ʱ�� |
| 4    | unit            | TimeUnit                 | ʱ�䵥λ         |
| 5    | workQueue       | BlockingQueue<Runnable>  | �̵߳ȴ�����     |
| 6    | threadFactory   | ThreadFactory            | �̴߳�������     |
| 7    | handler         | RejectedExecutionHandler | �ܾ�����         |