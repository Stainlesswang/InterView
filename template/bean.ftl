package com.chinaso.modules.app.bean;

import java.io.Serializable;
/**
 * 动物数据实体类
 * @author WangJianQiang
 * @date 2018年12月25日 上午08:45:50
 */
public class AppAnimalSoundBean implements Serializable {
	private static final long serialVersionUID = 4982769743468645245L;

	public final static String FINAL_TABLE_NAME = "appanimalsound";
	private Integer aasid;
	private Integer aacid;
	private String aasname;
	private String imgtfskey;
	private String audiourl;
	private Integer imgw;
	private Integer imgh;

	private Integer idx;

	private Integer creatorid;


	private String createtime;


	private Integer updateuserid;


	private String updatetime;


	private Integer deleteflag;

	private String tmp_aacName;
	private String tmp_creatorName;

	private String tmp_updateUserName;
	private Integer tmp_oldIdx;

	public Integer getAasid() {
		return aasid;
	}

	public void setAasid(Integer aasid) {
		this.aasid = aasid;
	}

	public Integer getAacid() {
		return aacid;
	}

	public void setAacid(Integer aacid) {
		this.aacid = aacid;
	}

	public String getAasname() {
		return aasname;
	}

	public void setAasname(String aasname) {
		this.aasname = aasname;
	}

	public String getImgtfskey() {
		return imgtfskey;
	}

	public void setImgtfskey(String imgtfskey) {
		this.imgtfskey = imgtfskey;
	}

	public String getAudiourl() {
		return audiourl;
	}

	public void setAudiourl(String audiourl) {
		this.audiourl = audiourl;
	}

	public Integer getImgw() {
		return imgw;
	}

	public void setImgw(Integer imgw) {
		this.imgw = imgw;
	}

	public Integer getImgh() {
		return imgh;
	}

	public void setImgh(Integer imgh) {
		this.imgh = imgh;
	}

	public String getTmp_aacName() {
		return tmp_aacName;
	}

	public void setTmp_aacName(String tmp_aacName) {
		this.tmp_aacName = tmp_aacName;
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

	public Integer getTmp_oldIdx() {
		return tmp_oldIdx;
	}

	public void setTmp_oldIdx(Integer tmp_oldIdx) {
		this.tmp_oldIdx = tmp_oldIdx;
	}
}