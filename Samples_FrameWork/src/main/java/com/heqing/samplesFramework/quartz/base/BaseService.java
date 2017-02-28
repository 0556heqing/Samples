package com.heqing.samplesFramework.quartz.base;

import java.util.List;

public interface BaseService<T> {

	/**
	 * 保存实体
	 * @param entity 实体
	 */
	public boolean save(T entity);

	/**
	 * 删除实体
	 * @param id 实例ID
	 */
	public boolean delete(Long id);

	/**
	 * 更新实体
	 * @param entity 实体
	 */
	public boolean update(T entity);

	/**
	 * 查询实体，如果id为null，则返回null，并不会抛异常。
	 * @param id 实体类ID
	 * @return T  实体
	 */
	public T getById(Long id);

	/**
	 * 查询实体
	 * @param ids 实体类ID集合
	 * @return List<T> 实体集合
	 */
	public List<T> getByIds(Long[] ids);

	/**
	 * 查询所有实体
	 * @return List<T> 实体集合
	 */
	List<T> findAll();

	/**
	 * 公共的查询分页信息的方法
	 * @param pageNum	哪一页,
	 * @param pageSize 一页的数目
	 * @return List<T> 实体集合 	
	 */
	List<T> getPageBean(int pageNum, int pageSize);
}
