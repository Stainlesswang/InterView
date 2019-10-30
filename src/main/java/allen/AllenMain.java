package allen;


/**
 * @author wangjianqiang
 */
public class AllenMain {

    public static void main(String[] args) {
        String ip="10.10.267.98";
        int hashcode=ip.hashCode();
        System.out.println(hashcode);
        System.out.println(hashcode % 4);
    }
}
