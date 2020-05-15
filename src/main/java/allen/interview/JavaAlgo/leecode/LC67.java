package allen.interview.JavaAlgo.leecode;

/**
 * @author AllenWong
 * @date 2020/5/14 11:36 AM
 */
public class LC67 {
    public static String addBinary(String a, String b) {
        int m=a.length();
        int n=b.length();
        StringBuilder builder=new StringBuilder();
        int last=0;
        for(int i=m-1, j=n-1;i>=0||j>=0;i--,j--){
            char aNow=i>=0?a.charAt(i):'0';
            char bNow=j>=0?b.charAt(j):'0';
            int temp=(aNow-'0')+(bNow-'0');
            int all=temp+last;
            builder.append(all%2);
            last=all/2;
        }
        if (last>0) return builder.append("1").reverse().toString();

        return builder.reverse().toString();


    }

    public static void main(String[] args) {
        addBinary("11","1");
    }
}
