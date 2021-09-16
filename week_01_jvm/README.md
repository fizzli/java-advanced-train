# 第一周作业

### 1.自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。
代码说明：Hello.xlass放在resources目录下，通过`OwnClassLoader.java`类重写`ClassLoader.findClass()`方法读取到文件后，解密还原成class字节码数组，并返回实例化类。测试方法在`com.fizzli.classLoader.OwnCLassLoaderTest`.

