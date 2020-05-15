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


    }
}
