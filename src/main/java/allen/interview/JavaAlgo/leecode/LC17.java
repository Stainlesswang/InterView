package allen.interview.JavaAlgo.leecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AllenWong
 * @date 2020/4/22 12:33 AM
 */
public class LC17 {
    class Solution {
        Map<String, String> phone = new HashMap<String, String>() {{
            put("2", "abc");
            put("3", "def");
            put("4", "ghi");
            put("5", "jkl");
            put("6", "mno");
            put("7", "pqrs");
            put("8", "tuv");
            put("9", "wxyz");
        }};

        List<String> output = new ArrayList<>();

        public List<String> getAllLetterByPhone(String number) {
            if (number.length() > 0) {
                backTrack("", number);
            }
            return output;

        }

        public void backTrack(String lastStr, String number) {
            if (number.length() == 0) {
                output.add(number);
            } else {
                String nowNum = number.substring(0, 1);
                String letter = phone.get(nowNum);
                for (int i = 0; i < letter.length(); i++) {
                    backTrack(lastStr + letter.substring(i, i + 1), number.substring(1));
                }
            }

        }


    }
}
