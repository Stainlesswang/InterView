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
		String url = "jdbc:mysql://:4306/young";
		String username = "";
		String password = "";
		String driver = "com.mysql.cj.jdbc.Driver";


		String tableName = "sensorsdata";
		String chineseName = "神策推广";
		String upClassName = "SensorsData";
		String lowClassName = "sensorsData";
		//排重需要的参数名称
		String searchCondition =null;
		//是否加排序字段idx  排序是否需要同分类下排序 不需要设为空
		String idx=null; String typeid=null;
		String basePath = "com.chinaso.modules.app.poetry";

//		String tableName = "appsong";
//		String chineseName = "儿歌";
//		String upClassName = "AppSong";
//		String lowClassName = "appSong";
//		//排重需要的参数名称
//		String searchCondition ="asname";
//		//是否加排序字段idx  排序是否需要同分类下排序 不需要设为空
//		String idx=null; String typeid=null;
//		String basePath = "com.chinaso.modules.app.song";

//		String tableName = "appbriefstrokes";
//		String chineseName = "简笔画";
//		String upClassName = "AppBriefStrokes";
//		String lowClassName = "appBriefStrokes";
//		//排重需要的参数名称
//		String searchCondition ="absname";
//		//是否加排序字段idx  排序是否需要同分类下排序 不需要设为空
//		String idx="idx"; String typeid="abscid";
//		String basePath = "com.chinaso.modules.app.briefstrokes";

//		String tableName = "appbriefstrokesclassify";
//		String chineseName = "简笔画分类";
//		String upClassName = "AppBriefStrokesClassify";
//		String lowClassName = "appBriefStrokesClassify";
//		//排重需要的参数名称
//		String searchCondition ="abscname";
//		//是否加排序字段idx  排序是否需要同分类下排序 不需要设为空
//		String idx=null; String typeid=null;
//		String basePath = "com.chinaso.modules.app.briefstrokes";

//		String tableName = "appplanetclassify";
//		String chineseName = "星系";
//		String upClassName = "AppPlanetClassify";
//		String lowClassName = "appPlanetClassify";
//		//排重需要的参数名称
//		String searchCondition ="apcname";
//		//是否加排序字段idx  排序是否需要同分类下排序 不需要设为空
//		String idx=null; String typeid=null;
//		String basePath = "com.chinaso.modules.app.planet";

//		String tableName = "appplanet";
//		String chineseName = "星球";
//		String upClassName = "AppPlanet";
//		String lowClassName = "appPlanet";
//		//排重需要的参数名称
//		String searchCondition ="apname";
//		//是否加排序字段idx  排序是否需要同分类下排序 不需要设为空
//		String idx="idx"; String typeid="apcid";
//		String basePath = "com.chinaso.modules.app.planet";


		String generateFilePath = System.getProperty("user.dir") + "/src/main/java/" + basePath.replace(".", "/");
		GenerationJavaCode autoGenerationJavaCode =
				new GenerationJavaCode(url, username, password, driver,
						tableName, generateFilePath, basePath, upClassName,
						lowClassName, chineseName, searchCondition,idx,typeid);
		autoGenerationJavaCode.autoGenerationJavaCode();
	}
}
