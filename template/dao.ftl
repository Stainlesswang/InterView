package ${packageName};

import java.util.List;
import java.util.Map;

/**
 * ${chineseName}连接层接口
 * @author WangJianQiang
 * @date 2018年12月25日 上午08:59:53
 */
public interface ${upClassName}Dao extends BaseDao<${upClassName}Bean> {

		/**
		* 条件查询相应${chineseName}
		* @param params
		* @return java.util.List<${upClassName}Bean>
		* @author WangJianQiang
		* @date 2018年12月25日 上午09:00:18
		*/
		public List<${upClassName}Bean> search${upClassName}(Map<String, Object> params) throws Exception;

		/**
		* 条件查询对应数据的条数
		* @param params
		* @return long
		* @author WangJianQiang
		* @date 2018年12月25日 上午09:00:44
		*/
		public long getCount${upClassName}(Map<String, Object> params) throws Exception;

		/**
		* 更新${chineseName}
		* @param bean
		* @return int
		* @author WangJianQiang
		* @date 2018年12月25日 上午09:00:56
		*/
		public int update${upClassName}(${upClassName}Bean bean) throws Exception;

		/**
		* 增加${chineseName}
		* @param bean
		* @return long
		* @author WangJianQiang
		* @date 2018年12月25日 上午09:01:12
		*/
		public long add${upClassName}(${upClassName}Bean bean) throws Exception;

		/**
		* 删除${chineseName}
		* @param bean
		* @return int
		* @author WangJianQiang
		* @date 2018年12月25日 上午09:01:20
		*/
		public int delete${upClassName}(${upClassName}Bean bean) throws Exception;

		/**
		* 获取最大序号
		* @param aacid
		* @return int
		* @author WangJianQiang
		* @date 2018年12月25日 上午09:01:35
		*/
		public int getMaxIdx(Integer aacid);


		/**
		* 根据id获取${chineseName}
		* @param id
		* @return ${packageName}.bean.${upClassName}Bean
		* @author WangJianQiang
		* @date 2018年12月25日 上午09:02:12
		*/
		public ${upClassName}Bean get${upClassName}ById(Integer id) throws Exception;

		/**
		* 根据名称获取${chineseName}
		* @param name
		* @return ${packageName}.bean.${upClassName}Bean
		* @author WangJianQiang
		* @date 2018年12月25日 上午09:02:26
		*/
		public ${upClassName}Bean get${upClassName}ByName(String name);


		}