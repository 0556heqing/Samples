package com.heqing.samplesFramework.hibernate.base;

import java.util.List;

import com.heqing.samplesBase.bean.PageBean;
import com.heqing.samplesFramework.hibernate.utils.HqlHelper;

public interface BaseService<T> {

	/**
	 * 保存实体
	 * @param <T>
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 删除实体
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 更新实体
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 查询实体，如果id为null，则返回null，并不会抛异常。
	 * @param id
	 * @return
	 */
	T getById(Long id);

	/**
	 * 查询实体
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Long[] ids);

	/**
	 * 查询所有
	 * @return
	 */
	List<T> findAll();

	/**
	 * 公共的查询分页信息的方法
	 * @param pageNum
	 * @param hqlHelper  查询条件（HQL语句与参数列表）
	 * @return
	 */
	PageBean getPageBeanByHql(int pageNum, int pageSize, HqlHelper hqlHelper);
	
	/**
	 * 公共的查询分页信息的方法
	 * @param hqlHelper  查询条件（HQL语句与参数列表）
	 * @return
	 */
	 List<T> findListByHQL(HqlHelper hqlHelper);
	 
	 /**
	 * 公共的查询分页信息的方法
	 * @param sql  sql语句
	 * @return
	 */
	 List<T> findListBySQL(String sql);
	 
	/**
	 * 公共的查询分页信息的方法
	 * @param pageNum	当前页
	 * @param pageSize	每页显示多少条记录
	 * @param sql  		sql语句
	 * @return
	 */
	PageBean getPageBeanBySql(int pageNum, int pageSize, String sql);
}
