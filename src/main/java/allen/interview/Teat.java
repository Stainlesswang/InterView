package allen.interview;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

public class Teat {
    public static void main(String[] args){
//                Integer a = 5, b = 10;
//                swap (a,  b);
//                // a = 10, b = 5;
//                out.println ("a =  "+a);
//                out.println (" b = "+ b);
//
//
//        String s1 = "Programming";
//        String s2 = "Programming";
//        String s3 = "Program";
//        String s4 = "ming";
//        String s5 = "Program" + "ming";
//        String s6 = s3 + s4;
//        System.out.println(s1 == s2);
//        System.out.println(s1 == s5);
//        System.out.println(s1 == s6);
//        System.out.println(s1 == s6.intern());
//        System.out.println(s2 == s2.intern());
	    String oldImgUrl="T125bbBsdv1RCvBVdK;T15mAbB4CT1RCvBVdK";
	    String newUrl="T125bbBsdv1RCvBVdK;T15SSbB4CT1RCvBVdK;T1556SSbB4CTCvBVdK";
	    List<String> old=  Arrays.asList(oldImgUrl.split(";"));
	    List<String>  newkey=  Arrays.asList(newUrl.split(";"));
	    ArrayList<String> oldArrayList=new ArrayList<>(old);
	    ArrayList<String> newkeyArrayList=new ArrayList<>(newkey);
	    System.out.println(oldArrayList);
	    oldArrayList.removeIf(s -> s.contains("bb"));
	    System.out.println(oldArrayList);

    }

     private static void swap(Integer x, Integer y) {
        Integer temp = x;
        x = y;
        y = temp;
        out.println ("x"+x);
        out.println ("y"+ y);
    }
    }

