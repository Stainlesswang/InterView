package com.chinaso.modules.app.service.impl;

import com.chinaso.common.ajax.AjaxResponse;
import com.chinaso.common.ajax.AlertMessage;
import com.chinaso.common.constant.GlobalFunctionConstants;
import com.chinaso.common.exception.ChinasoException;
import com.chinaso.common.flexigrid.FlexigridFilter;
import com.chinaso.common.flexigrid.bean.FlexiGrid;
import com.chinaso.common.json.JackJsonUtil;
import com.chinaso.common.util.DateTools;
import com.chinaso.common.util.StringTools;
import com.chinaso.modules.activity.bean.TempImageBean;
import com.chinaso.modules.activity.dao.TempImageDao;
import com.chinaso.modules.app.bean.AppAnimalSoundBean;
import com.chinaso.modules.app.dao.AppAnimalSoundDao;
import com.chinaso.modules.app.service.AppAnimalSoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 动物数据服务层实现层
 * @author WangJianQiang
 * @date 2018年12月25日 上午09:12:43
 */
@Service
public class AppAnimalSoundServiceImpl implements AppAnimalSoundService {

	@Autowired
	private AppAnimalSoundDao appAnimalSoundDao;
	@Autowired
	private TempImageDao tempImageDao;

	/*
	 *
	 * (non-Javadoc)
	 * @see com.chinaso.modules.app.service#searchAppAnimalSoundFG[query]
	 * @author WangJianQiang
	 * @date 2018年12月25日 上午09:13:40
	 * @param query
	 * @return com.chinaso.common.flexigrid.bean.FlexiGrid
	 */
	@Override
	public FlexiGrid searchAppAnimalSoundFG(String query) throws Exception {
		if (StringTools.isEmpty(query)) {
			throw new ChinasoException(AlertMessage.getMessageObjByKey("msgCommonIllegalParam"));
		}
		FlexiGrid flexiGrid = JackJsonUtil.fromJsonToObject(query, FlexiGrid.class);
		Map<String, Object> params = FlexigridFilter.parseFlexigrid(flexiGrid);
		params.put("deleteflag", GlobalFunctionConstants.FINAL_DELETE_FLAG_0);
		long total = appAnimalSoundDao.getCountAppAnimalSound(params);
		flexiGrid.adjust(total);
		if (0L < total) {
			flexiGrid.setRows(appAnimalSoundDao.searchAppAnimalSound(params));
		}
		return flexiGrid;
	}

	/*
	 *
	 * (non-Javadoc)
	 * @see com.chinaso.modules.app.service#searchAppAnimalSoundList[]
	 * @author WangJianQiang
	 * @date 2018年12月25日 上午09:13:45
	 * @param
	 * @return java.util.List<com.chinaso.modules.app.bean.AppAnimalSoundBean>
	*/
	@Override
	public List<AppAnimalSoundBean> searchAppAnimalSoundList() throws Exception {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("deleteflag", GlobalFunctionConstants.FINAL_DELETE_FLAG_0);
	return appAnimalSoundDao.searchAppAnimalSound(params);
	}

	/*
	*
	* (non-Javadoc)
	* @see com.chinaso.modules.app.service#deleteAppAnimalSound[id, updateUserId]
	* @author WangJianQiang
	* @date 2018年12月25日 上午09:13:49
	* @param id
	* @param updateUserId
	* @return com.chinaso.common.ajax.AjaxResponse
	*/
	@Override
	public AjaxResponse deleteAppAnimalSound(Integer id, Integer updateUserId) throws Exception {
	AjaxResponse ajaxResponse;
	AppAnimalSoundBean dbBean = appAnimalSoundDao.getAppAnimalSoundById(id);
	if (null == dbBean) {
	ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
	} else {
	if (0 < appAnimalSoundDao.deleteAppAnimalSound(dbBean)) {
	ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
	} else {
	ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
	}
	}
	return ajaxResponse;
	}

	/*
	*
	* (non-Javadoc)
	* @see com.chinaso.modules.app.service#updateAppAnimalSoundIdxById[appAnimalSoundBean]
	* @author WangJianQiang
	* @date 2018年12月25日 上午09:13:53
	* @param appAnimalSoundBean
	* @return com.chinaso.common.ajax.AjaxResponse
	*/
	@Override
	public AjaxResponse updateAppAnimalSoundIdxById(AppAnimalSoundBean appAnimalSoundBean) throws Exception {
	AjaxResponse ajaxResponse = null;
	AppAnimalSoundBean dbBean = appAnimalSoundDao.getAppAnimalSoundById(appAnimalSoundBean.getAasid());
	if (null == dbBean) {
	ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
	} else {
	if (appAnimalSoundBean.getIdx().equals(dbBean.getIdx())) {
	ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
	} else {
	int maxIdx = appAnimalSoundDao.getMaxIdx(dbBean.getAacid());
	if (appAnimalSoundBean.getIdx() >= 1 && appAnimalSoundBean.getIdx() <= maxIdx) {
	dbBean.setTmp_oldIdx(dbBean.getIdx());
	dbBean.setIdx(appAnimalSoundBean.getIdx());
	dbBean.setUpdatetime(DateTools.getFormatNowDate());
	dbBean.setUpdateuserid(appAnimalSoundBean.getUpdateuserid());
	if (0 < appAnimalSoundDao.updateAppAnimalSoundIdxById(dbBean)) {
	ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
	}
	}
	}
	}
	return ajaxResponse;
	}

	/*
	*
	* (non-Javadoc)
	* @see com.chinaso.modules.app.service#updateAppAnimalSoundById[appAnimalSoundBean]
	* @author WangJianQiang
	* @date 2018年12月25日 上午09:13:57
	* @param appAnimalSoundBean
	* @return com.chinaso.common.ajax.AjaxResponse
	*/
	@Transactional(value = "youngWriteTransactionManager", rollbackFor = Exception.class)
	@Override
	public AjaxResponse updateAppAnimalSoundById(AppAnimalSoundBean appAnimalSoundBean) throws Exception {
	AjaxResponse ajaxResponse = null;
	AppAnimalSoundBean dbBean = appAnimalSoundDao.getAppAnimalSoundById(appAnimalSoundBean.getAasid());
	if (null == dbBean) {
	return new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
	}
	if (!dbBean.getAasname().equals(appAnimalSoundBean.getAasname())) {
	AppAnimalSoundBean dMenuBean = appAnimalSoundDao.getAppAnimalSoundByName(appAnimalSoundBean.getAasname());
	if (null != dMenuBean) {
	return AlertMessage.getActionResponse("msgAppAnimalRepeat");
	}
	}
	if (!dbBean.getAudiourl().equals(appAnimalSoundBean.getAudiourl())) {
	tempImageDao.deleteTempImage(appAnimalSoundBean.getAudiourl());
	if (!StringUtils.isEmpty(dbBean.getAudiourl())) {
	TempImageBean imageBean = new TempImageBean();
	imageBean.setImgkey(dbBean.getAudiourl());
	imageBean.setCreatorid(appAnimalSoundBean.getUpdateuserid());
	imageBean.setCreatetime(DateTools.getFormatNowDate());
	tempImageDao.insertTempImage(imageBean);
	}
	}
	if (!dbBean.getImgtfskey().equals(appAnimalSoundBean.getImgtfskey())) {
	tempImageDao.deleteTempImageByKey(Collections.singletonList(appAnimalSoundBean.getImgtfskey()));
	if (!StringUtils.isEmpty(dbBean.getImgtfskey())) {
	TempImageBean imageBean = new TempImageBean();
	Random random = new Random();
	imageBean.setImgkey("http://n" + random.nextInt(9) + 1 + ".map.pg0.cn/" + dbBean.getImgtfskey());
	imageBean.setCreatorid(appAnimalSoundBean.getUpdateuserid());
	imageBean.setCreatetime(DateTools.getFormatNowDate());
	tempImageDao.insertTempImage(imageBean);
	}
	}
	if (!dbBean.getAacid().equals(appAnimalSoundBean.getAacid())) {
	appAnimalSoundDao.deleteOneIdx(dbBean.getAacid(), dbBean.getIdx());
	int maxIdx = appAnimalSoundDao.getMaxIdx(appAnimalSoundBean.getAacid());
	dbBean.setIdx(++maxIdx);
	}
	dbBean.setAasname(appAnimalSoundBean.getAasname());
	dbBean.setAacid(appAnimalSoundBean.getAacid());
	dbBean.setAudiourl(appAnimalSoundBean.getAudiourl());
	dbBean.setImgtfskey(appAnimalSoundBean.getImgtfskey());
	dbBean.setImgh(appAnimalSoundBean.getImgh());
	dbBean.setImgw(appAnimalSoundBean.getImgw());
	dbBean.setUpdatetime(DateTools.getFormatNowDate());
	dbBean.setUpdateuserid(appAnimalSoundBean.getUpdateuserid());
	if (0 < appAnimalSoundDao.updateAppAnimalSound(dbBean)) {
	ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
	}
	return ajaxResponse;
	}

	/*
	*
	* (non-Javadoc)
	* @see com.chinaso.modules.app.service#addAppAnimalSound[bean]
	* @author WangJianQiang
	* @date 2018年12月25日 上午09:14:24
	* @param bean
	* @return com.chinaso.common.ajax.AjaxResponse
	*/
	@Transactional(value = "youngWriteTransactionManager", rollbackFor = Exception.class)
	@Override
	public AjaxResponse addAppAnimalSound(AppAnimalSoundBean bean) throws Exception {
	AjaxResponse ajaxResponse = null;
	if (StringUtils.isEmpty(bean.getAasname())) {
	return AlertMessage.getActionResponse("msgAppAnimalNameNull");
	}
	AppAnimalSoundBean dMenuBean = appAnimalSoundDao.getAppAnimalSoundByName(bean.getAasname());
	if (null != dMenuBean) {
	return AlertMessage.getActionResponse("msgAppAnimalRepeat");
	}
	int maxIdx = appAnimalSoundDao.getMaxIdx(bean.getAacid());
	bean.setCreatetime(DateTools.getFormatNowDate());
	bean.setUpdateuserid(bean.getCreatorid());
	bean.setUpdatetime(DateTools.getFormatNowDate());
	bean.setIdx(++maxIdx);
	tempImageDao.deleteTempImage(bean.getAudiourl());
	tempImageDao.deleteTempImageByKey(Collections.singletonList(bean.getImgtfskey()));
	if (0 < appAnimalSoundDao.addAppAnimalSound(bean)) {
	ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
	}
	return ajaxResponse;
	}

	/*
	*
	* (non-Javadoc)
	* @see com.chinaso.modules.app.service#getAppAnimalSoundById[appAnimalSoundId]
	* @author WangJianQiang
	* @date 2018年12月25日 上午09:14:31
	* @param appAnimalSoundId
	* @return com.chinaso.modules.app.bean.AppAnimalSoundBean
	*/
	@Override
	public AppAnimalSoundBean getAppAnimalSoundById(Integer appAnimalSoundId) throws Exception {
	if (null == appAnimalSoundId) {
	throw new ChinasoException(AlertMessage.getMessageObjByKey("msgCommonIllegalParam"));
	}
	AppAnimalSoundBean appAnimalSoundBean = appAnimalSoundDao.getAppAnimalSoundById(appAnimalSoundId);
	if (null == appAnimalSoundBean) {
	throw new ChinasoException(AlertMessage.getMessageObjByKey("msgAppAnimalNotExist"));
	}
	return appAnimalSoundBean;
	}
	}
