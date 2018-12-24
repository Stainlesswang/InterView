package allen.interview.templet.some.referrence;

import com.chinaso.common.ajax.AjaxResponse;
import com.chinaso.common.constant.GlobalPagePathConstants;
import com.chinaso.common.fastdfs.FastDFSClientWrapper;
import com.chinaso.common.flexigrid.bean.FlexiGrid;
import com.chinaso.common.global.baseAbstract.BaseController;
import com.chinaso.common.json.JackJsonUtil;
import com.chinaso.common.tfs.TFSService;
import com.chinaso.modules.activity.service.TempImageService;
import com.chinaso.modules.baike.dinosaur.bean.DinosaurReferenceBean;
import com.chinaso.modules.baike.dinosaur.service.DinosaurReferenceService;
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

/**
 * 恐龙尺寸参照物控制器
 * @author WangJianQiang
 * @date 2018年12月11日 下午03:14:11
 */
@Controller
@RequestMapping("/dinosaurReference")
public class DinosaurReferenceController extends BaseController {
	private final DinosaurReferenceService dinosaurReferenceService;
	@Autowired
	private TempImageService tempImageService;
	@Autowired
	private TFSService tfsService;
	@Value("${image.server.timeout}")
	private String imageServerTimeout;

	@Autowired
	public DinosaurReferenceController(DinosaurReferenceService dinosaurReferenceService) {
		this.dinosaurReferenceService = dinosaurReferenceService;
	}

	/**
	 * 跳转到首页
	 * @author WangJianQiang
	 * @date 2018年12月11日 下午03:14:28
	 * @param
	 * @return java.lang.String
	 */
	@RequestMapping("/dinosaurReference_toIndex")
	public String toIndex() {
		return GlobalPagePathConstants.TO_DINOSAUR_REFER_INDEX;
	}

	/**
	 * 查找参照物列表
	 * @author WangJianQiang
	 * @date 2018年12月11日 下午03:14:49
	 * @param query_json
	 * @return com.chinaso.common.flexigrid.bean.FlexiGrid
	 */
	@RequestMapping("/dinosaurReference_searchDinosaurReferenceFG")
	public @ResponseBody
	FlexiGrid searchDinosaurReferenceFG(String query_json) throws Exception {
		return dinosaurReferenceService.searchDinosaurReferenceFG(query_json);
	}

	/**
	 * 增加参照物
	 * @author WangJianQiang
	 * @date 2018年12月11日 下午03:15:01
	 * @param bean
	 * @return com.chinaso.common.ajax.AjaxResponse
	 */
	@RequestMapping("/dinosaurReference_addDinosaurReference")
	public @ResponseBody
	AjaxResponse addDinosaurReference(DinosaurReferenceBean bean) throws Exception {
		return dinosaurReferenceService.addDinosaurReference(bean, getCurrentUserId());
	}

	/**
	 * 删除参照物
	 * @author WangJianQiang
	 * @date 2018年12月11日 下午03:15:12
	 * @param id
	 * @return com.chinaso.common.ajax.AjaxResponse
	 */
	@RequestMapping("/dinosaurReference_deleteDinosaurReferenceById")
	public @ResponseBody
	AjaxResponse deleteDinosaurReferenceById(Integer id) throws Exception {
		return dinosaurReferenceService.deleteDinosaurReferenceById(id, getCurrentUserId());
	}

	/**
	 * 根据id获取参照物信息
	 * @author WangJianQiang
	 * @date 2018年12月11日 下午03:15:28
	 * @param id
	 * @return com.chinaso.common.ajax.AjaxResponse
	 */
	@RequestMapping("/dinosaurReference_getDinosaurReferenceById")
	public @ResponseBody
	AjaxResponse getDinosaurReferenceById(String id) throws Exception {
		return dinosaurReferenceService.getDinosaurReferenceById(Integer.valueOf(id));
	}

	/**
	 * 根据id 更新参照物
	 * @author WangJianQiang
	 * @date 2018年12月11日 下午03:15:39
	 * @param bean
	 * @return com.chinaso.common.ajax.AjaxResponse
	 */
	@RequestMapping("/dinosaurReference_updateDinosaurReferenceById")
	public @ResponseBody
	AjaxResponse updateDinosaurReferenceById(DinosaurReferenceBean bean) throws Exception {
		return dinosaurReferenceService.updateDinosaurReferenceById(bean, getCurrentUserId());
	}

	/**
	 * 图片上传功能
	 * @author WangJianQiang
	 * @date 2018年12月11日 下午03:24:29
	 * @param file now i have a dream give me a
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/dinosaurReference_uploadImage", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String uploadImage(MultipartFile file) throws Exception {
//		AjaxResponse ajaxResponse = null;
//		if (null != file && !StringUtils.isEmpty(file.getOriginalFilename())) {
//			String pre = fastDFSClientWrapper.getPrefix();
//			String back = fastDFSClientWrapper.uploadFile(file);
//			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
//			tempImageService.addTempImage(pre + back, getCurrentUserId());
//			DinosaurReferenceBean bean=new DinosaurReferenceBean();
//			bean.setIcon(pre + back);
//			BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
//			bean.setIconwidth(bufferedImage.getWidth());
//			bean.setIconheight(bufferedImage.getHeight());
//			ajaxResponse.setData(bean);
//		}
//		return JackJsonUtil.fromObjectToJson(ajaxResponse);
//	}
		AjaxResponse ajaxResponse;
		String tfsUrl;
		InputStream fis = null;
		DinosaurReferenceBean bean = new DinosaurReferenceBean();
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			fis = file.getInputStream();
			IOUtils.copy(fis, baos);
			TFSService.TFSResult tfsResult = tfsService.storeUploadImage(baos, Integer.valueOf(imageServerTimeout));
			tfsUrl = tfsResult.getTfsUrl();
			final BufferedImage image = ImageIO.read(file.getInputStream());
			tempImageService.addTempImage(tfsUrl, getCurrentUserId());
			bean.setIcon(tfsUrl);
			bean.setIconwidth(image.getWidth());
			bean.setIconheight(image.getHeight());
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
