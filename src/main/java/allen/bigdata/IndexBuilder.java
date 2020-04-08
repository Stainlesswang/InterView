package allen.bigdata;

/**
 * @author AllenWong
 * @date 2020/3/26 4:47 PM
 */
public class IndexBuilder {

    public static int getStartKeybyGid(int gid) {
        return (gid / 5000000)*(5000000 / 1000000);
    }
    public static void main(String[] args) {
        for (int i = 0; i <100 ; i++) {
            System.out.println(i / 5);
        }
    }
}
