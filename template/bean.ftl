package ${packageName};

import java.io.Serializable;
/**
 * ${chineseName}实体类
 * @author WangJianQiang
 * @date ${.now?date} ${.now?time}
 */
public class ${upClassName}Bean implements Serializable {

	public final static String FINAL_TABLE_NAME = "${tableName}";
<#list columns as col>
    private ${col.type.javaType} ${col.fieldName};
</#list>
    private String tmp_creatorName;

	private String tmp_updateUserName;
<#list columns as col>
    public ${col.type.javaType} get${col.methodName}() {
		return ${col.fieldName};
	}

	public void set${col.methodName}(${col.type.javaType} ${col.fieldName}) {
		this.${col.fieldName} = ${col.fieldName};
	}
</#list>
    public String getTmp_creatorName() {
		return tmp_creatorName;
	}

	public void setTmp_creatorName(String tmp_creatorName) {
		this.tmp_creatorName = tmp_creatorName;
	}

	public String getTmp_updateUserName() {
		return tmp_updateUserName;
	}

	public void setTmp_updateUserName(String tmp_updateUserName) {
		this.tmp_updateUserName = tmp_updateUserName;
	}
}