package ${packageName};


import com.chinaso.common.ajax.AjaxResponse;
import com.chinaso.common.flexigrid.bean.FlexiGrid;

import java.util.List;

/**
 * ${chineseName}服务层接口
 *
 * @author WangJianQiang
 * @date ${.now?date} ${.now?time}
 */
public interface ${upClassName}Service {
	/**
	 * 条件查询所有${chineseName}
	 *
	 * @param query
	 * @return com.chinaso.common.flexigrid.bean.FlexiGrid
	 * @author WangJianQiang
	 * @date ${.now?date} ${.now?time}
	 */
	public FlexiGrid search${upClassName}FG(String query) throws Exception;

	/**
	 * 查询所有${chineseName}列表
	 *
	 * @param
	 * @return java.util.List<${upClassName}Bean>
	* @author WangJianQiang
	* @date ${.now?date} ${.now?time}
	*/
	public List<${upClassName}Bean> search${upClassName}List() throws Exception;

	/**
	* 删除${chineseName}
	*
	* @param id
	* @param updateUserId
	* @return com.chinaso.common.ajax.AjaxResponse
	* @author WangJianQiang
	* @date ${.now?date} ${.now?time}
	*/
	public AjaxResponse delete${upClassName}(Integer id, Integer updateUserId) throws Exception;


	/**
	* 更新${chineseName}
	* @author WangJianQiang
	* @date ${.now?date} ${.now?time}
	* @param ${lowClassName}Bean
	* @return com.chinaso.common.ajax.AjaxResponse
	*/
	public AjaxResponse update${upClassName}ById(${upClassName}Bean ${lowClassName}Bean) throws Exception;

	/**
	* 增加${chineseName}
	* @author WangJianQiang
	* @date ${.now?date} ${.now?time}
	* @param bean
	* @return com.chinaso.common.ajax.AjaxResponse
	*/
	public AjaxResponse add${upClassName}(${upClassName}Bean bean) throws Exception;

	/**
	* 根据id获取${chineseName}
	* @author WangJianQiang
	* @date ${.now?date} ${.now?time}
	* @param ${lowClassName}Id
	* @return ${upClassName}Bean
	*/
	public ${upClassName}Bean get${upClassName}ById(Integer ${lowClassName}Id) throws Exception;
    <#if idx??>
/**
	 * 修改序号
	 * @return void
	 * @author WangJianQiang
	 * @date
	 * @Param id
	 * @Param newNum
	 * @Param updateUserId
	 */
	public AjaxResponse changeOrderNum(Integer id, int newNum, String updateUserId);
    </#if>
	}
