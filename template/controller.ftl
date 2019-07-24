package ${packageName};

import com.chinaso.common.ajax.AjaxResponse;
import com.chinaso.common.ajax.AlertMessage;
import com.chinaso.common.constant.GlobalPagePathConstants;
import com.chinaso.common.global.baseAbstract.BaseController;
import com.chinaso.common.json.JackJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * ${chineseName}控制器
 * @author WangJianQiang
 * @date ${.now?date} ${.now?time}
 */
@Controller
@RequestMapping("/${lowClassName}")
public class ${upClassName}Controller extends BaseController {
	@Autowired
	private ${upClassName}Service ${lowClassName}Service;
	/**
	 * 跳转到${chineseName}管理首页
	 * @author WangJianQiang
	 * @date ${.now?date} ${.now?time}
	 * @param
	 * @return java.lang.String
	 */
	@RequestMapping("/${lowClassName}_toIndex")
	public String toIndex() {
		return GlobalPagePathConstants.TO_APP_ANIMAL_SOUND_INDEX;
	}

	/**
	 * 查找${chineseName}数据表格
	 * @author WangJianQiang
	 * @date ${.now?date} ${.now?time}
	 * @param query_json
	 * @return com.chinaso.common.flexigrid.bean.FlexiGrid
	 */
	@RequestMapping("/${lowClassName}_search${upClassName}FG")
	public @ResponseBody
	FlexiGrid search${upClassName}FG(String query_json) throws Exception {
		return ${lowClassName}Service.search${upClassName}FG(query_json);
	}

	/**
	 * 根据id获取${chineseName}数据
	 * @author WangJianQiang
	 * @date ${.now?date} ${.now?time}
	 * @param bean
	 * @return java.lang.Object
	 */
	@RequestMapping("/${lowClassName}_update${upClassName}IdxById")
	public @ResponseBody
	Object update${upClassName}IdxById(${upClassName}Bean bean) {
		return ${lowClassName}Service.update${upClassName}IdxById(bean);
	}

	/**
	 * 删除${chineseName}数据
	 * @author WangJianQiang
	 * @date ${.now?date} ${.now?time}
	 * @param id
	 * @return java.lang.Object
	 */
	@RequestMapping("/${lowClassName}_delete${upClassName}")
	public @ResponseBody
	Object delete${upClassName}(Integer id) throws Exception {
		return ${lowClassName}Service.delete${upClassName}(id, getCurrentUserId());
	}

	/**
	 * 根据id获取${chineseName}信息
	 * @author WangJianQiang
	 * @date ${.now?date} ${.now?time}
	 * @param id
	 * @return com.chinaso.common.ajax.AjaxResponse
	 */
	@RequestMapping("/${lowClassName}_get${upClassName}ById")
	public @ResponseBody
	AjaxResponse get${upClassName}ById(Integer id) throws Exception {
        ${upClassName}Bean ${lowClassName}Bean = ${lowClassName}Service.get${upClassName}ById(id);
		AjaxResponse result = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		result.setData(${lowClassName}Bean);
		return result;
	}

	/**
	* 增加${chineseName}数据
	* @author WangJianQiang
	* @date ${.now?date} ${.now?time}
	* @param ${lowClassName}Bean
	* @return java.lang.Object
	*/
	@RequestMapping("/${lowClassName}_add${upClassName}")
	public @ResponseBody
	Object add${upClassName}(${upClassName}Bean ${lowClassName}Bean) throws Exception {
${lowClassName}Bean.setCreatorsuid(getCurrentUserId());
	return ${lowClassName}Service.add${upClassName}(${lowClassName}Bean);
	}

	/**
	* 更新${chineseName}数据
	* @author WangJianQiang
	* @date ${.now?date} ${.now?time}
	* @param ${lowClassName}Bean
	* @return java.lang.Object
	*/
	@RequestMapping("/${lowClassName}_update${upClassName}")
	public @ResponseBody
	Object update${upClassName}(${upClassName}Bean ${lowClassName}Bean) throws Exception {
	${lowClassName}Bean.setUpdatesuid(getCurrentUserId());
	return ${lowClassName}Service.update${upClassName}ById(${lowClassName}Bean);
	}
   //var urlPath = path + "/${lowClassName}/${lowClassName}_";
	//var addPath = "add${upClassName}";
	//var deletByIdPath = "delete${upClassName}";
	//var updatePath = "update${upClassName}";
	//var flexigridPath = "search${upClassName}FG";
	//var getBeanByIdPath = "get${upClassName}ById";

	}
