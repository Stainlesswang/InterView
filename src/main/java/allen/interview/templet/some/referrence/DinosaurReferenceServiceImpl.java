package allen.interview.templet.some.referrence;

import com.chinaso.common.ajax.AjaxResponse;
import com.chinaso.common.ajax.AlertMessage;
import com.chinaso.common.flexigrid.FlexigridFilter;
import com.chinaso.common.flexigrid.bean.FlexiGrid;
import com.chinaso.common.json.JackJsonUtil;
import com.chinaso.common.util.DateTools;
import com.chinaso.modules.activity.bean.TempImageBean;
import com.chinaso.modules.activity.dao.TempImageDao;
import com.chinaso.modules.baike.dinosaur.bean.DinosaurReferenceBean;
import com.chinaso.modules.baike.dinosaur.dao.DinosaurReferenceDao;
import com.chinaso.modules.baike.dinosaur.service.DinosaurReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 参照物管理服务层实现层
 * @author WangJianQiang
 * @date 2018年12月10日 下午05:20:57
 */
@Service
public class DinosaurReferenceServiceImpl implements DinosaurReferenceService {
	private final DinosaurReferenceDao dinosaurReferenceDao;
	private final TempImageDao tempImageDao;

	@Autowired
	public DinosaurReferenceServiceImpl(DinosaurReferenceDao dinosaurReferenceDao, TempImageDao tempImageDao) {
		this.dinosaurReferenceDao = dinosaurReferenceDao;
		this.tempImageDao = tempImageDao;
	}

	/*
	 * 
	 * (non-Javadoc) 
	 * @see com.chinaso.modules.baike.dinosaur.service#addDinosaurReference[bean, userId]
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:21:04
	 * @param bean
	 * @param userId
	 * @return com.chinaso.common.ajax.AjaxResponse
	 */
	@Transactional(value = "youngWriteTransactionManager", rollbackFor = Exception.class)
	@Override
	public AjaxResponse addDinosaurReference(DinosaurReferenceBean bean, Integer userId) throws Exception {
		if (StringUtils.isEmpty(bean.getDrname())) {
			return AlertMessage.getActionResponse("msgDinosaurReferenceNameIsNull");
		}
		if (StringUtils.isEmpty(bean.getIcon())) {
			return AlertMessage.getActionResponse("msgDinosaurReferenceIconIsNull");
		}
		if (StringUtils.isEmpty(bean.getNumber())) {
			return AlertMessage.getActionResponse("msgDinosaurReferenceNumberIsNull");
		}
//		List<DinosaurReferenceBean> dbCustomerBean = dinosaurReferenceDao.searchDinosaurReferenceByName(bean.getDrname());
//		if (null != dbCustomerBean && dbCustomerBean.size() > 0) {
//			return AlertMessage.getActionResponse("msgDinosaurReferenceIsExist");
//		}
		bean.setCreatetime(DateTools.getFormatNowDate());
		bean.setUpdatetime(DateTools.getFormatNowDate());
		bean.setCreatorid(userId);
		bean.setUpdateuserid(userId);
		if (0L < dinosaurReferenceDao.insertDinosaurReference(bean)) {
			tempImageDao.deleteTempImage(bean.getIcon());
			return new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		} else {
			return new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
		}
	}
	
	/*
	 * 
	 * (non-Javadoc) 
	 * @see com.chinaso.modules.baike.dinosaur.service#deleteDinosaurReferenceById[id, updateUserId]
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:21:10
	 * @param id
	 * @param updateUserId
	 * @return com.chinaso.common.ajax.AjaxResponse
	 */
	@Transactional(value = "youngWriteTransactionManager", rollbackFor = Exception.class)
	@Override
	public AjaxResponse deleteDinosaurReferenceById(Integer id, Integer updateUserId) throws Exception {
		if (StringUtils.isEmpty(id)) {
			return AlertMessage.getActionResponse("msgCommonIllegalParam");
		}
		DinosaurReferenceBean dbBean = dinosaurReferenceDao.getDinosaurReferenceById(id);
		if (null == dbBean) {
			return AlertMessage.getActionResponse("msgDinosaurReferenceNotExist");
		}
		if (0 < dinosaurReferenceDao.deleteDinosaurReference(id, updateUserId)) {
			TempImageBean imageBean = new TempImageBean();
			imageBean.setImgkey(dbBean.getIcon());
			imageBean.setCreatorid(updateUserId);
			imageBean.setCreatetime(DateTools.getFormatNowDate());
			tempImageDao.insertTempImage(imageBean);
			return new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		} else {
			return new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
		}
	}


	/*
	 * 
	 * (non-Javadoc) 
	 * @see com.chinaso.modules.baike.dinosaur.service#searchDinosaurReferenceFG[query]
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:21:14
	 * @param query
	 * @return com.chinaso.common.flexigrid.bean.FlexiGrid
	 */
	@Override
	public FlexiGrid searchDinosaurReferenceFG(String query) throws Exception {
		FlexiGrid flexiGrid = JackJsonUtil.fromJsonToObject(query, FlexiGrid.class);
		Map<String, Object> params;
		if (flexiGrid != null) {
			params = FlexigridFilter.parseFlexigrid(flexiGrid);
			long total = dinosaurReferenceDao.getCountDinosaurReference(params);
			flexiGrid.adjust(total);
			if (0L < total) {
				flexiGrid.setRows(dinosaurReferenceDao.searchDinosaurReference(params));
			}
		}
		return flexiGrid;
	}


	/*
	 * 
	 * (non-Javadoc) 
	 * @see com.chinaso.modules.baike.dinosaur.service#getDinosaurReferenceById[id]
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:21:18
	 * @param id
	 * @return com.chinaso.common.ajax.AjaxResponse
	 */
	@Override
	public AjaxResponse getDinosaurReferenceById(Integer id){
		if (StringUtils.isEmpty(id)) {
			return AlertMessage.getActionResponse("msgCommonIllegalParam");
		}
		DinosaurReferenceBean dbBean = dinosaurReferenceDao.getDinosaurReferenceById(id);
		if (null == dbBean) {
			return AlertMessage.getActionResponse("msgDinosaurReferenceNotExist");
		}
		AjaxResponse ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		ajaxResponse.setData(dbBean);
		return ajaxResponse;
	}


	/*
	 * 
	 * (non-Javadoc) 
	 * @see com.chinaso.modules.baike.dinosaur.service#updateDinosaurReferenceById[bean, userId]
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:21:21
	 * @param bean
	 * @param userId
	 * @return com.chinaso.common.ajax.AjaxResponse
	 */
	@Transactional(value = "youngWriteTransactionManager", rollbackFor = Exception.class)
	@Override
	public AjaxResponse updateDinosaurReferenceById(DinosaurReferenceBean bean, Integer userId) throws Exception {
		if (StringUtils.isEmpty(bean.getDrname())) {
			return AlertMessage.getActionResponse("msgDinosaurReferenceNameIsNull");
		}
		if (StringUtils.isEmpty(bean.getIcon())) {
			return AlertMessage.getActionResponse("msgDinosaurReferenceIconIsNull");
		}
		if (StringUtils.isEmpty(bean.getNumber())) {
			return AlertMessage.getActionResponse("msgDinosaurReferenceNumberIsNull");
		}
		DinosaurReferenceBean dbBean = dinosaurReferenceDao.getDinosaurReferenceById(bean.getDrid());
		if (null == dbBean) {
			return AlertMessage.getActionResponse("msgDinosaurReferenceNotExist");
		}
//		if (!dbBean.getDrname().equals(bean.getDrname())) {
//			List<DinosaurReferenceBean> dbCustomerBean = dinosaurReferenceDao.searchDinosaurReferenceByName(bean.getDrname());
//			if (null != dbCustomerBean && dbCustomerBean.size() > 0) {
//				return AlertMessage.getActionResponse("msgDinosaurReferenceIsExist");
//			}
//		}
		if (!bean.getIcon().equals(dbBean.getIcon())) {
			if (!StringUtils.isEmpty(bean.getIcon()))
				tempImageDao.deleteTempImage(bean.getIcon());
			if (!StringUtils.isEmpty(dbBean.getIcon())) {
				TempImageBean imageBean = new TempImageBean();
				imageBean.setImgkey(dbBean.getIcon());
				imageBean.setCreatorid(userId);
				imageBean.setCreatetime(DateTools.getFormatNowDate());
				tempImageDao.insertTempImage(imageBean);
			}
		}
		dbBean.setDrname(bean.getDrname());
		dbBean.setIcon(bean.getIcon());
		dbBean.setIconheight(bean.getIconheight());
		dbBean.setIconwidth(bean.getIconwidth());
		dbBean.setNumber(bean.getNumber());
		dbBean.setMetering(bean.getMetering());
		dbBean.setUnit(bean.getUnit().equals("") ? null : bean.getUnit());
		dbBean.setUpdateuserid(userId);
		dbBean.setUpdatetime(DateTools.getFormatNowDate());
		if (0 < dinosaurReferenceDao.updateDinosaurReference(dbBean)) {
			return new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		} else {
			return new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
		}
	}

	/*
	 * 获取参照物集合
	 * (non-Javadoc)
	 * @see com.chinaso.modules.baike.dinosaur.service#getDinosaurReferenceList[]
	 * @author zhaoxinyu
	 * @date 2018年12月17日 16:07:58
	 * @param
	 * @return java.util.List<com.chinaso.modules.baike.dinosaur.bean.DinosaurReferenceBean>
	 */
	@Override
	public List<DinosaurReferenceBean> getDinosaurReferenceList() throws Exception {
		return dinosaurReferenceDao.getDinosaurReferenceList();
	}

}
