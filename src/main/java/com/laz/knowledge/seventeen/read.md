#����SPI

SPI Service Provider Interface

����1������һ��ӿ� (������org.foo.demo.IShout)����д���ӿڵ�һ������ʵ�֣�(������org.foo.demo.animal.Dog��org.foo.demo.animal.Cat)��

����2���� src/main/resources/ �½��� /META-INF/services Ŀ¼�� ����һ���Խӿ��������ļ� (org.foo.demo.IShout�ļ�)��������ҪӦ�õ�ʵ���ࣨ������org.foo.demo.animal.Dog��org.foo.demo.animal.Cat��ÿ��һ���ࣩ��

����3��ʹ�� ServiceLoader �����������ļ���ָ����ʵ�֡�