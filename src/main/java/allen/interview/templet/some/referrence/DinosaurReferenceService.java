package allen.interview.templet.some.referrence;

import com.chinaso.common.ajax.AjaxResponse;
import com.chinaso.common.flexigrid.bean.FlexiGrid;
import com.chinaso.modules.baike.dinosaur.bean.DinosaurReferenceBean;

import java.util.List;

/**
 * 恐龙百科参照图片服务层接口
 *
 * @author WangJianQiang
 * @date 2018年12月10日 下午05:18:28
 */
public interface DinosaurReferenceService {

	/**
	 * 增加参照物
	 * @param bean
	 * @param userId
	 * @return com.chinaso.common.ajax.AjaxResponse
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:19:45
	 */
	public AjaxResponse addDinosaurReference(DinosaurReferenceBean bean, Integer userId) throws Exception;


	/**
	 * 删除参照物
	 * @param id
	 * @param updateUserId
	 * @return com.chinaso.common.ajax.AjaxResponse
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:19:54
	 */
	public AjaxResponse deleteDinosaurReferenceById(Integer id, Integer updateUserId) throws Exception;


	/**
	 * 查找参照物列表
	 * @param query
	 * @return com.chinaso.common.flexigrid.bean.FlexiGrid
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:20:05
	 */
	public FlexiGrid searchDinosaurReferenceFG(String query) throws Exception;

	/**
	 * 根据id获取参照物信息
	 * @param id
	 * @return com.chinaso.common.ajax.AjaxResponse
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:20:16
	 */
	public AjaxResponse getDinosaurReferenceById(Integer id) throws Exception;

	/**
	 * 更新参照物信息
	 * @param bean
	 * @param userId
	 * @return com.chinaso.common.ajax.AjaxResponse
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:20:31
	 */
	public AjaxResponse updateDinosaurReferenceById(DinosaurReferenceBean bean, Integer userId) throws Exception;

	/**
	 * 获取参照物集合
	 * @author zhaoxinyu
	 * @date 2018年12月17日 16:07:34
	 * @param
	 * @return java.util.List<com.chinaso.modules.baike.dinosaur.bean.DinosaurReferenceBean>
	 * @throws
	 */
	public List<DinosaurReferenceBean> getDinosaurReferenceList() throws Exception;

}
