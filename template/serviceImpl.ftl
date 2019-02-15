package ${packageName};

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
 * ${chineseName}服务层实现层
 * @author WangJianQiang
 * @date ${.now?date} ${.now?time}
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
	 * @see ${packageName?substring(0,packageName?index_of(".impl"))}#search${upClassName}FG[query]
	 * @author WangJianQiang
	 * @date ${.now?date} ${.now?time}
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
	 * @see ${packageName?substring(0,packageName?index_of(".impl"))}#search${upClassName}List[]
	 * @author WangJianQiang
	 * @date ${.now?date} ${.now?time}
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
	* @see ${packageName?substring(0,packageName?index_of(".impl"))}#delete${upClassName}[id, updateUserId]
	* @author WangJianQiang
	* @date ${.now?date} ${.now?time}
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
	//changeOrderNum(dbBean, updateUserId);
	dbBean.setUpdateuserid(updateUserId);
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
	* @see ${packageName?substring(0,packageName?index_of(".impl"))}#update${upClassName}ById[${lowClassName}Bean]
	* @author WangJianQiang
	* @date ${.now?date} ${.now?time}
	* @param ${lowClassName}Bean
	* @return com.chinaso.common.ajax.AjaxResponse
	*/
	@Transactional(value = "youngWriteTransactionManager", rollbackFor = Exception.class)
	@Override
	public AjaxResponse update${upClassName}ById(${upClassName}Bean ${lowClassName}Bean) throws Exception {
	AjaxResponse ajaxResponse = null;
	${upClassName}Bean dbBean = ${lowClassName}Dao.get${upClassName}ById(${lowClassName}Bean.get${id?cap_first}());
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
	dbBean.setAasname(${lowClassName}Bean.getAasname());
    <#if typeid??>
	if (!dbBean.get${typeid?cap_first}().equals(${lowClassName}Bean.get${typeid?cap_first}())) {
	changeOrderNum(dbBean, ${lowClassName}Bean.getUpdateuserid());
	int maxIdx = ${lowClassName}Dao.maxIdx(${lowClassName}Bean.get${typeid?cap_first}());
	dbBean.setIdx(++maxIdx);
	}

	dbBean.set${typeid?cap_first}(${lowClassName}Bean.get${typeid?cap_first}());
    </#if>
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
	* @see ${packageName?substring(0,packageName?index_of(".impl"))}#add${upClassName}[bean]
	* @author WangJianQiang
	* @date ${.now?date} ${.now?time}
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
    <#if typeid??>
	int maxIdx = ${lowClassName}Dao.maxIdx(bean.get${typeid?cap_first}());
	bean.setIdx(++maxIdx);

    </#if>
	bean.setCreatetime(DateTools.getFormatNowDate());
	bean.setUpdateuserid(bean.getCreatorid());
	bean.setUpdatetime(DateTools.getFormatNowDate());
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
	* @see ${packageName?substring(0,packageName?index_of(".impl"))}#get${upClassName}ById[${lowClassName}Id]
	* @author WangJianQiang
	* @date ${.now?date} ${.now?time}
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
	throw new ChinasoException(AlertMessage.getMessageObjByKey("msg${upClassName}NotExist"));
	}
	return ${lowClassName}Bean;
	}

    <#if idx??>
    /*
	 *
	 * (non-Javadoc)
	 * @see ${packageName?substring(0,packageName?index_of(".impl"))}#changeOrderNum[id, newNum, updateUserId]
	 * @author WangJianQiang
	 * @date ${.now?date} ${.now?time}
	 * @Param id
	 * @Param newNum
	 * @Param updateUserId
	 * @return void
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public AjaxResponse changeOrderNum(Integer id, int newNum, String updateUserId <#if typeid??>, Integer ${typeid}</#if>) {
		if (StringUtils.isEmpty(id)) {
			return AlertMessage.getActionResponse("msgCommonIllegalParam");
		}
		${upClassName}Bean dbBean = ${lowClassName}Dao.get${upClassName}ById(id);
		if (null == dbBean) {
			return AlertMessage.getActionResponse("msg${upClassName}NotExist");
		}
		//修改 判断向前移动还是向后移动
		int oldIdx = dbBean.getIdx();
		if (oldIdx < newNum) {
			${lowClassName}Dao.decIdx(++oldIdx, newNum, updateUserId, DateTools.getFormatNowDate() <#if typeid??>, dbBean.get${typeid?cap_first}()</#if>);
		} else {
			${lowClassName}Dao.incIdx(newNum, --oldIdx, updateUserId, DateTools.getFormatNowDate()<#if typeid??>, dbBean.get${typeid?cap_first}()</#if>);
		}
		if (0 < ${lowClassName}Dao.updateIdx(String.valueOf(id), newNum, updateUserId, DateTools.getFormatNowDate())) {
			return new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		} else {
			return new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
		}
	}
/**
	 * 删除一条数据序号要做的改变
	 *
	 * @param bean
	 * @param updateUserId
	 * @return com.chinaso.common.ajax.AjaxResponse
	 * @author WangJianQiang
	 * @date  ${.now?date} ${.now?time}
	 */
	private AjaxResponse changeOrderNum(${upClassName}Bean bean, Integer updateUserId) {
		if (null == bean) {
			return AlertMessage.getActionResponse("msg${upClassName}NotExist");
		}
		int maxIdx = ${lowClassName}Dao.maxIdx();
		if (0 < ${lowClassName}Dao.decIdx(bean.getIdx() + 1, maxIdx, String.valueOf(updateUserId), DateTools.getFormatNowDate()<#if typeid??>, bean.get${typeid?cap_first}()</#if>)) {
			return new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		} else {
			return new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
		}
	}

    </#if>
	//msg.messagemap[msg${upClassName}Repeat]=-10003::${chineseName}名称已存在!
	//msg.messagemap[msg${upClassName}NameNull]=-10003::名称不能为空!
	//msg.messagemap[msg${upClassName}NotExist]=-10004::该${chineseName}不存在!
	}
