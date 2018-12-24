package allen.interview.templet.some;


import com.chinaso.modules.app.bean.AppAnimalClassifyBean;

import java.util.List;
import java.util.Map;


public interface AppAnimalClassifyDao {


	public List<AppAnimalClassifyBean> getAppAnimalClassifyList() throws Exception;

	public List<AppAnimalClassifyBean> searchAppAnimalClassify(Map<String, Object> params) throws Exception;

	public long getCountAppAnimalClassify(Map<String, Object> params) throws Exception;

	public AppAnimalClassifyBean getAppAnimalClassifyById(Integer id);


	public List<AppAnimalClassifyBean> searchAppAnimalClassifyByName(String name) throws Exception;


	public long insertAppAnimalClassify(AppAnimalClassifyBean bean) throws Exception;


	public int updateAppAnimalClassify(AppAnimalClassifyBean bean) throws Exception;


	public int deleteAppAnimalClassify(Integer id, Integer updateUserId) throws Exception;


	public int updateIdx(String id, int newIdx, String userId, String time);


	public int incIdx(int beginIdx, int endIdx, String userId, String time);

	public int decIdx(int beginIdx, int endIdx, String userId, String time);

	public int maxIdx();
}
