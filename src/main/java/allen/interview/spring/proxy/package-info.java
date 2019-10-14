/**
 * @author AllenWong
 * @date 2019/10/14 11:27 PM
 *
 * 该包下实现了两种AOP的实现,两种动态代理生成代码的方式没有很难的逻辑,但是要自己看
 * 第一个是实现了接口的类,JDK动态代理
 *    JDK动态代理例子在 MyInvocationHandler 类下,关键代码是invoke()方法下边的
 *    Object result= method.invoke(target,args);
 *
 * 第二个是对没有实现接口的普通类的 CGLIB(Code Generator Library)
 *    CGLIB 例子主要在 cglib.CGLBProxy 类下,实现了MethodInterceptor,主要原理是根据继承子类并且通过拦截器动态生成拦截器实现中
 *    intercept()方法
 *    注意不能实现final方法或者final类,因为是通过继承出来的子类生成的代理对象
 *
 *
 */
package allen.interview.spring.proxy;