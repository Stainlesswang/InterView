package com.chinaso.modules.app.dao.impl;

import com.chinaso.common.constant.GlobalFunctionConstants;
import com.chinaso.common.global.bean.BeanTools;
import com.chinaso.modules.app.bean.AppAnimalSoundBean;
import com.chinaso.modules.app.dao.AppAnimalSoundDao;
import com.chinaso.modules.db.YoungManage;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动物数据数据库连接层实现
 *
 * @author WangJianQiang
 * @date 2018年12月25日 上午09:03:08
 */
@Repository
public class AppAnimalSoundDaoImpl extends YoungManage<AppAnimalSoundBean> implements AppAnimalSoundDao {

	/*
	*
	* (non-Javadoc)
	* @see com.chinaso.modules.app.dao#searchAppAnimalSound[params]
	* @author WangJianQiang
	* @date 2018年12月25日 上午09:03:12
	* @param params
	* @return java.util.List<com.chinaso.modules.app.bean.AppAnimalSoundBean>
		*/
		@Override
		public List<AppAnimalSoundBean> searchAppAnimalSound(Map<String, Object> params) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT tsd.*  FROM " + AppAnimalSoundBean.FINAL_TABLE_NAME + " tsd ");
		this.addQueryCondition(sql, params, true, true);
		return mNamedReadJdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<AppAnimalSoundBean>(AppAnimalSoundBean.class));
			}

			/*
			*
			* (non-Javadoc)
			* @see com.chinaso.modules.app.dao#getCountAppAnimalSound[params]
			* @author WangJianQiang
			* @date 2018年12月25日 上午09:03:16
			* @param params
			* @return long
			*/
			@Override
			public long getCountAppAnimalSound(Map<String, Object> params) throws Exception {
			StringBuffer sqlCount = new StringBuffer();
			sqlCount.append("SELECT COUNT(*) FROM " + AppAnimalSoundBean.FINAL_TABLE_NAME + " tsd ");
			this.addQueryCondition(sqlCount, params, false, false);
			return mNamedReadJdbcTemplate.queryForObject(sqlCount.toString(), params, Integer.class);
			}

			/*
			*
			* (non-Javadoc)
			* @see com.chinaso.modules.app.dao#updateAppAnimalSound[bean]
			* @author WangJianQiang
			* @date 2018年12月25日 上午09:03:20
			* @param bean
			* @return int
			*/
			@Override
			public int updateAppAnimalSound(AppAnimalSoundBean bean) throws Exception {
			return updateBeanByPrimary(BeanTools.beanToMapForDaoAll(bean), "aasid");
			}

			/*
			*
			* (non-Javadoc)
			* @see com.chinaso.modules.app.dao#addAppAnimalSound[bean]
			* @author WangJianQiang
			* @date 2018年12月25日 上午09:03:24
			* @param bean
			* @return long
			*/
			@Override
			public long addAppAnimalSound(AppAnimalSoundBean bean) throws Exception {
			return insertBean(BeanTools.beanToMapForDaoNotNull(bean), "aasid");
			}

			/*
			*
			* (non-Javadoc)
			* @see com.chinaso.modules.app.dao#deleteAppAnimalSound[bean]
			* @author WangJianQiang
			* @date 2018年12月25日 上午09:03:30
			* @param bean
			* @return int
			*/
			@Override
			public int deleteAppAnimalSound(AppAnimalSoundBean bean) throws Exception {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("idx", bean.getIdx());
			paramMap.put("aacid", bean.getAacid());
			String sql = "UPDATE " + AppAnimalSoundBean.FINAL_TABLE_NAME + " SET idx = idx - 1 WHERE idx > :idx AND aacid = :aacid ";
			mNamedWriteJdbcTemplate.update(sql, paramMap);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("aasid", bean.getAasid());
			params.put("updateuserid", bean.getUpdateuserid());
			params.put("deleteflag", GlobalFunctionConstants.FINAL_DELETE_FLAG_1);
			String sqlDelete = "UPDATE " + AppAnimalSoundBean.FINAL_TABLE_NAME + " SET deleteflag = :deleteflag, updateuserid = :updateuserid WHERE aasid = :aasid";
			return mNamedWriteJdbcTemplate.update(sqlDelete, params);
			}

			/*
			*
			* (non-Javadoc)
			* @see com.chinaso.modules.app.dao#getMaxIdx[aacid]
			* @author WangJianQiang
			* @date 2018年12月25日 上午09:03:35
			* @param aacid
			* @return int
			*/
			@Override
			public int getMaxIdx(Integer aacid) {
			String sql = "SELECT COUNT(*) FROM " + AppAnimalSoundBean.FINAL_TABLE_NAME + " WHERE aacid = :aacid AND deleteflag = 0 ";
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("aacid", aacid);
			return getNamedJdbc().queryForObject(sql, paramMap, Integer.class);
			}

			/*
			*
			* (non-Javadoc)
			* @see com.chinaso.modules.app.dao#deleteOneIdx[aacid, idx]
			* @author WangJianQiang
			* @date 2018年12月25日 上午09:03:40
			* @param aacid
			* @param idx
			* @return int
			*/
			@Override
			public int deleteOneIdx(Integer aacid, Integer idx) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("idx", idx);
			paramMap.put("aacid", aacid);
			String sql = "UPDATE " + AppAnimalSoundBean.FINAL_TABLE_NAME + " SET idx = idx - 1 WHERE idx > :idx AND aacid = :aacid ";
			return mNamedWriteJdbcTemplate.update(sql, paramMap);
			}


			/*
			*
			* (non-Javadoc)
			* @see com.chinaso.modules.app.dao#getAppAnimalSoundById[id]
			* @author WangJianQiang
			* @date 2018年12月25日 上午09:03:46
			* @param id
			* @return com.chinaso.modules.app.bean.AppAnimalSoundBean
			*/
			@Override
			public AppAnimalSoundBean getAppAnimalSoundById(Integer id) throws Exception {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("aasid", id);
			String sql = "SELECT * FROM " + AppAnimalSoundBean.FINAL_TABLE_NAME + " a  WHERE a.aasid = :aasid AND deleteflag = 0";
			try {
			return mNamedReadJdbcTemplate.queryForObject(sql, paramMap, new BeanPropertyRowMapper<AppAnimalSoundBean>(AppAnimalSoundBean.class));
				} catch (EmptyResultDataAccessException e) {
				return null;
				}
				}


				/*
				*
				* (non-Javadoc)
				* @see com.chinaso.modules.app.dao#getAppAnimalSoundByName[name]
				* @author WangJianQiang
				* @date 2018年12月25日 上午09:03:50
				* @param name
				* @return com.chinaso.modules.app.bean.AppAnimalSoundBean
				*/
				@Override
				public AppAnimalSoundBean getAppAnimalSoundByName(String name) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("aasname", name);
				String sql = "SELECT * FROM " + AppAnimalSoundBean.FINAL_TABLE_NAME + " a  WHERE  a.deleteflag = 0  AND a.aasname = :aasname";
				try {
				return mNamedReadJdbcTemplate.queryForObject(sql, paramMap, new BeanPropertyRowMapper<AppAnimalSoundBean>(AppAnimalSoundBean.class));
					} catch (EmptyResultDataAccessException e) {
					return null;
					}
					}

					/*
					*
					* (non-Javadoc)
					* @see com.chinaso.modules.app.dao#updateAppAnimalSoundIdxById[bean]
					* @author WangJianQiang
					* @date 2018年12月25日 上午09:03:53
					* @param bean
					* @return int
					*/
					@Override
					public int updateAppAnimalSoundIdxById(AppAnimalSoundBean bean) throws Exception {
					if (null != bean.getTmp_oldIdx()) {
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("idx", bean.getIdx());
					paramMap.put("oldIdx", bean.getTmp_oldIdx());
					paramMap.put("aacid", bean.getAacid());
					if (bean.getIdx() < bean.getTmp_oldIdx()) {
					String sql = "UPDATE " + AppAnimalSoundBean.FINAL_TABLE_NAME + " SET idx = idx + 1 WHERE idx >= :idx AND idx < :oldIdx AND aacid = :aacid ";
					mNamedWriteJdbcTemplate.update(sql, paramMap);
					} else if (bean.getIdx() > bean.getTmp_oldIdx()) {
					String sql = "UPDATE " + AppAnimalSoundBean.FINAL_TABLE_NAME + " SET idx = idx - 1 WHERE idx <= :idx AND idx > :oldIdx AND aacid = :aacid ";
					mNamedWriteJdbcTemplate.update(sql, paramMap);
					}
					}
					return updateBeanByPrimary(BeanTools.beanToMapForDaoAll(bean), "aasid");
					}

					/**
					* 增加查询条件
					* @param condtion
					* @param params
					* @param isOrderBy
					* @param isLimit
					* @return java.lang.String
					* @author WangJianQiang
					* @date 2018年12月25日 上午09:04:09
					*/
					private String addQueryCondition(StringBuffer condtion, Map<String, Object> params, boolean isOrderBy, boolean isLimit) {
					condtion.append(" WHERE 1=1 AND tsd.deleteflag=0 ");
					Map<String, Object> paramMap = new HashMap<String, Object>();
					if (null != params) {
					for (Map.Entry<String, Object> entry : params.entrySet()) {
					String key = entry.getKey();
					String keyLower = key.toLowerCase();
					Object value = entry.getValue();
					if (null == value) {
					continue;
					}
					if ("pagenow".equals(keyLower) || "pagesize".equals(keyLower)
					|| "sortname".equals(keyLower) || "sortorder".equals(keyLower) || "_order_by".equals(keyLower)) {
					continue;
					}
					if ("aasname".equals(key) && value instanceof String) {
					condtion.append(" and tsd.").append(key).append(" like CONCAT('%',:").append(key).append(",'%')");
					}
					if ("aacid".equals(key)) {
					condtion.append(" and tsd.").append(key).append("=:").append(key);
					}
					}
					}
					if (isOrderBy) {
					if (null != params.get("_order_by")) {
					condtion.append(" ORDER BY ");
					condtion.append(params.get("_order_by"));
					} else {
					if (null != params.get("sortName")) {
					condtion.append(params.get("sortName")).append(" ");
					condtion.append(ORDER_DESC.equals(params.get("sortOrder")) ? ORDER_DESC : ORDER_ASC);
					}
					}
					}
					if (isLimit) {
					Object pageNow = params.get("pageNow");
					Object pageSize = params.get("pageSize");
					if (null != pageNow && null != pageSize && pageNow instanceof Integer && pageSize instanceof Integer) {
					Integer _pageNow = (Integer) pageNow;
					Integer _pageSize = (Integer) pageSize;
					condtion.append(" LIMIT " + (_pageNow - 1) * _pageSize + "," + _pageSize);
					}
					}
					return condtion.toString();
					}
					}
