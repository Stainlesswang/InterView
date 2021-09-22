package allen.interview.aboutJava;

import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author AllenWong
 * @date 2020/4/30 4:45 PM
 */
public class Java8 {
    static class Account{
        String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
    public static Map<String, Account> getNameAccountMap(List<Account> accounts) {
        return accounts.stream().
                collect(Collectors.toMap(Account::getUsername,
                        Function.identity(), (a1, a2) -> a2));
    }

    public static void main(String[] args) {
//        Account account=new Account();
//        account.setUsername("1");
        Integer[] array={1, 2, 4, 23, 11, 23, 45, 33, 66, 76, 4, 5};

       List<Integer> list= Arrays.asList(array);
        System.out.println(list.stream().filter(x -> x > 4).findFirst().toString());

        /**
         * 静态代理,代理模式,代码要先写好,在方法之前和之后执行一些其他事情
         *
         * JDK动态代理的实现步骤
         *
         * 1. 被代理的对象必须要基于接口实现的类
         * 2. 需要一个实现 InvocationHandler接口的类,该类里边加上实现代理方法的实现
         * 3. new一个接口实现的类 然后生成一个代理类即可
         *
         *
         * CJLB代理
         *
         * 1. cglb代理是需要依赖继承实现,也就是必须要有父类子类
         *
         */

    }
}
