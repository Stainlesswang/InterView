package allen.auto.code;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2019年01月02日 15:53
 */
public class CodeTest {
	public static void main(String[] args) throws ClassNotFoundException, TemplateException, SQLException, IOException {
		String url="jdbc:mysql://10.10.160.5:3306/young";
		String username="java_admin";
		String password="GeHa5MT3I4tyqS0oRV30SVP3";
		String driver="com.mysql.jdbc.Driver";
		String tableName="userheadlinemodify";
		String chineseName="小程序修改";
		String upClassName="UserHeadlineModify";
		String lowClassName="userHeadlineModify";
		String basePath="com.chinaso.modules.applet";
		String generateFilePath=System.getProperty("user.dir")+"/src/main/java/"+basePath.replace(".", "/");
		GenerationJavaCode autoGenerationJavaCode=
				new GenerationJavaCode(url, username, password, driver, tableName, generateFilePath, basePath,upClassName,lowClassName,chineseName);
		 autoGenerationJavaCode.autoGenerationJavaCode();
	}
}
