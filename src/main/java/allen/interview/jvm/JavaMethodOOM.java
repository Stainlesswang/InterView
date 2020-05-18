package allen.interview.jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * @author AllenWong
 * @date 2020/5/15 11:31 PM
 */
public class JavaMethodOOM {
    public static void main(String[] args) {
        while (true) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OOMObject.class);
        enhancer.setUseCache(false);
        enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) -> proxy.invokeSuper(obj, args1)
        );
        enhancer.create();
    }
}
static class OOMObject {

}
}
