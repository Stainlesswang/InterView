package allen.interview.templet.some.referrence;


import com.chinaso.modules.baike.dinosaur.bean.DinosaurReferenceBean;

import java.util.List;
import java.util.Map;

/**
 * 恐龙尺寸参照物数据库连接层
 * @author WangJianQiang
 * @date 2018年12月10日 下午05:03:21
 */
public interface DinosaurReferenceDao {
	
	/**
	 * 查找参照物列表 
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:04:13 
	 * @param 
	 * @return java.util.List<com.chinaso.modules.baike.dinosaur.bean.DinosaurReferenceBean>
	 */
	public List<DinosaurReferenceBean> getDinosaurReferenceList() throws Exception;
	
	/**
	 * 条件查询参照物列表 
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:04:35 
	 * @param params
	 * @return java.util.List<com.chinaso.modules.baike.dinosaur.bean.DinosaurReferenceBean>
	 */
	public List<DinosaurReferenceBean> searchDinosaurReference(Map<String, Object> params) throws Exception;


	/**
	 * 条件查询相应参照物数量 
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:04:48 
	 * @param params
	 * @return long
	 */
	public long getCountDinosaurReference(Map<String, Object> params) throws Exception;
	
	/**
	 * 根据id获取参照物 
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:04:58 
	 * @param id
	 * @return com.chinaso.modules.baike.dinosaur.bean.DinosaurReferenceBean
	 */
	public DinosaurReferenceBean getDinosaurReferenceById(Integer id);

	/**
	 * 根据名称获取参照物 
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:05:09 
	 * @param name
	 * @return java.util.List<com.chinaso.modules.baike.dinosaur.bean.DinosaurReferenceBean>
	 */
	public List<DinosaurReferenceBean> searchDinosaurReferenceByName(String name) throws Exception;
	
	/**
	 * 增加一个参照物 
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:05:23 
	 * @param bean
	 * @return long
	 */
	public long insertDinosaurReference(DinosaurReferenceBean bean) throws Exception;
	
	/**
	 * 更新一个参照物 
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:05:32 
	 * @param bean
	 * @return int
	 */
	public int updateDinosaurReference(DinosaurReferenceBean bean) throws Exception;

	/**
	 * 删除一个参照物 
	 * @author WangJianQiang
	 * @date 2018年12月10日 下午05:05:42 
	 * @param id
	 * @param updateUserId
	 * @return int
	 */
	public int deleteDinosaurReference(Integer id, Integer updateUserId) throws Exception;


}
