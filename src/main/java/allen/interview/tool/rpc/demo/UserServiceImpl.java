package allen.interview.tool.rpc.demo;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {


    Map<String, String> userPool = new HashMap<String, String>();

    public UserServiceImpl() {
        userPool.put("441621", "小张");
        userPool.put("441622", "小王");
        userPool.put("441623", "小刘");
    }

    @Override
    public String getUserName(String id) {
        if (id == null) {
            return "参数异常";
        }
        String userName = userPool.get(id);
        if (userName != null) {
            return userName;
        } else {
            return "用户不存在";
        }

    }
}
