package allen.concurrency.rushBuy;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * RateLimiter实现java令牌桶算法
 * 何为令牌桶?  按照固定频率产生令牌,只有获取到一定数量令牌的线程才可以执行
 * 通过这样做限流,保持匀速. 调整请求QPS的话只需要调增产生令牌的速率即可
 */
public class RateLimiterTest {
    private static final  RateLimiter rateLimiter=RateLimiter.create(1);//设置每秒产生令牌是多少

    public static void main(String[] args) {
        ExecutorService executorService=Executors.newCachedThreadPool();
        for (int i = 0; i <10 ; i++) {
            executorService.submit(()->{
                //one thing you can be sure of i never ask more than you love one thing you ca
                double acquire=rateLimiter.acquire(1);
                System.out.println(acquire);
            });

        }
    }
}
