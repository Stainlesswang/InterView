package allen.interview.tool.rpc.demo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * -----自定义RPC框架,使用socket通信------
 * socket服务提供者,监听一个端口,收到信息,处理后并返回数据
 * 启动该类
 */
public class ProviderRPC {
    private static Map<String, Object> servicesPool = new HashMap<>();

    public static void main(String[] args) throws Exception{
        init();
        System.out.println("RPC Server 启动");

        @SuppressWarnings("resource")
        ServerSocket server = new ServerSocket(1234);

        while (true) {
            try {
                //开启SocketServer用于传输数据
                Socket socket = server.accept();

                //反序列化RPC调用数据包括，接口名称，方法名，参数类型，参数i
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                //接口名称
                String interfacename = input.readUTF();
                System.out.println("接口名称"+interfacename);
                //方法名
                String methodName = input.readUTF();
                System.out.println("方法名:"+methodName);
                //参数类型
                Class<?>[] paramterType = (Class<?>[])input.readObject();
                System.out.println("参数类型:"+ Arrays.toString(paramterType));
                //参数
                Object[] params = (Object[]) input.readObject();
                for (Object s: params){
                    System.out.println("方法名:"+s);
                }

                //将类加载到内存中
                Class<?> serviceInterfaceClass = Class.forName(interfacename);
                //获取对象，线程安全的方法所以可以预先加载类，可以节省每次创建对内存处理器的消耗
                Object service = servicesPool.get(interfacename);
                //根据方法名跟参数类型确认一个方法
                Method method = serviceInterfaceClass.getMethod(methodName, paramterType);
                //调用方法
                Object result = method.invoke(service, params);

                //根据socket通道返回RPC调用数据
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void init(){
        servicesPool.put("allen.interview.tool.rpc.demo.UserService", new UserServiceImpl());
    }
}
