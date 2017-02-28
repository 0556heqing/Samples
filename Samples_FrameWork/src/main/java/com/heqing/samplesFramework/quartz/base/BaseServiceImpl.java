package com.heqing.samplesFramework.quartz.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	private BaseDao<T> baseDao;

	public BaseDao<T> getBaseDao() {
		return baseDao;
	}
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@CacheEvict(value = "data", allEntries = true) 
	public boolean save(T entity) {
		baseDao.save(entity);
		return true;
	}

	@CachePut(value = "data")    
	public boolean update(T entity) {
		baseDao.update(entity);
		return true;
	}

	@CacheEvict(value = "data", allEntries = true)    
	public boolean delete(Long id) {
		baseDao.delete(id);
		return true;
	}

	@Cacheable(value = "data") 
	public T getById(Long id) {
		return (T) baseDao.getById(id);
	}

	@Cacheable(value = "data") 
	public List<T> getByIds(Long[] ids) {
		return baseDao.getByIds(ids);
	}

	@Cacheable(value = "data") 
	public List<T> findAll() {
		return baseDao.findAll();
	}

	@Cacheable(value = "data") 
	public List<T> getPageBean(int pageNum, int pageSize){
		return baseDao.getPageBean(pageNum, pageSize);
	}
}
