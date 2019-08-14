package allen.autocode;

import freemarker.template.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
            Map<String, Object> rootMap = new HashMap<String, Object>();
            rootMap.put("upClassName", "Allen");
            rootMap.put("lowClassName", "allen");
            rootMap.put("chineseName", "艾伦");
            ArrayList<Col> list=new ArrayList<Col>();
            Col col=new Col(0,"id",Type.get("INT"));
            col.setMethodName("Id");
            col.setName("id");
            Col col2=new Col(0,"name",Type.get("VERCHAR"));
            col2.setMethodName("Name");
            col2.setName("name");
            list.add(col);
            list.add(col2);
            rootMap.put("columns",list);
            rootMap.put("tableName","allenDb");
            rootMap.put("packageName", "allen.auto.code");

            //3.0 获取模板
            Template template = cfg.getTemplate("bean.ftl");

            File testFile = new File("./template", "Allen.java");
            //4.0 给模板绑定数据模型
            Writer out = new FileWriter(testFile);
            template.process(rootMap, out);
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
