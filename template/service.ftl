package com.chinaso.modules.app.service;


import com.chinaso.common.ajax.AjaxResponse;
import com.chinaso.common.flexigrid.bean.FlexiGrid;
import com.chinaso.modules.app.bean.AppAnimalSoundBean;

import java.util.List;

/**
 * 动物数据服务层接口
 *
 * @author WangJianQiang
 * @date 2018年12月25日 上午09:07:59
 */
public interface AppAnimalSoundService {
	/**
	 * 条件查询所有动物数据
	 *
	 * @param query
	 * @return com.chinaso.common.flexigrid.bean.FlexiGrid
	 * @author WangJianQiang
	 * @date 2018年12月25日 上午09:08:13
	 */
	public FlexiGrid searchAppAnimalSoundFG(String query) throws Exception;

	/**
	 * 查询所有动物数据列表
	 *
	 * @param
	 * @return java.util.List<com.chinaso.modules.app.bean.AppAnimalSoundBean>
	* @author WangJianQiang
	* @date 2018年12月25日 上午09:08:31
	*/
	public List<AppAnimalSoundBean> searchAppAnimalSoundList() throws Exception;

	/**
	* 删除动物数据
	*
	* @param id
	* @param updateUserId
	* @return com.chinaso.common.ajax.AjaxResponse
	* @author WangJianQiang
	* @date 2018年12月25日 上午09:08:44
	*/
	public AjaxResponse deleteAppAnimalSound(Integer id, Integer updateUserId) throws Exception;


	/**
	* 更新动物数据排序号
	*
	* @param appAnimalSoundBean
	* @return com.chinaso.common.ajax.AjaxResponse
	* @author WangJianQiang
	* @date 2018年12月25日 上午09:08:53
	*/
	public AjaxResponse updateAppAnimalSoundIdxById(AppAnimalSoundBean appAnimalSoundBean) throws Exception;

	/**
	* 更新动物数据
	* @author WangJianQiang
	* @date 2018年12月25日 上午09:09:14
	* @param appAnimalSoundBean
	* @return com.chinaso.common.ajax.AjaxResponse
	*/
	public AjaxResponse updateAppAnimalSoundById(AppAnimalSoundBean appAnimalSoundBean) throws Exception;

	/**
	* 增加动物信息
	* @author WangJianQiang
	* @date 2018年12月25日 上午09:09:25
	* @param bean
	* @return com.chinaso.common.ajax.AjaxResponse
	*/
	public AjaxResponse addAppAnimalSound(AppAnimalSoundBean bean) throws Exception;

	/**
	* 根据id获取动物数据
	* @author WangJianQiang
	* @date 2018年12月25日 上午09:20:25
	* @param appAnimalSoundId
	* @return com.chinaso.modules.app.bean.AppAnimalSoundBean
	*/
	public AppAnimalSoundBean getAppAnimalSoundById(Integer appAnimalSoundId) throws Exception;
	}
