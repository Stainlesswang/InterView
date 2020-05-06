package allen.interview.aboutJava;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        Account account=new Account();
        account.setUsername("1");
    }
}
