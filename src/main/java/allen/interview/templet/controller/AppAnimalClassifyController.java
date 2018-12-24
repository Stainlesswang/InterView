package allen.interview.templet.controller;

import com.chinaso.common.ajax.AjaxResponse;
import com.chinaso.common.constant.GlobalPagePathConstants;
import com.chinaso.common.flexigrid.bean.FlexiGrid;
import com.chinaso.common.global.baseAbstract.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/appAnimalClassify")
public class AppAnimalClassifyController extends BaseController {
	private final AppAnimalClassifyService appAnimalClassifyService;

	@Autowired
	public AppAnimalClassifyController(AppAnimalClassifyService appAnimalClassifyService) {
		this.appAnimalClassifyService = appAnimalClassifyService;
	}


	@RequestMapping("/appAnimalClassify_toIndex")
	public String toIndex() {
		return GlobalPagePathConstants.TO_SEARCH_HOTWORD_INDEX;
	}


	@RequestMapping("/appAnimalClassify_searchAppAnimalClassifyFG")
	public @ResponseBody
	FlexiGrid searchAppAnimalClassifyForFlexigrid(String query_json) throws Exception {
		return appAnimalClassifyService.searchAppAnimalClassifyFG(query_json);
	}


	
	@RequestMapping("/appAnimalClassify_addAppAnimalClassify")
	public @ResponseBody
	AjaxResponse addAppAnimalClassify(AppAnimalClassifyBean bean) throws Exception {
		return appAnimalClassifyService.addAppAnimalClassify(bean, getCurrentUserId());
	}


	@RequestMapping("/appAnimalClassify_deleteAppAnimalClassifyById")
	public @ResponseBody
	AjaxResponse deleteAppAnimalClassifyById(Integer id) throws Exception {
		return appAnimalClassifyService.deleteAppAnimalClassifyById(id, getCurrentUserId());
	}


	
	@RequestMapping("/appAnimalClassify_getAppAnimalClassifyById")
	public @ResponseBody
	AjaxResponse getAppAnimalClassifyById(String id) throws Exception {
		return appAnimalClassifyService.getAppAnimalClassifyById(Integer.valueOf(id));
	}

	@RequestMapping("/appAnimalClassify_updateAppAnimalClassifyById")
	public @ResponseBody
	AjaxResponse updateAppAnimalClassifyById(AppAnimalClassifyBean bean) throws Exception {
		return appAnimalClassifyService.updateAppAnimalClassifyById(bean, getCurrentUserId());
	}

	@RequestMapping("/appAnimalClassify_disableAppAnimalClassifyById")
	public @ResponseBody
	AjaxResponse disableAppAnimalClassifyById(Integer id, boolean isDisable) throws Exception {
		return appAnimalClassifyService.changeDisableAppAnimalClassifyById(id, isDisable, getCurrentUserId());
	}

	
	@RequestMapping("/appAnimalClassify_changeOrderNum")
	public @ResponseBody
	Object updateSensitiveClassById(Integer id, int newNum) throws Exception {
		return appAnimalClassifyService.changeOrderNum(id, newNum, String.valueOf(getCurrentUserId()));
	}

}
