package allen.concurrency.aqs;

/**
 * @author AllenWong
 * @date 2019/9/26 11:20 PM
 *
 *
 *
 *  AQS核心思想是，如果被请求的共享资源空闲，
 *  则将当前请求资源的线程设置为有效的工作线程，并且将共享资源设置为锁定状态。
 *  如果被请求的共享资源被占用，那么就需要一套线程阻塞等待以及被唤醒时锁分配的机制，这个机制AQS是用CLH队列锁实现的，
 *  即将暂时获取不到锁的线程加入到队列中。
 *
 */
public class AQSTest {
}
