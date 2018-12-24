package allen.interview.templet.some;

import com.chinaso.common.ajax.AjaxResponse;
import com.chinaso.common.ajax.AlertMessage;
import com.chinaso.common.flexigrid.FlexigridFilter;
import com.chinaso.common.flexigrid.bean.FlexiGrid;
import com.chinaso.common.json.JackJsonUtil;
import com.chinaso.common.util.DateTools;
import com.chinaso.modules.app.bean.AppAnimalClassifyBean;
import com.chinaso.modules.app.dao.AppAnimalClassifyDao;
import com.chinaso.modules.app.service.AppAnimalClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class AppAnimalClassifyServiceImpl implements AppAnimalClassifyService {
	private final AppAnimalClassifyDao appAnimalClassifyDao;

	@Autowired
	public AppAnimalClassifyServiceImpl(AppAnimalClassifyDao appAnimalClassifyDao) {
		this.appAnimalClassifyDao = appAnimalClassifyDao;
	}


	@Override
	public AjaxResponse addAppAnimalClassify(AppAnimalClassifyBean bean, Integer userId) throws Exception {
		if (StringUtils.isEmpty(bean.getAacname())) {
			return AlertMessage.getActionResponse("msgAppAnimalNameNull");
		}
		List<AppAnimalClassifyBean> dbCustomerBean = appAnimalClassifyDao.searchAppAnimalClassifyByName(bean.getAacname());
		if (null != dbCustomerBean && dbCustomerBean.size() > 0) {
			return AlertMessage.getActionResponse("msgAppAnimalRepeat");
		}
		bean.setIdx(appAnimalClassifyDao.maxIdx() + 1);
		bean.setCreatetime(DateTools.getFormatNowDate());
		bean.setUpdatetime(DateTools.getFormatNowDate());
		bean.setCreatorid(userId);
		bean.setUpdateuserid(userId);
		if (0L < appAnimalClassifyDao.insertAppAnimalClassify(bean)) {
			return new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		} else {
			return new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
		}
	}


	@Transactional(rollbackFor = Exception.class)
	@Override
	public AjaxResponse deleteAppAnimalClassifyById(Integer id, Integer updateUserId) throws Exception {
		if (StringUtils.isEmpty(id)) {
			return AlertMessage.getActionResponse("msgCommonIllegalParam");
		}
		AppAnimalClassifyBean dbBean = appAnimalClassifyDao.getAppAnimalClassifyById(id);
		if (null == dbBean) {
			return AlertMessage.getActionResponse("msgAppAnimalNotExist");
		}
		changeOrderNum(id, -1, String.valueOf(updateUserId));
		if (0 < appAnimalClassifyDao.deleteAppAnimalClassify(id, updateUserId)) {
			return new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		} else {
			return new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
		}
	}


	@Override
	public FlexiGrid searchAppAnimalClassifyFG(String query) throws Exception {
		FlexiGrid flexiGrid = JackJsonUtil.fromJsonToObject(query, FlexiGrid.class);
		Map<String, Object> params = null;
		if (flexiGrid != null) {
			params = FlexigridFilter.parseFlexigrid(flexiGrid);
			long total = appAnimalClassifyDao.getCountAppAnimalClassify(params);
			flexiGrid.adjust(total);
			if (0L < total) {
				flexiGrid.setRows(appAnimalClassifyDao.searchAppAnimalClassify(params));
			}
		}
		return flexiGrid;
	}


	@Override
	public AjaxResponse getAppAnimalClassifyById(Integer id) {
		if (StringUtils.isEmpty(id)) {
			return AlertMessage.getActionResponse("msgCommonIllegalParam");
		}
		AppAnimalClassifyBean dbBean = appAnimalClassifyDao.getAppAnimalClassifyById(id);
		if (null == dbBean) {
			return AlertMessage.getActionResponse("msgAppAnimalNotExist");
		}
		AjaxResponse ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		ajaxResponse.setData(dbBean);
		return ajaxResponse;
	}


	@Override
	public AjaxResponse updateAppAnimalClassifyById(AppAnimalClassifyBean bean, Integer userId) throws Exception {
		if (StringUtils.isEmpty(bean.getAacname())) {
			return AlertMessage.getActionResponse("msgAppAnimalNameNull");
		}
		AppAnimalClassifyBean dbBean = appAnimalClassifyDao.getAppAnimalClassifyById(bean.getAacid());
		if (null == dbBean) {
			return AlertMessage.getActionResponse("msgAppAnimalNotExist");
		}

		if (!dbBean.getAacname().equals(bean.getAacname())) {
			List<AppAnimalClassifyBean> dbCustomerBean = appAnimalClassifyDao.searchAppAnimalClassifyByName(bean.getAacname());
			if (null != dbCustomerBean && dbCustomerBean.size() > 0) {
				return AlertMessage.getActionResponse("msgAppAnimalRepeat");
			}
		}
		dbBean.setAacname(bean.getAacname());
		dbBean.setUpdateuserid(userId);
		dbBean.setUpdatetime(DateTools.getFormatNowDate());
		if (0 < appAnimalClassifyDao.updateAppAnimalClassify(dbBean)) {
			return new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		} else {
			return new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
		}
	}


	@Override
	public List<AppAnimalClassifyBean> getListAppAnimalClassify() throws Exception {
		return appAnimalClassifyDao.getAppAnimalClassifyList();
	}


	@Transactional(rollbackFor = Exception.class)
	@Override
	public AjaxResponse changeOrderNum(Integer id, int newNum, String updateUserId) {
		if (StringUtils.isEmpty(id)) {
			return AlertMessage.getActionResponse("msgCommonIllegalParam");
		}
		AppAnimalClassifyBean dbBean = appAnimalClassifyDao.getAppAnimalClassifyById(id);
		if (null == dbBean) {
			return AlertMessage.getActionResponse("msgAppAnimalNotExist");
		}
		if (0 >= newNum) {
			int maxIdx = appAnimalClassifyDao.maxIdx();
			if (0 < appAnimalClassifyDao.decIdx(dbBean.getIdx() + 1, maxIdx, updateUserId, DateTools.getFormatNowDate())) {
				return new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
			} else {
				return new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
			}
		} else {
			int oldIdx = dbBean.getIdx();
			if (dbBean.getIdx() != newNum) {
				if (oldIdx < newNum) {
					appAnimalClassifyDao.decIdx(++oldIdx, newNum, updateUserId, DateTools.getFormatNowDate());
					if (0 < appAnimalClassifyDao.updateIdx(String.valueOf(id), newNum, updateUserId, DateTools.getFormatNowDate())) {
						return new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
					} else {
						return new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
					}
				} else {
					appAnimalClassifyDao.incIdx(newNum, --oldIdx, updateUserId, DateTools.getFormatNowDate());
					if (0 < appAnimalClassifyDao.updateIdx(String.valueOf(id), newNum, updateUserId, DateTools.getFormatNowDate())) {
						return new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
					} else {
						return new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
					}
				}
			} else {
				return new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
			}
		}
	}
}
