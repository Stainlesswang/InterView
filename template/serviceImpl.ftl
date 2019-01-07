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
public class ${upClassName}ServiceImpl implements ${upClassName}Service {

	@Autowired
	private ${upClassName}Dao ${lowClassName}Dao;
	//@Autowired
	//private TempImageDao tempImageDao;

	/*
	 *
	 * (non-Javadoc)
	 * @see com.chinaso.modules.app.service#search${upClassName}FG[query]
	 * @author WangJianQiang
	 * @date 2018年12月25日 上午09:13:40
	 * @param query
	 * @return com.chinaso.common.flexigrid.bean.FlexiGrid
	 */
	@Override
	public FlexiGrid search${upClassName}FG(String query) throws Exception {
		if (StringTools.isEmpty(query)) {
			throw new ChinasoException(AlertMessage.getMessageObjByKey("msgCommonIllegalParam"));
		}
		FlexiGrid flexiGrid = JackJsonUtil.fromJsonToObject(query, FlexiGrid.class);
		Map<String, Object> params = FlexigridFilter.parseFlexigrid(flexiGrid);
		long total = ${lowClassName}Dao.getCount${upClassName}(params);
		flexiGrid.adjust(total);
		if (0L < total) {
			flexiGrid.setRows(${lowClassName}Dao.search${upClassName}(params));
		}
		return flexiGrid;
	}

	/*
	 *
	 * (non-Javadoc)
	 * @see com.chinaso.modules.app.service#search${upClassName}List[]
	 * @author WangJianQiang
	 * @date 2018年12月25日 上午09:13:45
	 * @param
	 * @return java.util.List<com.chinaso.modules.app.bean.${upClassName}Bean>
	*/
	@Override
	public List<${upClassName}Bean> search${upClassName}List() throws Exception {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("deleteflag", GlobalFunctionConstants.FINAL_DELETE_FLAG_0);
	return ${lowClassName}Dao.search${upClassName}(params);
	}

	/*
	*
	* (non-Javadoc)
	* @see com.chinaso.modules.app.service#delete${upClassName}[id, updateUserId]
	* @author WangJianQiang
	* @date 2018年12月25日 上午09:13:49
	* @param id
	* @param updateUserId
	* @return com.chinaso.common.ajax.AjaxResponse
	*/
	@Override
	public AjaxResponse delete${upClassName}(Integer id, Integer updateUserId) throws Exception {
	AjaxResponse ajaxResponse;
	${upClassName}Bean dbBean = ${lowClassName}Dao.get${upClassName}ById(id);
	if (null == dbBean) {
	ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
	} else {
	if (0 < ${lowClassName}Dao.delete${upClassName}(dbBean)) {
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
	* @see com.chinaso.modules.app.service#update${upClassName}IdxById[${lowClassName}Bean]
	* @author WangJianQiang
	* @date 2018年12月25日 上午09:13:53
	* @param ${lowClassName}Bean
	* @return com.chinaso.common.ajax.AjaxResponse
	*/
	@Override
	public AjaxResponse update${upClassName}IdxById(${upClassName}Bean ${lowClassName}Bean) throws Exception {
	AjaxResponse ajaxResponse = null;
	${upClassName}Bean dbBean = ${lowClassName}Dao.get${upClassName}ById(${lowClassName}Bean.getAasid());
	if (null == dbBean) {
	ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
	} else {
	if (${lowClassName}Bean.getIdx().equals(dbBean.getIdx())) {
	ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
	} else {
	int maxIdx = ${lowClassName}Dao.getMaxIdx(dbBean.getAacid());
	if (${lowClassName}Bean.getIdx() >= 1 && ${lowClassName}Bean.getIdx() <= maxIdx) {
	dbBean.setTmp_oldIdx(dbBean.getIdx());
	dbBean.setIdx(${lowClassName}Bean.getIdx());
	dbBean.setUpdatetime(DateTools.getFormatNowDate());
	dbBean.setUpdateuserid(${lowClassName}Bean.getUpdateuserid());
	if (0 < ${lowClassName}Dao.update${upClassName}IdxById(dbBean)) {
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
	* @see com.chinaso.modules.app.service#update${upClassName}ById[${lowClassName}Bean]
	* @author WangJianQiang
	* @date 2018年12月25日 上午09:13:57
	* @param ${lowClassName}Bean
	* @return com.chinaso.common.ajax.AjaxResponse
	*/
	@Transactional(value = "youngWriteTransactionManager", rollbackFor = Exception.class)
	@Override
	public AjaxResponse update${upClassName}ById(${upClassName}Bean ${lowClassName}Bean) throws Exception {
	AjaxResponse ajaxResponse = null;
	${upClassName}Bean dbBean = ${lowClassName}Dao.get${upClassName}ById(${lowClassName}Bean.getAasid());
	if (null == dbBean) {
	return new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
	}
	if (!dbBean.getAasname().equals(${lowClassName}Bean.getAasname())) {
	${upClassName}Bean dMenuBean = ${lowClassName}Dao.get${upClassName}ByName(${lowClassName}Bean.getAasname());
	if (null != dMenuBean) {
	return AlertMessage.getActionResponse("msgAppAnimalRepeat");
	}
	}
	if (!dbBean.getAudiourl().equals(${lowClassName}Bean.getAudiourl())) {
	tempImageDao.deleteTempImage(${lowClassName}Bean.getAudiourl());
	if (!StringUtils.isEmpty(dbBean.getAudiourl())) {
	TempImageBean imageBean = new TempImageBean();
	imageBean.setImgkey(dbBean.getAudiourl());
	imageBean.setCreatorid(${lowClassName}Bean.getUpdateuserid());
	imageBean.setCreatetime(DateTools.getFormatNowDate());
	tempImageDao.insertTempImage(imageBean);
	}
	}
	if (!dbBean.getImgtfskey().equals(${lowClassName}Bean.getImgtfskey())) {
	tempImageDao.deleteTempImageByKey(Collections.singletonList(${lowClassName}Bean.getImgtfskey()));
	if (!StringUtils.isEmpty(dbBean.getImgtfskey())) {
	TempImageBean imageBean = new TempImageBean();
	Random random = new Random();
	imageBean.setImgkey("http://n" + random.nextInt(9) + 1 + ".map.pg0.cn/" + dbBean.getImgtfskey());
	imageBean.setCreatorid(${lowClassName}Bean.getUpdateuserid());
	imageBean.setCreatetime(DateTools.getFormatNowDate());
	tempImageDao.insertTempImage(imageBean);
	}
	}
	if (!dbBean.getAacid().equals(${lowClassName}Bean.getAacid())) {
	${lowClassName}Dao.deleteOneIdx(dbBean.getAacid(), dbBean.getIdx());
	int maxIdx = ${lowClassName}Dao.getMaxIdx(${lowClassName}Bean.getAacid());
	dbBean.setIdx(++maxIdx);
	}
	dbBean.setAasname(${lowClassName}Bean.getAasname());
	dbBean.setAacid(${lowClassName}Bean.getAacid());
	dbBean.setAudiourl(${lowClassName}Bean.getAudiourl());
	dbBean.setImgtfskey(${lowClassName}Bean.getImgtfskey());
	dbBean.setImgh(${lowClassName}Bean.getImgh());
	dbBean.setImgw(${lowClassName}Bean.getImgw());
	dbBean.setUpdatetime(DateTools.getFormatNowDate());
	dbBean.setUpdateuserid(${lowClassName}Bean.getUpdateuserid());
	if (0 < ${lowClassName}Dao.update${upClassName}(dbBean)) {
	ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
	}
	return ajaxResponse;
	}

	/*
	*
	* (non-Javadoc)
	* @see com.chinaso.modules.app.service#add${upClassName}[bean]
	* @author WangJianQiang
	* @date 2018年12月25日 上午09:14:24
	* @param bean
	* @return com.chinaso.common.ajax.AjaxResponse
	*/
	@Transactional(value = "youngWriteTransactionManager", rollbackFor = Exception.class)
	@Override
	public AjaxResponse add${upClassName}(${upClassName}Bean bean) throws Exception {
	AjaxResponse ajaxResponse = null;
	if (StringUtils.isEmpty(bean.getAasname())) {
	return AlertMessage.getActionResponse("msgAppAnimalNameNull");
	}
	${upClassName}Bean dMenuBean = ${lowClassName}Dao.get${upClassName}ByName(bean.getAasname());
	if (null != dMenuBean) {
	return AlertMessage.getActionResponse("msgAppAnimalRepeat");
	}
	int maxIdx = ${lowClassName}Dao.getMaxIdx(bean.getAacid());
	bean.setCreatetime(DateTools.getFormatNowDate());
	bean.setUpdateuserid(bean.getCreatorid());
	bean.setUpdatetime(DateTools.getFormatNowDate());
	bean.setIdx(++maxIdx);
	tempImageDao.deleteTempImage(bean.getAudiourl());
	tempImageDao.deleteTempImageByKey(Collections.singletonList(bean.getImgtfskey()));
	if (0 < ${lowClassName}Dao.add${upClassName}(bean)) {
	ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
	}
	return ajaxResponse;
	}

	/*
	*
	* (non-Javadoc)
	* @see com.chinaso.modules.app.service#get${upClassName}ById[${lowClassName}Id]
	* @author WangJianQiang
	* @date 2018年12月25日 上午09:14:31
	* @param ${lowClassName}Id
	* @return com.chinaso.modules.app.bean.${upClassName}Bean
	*/
	@Override
	public ${upClassName}Bean get${upClassName}ById(Integer ${lowClassName}Id) throws Exception {
	if (null == ${lowClassName}Id) {
	throw new ChinasoException(AlertMessage.getMessageObjByKey("msgCommonIllegalParam"));
	}
	${upClassName}Bean ${lowClassName}Bean = ${lowClassName}Dao.get${upClassName}ById(${lowClassName}Id);
	if (null == ${lowClassName}Bean) {
	throw new ChinasoException(AlertMessage.getMessageObjByKey("msgAppAnimalNotExist"));
	}
	return ${lowClassName}Bean;
	}
	}
