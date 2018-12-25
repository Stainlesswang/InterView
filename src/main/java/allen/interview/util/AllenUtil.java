package allen.interview.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2018年11月07日 10:37
 */
public class AllenUtil {
    public static void main(String[] args) throws IOException {
//        File file = new File("test.zip");
//        if(!file.exists())
//            file.createNewFile();
//        String string="test ZIP!";
//        byte[] buffer =string.getBytes();
//        FileOutputStream fOutputStream = new FileOutputStream(file);
//        ZipOutputStream zoutput = new ZipOutputStream(fOutputStream);
//        ZipEntry zEntry  = new ZipEntry("test.txt");
//        zoutput.putNextEntry(zEntry);
//        zoutput.write(buffer);
//        zoutput.closeEntry();
//        zoutput.close();

        String path=Thread.currentThread().getContextClassLoader().getResource("").toString();
        path = path.substring(5,path.length());
        System.out.println("jar包目录："+path);
        String folderName = "myfolder";
        String filePath = path+folderName;
        File file = new File(filePath);
        if(file.exists()&&file.isDirectory()) {
            String fileAbsolutePath = file.getAbsolutePath();//文件绝对路径
            System.out.println("找到了jar包同目录的文件夹："+fileAbsolutePath);
            File[] childFiles = file.listFiles();
            for(File childFile:childFiles) {
                System.out.println("-childName："+childFile.getName()+" 最后修改时间："+new Date(childFile.lastModified()));
            }
        }else {
            System.out.println("文件夹在jar包同目录下不存在，或者不是文件夹");
        }
    }


}
