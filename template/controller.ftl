package com.chinaso.modules.app.controller;

import com.chinaso.common.ajax.AjaxResponse;
import com.chinaso.common.ajax.AlertMessage;
import com.chinaso.common.constant.GlobalPagePathConstants;
import com.chinaso.common.fastdfs.FastDFSClientWrapper;
import com.chinaso.common.flexigrid.bean.FlexiGrid;
import com.chinaso.common.global.baseAbstract.BaseController;
import com.chinaso.common.json.JackJsonUtil;
import com.chinaso.common.tfs.TFSService;
import com.chinaso.modules.activity.service.TempImageService;
import com.chinaso.modules.app.bean.AppAnimalClassifyBean;
import com.chinaso.modules.app.bean.AppAnimalSoundBean;
import com.chinaso.modules.app.service.AppAnimalClassifyService;
import com.chinaso.modules.app.service.AppAnimalSoundService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 动物数据控制器
 * @author WangJianQiang
 * @date 2018年12月24日 上午10:13:48
 */
@Controller
@RequestMapping("/appAnimalSound")
public class AppAnimalSoundController extends BaseController {
	@Autowired
	private AppAnimalSoundService appAnimalSoundService;
	@Autowired
	private AppAnimalClassifyService appAnimalClassifyService;
	@Autowired
	private TempImageService tempImageService;
	@Autowired
	private FastDFSClientWrapper fastDFSClientWrapper;
	@Autowired
	private TFSService tfsService;
	@Value("${image.server.timeout}")
	private String imageServerTimeout;
	/**
	 * 跳转到动物数据管理首页
	 * @author WangJianQiang
	 * @date 2018年12月24日 上午10:14:14
	 * @param
	 * @return java.lang.String
	 */
	@RequestMapping("/appAnimalSound_toIndex")
	public String toIndex() {
		return GlobalPagePathConstants.TO_APP_ANIMAL_SOUND_INDEX;
	}

	/**
	 * 查找动物数据表格
	 * @author WangJianQiang
	 * @date 2018年12月24日 上午10:14:24
	 * @param query_json
	 * @return com.chinaso.common.flexigrid.bean.FlexiGrid
	 */
	@RequestMapping("/appAnimalSound_searchAppAnimalSoundFG")
	public @ResponseBody
	FlexiGrid searchAppAnimalSoundFG(String query_json) throws Exception {
		return appAnimalSoundService.searchAppAnimalSoundFG(query_json);
	}

	/**
	 * 根据id获取动物数据
	 * @author WangJianQiang
	 * @date 2018年12月24日 上午10:14:41
	 * @param bean
	 * @return java.lang.Object
	 */
	@RequestMapping("/appAnimalSound_updateAppAnimalSoundIdxById")
	public @ResponseBody
	Object updateAppAnimalSoundIdxById(AppAnimalSoundBean bean) {
		AjaxResponse ajaxResponse;
		bean.setUpdateuserid(getCurrentUserId());
		try {
			ajaxResponse = appAnimalSoundService.updateAppAnimalSoundIdxById(bean);
		} catch (Exception e) {
			ajaxResponse = AlertMessage.getActionResponse("msgCommonUpdateFail");
			ajaxResponse.setData(e.getMessage());
		}
		return ajaxResponse;
	}

	/**
	 * 删除动物数据
	 * @author WangJianQiang
	 * @date 2018年12月24日 上午10:14:51
	 * @param id
	 * @return java.lang.Object
	 */
	@RequestMapping("/appAnimalSound_deleteAppAnimalSound")
	public @ResponseBody
	Object deleteAppAnimalSound(Integer id) {
		AjaxResponse ajaxResponse;
		if (null != id) {
			try {
				ajaxResponse = appAnimalSoundService.deleteAppAnimalSound(id, getCurrentUserId());
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
	 * 根据id获取动物信息
	 * @author WangJianQiang
	 * @date 2018年12月25日 上午08:50:14
	 * @param id
	 * @return com.chinaso.common.ajax.AjaxResponse
	 */
	@RequestMapping("/appAnimalSound_getAppAnimalSoundById")
	public @ResponseBody
	AjaxResponse getAppAnimalSoundById(Integer id) throws Exception {
		AppAnimalSoundBean appAnimalSoundBean = appAnimalSoundService.getAppAnimalSoundById(id);
		AjaxResponse result = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		result.setData(appAnimalSoundBean);
		return result;
	}

	/**
	 * 获取全部动物分类
	 * @author WangJianQiang
	 * @date 2018年12月25日 上午08:50:29
	 * @param
	 * @return com.chinaso.common.ajax.AjaxResponse
	 */
	@RequestMapping("/appAnimalSound_getAnimalClass")
	public @ResponseBody
	AjaxResponse getAnimalClass() throws Exception {
		List<AppAnimalClassifyBean> list = appAnimalClassifyService.getListAppAnimalClassify();
	AjaxResponse result = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
	result.setData(list);
	return result;
	}

	/**
	* 增加动物数据
	* @author WangJianQiang
	* @date 2018年12月25日 上午08:50:49
	* @param appAnimalSoundBean
	* @return java.lang.Object
	*/
	@RequestMapping("/appAnimalSound_addAppAnimalSound")
	public @ResponseBody
	Object addAppAnimalSound(AppAnimalSoundBean appAnimalSoundBean) throws Exception {
	appAnimalSoundBean.setCreatorid(getCurrentUserId());
	return appAnimalSoundService.addAppAnimalSound(appAnimalSoundBean);
	}

	/**
	* 更新动物数据
	* @author WangJianQiang
	* @date 2018年12月25日 上午08:51:04
	* @param appAnimalSoundBean
	* @return java.lang.Object
	*/
	@RequestMapping("/appAnimalSound_updateAppAnimalSound")
	public @ResponseBody
	Object updateAppAnimalSound(AppAnimalSoundBean appAnimalSoundBean) throws Exception {
	appAnimalSoundBean.setUpdateuserid(getCurrentUserId());
	return appAnimalSoundService.updateAppAnimalSoundById(appAnimalSoundBean);
	}
	/**
	* 上传音频
	* @author WangJianQiang
	* @date 2018年12月25日 上午08:51:18
	* @param file
	* @return java.lang.String
	*/
	@RequestMapping(value = "/appAnimalSound_uploadAudio", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String uploadAudio(MultipartFile file) throws Exception {
	AjaxResponse ajaxResponse = null;
	if (null != file && !StringUtils.isEmpty(file.getOriginalFilename())) {
	String audio = fastDFSClientWrapper.getPrefix()+fastDFSClientWrapper.uploadFile(file);
	ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
	tempImageService.addTempImage(audio, getCurrentUserId());
	ajaxResponse.setData(audio);
	}
	return JackJsonUtil.fromObjectToJson(ajaxResponse);
	}

	/**
	* 上传图片
	* @author WangJianQiang
	* @date 2018年12月25日 上午08:51:38
	* @param file
	* @return java.lang.String
	*/
	@RequestMapping(value = "/appAnimalSound_uploadImage", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String uploadImage(MultipartFile file){
	AjaxResponse ajaxResponse;
	String tfsUrl;
	String tfsKey;
	InputStream fis = null;
	AppAnimalSoundBean bean=new AppAnimalSoundBean();
	try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
	fis = file.getInputStream();
	IOUtils.copy(fis, baos);
	TFSService.TFSResult tfsResult = tfsService.storeUploadImage(baos, Integer.valueOf(imageServerTimeout));
	tfsUrl = tfsResult.getTfsUrl();
	tfsKey = tfsResult.getTfsKey();
	final BufferedImage image = ImageIO.read(file.getInputStream());
	tempImageService.addTempImage(tfsUrl, getCurrentUserId());
	bean.setImgtfskey(tfsKey);
	bean.setImgw(image.getWidth());
	bean.setImgh(image.getHeight());
	} catch (Exception e) {
	ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_FAIL);
	return JackJsonUtil.fromObjectToJson(ajaxResponse);
	} finally {
	if (fis != null) {
	try {
	fis.close();
	} catch (IOException e) {
	e.printStackTrace();
	}
	}
	}
	ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
	ajaxResponse.setData(bean);
	return JackJsonUtil.fromObjectToJson(ajaxResponse);
	}
	}
