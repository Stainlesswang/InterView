package allen.interview;

/**
 * @author AllenWong
 * 滴滴面试题目   给一个字符串, 将字符的顺序反过来
 *
 * @date 2020/5/14 5:22 PM
 */
public class Main {

    public static void main(String[] args) {
        String s="  he is   a dog! ";
        System.out.println(getStr(s));
    }

    private static String getStr(String s){
        if ("".equals(s)) return "";
        StringBuilder builder =new StringBuilder();
        for (int i = 0; i <s.length() ; i++) {
            StringBuilder temp=new StringBuilder();
            char now=s.charAt(i);
            if(' '!=now){
                temp.append(now);
                while (++i<s.length() && ' '!=s.charAt(i)){
                    temp.append(s.charAt(i));
                }i--;
                builder.insert(0,temp.append(" "));
            }
        }

        return builder.substring(0,builder.length());

    }
}
