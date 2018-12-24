package allen.interview.templet.dao.impl;

import com.chinaso.modules.db.YoungManage;
import com.chinaso.modules.search.bean.AppAnimalClassifyBean;
import com.chinaso.modules.search.dao.AppAnimalClassifyDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class AppAnimalClassifyDaoImpl extends YoungManage<AppAnimalClassifyBean> implements AppAnimalClassifyDao {

	
	@Override
	public List<AppAnimalClassifyBean> getAppAnimalClassifyList() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT a.* FROM ").append(AppAnimalClassifyBean.FINAL_TABLE_NAME).append(" a WHERE a.deleteFlag = 0 ORDER BY a.shwid");
		return mNamedReadJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<AppAnimalClassifyBean>(AppAnimalClassifyBean.class));
	}


	@Override
	public List<AppAnimalClassifyBean> searchAppAnimalClassify(Map<String, Object> params) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT tsd.* FROM " + AppAnimalClassifyBean.FINAL_TABLE_NAME + " tsd  ");
		sql.append(" WHERE deleteflag = 0 ");
		if (params.get("shwname") != null) {
			sql.append(" AND tsd.shwname").append(" LIKE CONCAT('%',:").append("shwname").append(",'%')");
		}
		if (params.get("_order_by") != null) {
			sql.append(" ORDER BY ").append(params.get("_order_by"));
		}
//		Object pageNow = params.get("pageNow");
//		Object pageSize = params.get("pageSize");
//		if (pageNow != null && pageSize != null && pageNow instanceof Integer && pageSize instanceof Integer) {
//			Integer _pageNow = (Integer) pageNow;
//			Integer _pageSize = (Integer) pageSize;
//			sql.append(" LIMIT " + (_pageNow - 1) * _pageSize + "," + _pageSize);
//		}
		return mNamedReadJdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<AppAnimalClassifyBean>(AppAnimalClassifyBean.class));
	}


	
	@Override
	public List<AppAnimalClassifyBean> searchAppAnimalClassifyByName(String name) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT a.* FROM " + AppAnimalClassifyBean.FINAL_TABLE_NAME + " a ");
		sql.append(" WHERE 1=1 AND a.`deleteflag` = :deleteflag");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deleteflag", AppAnimalClassifyBean.FINAL_DELETE_FLAG_VALID);
		if (null != name && !"".equals(name.trim())) {
			sql.append(" AND a.`shwname` = :shwname");
			params.put("shwname", name.trim());
		}
		return mNamedReadJdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<AppAnimalClassifyBean>(AppAnimalClassifyBean.class));
	}


	
	@Override
	public long getCountAppAnimalClassify(Map<String, Object> params) throws Exception {
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("SELECT COUNT(*) FROM " + AppAnimalClassifyBean.FINAL_TABLE_NAME + " tsd");
		sqlCount.append(" WHERE deleteflag= 0 ");
		return mNamedReadJdbcTemplate.queryForObject(sqlCount.toString(), params, Integer.class);
	}


	
	@Override
	public AppAnimalClassifyBean getAppAnimalClassifyById(Integer shwid) {
		try {
			String sql = "SELECT * FROM " + AppAnimalClassifyBean.FINAL_TABLE_NAME + " WHERE shwid = :shwid AND deleteflag = 0";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("shwid", shwid);
			return mNamedReadJdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<AppAnimalClassifyBean>(AppAnimalClassifyBean.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}


	
	@Override
	public long insertAppAnimalClassify(AppAnimalClassifyBean bean) throws Exception {
		return (int) insertBean(bean, AppAnimalClassifyBean.FINAL_TABLE_NAME,
				"shwname,idx,creatorid,createtime,updateuserid,updatetime");
	}


	@Override
	public int updateAppAnimalClassify(AppAnimalClassifyBean bean) throws Exception {
		return updateBean(bean, AppAnimalClassifyBean.FINAL_TABLE_NAME, "shwname,isdisable,idx,updateuserid,updatetime", "shwid");
	}


	
	@Override
	public int deleteAppAnimalClassify(Integer shwid, Integer updateuserid) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("shwid", shwid);
		params.put("updateuserid", updateuserid);
		params.put("deleteflag", AppAnimalClassifyBean.FINAL_DELETE_FLAG_INVALID);
		String sql = "UPDATE " + AppAnimalClassifyBean.FINAL_TABLE_NAME + " SET deleteflag = :deleteflag, updateuserid = :updateuserid WHERE shwid = :shwid AND isdisable=0";
		return mNamedWriteJdbcTemplate.update(sql, params);
	}

	@Override
	public int updateIdx(String shwid, int newIdx, String userId, String time) {
		String sql = "UPDATE " + AppAnimalClassifyBean.FINAL_TABLE_NAME + " SET idx = :idx, updateUserId = :updateUserId WHERE shwid = :shwid ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("shwid", shwid);
		params.put("idx", newIdx);
		params.put("updateUserId", userId);
		return getNamedJdbc().update(sql, params);
	}

	
	@Override
	public int incIdx(int beginIdx, int endIdx, String userId, String time) {
		String sql = "UPDATE " + AppAnimalClassifyBean.FINAL_TABLE_NAME +
				" SET idx = (idx + 1), updateUserId = :updateUserId WHERE deleteflag= 0  AND idx >= :beginIdx AND idx <= :endIdx  ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beginIdx", beginIdx);
		params.put("endIdx", endIdx);
		params.put("updateUserId", userId);
		return getNamedJdbc().update(sql, params);
	}

	
	@Override
	public int decIdx(int beginIdx, int endIdx, String userId, String time) {
		String sql = "UPDATE " + AppAnimalClassifyBean.FINAL_TABLE_NAME +
				" SET idx = (idx - 1), updateUserId = :updateUserId WHERE deleteflag= 0  AND  idx >= :beginIdx AND idx <= :endIdx ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beginIdx", beginIdx);
		params.put("endIdx", endIdx);
		params.put("updateUserId", userId);
		return getNamedJdbc().update(sql, params);
	}

	
	@Override
	public int maxIdx() {
		String sql = "SELECT COUNT(*) FROM " + AppAnimalClassifyBean.FINAL_TABLE_NAME + " WHERE deleteflag= 0 ";
		Map<String, Object> params = new HashMap<String, Object>();
		return getNamedJdbc().queryForObject(sql, params, Integer.class);
	}

}
