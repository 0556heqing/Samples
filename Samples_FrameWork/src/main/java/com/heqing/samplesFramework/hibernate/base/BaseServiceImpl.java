package com.heqing.samplesFramework.hibernate.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	@Autowired
	private BaseDao<T> baseDao;
	
	@Override
	@CacheEvict(value = "data", allEntries = true) 
	public void save(T entity) {
		// TODO Auto-generated method stub
		baseDao.save(entity);
	}

	@Override
	@CacheEvict(value = "data", allEntries = true) 
	public void delete(Long id) {
		// TODO Auto-generated method stub
		baseDao.delete(id);
	}

	@Override
	@CachePut(value = "data") 
	public void update(T entity) {
		// TODO Auto-generated method stub
		baseDao.update(entity);
	}

	@Override
	@Cacheable(value = "data") 
	public T getById(Long id) {
		// TODO Auto-generated method stub
		return baseDao.getById(id);
	}

	@Override
	@Cacheable(value = "data") 
	public List<T> getByIds(Long[] ids) {
		// TODO Auto-generated method stub
		return baseDao.getByIds(ids);
	}

	@Override
	@Cacheable(value = "data") 
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return baseDao.findAll();
	}

}
