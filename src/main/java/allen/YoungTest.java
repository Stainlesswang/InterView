package allen;

import java.io.File;

public class YoungTest {

	public static void main(String[] args) {
		findFile("D:/WorkSpace");
	}

	private static void findFile(String path) {
		if (null == path) {
			return;
		}
		File[] files = new File(path).listFiles();
		assert files != null;


		for (File f : files) {
			if (f.isFile()) {
				System.out.println("this fileName is " + f.getName());
			} else if (f.isDirectory()) {
				findFile(f.getPath());
			} else {
				System.out.println("someThing wrong!");
			}
		}
	}
}