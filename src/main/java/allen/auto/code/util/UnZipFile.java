package allen.auto.code.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2018年12月25日 15:09
 */
public class UnZipFile {
	/**
	 * 解压到指定目录
	 * @param zipPath
	 * @param descDir
	 */
	public static void unZipFiles(String zipPath, String descDir) throws IOException {
		unZipFiles(new File(zipPath), descDir);
	}

	/**
	 * 解压文件到指定目录
	 * 解压后的文件名，和之前一致
	 * @param zipFile	待解压的zip文件
	 * @param descDir 	指定目录
	 */
	@SuppressWarnings("rawtypes")
	public static void unZipFiles(File zipFile, String descDir) throws IOException {

//		ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));//解决中文文件夹乱码
		ZipFile zip = new ZipFile(zipFile);

//		String name = zip.getName().substring(zip.getName().lastIndexOf('\\')+1, zip.getName().lastIndexOf('.'));

		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}

		for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();) {
			ZipEntry entry = entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir +zipEntryName).replaceAll("\\*", "/");

			// 判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if (!file.exists()) {
				file.mkdirs();
			}
			// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if (new File(outPath).isDirectory()) {
				continue;
			}
			// 输出文件路径信息

			FileOutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
		}
		System.out.println("******************解压完毕********************");
	}

	//测试
	public static void main(String[] args) {
		try {
			unZipFiles(new File("E:/devFile/test.zip"), "E:/devFile/1_4/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
