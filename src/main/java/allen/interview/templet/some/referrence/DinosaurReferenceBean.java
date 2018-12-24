package allen.interview.templet.some.referrence;

import java.io.Serializable;

public class DinosaurReferenceBean implements Serializable {

	private static final long serialVersionUID = -8601575290764177360L;
	public static final int FINAL_DELETE_FLAG_VALID = 0;
	public static final int FINAL_DELETE_FLAG_INVALID = 1;

	public final static String FINAL_TABLE_NAME = "dinosaurreference";

	private Integer drid;
	private String drname;
	private String icon;
	private Integer iconwidth;
	private Integer iconheight;

	/**
	 * 数值
	 */
	private Double number;
	/**
	 * 单位: kg，t
	 */
	private String unit;
	/**
	 * 测量：1重量，2长度，3高度
	 */
	private String metering;
	private Integer creatorid;
	private String createtime;
	private Integer updateuserid;
	private String updatetime;
	private Integer deleteflag;
	private String tmp_creatorName;
	private String tmp_updateUserName;

	public Integer getDrid() {
		return drid;
	}

	public void setDrid(Integer drid) {
		this.drid = drid;
	}

	public String getDrname() {
		return drname;
	}

	public void setDrname(String drname) {
		this.drname = drname;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getIconwidth() {
		return iconwidth;
	}

	public void setIconwidth(Integer iconwidth) {
		this.iconwidth = iconwidth;
	}

	public Integer getIconheight() {
		return iconheight;
	}

	public void setIconheight(Integer iconheight) {
		this.iconheight = iconheight;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMetering() {
		return metering;
	}

	public void setMetering(String metering) {
		this.metering = metering;
	}

	public Integer getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(Integer creatorid) {
		this.creatorid = creatorid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getUpdateuserid() {
		return updateuserid;
	}

	public void setUpdateuserid(Integer updateuserid) {
		this.updateuserid = updateuserid;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(Integer deleteflag) {
		this.deleteflag = deleteflag;
	}

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