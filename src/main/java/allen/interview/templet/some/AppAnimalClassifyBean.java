package allen.interview.templet.some;

import java.io.Serializable;

public class AppAnimalClassifyBean implements Serializable {

	private static final long serialVersionUID = 5104452574653031006L;
	public final static String FINAL_TABLE_NAME = "appanimalclassify";
	public static final int FINAL_DELETE_FLAG_VALID = 0;
	public static final int FINAL_DELETE_FLAG_INVALID = 1;


	private Integer aacid;

	private String aacname;
	
	private Integer idx;
	
	private Integer creatorid;
	private String createtime;
	private Integer updateuserid;
	private String updatetime;
	private Integer deleteflag;
	
	private String tmp_creatorName;
	
	private String tmp_updateUserName;

	public Integer getAacid() {
		return aacid;
	}

	public void setAacid(Integer aacid) {
		this.aacid = aacid;
	}

	public String getAacname() {
		return aacname;
	}

	public void setAacname(String aacname) {
		this.aacname = aacname;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
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