package allen.auto.code;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2019年01月02日 15:53
 */
public class CodeTest {
	public static void main(String[] args) throws ClassNotFoundException, TemplateException, SQLException, IOException {
		String url = "jdbc:mysql://:3306/young";
		String username = "";
		String password = "";
		String driver = "com.mysql.jdbc.Driver";
		String tableName = "apppinyinclassify";
		String chineseName = "拼音分类";
		String upClassName = "AppPinYinClassify";
		String lowClassName = "appPinYinClassify";
		//排重需要的参数名称
		String searchCondition = "name";
		String basePath = "com.chinaso.modules.app.pinyin";
		String generateFilePath = System.getProperty("user.dir") + "/src/main/java/" + basePath.replace(".", "/");
		GenerationJavaCode autoGenerationJavaCode =
				new GenerationJavaCode(url, username, password, driver,
						tableName, generateFilePath, basePath, upClassName,
						lowClassName, chineseName, searchCondition);
		autoGenerationJavaCode.autoGenerationJavaCode();
	}
}
