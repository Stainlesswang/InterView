package allen.util;


/**
 * @author wangjianqiang
 */
public class AllenMain {

    public static void main(String[] args) {
       getNum();
    }
    public static boolean getNum(){
        int [] ints= new int[]{1,2,5,8,3,6};
        int length= ints.length;

        for (int i = 0; i <length ; i++) {
            System.out.println("****"+ints[i]+"****");

            for (int j = length-1; j >i ; j--) {
                System.out.println(ints[j]);
            }
        }
        return false;
    }
}
