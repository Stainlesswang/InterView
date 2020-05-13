package allen.interview.JavaAlgo.leecode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author AllenWong
 * @date 2020/5/11 9:56 PM
 */
public class YY {
    List<String> res = new LinkedList<>();
    char[] c;
    public String[] permutation(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }


    void dfs(int x) {
        if(x == c.length - 1) {
            res.add(String.valueOf(c)); // 添加排列方案
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for(int i = x; i < c.length; i++) {
            if(set.contains(c[i])) continue; // 重复，因此剪枝
            set.add(c[i]);
            swap(i, x); // 交换，将 c[i] 固定在第 x 位
            dfs(x + 1); // 开启固定第 x + 1 位字符
            swap(i, x); // 恢复交换
        }
    }
    void dfs2(int x){
        if(x==c.length-1){
            res.add(String.valueOf(c));
            return;
        }
        HashSet<Character> set =new HashSet<>();
        for (int i=x;i<c.length;i++){
            if (set.contains(c[i])) continue;
            set.add(c[i]);
            swap(i,x);
            dfs2(x+1);
            swap(i,x);
        }
    }


    void swap(int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }

//    public static void main(String[] args) {
//        YY yy=new YY();
//        yy.permutation("abcdefghhi");
//    }

    static boolean flag=true;
    public static void main(String[] args) {
        String A="AAABCCDDDCB";
        A=getStr(A);
        System.out.println(A);

    }


    public static  String getStr(String input){
        int len=input.length();
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<len;i++){
            char curr=input.charAt(i);
            StringBuilder temp=new StringBuilder(String.valueOf(curr));
            int count=1;
            while(++i<len&&curr==input.charAt(i)){
                temp.append(input.charAt(i));
                count++;
            }i--;
            if(count<3){
                builder.append(temp);
            }else{
                flag=true;
            }
        }
        return builder.toString();
    }

}
