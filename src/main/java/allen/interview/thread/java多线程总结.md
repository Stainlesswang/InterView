# java多线程总结

##1. 进程和线程的区别

>一句话总结多线程： 一条线程是进程中的一个顺序的控制流，一个进程可以有一个或者多个线程，每条线程并行执行多个任务，能够满足程序员编写高效率的代码来达到充分利用CPU的目的？
>**进程是啥？**一个进程包括操作系统分配的内存空间、包含一个或者多个线程。线程必须依赖进程的存在

- 进程是什么?

  进程就是计算机开启了一个应用程序,是一次应用程序执行过程.典型例子就是windows下的任务管理器看到的每一个就是一个进程.是计算机执行的基本单位,一个进程包含多个线程
- 线程是撒? 
  
  线程是和进程类似的东西,但是线程更加的轻量级,是更小的执行单位. java中线程共享**堆**和**方法区**,每个线程有自己独自的**程序计数器,虚拟机栈,本地方法栈**
  
  一张图表明进程和线程的关系:![进程线程区别](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9teS1ibG9nLXRvLXVzZS5vc3MtY24tYmVpamluZy5hbGl5dW5jcy5jb20vMjAxOS0zL0pWTSVFOCVCRiU5MCVFOCVBMSU4QyVFNiU5NyVCNiVFNiU5NSVCMCVFNiU4RCVBRSVFNSU4QyVCQSVFNSU5RiU5Ri5wbmc?x-oss-process=image/format,png)


****
##一.如何New一个新线程
在java中要想实现多线程，有两种手段，
 
 * **一种是继承Thread类，**（详见 我的Interview项目 thread包下的 NewThread1.java）
 *  **另外一种是实现Runable接口.**（详见 我的Interview项目 thread包下的 NewThread2.java）
(其实准确来讲，应该有三种，还有一种是实现Callable接口，并与Future、线程池结合使用，此文这里不讲这个，有兴趣看这里) [Java并发编程与技术内幕:Callable、Future、FutureTask、CompletionService ](http://blog.csdn.net/evankaka/article/details/51610635)



>2018.5.11补充，关于使用ExecutorService、Callable、Future实现有返回结果的多线程

**代码实现如下：**

```java
public class Test {  

    public static void main(String[] args) throws ExecutionException,  InterruptedException {
        System.out.println("----程序开始运行----");
        Date date1 = new Date();

        int taskSize = 5;
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Callable c = new MyCallable(i + " ");
            // 执行任务并获取Future对象
            Future f = pool.submit(c);
            // System.out.println(">>>" + f.get().toString());
            list.add(f);
        }
        // 关闭线程池
        pool.shutdown();

        // 获取所有并发任务的运行结果
        for (Future f : list) {
            // 从Future对象上获取任务的返回值，并输出到控制台
            System.out.println(">>>" + f.get().toString());
        }

        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【"
                + (date2.getTime() - date1.getTime()) + "毫秒】");
    }
    static class MyCallable implements Callable<Object> {
        private String taskNum;

        MyCallable(String taskNum) {
            this.taskNum = taskNum;
        }

        public Object call() throws Exception {
            System.out.println(">>>" + taskNum + "任务启动");
            Date dateTmp1 = new Date();
            Thread.sleep(1000);
            Date dateTmp2 = new Date();
            long time = dateTmp2.getTime() - dateTmp1.getTime();
            System.out.println(">>>" + taskNum + "任务终止");
            return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
        }
    }
}  
```

##二.关于线程的状态改变
**线程的状态示意图（详细版本）：**
![Alt text](https://my-blog-to-use.oss-cn-beijing.aliyuncs.com/19-1-29/Java+%E7%BA%BF%E7%A8%8B%E7%8A%B6%E6%80%81%E5%8F%98%E8%BF%81.png)

* **新建状态**： 
使用new关键字新建一个Thread类或者其子类的对象后，该线程就处于新建状态，程序调用start（）方法之前线程会一直保持新建状态。
* **就绪状态**：
当线程对象调用了start（）方法之后，该线程就进入就绪状态，就绪状态的线程会在就绪队列中等待，等待JVM里边的线程调度器的调度。
* **运行状态**：
如果就绪状态的线程获取 CPU 资源，就可以执行 run()，此时线程便处于运行状态。处于运行状态的线程最为复杂，它可以变为阻塞状态、就绪状态和死亡状态。
* **阻塞状态:**
如果一个线程执行了sleep（睡眠）、suspend（挂起）等方法，失去所占用资源之后，该线程就从运行状态进入阻塞状态。在睡眠时间已到或获得设备资源后可以重新进入就绪状态。可以分为三种：

  1. 等待阻塞：运行状态中的线程执行 wait() 方法，使线程进入到等待阻塞状态。

  2. 同步阻塞：线程在获取 synchronized 同步锁失败(因为同步锁被其他线程占用)。

  3. 其他阻塞：通过调用线程的 sleep() 或 join() 发出了 I/O 请求时，线程就会进入到阻塞状态。当sleep() 状态超时，join() 等待线程终止或超时，或者 I/O 处理完毕，线程重新转入就绪状态。
* **死亡状态：**
一个运行状态的线程完成任务或者其他终止条件发生时，该线程就切换到终止状态。

##三.关于线程调度
1. 调整线程优先级：Java线程有优先级，优先级高的线程会获得较多的运行机会。
 
Java线程的优先级用整数表示，取值范围是1~10，Thread类有以下三个静态常量：
>
static int MAX_PRIORITY  线程可以具有的最高优先级，取值为10。  
static int MIN_PRIORITY   线程可以具有的最低优先级，取值为1。  
static int NORM_PRIORITY  分配给线程的默认优先级，取值为5。  

  Thread类的setPriority()和getPriority()方法分别用来设置和获取线程的优先级。每个线程都有默认的优先级。主线程的默认优先级为Thread.NORM_PRIORITY。线程的优先级有继承关系，比如A线程中创建了B线程，那么B将和A具有相同的优先级。JVM提供了10个线程优先级，但与常见的操作系统都不能很好的映射。如果希望程序能移植到各个操作系统中，应该仅仅使用Thread类有以下三个静态常量作为优先级，这样能保证同样的优先级采用了同样的调度方式。
 
2. 线程睡眠：Thread.sleep(long millis)方法，使线程转到阻塞状态。millis参数设定睡眠的时间，以毫秒为单位。当睡眠结束后，就转为就绪（Runnable）状态。sleep()平台移植性好。
 
3. 线程等待：Object类中的wait()方法，导致当前的线程等待，直到其他线程调用此对象的 notify() 方法或 notifyAll() 唤醒方法。这个两个唤醒方法也是Object类中的方法，行为等价于调用 wait(0) 一样。
 
4. 线程让步：Thread.yield() 方法，暂停当前正在执行的线程对象，把执行机会让给相同或者更高优先级的线程。
 
5. 线程加入：join()方法，等待其他线程终止。在当前线程中调用另一个线程的join()方法，则当前线程转入阻塞状态，直到另一个线程运行结束，当前线程再由阻塞转为就绪状态。
 
6.  线程唤醒：Object类中的notify()方法，唤醒在此对象监视器上等待的单个线程。如果所有线程都在此对象上等待，则会选择唤醒其中一个线程。选择是任意性的，并在对实现做出决定时发生。线程通过调用其中一个 wait 方法，在对象的监视器上等待。 直到当前的线程放弃此对象上的锁定，才能继续执行被唤醒的线程。被唤醒的线程将以常规方式与在该对象上主动同步的其他所有线程进行竞争；例如，唤醒的线程在作为锁定此对象的下一个线程方面没有可靠的特权或劣势。类似的方法还有一个notifyAll()，唤醒在此对象监视器上等待的所有线程。
 注意：Thread中suspend()和resume()两个方法在JDK1.5中已经废除，不再介绍。因为有死锁倾向。


## 四、常用函数说明
1. **sleep(long millis):** 在指定的毫秒数内让当前正在执行的线程休眠（暂停执行）

2. **join():**指等待t线程终止。

  **使用方式:**主线程生成并起动了子线程，主线程需要等待子线程执行完成之后再结束，这个时候就要用到join()方法了。主线程一定会等子线程都结束了才结束.
  
  ```java
  public class MyRunnable implements Runnable{
    	private String name;
    
    	MyRunnable(String name){
       		 this.name=name;
   		 }
    
	    public void run() {
	        for (int i = 0; i < 5; i++) {
	            System.out.println(name + "运行  :  " + i);
	            try {
	                Thread.sleep((int) (Math.random() * 10));
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	
	
	    public static void main(String[] args) throws InterruptedException {
	    
	        System.out.println("main thread start running!");
	        Thread thread1=new Thread(new MyRunnable("A"));
	        thread1.start();
	        thread1.join();
	        System.out.println("main thread now is End My dear!");
	     }
    	
	}
  	//输出结果:     main thread start running!
        //        A运行  :  0
        //        A运行  :  1
        //        A运行  :  2
        //        A运行  :  3
        //        A运行  :  4
        //        main thread now is End My dear!  
  ```


3. yield():

  - 暂停当前正在执行的线程对象，并执行其他线程。Thread.yield()方法作用是：暂停当前正在执行的线程对象，并执行其他线程。yield()应该做的是让当前运行线程回到可运行状态，以允许具有相同优先级的其他线程获得运行机会。因此，使用yield()的目的是让相同优先级的线程之间能适当的轮转执行。但是，实际中无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中
    
     **结论：yield()从未导致线程转到等待/睡眠/阻塞状态。在大多数情况下，yield()将导致线程从运行状态转到可运行状态，但有可能没有效果。可看上面的图。**
   - **sleep()和yield()的区别**
     
     sleep()使当前线程进入停滞状态，所以执行sleep()的线程在指定的时间内肯定不会被执行；yield()只是使当前线程重新回到可执行状态，所以执行yield()的线程有可能在进入到可执行状态后马上又被执行。  
     
     sleep() 方法使当前运行中的线程睡眼一段时间，进入不可运行状态，这段时间的长短是由程序设定的，yield 方法使当前线程让出 CPU 占有权，但让出的时间是不可设定的。实际上，yield()方法对应了如下操作：先检测当前是否有相同优先级的线程处于同可运行状态，如有，则把 CPU  的占有权交给此线程，否则，继续运行原来的线程。*所以yield()方法称为“退让”，它把运行机会让给了同等优先级的其他线程* 
     
     另外，sleep 方法允许较低优先级的线程获得运行机会，但 yield()  方法执行时，当前线程仍处在可运行状态，所以，不可能让出较低优先级的线程些时获得 CPU 占有权。在一个运行系统中，如果较高优先级的线程没有调用 sleep 方法，又没有受到 I\O 阻塞，那么，较低优先级线程只能等待所有较高优先级的线程运行结束，才有机会运行。 


4. setPriority(): 更改线程的优先级。用法：
   
   ```
     MIN_PRIORITY = 1
     NORM_PRIORITY = 5
     MAX_PRIORITY = 10
	  Thread4 t1 = new Thread4("t1");
	  Thread4 t2 = new Thread4("t2");
	  t1.setPriority(Thread.MAX_PRIORITY);
	  t2.setPriority(Thread.MIN_PRIORITY);
    
   ```

  

5. interrupt():  
   不要以为它是中断某个线程！它只是线线程发送一个中断信号，让线程在无限等待时（如死锁时）能抛出抛出，从而结束线程，但是如果你吃掉了这个异常，那么这个线程还是不会中断的！
6. wait()  
   
   **Obj.wait()，与Obj.notify()**必须要与synchronized(Obj)一起使用，也就是wait,与notify是**针对已经获取了Obj锁进行操作**  
   
   从语法角度来说就是Obj.wait(),Obj.notify必须在synchronized(Obj){...}语句块内。
   
   从功能上来说wait就是说线程在获取对象锁后，主动释放对象锁，同时本线程休眠。直到有其它线程调用对象的notify()唤醒该线程，才能继续获取对象锁，并继续执行。
   
   相应的notify()就是对对象锁的唤醒操作。但有一点需要注意的是notify()调用后，并不是马上就释放对象锁的，而是在相应的synchronized(){}语句块执行结束，自动释放锁后，JVM会在wait()对象锁的线程中随机选取一线程，赋予其对象锁，唤醒线程，继续执行。这样就提供了在线程间同步、唤醒的操作。
   
   Thread.sleep()与Object.wait()二者都可以暂停当前线程，释放CPU控制权，主要的区别在于Object.wait()在释放CPU同时，释放了对象锁的控制。
    
####wait和sleep区别

**共同点：** 

  1. 他们都是在多线程的环境下，都可以在程序的调用处阻塞指定的毫秒数，并返回。 

  2. **wait()和sleep()**都可以通过interrupt()方法 打断线程的暂停状态 ，从而使线程立刻抛出InterruptedException。 如果线程A希望立即结束线程B，则可以对线程B对应的Thread实例调用interrupt方法。如果此刻线程B正在*wait/sleep/join*，则线程B会立刻抛出InterruptedException，在`catch() {}` 中直接return即可安全地结束线程。 需要注意的是，InterruptedException是线程自己从内部抛出的，并不是interrupt()方法抛出的。对某一线程调用 interrupt()时，如果该线程正在执行普通的代码，那么该线程根本就不会抛出InterruptedException。但是，一旦该线程进入到 wait()/sleep()/join()后，就会立刻抛出InterruptedException 。 


**不同点：**
  
  1. Thread类的方法：sleep(),yield()等  Object的方法：wait()和notify()等 
  
  2. 每个对象都有一个锁来控制同步访问。Synchronized关键字可以和对象的锁交互，来实现线程的同步。 sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法。 
   
  5. wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用所以sleep()和wait()方法的最大区别是：sleep()睡眠时，保持对象锁，仍然占有该锁；而wait()睡眠时，释放对象锁。但是wait()和sleep()都可以通过interrupt()方法打断线程的暂停状态，从而使线程立刻抛出InterruptedException（但不建议使用该方法）。
  

**sleep（）方法:**
   
   sleep()使当前线程进入停滞状态（阻塞当前线程），让出CUP的使用、目的是不让当前线程独自霸占该进程所获的CPU资源，以留一定时间给其他线程执行的机会;sleep()是Thread类的Static(静态)的方法；因此他不能改变对象的机锁，所以当在一个Synchronized块中调用Sleep()方法是，线程虽然休眠了，但是对象的机锁并木有被释放，其他线程无法访问这个对象（即使睡着也持有对象锁）。
   
 在sleep()休眠时间期满后，该线程不一定会立即执行，这是因为其它线程可能正在运行而且没有被调度为放弃执行，除非此线程具有更高的优先级。
   

**wait（）方法**
   
   wait()方法是Object类里的方法；当一个线程执行到wait()方法时，它就进入到一个和该对象相关的等待池中，同时失去（释放）了对象的机锁（暂时失去机锁，wait(long timeout)超时时间到后还需要返还对象锁;
   
   其他线程可以访问;wait()使用notify或者notifyAlll或者指定睡眠时间来唤醒当前等待池中的线程。wait()必须放在synchronized block中，否则会在program runtime时扔出”java.lang.IllegalMonitorStateException“异常.

----
## 五、synchronized 是啥?

- 首先说一下synchronized用法
  
  1. 修饰实例方法:   **需要获得创建的实例的锁才可以运行**
  2. 修饰静态方法:   静态方法是属于当前类,无论有多少实例,静态方法only one. **所以调用静态方法的时候只需获取SomeClass.clss的锁资源就行,和实例方法获取每个实例锁资源不冲突**
  3. 修饰代码块: synchronized作用代码块,进入代码块需要获取指定对应的锁资源
- 经典的双重检查的单例模式

  ```java
  public class SingleTonLazy {
	//使用volatile将该对象编程多线程间可见的
	private static volatile SingleTonLazy singleTonLazy = null;

    private SingleTonLazy() {}

	public static SingleTonLazy getInsTance() {
		/*修改成如下方式 双重检查锁**/
		if (null==singleTonLazy){
			synchronized (SingleTonLazy.class){
				if (null==singleTonLazy){
					singleTonLazy=new SingleTonLazy();
				}
			}
		}
		return singleTonLazy;
	}
}

  ```
  
  注意点:
    1. 实例 singleTonLazy 要加上volatile关键字. 为什么要加上呢? 这跟JVM对指令重排序相关. 简而言之,对一个非原子操作的代码,JVM要分成多个小的原子指令去执行,在执行的过程中这些小的原子操作在多线程的环境下顺序是没办法保证的!
    
      例如语句```singleTonLazy=new SingleTonLazy```这条语句,实际上JVM执行指令的时候是分三个原子指令.
       
       - 为singleTonLazy实例分配内存空间
       - 初始化该实例的指令
       - 将该实例指向已分配的内存地址
      
      **使用volatile作用:**各个线程会将共享变量从主内存中拷贝到工作内存，然后执行引擎会基于工作内存中的数据进行操作处理。线程在工作内存进行操作后何时会写到主内存中？这个时机对普通变量是没有规定的，而针对volatile修饰的变量给java虚拟机特殊的约定，线程对volatile变量的修改会立刻被其他线程所感知，即不会出现数据脏读的现象，从而保证数据的“可见性”。
      
- Synchronized在JVM实现的原理:

 synchronized在修饰代码块的时候:**synchronized 同步语句块的实现使用的是 monitorenter 和 monitorexit 指令，其中 monitorenter 指令指向同步代码块的开始位置，monitorexit 指令则指明同步代码块的结束位置**

	synchronized修饰方法的时候:**取得代之的确实是 ACC_SYNCHRONIZED 标识，该标识指明了该方法是一个同步方法，JVM 通过该 ACC_SYNCHRONIZED 访问标志来辨别一个方法是否声明为同步方法，从而执行相应的同步调用。**
    
- Synchronized和ReenTrantLock区别和联系

  1. 两者都是可重入锁
  2. Synchronized是依赖JVM实现的,ReenTrantLock是依赖java API
  3. ReentrantLock 提供了额外的三个功能,当有这些需要的时候再考虑使用ReenTrantLock **等待可中断特性,公平锁非公平锁,支持分组wait()和notify()**    
  
  
## 五、乐观锁和悲观锁概念? 什么是CAS?

首先展示乐观锁和悲观锁的例子

```
// ------------------------- 悲观锁的调用方式 -------------------------
// synchronized
public synchronized void testMethod() {
	// 操作同步资源
}
// ReentrantLock
private ReentrantLock lock = new ReentrantLock(); // 需要保证多个线程使用的是同一个锁
public void modifyPublicResources() {
	lock.lock();
	// 操作同步资源
	lock.unlock();
}

// ------------------------- 乐观锁的调用方式 -------------------------
private AtomicInteger atomicInteger = new AtomicInteger();  // 需要保证多个线程使用的是同一个AtomicInteger
atomicInteger.incrementAndGet(); //执行自增1
```

- **悲观锁:** 应该是很常见的了,平常经常用,是将整个资源的锁对象获取,其他对象必须等待锁资源释放才可以.
- **乐观锁:** 乐观锁看不出来明显的占用锁的操作. 常见的就是CAS算法
____

- **CAS算法:** (Compare And Swap),是一种无锁算法,在不使用锁的情况下,也就是没有线程会因为等待锁资源而阻塞的情况下,是如何实现线程安全的呢? 主要原因是因为如下三步
  
  * 需要读写的内存值V
  * 等待比较的值A
  * 要写入的新值B
    
    > 当写入B之前,首先比较A和V的值是否相等,相等的话说明没有别的线程在执行该变量. 然后写入新值B,否则不执行任何操作.
    
 
## 六、各种锁🔐的总结

1. **啥是自旋锁? 自适应自旋锁?**
  
  我们首先要知道的是: 线程阻塞或者唤醒,是需要**切换CPU状态**来完成,这种切换有时候会很浪费时间,所以为了让未获取到锁资源的线程'等一下',所以可以通过不放弃CPU的占用,等下再试. 但是一般会有自旋次数的上限,达到后就挂起或者阻塞线程. **所谓自适应自旋**,自旋的时间或者次数不再固定,而是根据前一次在同一个锁的自选时间以及锁拥有者的状态来决定自旋时间.
  
2. **公平锁和非公平锁**

  公平锁会根据获取锁请求的先后顺序进行,也就是在队头的线程才会被允许获取锁.
  
  非公平锁首先会尝试插队获取锁,成功便执行,失败了退回队尾排队等待
 
3. **可重入锁 VS 非可重入锁**
  
  可重入: 获得该锁资源的线程再次尝试获取锁资源的时候,会将计数加一,不会造成阻塞  ReentrantLock和synchronized都是可重入锁
  
  非可重入: 获得锁资源的当前线程在没有释放锁的时候再次获取锁资源,会被wait()造成阻塞,然而当前线程阻塞后原来的资源永远无法被释放,就造成了死锁
   
## 七、死锁是什么? 四个必要条件

死锁举例: 当前状态: 线程A获得B资源,线程B获得A资源,且两者都在运行当中

突然间: 强迫症烦了,字母顺序搞混了, 线程A 想要获得A资源, 线程B 想要获得B资源.  这时候就开始去傻傻等了,这样造成一种循环的状态, 线程A一直在等线程B释放A资源, 线程B也一直在等线程A释放B资源, 两个线程就成了死锁的状态

下边我们说下线程死锁的四个必要条件,也就是说,线程死锁的话,下边这四个条件是肯定满足的

1. 互斥条件: 同一时刻,一个资源只能被一个线程占用 (**这个是必然存在的一个条件,这也是锁的原理**)
2. 请求与保持条件: 一个进程因请求资源而阻塞时，对已获得的资源保持不放

   破坏方法: 一次性申请所有资源
   
3. 不剥夺条件:一个资源只能被占有它的线程使用完了放弃,不然其他任何人没有权利剥夺

   破坏方法:已经获取资源的线程当想申请更多资源的时候可以先把占有的释放

4. 循环等待条件:若干资源形成一种首尾相接的循环等待场景

   破坏方法:按照一定顺序获取锁资源,然后按照反序释放资源,这样就避免了循环等待的发生
   
## 八、AQS是什么? 

AQS是一个用来构建锁和同步器的框架.核心思想是 如果当前共享资源空闲,则当前线程作为工作线程,并且把资源设置为锁定状态(通常是一个int值,0代表空闲状态,1代表被锁定), 如果当前共享资源被锁定,则需要一个变种`CLH`(Craig、Landin和Hagersten三位作者)队列来保存当前线程封装到一个Node节点. 

AQS对资源的访问方式

- Exclusive(独占): 代表的类是ReentrantLock,只有一个线程可以获取资源,又可以细分为公平锁和非公平锁
- Share(共享): 多个线程可以同时执行,代表的类: Semaphore, CountDownLatCh, CyclicBarrier

## 九.Semaphore,CountDownLatch,CyclicBarrier说明
这些都是基于AQS

