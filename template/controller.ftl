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
 * @date 2018年12月24日 上午10:13:48
 */
@Controller
@RequestMapping("/${lowClassName}")
public class ${upClassName}Controller extends BaseController {
	@Autowired
	private ${upClassName}Service ${lowClassName}Service;
	/**
	 * 跳转到${chineseName}管理首页
	 * @author WangJianQiang
	 * @date 2018年12月24日 上午10:14:14
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
	 * @date 2018年12月24日 上午10:14:24
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
	 * @date 2018年12月24日 上午10:14:41
	 * @param bean
	 * @return java.lang.Object
	 */
	@RequestMapping("/${lowClassName}_update${upClassName}IdxById")
	public @ResponseBody
	Object update${upClassName}IdxById(${upClassName}Bean bean) {
		AjaxResponse ajaxResponse;
		bean.setUpdateuserid(getCurrentUserId());
		try {
			ajaxResponse = ${lowClassName}Service.update${upClassName}IdxById(bean);
		} catch (Exception e) {
			ajaxResponse = AlertMessage.getActionResponse("msgCommonUpdateFail");
			ajaxResponse.setData(e.getMessage());
		}
		return ajaxResponse;
	}

	/**
	 * 删除${chineseName}数据
	 * @author WangJianQiang
	 * @date 2018年12月24日 上午10:14:51
	 * @param id
	 * @return java.lang.Object
	 */
	@RequestMapping("/${lowClassName}_delete${upClassName}")
	public @ResponseBody
	Object delete${upClassName}(Integer id) {
		AjaxResponse ajaxResponse;
		if (null != id) {
			try {
				ajaxResponse = ${lowClassName}Service.delete${upClassName}(id, getCurrentUserId());
			} catch (Exception e) {
				ajaxResponse = AlertMessage.getActionResponse("msgCommonUpdateFail");
				ajaxResponse.setData(e.getMessage());
			}
		} else {
			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		return ajaxResponse;
	}

	/**
	 * 根据id获取${chineseName}信息
	 * @author WangJianQiang
	 * @date 2018年12月25日 上午08:50:14
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
	* @date 2018年12月25日 上午08:50:49
	* @param ${lowClassName}Bean
	* @return java.lang.Object
	*/
	@RequestMapping("/${lowClassName}_add${upClassName}")
	public @ResponseBody
	Object add${upClassName}(${upClassName}Bean ${lowClassName}Bean) throws Exception {
${lowClassName}Bean.setCreatorid(getCurrentUserId());
	return ${lowClassName}Service.add${upClassName}(${lowClassName}Bean);
	}

	/**
	* 更新${chineseName}数据
	* @author WangJianQiang
	* @date 2018年12月25日 上午08:51:04
	* @param ${lowClassName}Bean
	* @return java.lang.Object
	*/
	@RequestMapping("/${lowClassName}_update${upClassName}")
	public @ResponseBody
	Object update${upClassName}(${upClassName}Bean ${lowClassName}Bean) throws Exception {
	${lowClassName}Bean.setUpdateuserid(getCurrentUserId());
	return ${lowClassName}Service.update${upClassName}ById(${lowClassName}Bean);
	}


	}
