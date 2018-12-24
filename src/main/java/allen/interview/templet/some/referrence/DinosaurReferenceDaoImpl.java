package allen.interview.templet.some.referrence;

import com.chinaso.modules.baike.dinosaur.bean.DinosaurReferenceBean;
import com.chinaso.modules.baike.dinosaur.dao.DinosaurReferenceDao;
import com.chinaso.modules.db.YoungManage;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class DinosaurReferenceDaoImpl extends YoungManage<DinosaurReferenceBean> implements DinosaurReferenceDao {
	/*
	 * 
	 * (non-Javadoc) 
	 * @see com.chinaso.modules.baike.dinosaur.dao#getDinosaurReferenceList[]
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:06:02
	 * @param 
	 * @return java.util.List<com.chinaso.modules.baike.dinosaur.bean.DinosaurReferenceBean>
	 */
	@Override
	public List<DinosaurReferenceBean> getDinosaurReferenceList() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT a.* FROM ").append(DinosaurReferenceBean.FINAL_TABLE_NAME).append(" a WHERE a.deleteFlag = 0 ORDER BY a.number desc");
		return mNamedReadJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<DinosaurReferenceBean>(DinosaurReferenceBean.class));
	}
	
	/*
	 * 
	 * (non-Javadoc) 
	 * @see com.chinaso.modules.baike.dinosaur.dao#searchDinosaurReference[params]
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:06:07
	 * @param params
	 * @return java.util.List<com.chinaso.modules.baike.dinosaur.bean.DinosaurReferenceBean>
	 */
	@Override
	public List<DinosaurReferenceBean> searchDinosaurReference(Map<String, Object> params) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT tsd.* FROM " + DinosaurReferenceBean.FINAL_TABLE_NAME + " tsd  ");
		sql.append(" WHERE deleteflag = 0 ");
		this.addQueryCondition(sql, params);
		if (params.get("_order_by") != null) {
			sql.append(" ORDER BY ").append(params.get("_order_by"));
		}
		Object pageNow = params.get("pageNow");
		Object pageSize = params.get("pageSize");
		if (pageNow != null && pageSize != null && pageNow instanceof Integer && pageSize instanceof Integer) {
			Integer _pageNow = (Integer) pageNow;
			Integer _pageSize = (Integer) pageSize;
			sql.append(" LIMIT " + (_pageNow - 1) * _pageSize + "," + _pageSize);
		}
		return mNamedReadJdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<DinosaurReferenceBean>(DinosaurReferenceBean.class));
	}

	/*
	 *
	 * (non-Javadoc)
	 * @see com.chinaso.modules.baike.dinosaur.dao#searchDinosaurReferenceByName[name]
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:06:11
	 * @param name
	 * @return java.util.List<com.chinaso.modules.baike.dinosaur.bean.DinosaurReferenceBean>
	 */
	@Override
	public List<DinosaurReferenceBean> searchDinosaurReferenceByName(String name) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT a.* FROM " + DinosaurReferenceBean.FINAL_TABLE_NAME + " a ");
		sql.append(" WHERE 1=1 AND a.`deleteflag` = :deleteflag");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deleteflag", DinosaurReferenceBean.FINAL_DELETE_FLAG_VALID);
		if (null != name && !"".equals(name.trim())) {
			sql.append(" AND a.`drname` = :drname");
			params.put("drname", name.trim());
		}
		return mNamedReadJdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<DinosaurReferenceBean>(DinosaurReferenceBean.class));
	}

	/*
	 * 
	 * (non-Javadoc) 
	 * @see com.chinaso.modules.baike.dinosaur.dao#getCountDinosaurReference[params]
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:06:17
	 * @param params
	 * @return long
	 */
	@Override
	public long getCountDinosaurReference(Map<String, Object> params) throws Exception {
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("SELECT COUNT(*) FROM " + DinosaurReferenceBean.FINAL_TABLE_NAME + " tsd");
		sqlCount.append(" WHERE deleteflag= 0 ");
		this.addQueryCondition(sqlCount, params);
		return mNamedReadJdbcTemplate.queryForObject(sqlCount.toString(), params, Integer.class);
	}


	
	@Override
	public DinosaurReferenceBean getDinosaurReferenceById(Integer drid) {
		try {
			String sql = "SELECT * FROM " + DinosaurReferenceBean.FINAL_TABLE_NAME + " WHERE drid = :drid AND deleteflag = 0";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("drid", drid);
			return mNamedReadJdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<DinosaurReferenceBean>(DinosaurReferenceBean.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}


	
	@Override
	public long insertDinosaurReference(DinosaurReferenceBean bean) throws Exception {
		return (int) insertBean(bean, DinosaurReferenceBean.FINAL_TABLE_NAME,
				"drname,icon,iconwidth,iconheight,number,unit,metering,creatorid,createtime,updateuserid,updatetime");
	}


	@Override
	public int updateDinosaurReference(DinosaurReferenceBean bean) throws Exception {
		return updateBean(bean, DinosaurReferenceBean.FINAL_TABLE_NAME, "drname,icon,iconwidth,iconheight,number,unit,metering,updateuserid,updatetime", "drid");
	}


	
	@Override
	public int deleteDinosaurReference(Integer drid, Integer updateuserid) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("drid", drid);
		params.put("updateuserid", updateuserid);
		params.put("deleteflag", DinosaurReferenceBean.FINAL_DELETE_FLAG_INVALID);
		String sql = "UPDATE " + DinosaurReferenceBean.FINAL_TABLE_NAME + " SET deleteflag = :deleteflag, updateuserid = :updateuserid WHERE drid = :drid ";
		return mNamedWriteJdbcTemplate.update(sql, params);
	}


	private String addQueryCondition(StringBuffer condtion, Map<String, Object> params) {
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value == null) {
				continue;
			}
			if ("drname".equals(key)) {
				condtion.append(" AND tsd.").append(key).append(" LIKE CONCAT('%',:").append(key).append(",'%')");
			}
			if ("metering".equals(key)) {
				condtion.append(" AND tsd.").append(key).append(" =:metering");
			}
		}
		return condtion.toString();
	}

}
