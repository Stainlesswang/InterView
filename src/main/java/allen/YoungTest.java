package allen;

import allen.interview.JavaAlgo.leecode.DuplicateNum;

import java.io.File;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Date;

public class YoungTest {

	public static void main(String[] args) {
		BitSet bitSet= new BitSet();
		assert args != null;
		for(String childFile: args) {
			System.out.println("-childName："+childFile+" 最后修改时间：");
		}
	}
}