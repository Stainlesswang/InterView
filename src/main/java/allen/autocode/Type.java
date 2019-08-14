package allen.autocode;


import java.util.HashMap;

import allen.util.StringUtils;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2018年12月25日 10:11
 */
public class Type {
	private String sqlType;
	private String javaType;
	private static HashMap<String, String> typeMap = new HashMap<>();

	static {
		//基本的sql数据类型
		typeMap.put("VARCHAR", "String");
		typeMap.put("CHAR", "String");
		typeMap.put("BLOB", "Integer");
		typeMap.put("TEXT", "String");
		typeMap.put("INT", "Integer");
		typeMap.put("INT UNSIGNED", "Integer");
		typeMap.put("INTEGER", "Integer");
		typeMap.put("INTEGER UNSIGNED", "Integer");
		typeMap.put("TINYINT", "Integer");
		typeMap.put("TINYINT UNSIGNED", "Integer");
		typeMap.put("SMALLINT", "Integer");
		typeMap.put("SMALLINT UNSIGNED", "Integer");
		typeMap.put("MEDIUMINT", "Integer");
		typeMap.put("MEDIUMINT UNSIGNED", "Integer");
		typeMap.put("FLOAT", "Float");
		typeMap.put("FLOAT UNSIGNED", "Float");
		typeMap.put("DOUBLE", "Double");
		typeMap.put("BIT", "Integer");
		typeMap.put("BIGINT", "java.math.BigInteger");
		typeMap.put("DECIMAL", "java.math.BigDecimal");
		typeMap.put("BOOLEAN", "Integer");
		typeMap.put("DATE", "String");
		typeMap.put("TIME", "String");
		typeMap.put("DATETIME", "String");
		typeMap.put("TIMESTAMP", "String");
		typeMap.put("YEAR", "String");
		typeMap.put("OTHER", "String");
	}

	/**
	 * @param sqlType
	 * @param javaType
	 * @author hym
	 * @time 2017年7月20日 下午5:21:52
	 */
	public Type(String sqlType, String javaType) {
		this.sqlType = sqlType.toLowerCase();
		this.javaType = javaType;
	}

	public String getSqlType() {
		return sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}


	/**
	 * 当返回值找不到的时候，直接返回other-string
	 *
	 * @param sqlType
	 * @return
	 * @author hym
	 * @time 2017年7月20日下午5:22:09
	 */
	public static Type get(String sqlType) {
		String javaType = typeMap.get(sqlType.toUpperCase());
		if (StringUtils.isEmpty(javaType)) {
			javaType = typeMap.get("OTHER");
		}
		return new Type(sqlType, javaType);
	}

}
