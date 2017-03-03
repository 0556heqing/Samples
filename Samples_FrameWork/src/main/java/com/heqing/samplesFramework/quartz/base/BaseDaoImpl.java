package com.heqing.samplesFramework.quartz.base;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional	// @Transactional注解可以被继承，即对子类也有效
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	public void save(T entity) {
		// TODO Auto-generated method stub
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
	}

	public void update(T entity) {
		// TODO Auto-generated method stub
	}

	public T getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> getByIds(Long[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> findAll(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> getPageBean(T entity, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
}
