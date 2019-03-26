### java IO学习
Java IO 是操作输入计算机的数据及输出计算机结果的重要组成部门 

![img](http://upload-images.jianshu.io/upload_images/1752522-ee60b12bd1f9a3dc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

1. 如何选择I/O流？

   1. 确定是输入流还是输出流 输入流：InputSteam Reader.
      输出流：OutputStream Writer 
      
   2. 明确操作的对象是否是纯文本。是就选择字符流 否则字节流：OutputStream
      InputStream。
      
   3. 明确具体的设备。
   
      - 文件： 读：FileInputStream,, FileReader,
        写：FileOutputStream，FileWriter 
      
      -  数组： byte[]：ByteArrayInputStream, ByteArrayOutputStream
         char[]：CharArrayReader, CharArrayWriter 
      
      - String：
        StringBufferInputStream(已过时，因为其只能用于String的每个字符都是8位的字符串),
        StringReader, StringWriter
       
      -  Socket流
         键盘：用System.in（是一个InputStream对象）读取，用System.out（是一个OutputStream对象）打印