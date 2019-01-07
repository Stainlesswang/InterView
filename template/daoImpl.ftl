package ${packageName};

import com.chinaso.common.constant.GlobalFunctionConstants;
import com.chinaso.common.global.bean.BeanTools;
import com.chinaso.modules.db.YoungManage;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${chineseName}数据库连接层实现
 *
 * @author WangJianQiang
 * @date ${.now?date} ${.now?time}
 */
@Repository
public class ${upClassName}DaoImpl extends YoungManage<${upClassName}Bean> implements ${upClassName}Dao {

	/*
	*
	* (non-Javadoc)
	* @see ${packageName?substring(0,packageName?index_of(".impl"))}#search${upClassName}[params]
	* @author WangJianQiang
	* @date ${.now?date} ${.now?time}
	* @param params
	* @return java.util.List<${upClassName}Bean>
		*/
		@Override
		public List<${upClassName}Bean> search${upClassName}(Map<String, Object> params) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT tsd.*  FROM " + ${upClassName}Bean.FINAL_TABLE_NAME + " tsd ");
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
		return mNamedReadJdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<${upClassName}Bean>(${upClassName}Bean.class));
			}

			/*
			*
			* (non-Javadoc)
			* @see ${packageName?substring(0,packageName?index_of(".impl"))}#getCount${upClassName}[params]
			* @author WangJianQiang
			* @date ${.now?date} ${.now?time}
			* @param params
			* @return long
			*/
			@Override
			public long getCount${upClassName}(Map<String, Object> params) throws Exception {
			StringBuffer sqlCount = new StringBuffer();
			sqlCount.append("SELECT COUNT(*) FROM " + ${upClassName}Bean.FINAL_TABLE_NAME + " tsd ");
			sqlCount.append(" WHERE deleteflag = 0 ");
			this.addQueryCondition(sqlCount, params);
			return mNamedReadJdbcTemplate.queryForObject(sqlCount.toString(), params, Integer.class);
			}

			/*
			*
			* (non-Javadoc)
			* @see ${packageName?substring(0,packageName?index_of(".impl"))}#update${upClassName}[bean]
			* @author WangJianQiang
			* @date ${.now?date} ${.now?time}
			* @param bean
			* @return int
			*/
			@Override
			public int update${upClassName}(${upClassName}Bean bean) throws Exception {
			return updateBeanByPrimary(BeanTools.beanToMapForDaoAll(bean), "${id}");
			}

			/*
			*
			* (non-Javadoc)
			* @see ${packageName?substring(0,packageName?index_of(".impl"))}#add${upClassName}[bean]
			* @author WangJianQiang
			* @date ${.now?date} ${.now?time}
			* @param bean
			* @return long
			*/
			@Override
			public long add${upClassName}(${upClassName}Bean bean) throws Exception {
			return insertBean(BeanTools.beanToMapForDaoNotNull(bean), "${id}");
			}

			/*
			*
			* (non-Javadoc)
			* @see ${packageName?substring(0,packageName?index_of(".impl"))}#delete${upClassName}[bean]
			* @author WangJianQiang
			* @date ${.now?date} ${.now?time}
			* @param bean
			* @return int
			*/
			@Override
			public int delete${upClassName}(${upClassName}Bean bean) throws Exception {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("${id}", bean.getAasid());
			params.put("updateuserid", bean.getUpdateuserid());
			params.put("deleteflag", GlobalFunctionConstants.FINAL_DELETE_FLAG_1);
			String sqlDelete = "UPDATE " + ${upClassName}Bean.FINAL_TABLE_NAME + " SET deleteflag = :deleteflag, updateuserid = :updateuserid WHERE ${id} = :${id}";
			return mNamedWriteJdbcTemplate.update(sqlDelete, params);
			}

			/*
			*
			* (non-Javadoc)
			* @see ${packageName?substring(0,packageName?index_of(".impl"))}#get${upClassName}ById[id]
			* @author WangJianQiang
			* @date ${.now?date} ${.now?time}
			* @param id
			* @return ${upClassName}Bean
			*/
			@Override
			public ${upClassName}Bean get${upClassName}ById(Integer id) throws Exception {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("${id}", id);
			String sql = "SELECT * FROM " + ${upClassName}Bean.FINAL_TABLE_NAME + " a  WHERE a.${id} = :${id} AND deleteflag = 0";
			try {
			return mNamedReadJdbcTemplate.queryForObject(sql, paramMap, new BeanPropertyRowMapper<${upClassName}Bean>(${upClassName}Bean.class));
				} catch (EmptyResultDataAccessException e) {
				return null;
				}
				}

                <#if condition??>
                    	/*
				*
				* (non-Javadoc)
				* @see ${packageName?substring(0,packageName?index_of(".impl"))}#get${upClassName}ByName[name]
				* @author WangJianQiang
				* @date ${.now?date} ${.now?time}
				* @param name
				* @return ${upClassName}Bean
				*/
				@Override
				public ${upClassName}Bean get${upClassName}ByName(String ${condition}) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("${condition}", ${condition});
				String sql = "SELECT * FROM " + ${upClassName}Bean.FINAL_TABLE_NAME + " a  WHERE  a.deleteflag = 0  AND a.${condition} = :${condition}";
				try {
				return mNamedReadJdbcTemplate.queryForObject(sql, paramMap, new BeanPropertyRowMapper<${upClassName}Bean>(${upClassName}Bean.class));
					} catch (EmptyResultDataAccessException e) {
					return null;
					}
					}
                </#if>

					/**
					* 增加查询条件
					*
					* @return java.lang.String
					* @author WangJianQiang
					* @date
					* @Param condtion
					* @Param params
					*/
					private String addQueryCondition(StringBuffer condtion, Map<String, Object> params) {
					for (Map.Entry<String, Object> entry : params.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					if (value == null) {
					continue;
					}
					if ("spname".equals(key)) {
					condtion.append(" AND tsd.").append(key).append(" LIKE CONCAT('%',:").append(key).append(",'%')");
					}
					if ("status".equals(key)) {
					condtion.append(" AND tsd.").append(key).append(" =:status");
					}

					}
					return condtion.toString();
					}

					}
