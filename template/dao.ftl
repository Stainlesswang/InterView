package ${packageName};

import java.util.List;
import java.util.Map;

/**
 * ${chineseName}连接层接口
 * @author WangJianQiang
 * @date  ${.now?date} ${.now?time}
 */
public interface ${upClassName}Dao extends BaseDao<${upClassName}Bean> {

		/**
		* 条件查询相应${chineseName}
		* @param params
		* @return java.util.List<${upClassName}Bean>
		* @author WangJianQiang
		* @date  ${.now?date} ${.now?time}
		*/
		public List<${upClassName}Bean> search${upClassName}(Map<String, Object> params) throws Exception;

		/**
		* 条件查询对应数据的条数
		* @param params
		* @return long
		* @author WangJianQiang
		* @date  ${.now?date} ${.now?time}
		*/
		public long getCount${upClassName}(Map<String, Object> params) throws Exception;

		/**
		* 更新${chineseName}
		* @param bean
		* @return int
		* @author WangJianQiang
		* @date  ${.now?date} ${.now?time}
		*/
		public int update${upClassName}(${upClassName}Bean bean) throws Exception;

		/**
		* 增加${chineseName}
		* @param bean
		* @return long
		* @author WangJianQiang
		* @date  ${.now?date} ${.now?time}
		*/
		public long add${upClassName}(${upClassName}Bean bean) throws Exception;

		/**
		* 删除${chineseName}
		* @param bean
		* @return int
		* @author WangJianQiang
		* @date  ${.now?date} ${.now?time}
		*/
		public int delete${upClassName}(${upClassName}Bean bean) throws Exception;



		/**
		* 根据id获取${chineseName}
		* @param id
		* @return ${upClassName}Bean
		* @author WangJianQiang
		* @date  ${.now?date} ${.now?time}
		*/
		public ${upClassName}Bean get${upClassName}ById(Integer id) throws Exception;

		/**
		* 根据名称获取${chineseName}
		* @param name
		* @return ${upClassName}Bean
		* @author WangJianQiang
		* @date  ${.now?date} ${.now?time}
		*/
		public ${upClassName}Bean get${upClassName}ByName(String name);

        <#if idx??>

        /**
		* 根据id更新排序号
		* @return int
		* @author WangJianQiang
		* @date
		* @Param id
		* @Param newIdx
		* @Param userId
		* @Param time
		*/
		public int updateIdx(String id, int newIdx, String userId, String time);

		/**
		* 区间内序号加一
		* @return int
		* @author WangJianQiang
		* @date
		* @Param beginIdx
		* @Param endIdx
		* @Param userId
		* @Param time
		*/
		public int incIdx(int beginIdx, int endIdx, String userId, String time<#if typeid??>, Integer ${typeid}</#if>);

		/**
		* 区间内序号减一
		* @return int
		* @author WangJianQiang
		* @date
		* @Param beginIdx
		* @Param endIdx
		* @Param userId
		* @Param time
		*/
		public int decIdx(int beginIdx, int endIdx, String userId, String time<#if typeid??>, Integer ${typeid}</#if>);

		/**
		* 获取最大序号
		* @return int
		* @author WangJianQiang
		* @date
		* @Param
		*/
		public int maxIdx(<#if typeid??>Integer ${typeid}</#if>);
        </#if>

		}