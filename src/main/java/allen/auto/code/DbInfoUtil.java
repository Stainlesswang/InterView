package allen.auto.code;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2019年01月02日 16:48
 */

import java.sql.*;
import java.util.*;

/**
 * <p>Description: 获取数据库基本信息的工具类</p>
 *
 * @author qxl
 * @date 2016年7月22日 下午1:00:34
 */
public class DbInfoUtil {

	/**
	 * 根据数据库的连接参数，获取指定表的基本信息：字段名、字段类型、字段注释
	 *
	 * @param driver 数据库连接驱动
	 * @param url    数据库连接url
	 * @param user   数据库登陆用户名
	 * @param pwd    数据库登陆密码
	 * @param table  表名
	 * @return Map集合
	 */
	public static List<Col> getTableInfo(String driver, String url, String user, String pwd, String table) {
		List<Col> colList = new ArrayList<>();
		HashSet<String> nameSet = new HashSet<>();
		Connection conn = null;
		DatabaseMetaData dbmd = null;

		try {
			conn = getConnections(driver, url, user, pwd);

			dbmd = conn.getMetaData();
			ResultSet resultSet = dbmd.getTables(null, "%", table, new String[]{"TABLE"});

			while (resultSet.next()) {
				String tableName = resultSet.getString("TABLE_NAME");
				if (tableName.equals(table)) {
					ResultSet rs = conn.getMetaData().getColumns(null, conn.getMetaData().getUserName(), tableName.toUpperCase(), "%");
					while (rs.next()) {
						String colName = rs.getString("COLUMN_NAME");
						if (nameSet.contains(colName)) {
							break;
						} else {
							nameSet.add(colName);
						}
						//System.out.println("字段名："+rs.getString("COLUMN_NAME")+"--字段注释："+rs.getString("REMARKS")+"--字段数据类型："+rs.getString("TYPE_NAME"));
						String remarks = rs.getString("REMARKS");
						if (remarks == null || remarks.equals("")) {
							remarks = colName;
						}
						String dbType = rs.getString("TYPE_NAME");
						Col col = new Col();
						col.setFieldName(colName);
						col.setMethodName(dealClassName(colName));
						col.setDesc(remarks);
						col.setType(Type.get(dbType));
						colList.add(col);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null!=conn){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return colList;
	}

	//获取连接
	private static Connection getConnections(String driver, String url, String user, String pwd) throws Exception {
		Connection conn = null;
		try {
			Properties props = new Properties();
			props.put("remarksReporting", "true");
			props.put("user", user);
			props.put("password", pwd);
			Class.forName(driver);
			conn = DriverManager.getConnection(url, props);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return conn;
	}

	//其他数据库不需要这个方法 oracle和db2需要
	private static String getSchema(Connection conn) throws Exception {
		String schema;
		schema = conn.getMetaData().getUserName();
		if ((schema == null) || (schema.length() == 0)) {
			throw new Exception("ORACLE数据库模式不允许为空");
		}
		return schema.toUpperCase().toString();

	}

	public static void main(String[] args) {


		String table = "appletmodify";
		String url = "";
		String user = "";
		String pwd = "";
		String driver = "com.mysql.jdbc.Driver";
		//mysql
		/*
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String pwd = "123456";
		String url = "jdbc:mysql://localhost/onlinexam"
				+ "?useUnicode=true&characterEncoding=UTF-8";
		String table = "oe_student";
		*/

		List<Col> list = getTableInfo(driver, url, user, pwd, table);
		for (Col o : list) {
			System.out.println(o.getFieldName());
		}
	}

	private static String dealClassName(String className) {
		String first = className.substring(0, 1).toUpperCase();
		String rest = className.substring(1, className.length());
		return new StringBuffer(first).append(rest).toString();
	}
}