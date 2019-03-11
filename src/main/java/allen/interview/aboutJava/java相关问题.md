# Java Interview Question 
1. **为什么String 要设计为final 不可变**  
String的源代码：
```java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence {
    /** The value is used for character storage. */
    private final char value[];}
```
final修饰的 value[] 数组是final的只能说明它是地址不可变，并不代表该数组内容不可变。另一个关键的修饰符是**private**限定了value
数组的访问限制。
- String 不可变保证了常量池的使用
- String 不可变才可以利用它作为不可变的key
- 线程安全的
2. **Java异常**  
   - Throwable子类分为  Exception（异常）And Error（错误）囊括了java中会遇到的各种情况
   - Exception一般是程序出现的错误，是修改我们的代码可以修改的情况，但是Error就是一些我们处理不了的情况，例如：内存溢出异常或者虚拟机异常我们无法处理
   - Exception运行时异常是指
