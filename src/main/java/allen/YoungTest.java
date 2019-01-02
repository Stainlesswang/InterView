package allen;

import java.io.File;


public class YoungTest {
	public static void main(String[] args) {
		String linuxPath = "/data0/apph5/test";
		File file = new File(linuxPath);
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getParent());

	}


}
