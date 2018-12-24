package allen.interview.templet.some;

import com.chinaso.common.ajax.AjaxResponse;
import com.chinaso.common.flexigrid.bean.FlexiGrid;
import com.chinaso.modules.app.bean.AppAnimalClassifyBean;

import java.util.List;


public interface AppAnimalClassifyService {

	public AjaxResponse addAppAnimalClassify(AppAnimalClassifyBean bean, Integer userId) throws Exception;


	public AjaxResponse deleteAppAnimalClassifyById(Integer id, Integer updateUserId) throws Exception;

	public FlexiGrid searchAppAnimalClassifyFG(String query) throws Exception;

	public AjaxResponse getAppAnimalClassifyById(Integer id) throws Exception;

	public AjaxResponse updateAppAnimalClassifyById(AppAnimalClassifyBean bean, Integer userId) throws Exception;


	public List<AppAnimalClassifyBean> getListAppAnimalClassify() throws Exception;


	public AjaxResponse changeOrderNum(Integer id, int newNum, String updateUserId);

}
