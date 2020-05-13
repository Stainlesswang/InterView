package allen.interview.JavaAlgo.leecode;

/**
 * @author AllenWong
 * @date 2020/5/13 11:59 AM
 */
public class LC38 {
    public static String countAndSay(int n) {
        if(n==1) return "1";
        if (n==2) return "11";
        String now =countAndSay(n-1);
        int len=now.length();
        StringBuilder builder=new StringBuilder("");
        for(int i=0;i<len;i++){
            char temp=now.charAt(i);
            int count=1;
            while(++i<len&&now.charAt(i)==temp){
                count++;
            }i--;
            builder.append(count).append(temp);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }
}
