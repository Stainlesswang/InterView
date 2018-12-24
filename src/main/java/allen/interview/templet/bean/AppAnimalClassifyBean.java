package allen.interview.templet.bean;

import java.io.Serializable;

public class AppAnimalClassifyBean implements Serializable {

	public final static String FINAL_TABLE_NAME = "searchhotword";

	
	private Integer shwid;

	private String shwname;
	
	private Integer idx;
	
	private Integer isdisable;
	private Integer creatorid;
	private String createtime;
	private Integer updateuserid;
	private String updatetime;
	private Integer deleteflag;
	
	private String tmp_creatorName;
	
	private String tmp_updateUserName;

	public Integer getShwid() {
		return shwid;
	}

	public void setShwid(Integer shwid) {
		this.shwid = shwid;
	}

	public String getShwname() {
		return shwname;
	}

	public void setShwname(String shwname) {
		this.shwname = shwname;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public Integer getIsdisable() {
		return isdisable;
	}

	public void setIsdisable(Integer isdisable) {
		this.isdisable = isdisable;
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