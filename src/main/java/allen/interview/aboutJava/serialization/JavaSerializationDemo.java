package allen.interview.aboutJava.serialization;

import allen.interview.aboutJava.reflect.Person;
import allen.interview.aboutJava.reflect.Son;

import java.io.*;

/**
 * Java 内置序列化方式实现
 *
 * 对序列化的对象必须实现Serializable接口,仅仅是标识该对象可序列化
 */
public class JavaSerializationDemo {
    public static void main(String[] args) {
        try {
            //定义一个字节数组输出流
            ByteArrayOutputStream os= new ByteArrayOutputStream();
            //对象输出流
            ObjectOutputStream out=new ObjectOutputStream(os);
            AllenBean allenBean=new AllenBean();
            allenBean.setAge(10);
            allenBean.setName("songFucker");
            //将实现了Serializable接口的类写入到流中
            out.writeObject(allenBean);
            //二进制流就可以在网络中传输或者写入文件 做任意操作了
            byte[] songBytes=os.toByteArray();

            //字节数组输入流
            ByteArrayInputStream inputStream=new ByteArrayInputStream(songBytes);
            //执行反序列化,从流中取出对象
            ObjectInputStream objectInputStream=new ObjectInputStream(inputStream);

            AllenBean allenBean2= (AllenBean) objectInputStream.readObject();
            System.out.println(allenBean2.getName() + allenBean2.getAge());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
