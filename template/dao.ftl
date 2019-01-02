package com.chinaso.modules.app.dao;

import com.chinaso.common.global.BaseDao;
import com.chinaso.modules.app.bean.AppAnimalSoundBean;

import java.util.List;
import java.util.Map;

/**
 * 动物数据连接层接口
 * @author WangJianQiang
 * @date 2018年12月25日 上午08:59:53
 */
public interface AppAnimalSoundDao extends BaseDao<AppAnimalSoundBean> {

	/**
	* 条件查询相应动物数据
	* @param params
	* @return java.util.List<com.chinaso.modules.app.bean.AppAnimalSoundBean>
		* @author WangJianQiang
		* @date 2018年12月25日 上午09:00:18
		*/
		public List<AppAnimalSoundBean> searchAppAnimalSound(Map<String, Object> params) throws Exception;

		/**
		* 条件查询对应数据的条数
		* @param params
		* @return long
		* @author WangJianQiang
		* @date 2018年12月25日 上午09:00:44
		*/
		public long getCountAppAnimalSound(Map<String, Object> params) throws Exception;

		/**
		* 更新动物数据
		* @param bean
		* @return int
		* @author WangJianQiang
		* @date 2018年12月25日 上午09:00:56
		*/
		public int updateAppAnimalSound(AppAnimalSoundBean bean) throws Exception;

		/**
		* 增加动物数据
		* @param bean
		* @return long
		* @author WangJianQiang
		* @date 2018年12月25日 上午09:01:12
		*/
		public long addAppAnimalSound(AppAnimalSoundBean bean) throws Exception;

		/**
		* 删除动物数据
		* @param bean
		* @return int
		* @author WangJianQiang
		* @date 2018年12月25日 上午09:01:20
		*/
		public int deleteAppAnimalSound(AppAnimalSoundBean bean) throws Exception;

		/**
		* 获取最大序号
		* @param aacid
		* @return int
		* @author WangJianQiang
		* @date 2018年12月25日 上午09:01:35
		*/
		public int getMaxIdx(Integer aacid);

		/**
		* 将序号从某一类中删除
		* @param aacid
		* @param idx
		* @return int
		* @author WangJianQiang
		* @date 2018年12月25日 上午09:01:57
		*/
		public int deleteOneIdx(Integer aacid, Integer idx);

		/**
		* 根据id获取动物数据
		* @param id
		* @return com.chinaso.modules.app.bean.AppAnimalSoundBean
		* @author WangJianQiang
		* @date 2018年12月25日 上午09:02:12
		*/
		public AppAnimalSoundBean getAppAnimalSoundById(Integer id) throws Exception;

		/**
		* 根据名称获取动物数据
		* @param name
		* @return com.chinaso.modules.app.bean.AppAnimalSoundBean
		* @author WangJianQiang
		* @date 2018年12月25日 上午09:02:26
		*/
		public AppAnimalSoundBean getAppAnimalSoundByName(String name);

		/**
		* 更新序号
		* @param bean
		* @return int
		* @author WangJianQiang
		* @date 2018年12月25日 上午09:02:37
		*/
		public int updateAppAnimalSoundIdxById(AppAnimalSoundBean bean) throws Exception;

		}