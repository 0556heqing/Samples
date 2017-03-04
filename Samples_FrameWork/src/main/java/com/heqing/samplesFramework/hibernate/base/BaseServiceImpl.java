package com.heqing.samplesFramework.hibernate.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.heqing.samplesBase.bean.PageBean;
import com.heqing.samplesFramework.hibernate.utils.HqlHelper;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	@Autowired
	private BaseDao<T> baseDao;
	
	@Override
	@CacheEvict(value="data", key="#root.targetClass+#root.methodName", allEntries=true) 
	public void save(T entity) {
		// TODO Auto-generated method stub
		baseDao.save(entity);
	}

	@Override
	@CacheEvict(value="data", key="#root.targetClass+#root.methodName", allEntries=true) 
	public void delete(Long id) {
		// TODO Auto-generated method stub
		baseDao.delete(id);
	}

	@Override
	@CachePut(value="data", key="#root.targetClass+#root.methodName") 
	public void update(T entity) {
		// TODO Auto-generated method stub
		baseDao.update(entity);
	}

	@Override
	@Cacheable(value="data", key="#root.targetClass+#root.methodName") 
	public T getById(Long id) {
		// TODO Auto-generated method stub
		return baseDao.getById(id);
	}

	@Override
	@Cacheable(value="data", key="#root.targetClass+#root.methodName") 
	public List<T> getByIds(Long[] ids) {
		// TODO Auto-generated method stub
		return baseDao.getByIds(ids);
	}

	@Override
	@Cacheable(value="data", key="#root.targetClass+#root.methodName") 
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return baseDao.findAll();
	}
	
	public PageBean getPageBeanByHql(int pageNum, int pageSize, HqlHelper hqlHelper) {
		return baseDao.getPageBeanByHql(pageNum, pageSize, hqlHelper);
	}
	
	public List<T> findListByHQL(HqlHelper hqlHelper) {
		return baseDao.findListByHQL(hqlHelper);
	}
	
	public List<T> findListBySQL(String sql) {
		return baseDao.findListBySQL(sql);
	}

	public PageBean getPageBeanBySql(int pageNum, int pageSize, String sql) {
		return baseDao.getPageBeanBySql(pageNum, pageSize, sql);
	}
}
