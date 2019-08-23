package allen.interview.tool.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * 测试Zookeeper使用方法的类
 * Zookeeper类似一个文件系统,可以创建ZNode代表节点,还可以创建相应的子节点
 * 一个重要的功能就是 watch 功能,当一个节点内容发生变化,可以通知订阅该节点的相关业务做变化
 *
 * 实际使用的场景中常用作去中心化的服务治理,保证分布式系统下的一致性
 */
public class ZookeeperMain {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {


        // 创建一个与服务器的连接
        // 监控所有被触发的事件
        ZooKeeper zk = new ZooKeeper("localhost:2181",
                10, event -> System.out.println("***********已经触发了" + event.getType() + "事件！***********"));


        // 创建一个目录节点
        zk.create("/testRootPath", "testRootData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        // 创建一个子目录节点
        zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        Stat stat=new Stat();//一个传出参数,能够返回当前node的状态信息
        System.out.println(new String(zk.getData("/testRootPath", false, stat)));
        // 取出子目录节点列表
        System.out.println(zk.getChildren("/testRootPath", true));
        // 修改子目录节点数据
        zk.setData("/testRootPath/testChildPathOne", "modifyChildDataOne".getBytes(), -1);
        //返回目录节点状态信息,节点不存在 返回null 存在就非空
        System.out.println("目录节点状态：[" + zk.exists("/testRootPath", true) + "]");


        // 创建另外一个子目录节点
        zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo", true, null)));



        // 删除子目录节点
        zk.delete("/testRootPath/testChildPathTwo", -1);
        zk.delete("/testRootPath/testChildPathOne", -1);
        // 删除父目录节点
        zk.delete("/testRootPath", -1);
        // 关闭连接
        zk.close();
    }

}
