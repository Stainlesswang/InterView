package allen.interview.tool.rpc.demo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 服务使用者; 发送相应参数,id 方法名称,接口名称等
 *
 * 然后获取RPC服务提供者返回的信息
 */
public class ConsumerRPC {

    public static void main(String[] args) throws Exception{
        String interfaceName = UserService.class.getName();

        //需要远程执行的方法
        Method method = UserService.class.getMethod("getUserName", java.lang.String.class);
        //RPC参数
        Object[] arguments = {"441622"};
        //Socket数据通道
        Socket socket = new Socket("localhost",1234);

        //序列化RPC调用参数
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        output.writeUTF(interfaceName);
        output.writeUTF(method.getName());
        output.writeObject(method.getParameterTypes());
        output.writeObject(arguments);

        //获取RPC返回结果
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        Object result = input.readObject();
        System.out.println((String)result);
    }

}
