package allen.interview.tool.rpc.demo;

/**
 * -----自定义RPC框架,使用socket通信------
 * 一个简单的Service 接口示例
 *
 */
public interface UserService {
    /**
     * 简单的根据用户id获取名称的方法
     * @param id
     * @return
     */
    public String getUserName(String id);
}
