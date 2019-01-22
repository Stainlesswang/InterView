package allen.auto.code;

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

	public Col() {
	}
	/**
	 * @return
	 * @author WangJianQiang
	 * 2019年01月02日1下午04:35:16
	 */
	public Col(Integer index, String fieldName, Type type) {
		this.index = index;
		this.fieldName = fieldName;
		this.type = type;
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
