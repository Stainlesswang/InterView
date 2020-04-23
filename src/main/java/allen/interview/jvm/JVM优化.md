# JVM问题排查

常用的命令

jstat，该工具可以查看GC

jstack -l pid  dump线程堆栈信息

1、jmap -histo[:live] <pid>  通过histo选项，打印当前java堆中各个对象的数量、大小。

 3、jmap -heap <pid>

通过-heap选项，打印java堆的配置情况和使用情况，还有使用的GC算法。

 5、jmap -permstat <pid>

通过-permstat选项，打印java堆永久代的信息，包括class loader相关的信息,和interned Strings的信息。

jstat -gcutil 28893 5000查看进程gc情况

jstat -gcnew pid:new对象的信息。 


### CPU飙高

1. top 命令找到 CPU 消耗最高的进程，并记住进程 ID
2. 再次通过 top -Hp pid  找到 CPU 消耗最高的线程 ID，并记住线程 ID.
3. 通过 JDK 提供的 jstack 工具 dump 线程堆栈信息到指定文件中。具体命令：jstack -l
   pid >jstack.log。
4. 由于刚刚的线程 ID 是十进制的，而堆栈信息中的线程 ID 是16进制的
   因此我们需要将10进制的转换成16进制的，并用这个线程 ID 在堆栈中查找。 使用 printf
   "%x\n" [十进制数字] ,可以将10进制转换成16进制。
5. 通过刚刚转换的16进制数字从堆栈信息里找到对应的线程堆栈。就可以从该堆栈中看出端倪。


### Full GC次数太多了，如何优化

（1）调用System.gc时，系统建议执行Full GC，但是不必然执行

（2）老年代空间不足

（3）方法区空间不足

（4）通过Minor GC后进入老年代的平均大小大于老年代的可用内存

（5）由Eden区、From Space区向To Space区复制时，对象大小大于To Space可用内存，则把该对象转存到老年代，且老年代的可用内存小于该对象大小


### 内存问题排查

1. 内存溢出 内存溢出的情况可以通过加上 -XX:+HeapDumpOnOutOfMemoryError 参数，
   该参数作用是：在程序内存溢出时输出 dump 文件。 有了 dump 文件，就可以通过 dump
   分析工具进行分析了，比如常用的MAT,Jprofile，jvisualvm
   等工具都可以分析，这些工具都能够看出到底是哪里溢出，哪里创建了大量的对象等等信息
   
2. GC 不健康

而 GC 的优化有2个维度，一是频率，二是时长。

我们看YGC，首先看频率，如果 YGC 超过5秒一次，甚至更长，说明系统内存过大，应该缩小容量，
如果频率很高，说明 Eden 区过小，可以将 Eden 区增大，但整个新生代的容量应该在堆的 
30% - 40%之间，eden，from 和 to 的比例应该在 8：1：1左右，这个比例可根据对象晋升的大小进行调整。

如果 YGC 时间过长呢？YGC 有2个过程，一个是扫描，
一个是复制，通常扫描速度很快，复制速度相比而言要慢一些，如果每次都有大量对象要复制，
就会将 STW 时间延长，还有一个情况就是 StringTable ，
这个数据结构中存储着 String.intern 方法返回的常连池的引用，
YGC 每次都会扫描这个数据结构（HashTable），如果这个数据结构很大，且没有经过 FGC，
那么也会拉长 STW 时长，还有一种情况就是操作系统的虚拟内存，当 GC 时正巧操作系统正在交换内存，
也会拉长 STW 时长。

再来看看FGC，实际上，FGC 我们只能优化频率，无法优化时长，因为这个时长无法控制。如何优化频率呢？

首先，FGC 的原因有几个，

1. 是 Old 区内存不够，
2. 是元数据区内存不够，
3. 是 System.gc()， 
4 是 jmap 或者 jcmd，5 是CMS Promotion failed 或者 concurrent mode failure，
6 JVM 基于悲观策略认为这次 YGC 后 Old 区无法容纳晋升的对象，因此取消 YGC，提前 FGC。

通常优化的点是 Old 区内存不够导致 FGC。
如果 FGC 后还有大量对象，说明 Old 区过小，
应该扩大 Old 区，如果 FGC 后效果很好，说明 Old 区存在了大量短命的对象，

优化的点应该是让这些对象在新生代就被 YGC 掉，通常的做法是增大新生代，如果有大而短命的对象，
通过参数设置对象的大小，不要让这些对象进入 Old 区，还需要检查晋升年龄是否过小。如果 YGC 后，
有大量对象因为无法进入 Survivor 区从而提前晋升，这时应该增大 Survivor 区，但不宜太大。

上面说的都是优化的思路，我们也需要一些工具知道 GC 的状况。

JDK 提供了很多的工具，比如 jmap ，jcmd 等，oracle 官方推荐使用 jcmd 代替 jmap，
因为 jcmd 确实能代替 jmap 很多功能。jmap 可以打印对象的分布信息，可以 dump 文件，注意，
jmap 和 jcmd dump 文件的时候会触发 FGC ，使用的时候注意场景。

还有一个比较常用的工具是 jstat，该工具可以查看GC 的详细信息，
比如eden ，from，to，old 等区域的内存使用情况。

还有一个工具是 jinfo，该工具可以查看当前 jvm 使用了哪些参数，并且也可以在不停机的情况下修改参数。

包括我们上面说的一些分析 dump 文件的可视化工具，
MAT，Jprofile，jvisualvm 等，这些工具可以分析 jmap dump 下来的文件，看看哪个对象使用的内存较多，通常是能够查出问题的。

还有很重要的一点就是，线上环境一定要带上 GC 日志！！！


