package allen.auto.code;

import java.util.List;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2019年01月02日 14:49
 */
public class Col {
	private Integer index;
	private String name;
	private String fieldName;
	private String methodName;
	private String desc;
	private Type type;
	private String default_;
	private String isPK = "no";
	private String allowNull = "no";
	private Boolean hasNext = true;


	/**
	 * @return
	 * @author WangJianQiang
	 * 2019年01月02日1下午04:35:16午02:51:07
	 */
	public Col(Integer index, String fieldName, Type type) {
		this.index = index;
		this.fieldName = fieldName;
		this.type = type;
	}

	/**
	 * 构造校验
	 *
	 * @param tab
	 * @param index
	 * @param defs
	 * @param isColMap
	 * @author hym
	 * @time 2017年7月22日 下午5:19:20
	 */
	public Col(String tab, int index, List<Object> defs, Boolean isColMap) {
		int size = defs.size();
		this.index = index;
		if (size < 2) {
			throw new RuntimeException("表'" + tab + "'的第'" + index + "'字段没有定义字段名");
		}
		name = (String) defs.get(1);
		if (name == null || name.trim().length() == 0) {
			throw new RuntimeException("表'" + tab + "'的第'" + index + "'字段没有定义字段名");
		}
		desc = (String) defs.get(2);
		if (size < 4) {
			throw new RuntimeException("表'" + tab + "'的'" + name + "'字段没有定义类型");
		}
		String typeStr = (String) defs.get(3);
		if (typeStr == null || typeStr.trim().length() == 0) {
			throw new RuntimeException("表'" + tab + "'的'" + name + "'字段没有定义字类型");
		}
		typeStr = typeStr.trim().toLowerCase();
		type = Type.get(typeStr);
		if (type == null) {
			throw new RuntimeException("表'" + tab + "'的'" + name + "'字段类型定义错误");
		}
//		fieldName = format();
		if (size >= 6) {
			default_ = defs.get(5).toString().trim();
			if (default_.length() == 0) {
				default_ = null;
			}
		}
		if (size >= 7) {
			isPK = defs.get(6).toString().trim().toLowerCase();
			if (isPK.length() == 0) {
				isPK = "no";
			}
		}
		if (size >= 8) {
			allowNull = defs.get(7).toString().trim().toLowerCase();
			if (allowNull.length() == 0) {
				allowNull = "no";
			}
		}
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getName() {
		return new StringBuffer(name).toString();
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getDefault() {
		return default_;
	}

	public void setDefault(String default_) {
		this.default_ = default_;
	}

	public String getIsPK() {
		return isPK;
	}

	public void setPK(String isPK) {
		this.isPK = isPK;
	}

	public String getIsAllowNull() {
		return allowNull;
	}

	public void setAllowNull(String allowNull) {
		this.allowNull = allowNull;
	}

	public Boolean getHasNext() {
		return hasNext;
	}

	public void setHasNext(Boolean hasNext) {
		this.hasNext = hasNext;
	}
}
