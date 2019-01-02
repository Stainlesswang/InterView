package allen.auto.code;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2019年01月02日 15:10
 */
public class GenerationJavaCode {

	private String url;
	private String name;
	private String passWord;
	private String driver;
	private String sql;
	private String tableName;
	private String upClassName;
	private String lowClassName;
	private String templateDir;
	private String autoGeneratedFile;
	//包的基础路径 比如com.xx等
	private String basePath;
	private static String[][] fileNameArray = new String[6][2];


	static {
		fileNameArray[0][0] = "entityTemplate.ftl";
		fileNameArray[0][1] = ".java";


		fileNameArray[1][0] = "serviceTemplate.ftl";
		fileNameArray[1][1] = "Service.java";


		fileNameArray[2][0] = "serviceImplTemplate.ftl";
		fileNameArray[2][1] = "ServiceImpl.java";


		fileNameArray[3][0] = "daoTemplate.ftl";
		fileNameArray[3][1] = "Dao.java";


		fileNameArray[4][0] = "daoImplTemplate.ftl";
		fileNameArray[4][1] = "DaoImpl.java";

		fileNameArray[5][0] = "mapper.ftl";
		fileNameArray[5][1] = "Mapper.xml";
	}


	public GenerationJavaCode(String url, String name, String passWord, String driver, String tableName,
	                          String autoGeneratedFile, String basePath,String upClassName,String lowClassName) {
		this.upClassName=upClassName;
		this.lowClassName=lowClassName;
		this.url = url;
		this.name = name;
		this.passWord = passWord;
		this.driver = driver;
		this.sql = "select * from " + tableName;
		this.tableName = tableName;
		this.templateDir = this.getClass().getResource("").getPath().replace("target/classes/com/xx/common/code/autocode/", "")
				+ "src/main/java/com/xx/common/code/template";
		this.autoGeneratedFile = autoGeneratedFile;
		this.basePath = basePath;
	}


	public void autoGenerationJavaCode() throws IOException, TemplateException, ClassNotFoundException,
			SQLException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
		cfg.setDefaultEncoding("utf-8");

		String className = tableName;

		String fileName = className;
		// Map<String, Object> columnMap = getColumn();
//设置模板文件路径
		cfg.setDirectoryForTemplateLoading(new File(templateDir));

		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("className", className);
//rootMap.put("columnMap", columnMap);
		rootMap.put("columns", getColumnList());
		rootMap.put("basePath", basePath);

		for (int i = 0; i < fileNameArray.length; i++) {
			String packageName = null;
			String filePath = null;
			//设定包名
			switch (fileNameArray[i][0]) {
				case "entityTemplate.ftl":
					packageName = basePath + ".entity";
					filePath = autoGeneratedFile + "/bean";
					break;
				case "daoTemplate.ftl":
					packageName = basePath + ".dao";
					filePath = autoGeneratedFile + "/dao";
					break;
				case "daoImplTemplate.ftl":
					packageName = basePath + ".dao.impl";
					filePath = autoGeneratedFile + "/dao/impl";
					break;
				case "serviceTemplate.ftl":
					packageName = basePath + ".service";
					filePath = autoGeneratedFile + "/service";
					break;
				case "serviceImplTemplate.ftl":
					packageName = basePath + ".service.impl";
					filePath = autoGeneratedFile + "/service/impl";
					break;
				case "mapper.ftl":
					packageName = basePath;
					filePath = autoGeneratedFile + "/html";
					rootMap.put("tableName", tableName);
					break;
			}
			rootMap.put("packageName", packageName);
			File dir = new File(filePath);
			//检查目录是否存在，不存在则创建
			if (!dir.exists()) {
				dir.mkdir();
			}
			Template temp = cfg.getTemplate(fileNameArray[i][0]);

			File docFile = new File(filePath + "//" + fileName + fileNameArray[i][1]);
			Writer docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));

			//输出文件
			temp.process(rootMap, docout);
		}
		System.out.println("==============所有的文件已成功生成===============");
	}

	public List<Col> getColumnList() throws ClassNotFoundException, SQLException {
		Connection conn;
		PreparedStatement pStemt = null;
		Class.forName(driver);
		conn = DriverManager.getConnection(url, name, passWord);
		pStemt = conn.prepareStatement(sql);
		ResultSetMetaData rsmd = pStemt.getMetaData();

		List<Col> listColumn = new ArrayList<Col>();
		int size = rsmd.getColumnCount();
		for (int i = 0; i < size; i++) {
			// String columnName = dealColumnName(rsmd, i);
			Col col = getColumnInfo(rsmd, i);
			if (i == size - 1) {
				col.setHasNext(false);
			}

			listColumn.add(col);
		}
		conn.close();
		return listColumn;
	}

	/**
	 * 获取列信息  需要设置多属性，该处生成mapper.xml的时候需要各种属性
	 *
	 * @param rsmd
	 * @param i
	 * @return
	 * @throws SQLException
	 * @author hym
	 * @time 2017年7月20日下午7:48:39
	 */
	private Col getColumnInfo(ResultSetMetaData rsmd, int i) throws SQLException {
		String columnName = rsmd.getColumnName(i + 1).toLowerCase();
		String charAfterLine = String.valueOf(columnName.charAt((columnName.indexOf("_") + 1)));
		String convertedChar = charAfterLine.toUpperCase();
		String fieldName = columnName.replace("_" + charAfterLine, convertedChar);//映射到类中的名称
		String columntype = rsmd.getColumnTypeName(i + 1);//sql的列类型
		Type type = new Type(columntype, Type.get(columntype).getJavaType());
		Col col = new Col(i, fieldName, type);
		col.setMethodName(dealClassName(fieldName));
		col.setName(columnName);//表中的列名
		if (columnName.equalsIgnoreCase("id")) {
			col.setPK("yes");
		}
		return col;
	}



	public static void main(String[] args) {

	}

	/**
	 * 将类名转换为文件名，java公共类名与其文件名应该相同，这里将首字母转换为大写 如operateLog 转换后为 OperateLog
	 *
	 * @param className
	 * @return
	 * @author hym
	 * @time 2017年7月22日下午4:22:25
	 */
	private String dealClassName(String className) {
		String first = className.substring(0, 1).toUpperCase();
		String rest = className.substring(1, className.length());
		return new StringBuffer(first).append(rest).toString();
	}

}
