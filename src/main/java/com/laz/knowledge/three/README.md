# Java动态代理（基于CGLIB)

### CGLIB是一个Java字节码生成库，提供了易用的API对Java字节码进行创建和修改 无需代理类实现接口，需要代理类有一个空构造函数，不能并final修饰

Java动态代理实现方式三：通过编译期提供的API动态创建代理类

假设我们确实需要给一个既是final，又未实现任何接口的ProductOwner类创建动态代码。除了InvocationHandler和CGLIB外，我们还有最后一招： 我直接把一个代理类的源代码用字符串拼出来，然后基于这个字符串调用JDK的Compiler（编译期）API，动态的创建一个新的.java文件，然后动态编译这个.java文件，这样也能得到一个新的代理类。