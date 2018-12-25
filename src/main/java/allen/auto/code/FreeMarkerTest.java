package allen.auto.code;

import freemarker.template.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class  FreeMarkerTest {
    public static void main(String[] args) {
        try {
            //创建Configuration实例，指定FreeMarker的版本
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
            //指定模板所在的目录
            File templatesDir = new File("./template");
            cfg.setDirectoryForTemplateLoading(templatesDir);
            //设置默认字符集
            cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
            //设置出现异常处理的方式，开发阶段可以设置为HTML_DEBUG_HANDLER
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);

            //2.0 创建数据模型
            Map<String, Object> root = new HashMap<>();
            root.put("user", "神经病");
            Map latest = new HashMap();
            root.put("latestProduct", latest);
            latest.put("url", "products/greenmouse.html");
            latest.put("name", "green mouse");
            root.put("latestProduct", latest);

            //3.0 获取模板
            Template template = cfg.getTemplate("test.ftl");

            File testFile = new File("./template", "test.html");
            //4.0 给模板绑定数据模型
            Writer out = new FileWriter(testFile);
            template.process(root, out);
            System.out.println(testFile.getAbsolutePath());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
        }

    }
}
