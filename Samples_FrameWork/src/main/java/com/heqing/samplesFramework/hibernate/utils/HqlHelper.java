package com.heqing.samplesFramework.hibernate.utils;

import java.util.ArrayList;
import java.util.List;

import com.heqing.samplesBase.bean.PageBean;
import com.heqing.samplesFramework.hibernate.base.BaseDao;
import com.opensymphony.xwork2.ActionContext;

/**
 * 用于辅助拼接生成HQL的工具类
 */
public class HqlHelper {

	private String selectClause = ""; 	// Select子句
	private String fromClause="";          // From子句，必须
	private String whereClause = "";    // Where子句，可选
	private String orderByClause = "";  // OrderBy子句，可选

	private List<Object> parameters = new ArrayList<Object>(); // 参数列表
	
	/**
     * 拼接Select子句
     * @param propertyName
     */
    public HqlHelper addSelect(String propertyName) {
        if (selectClause.length() == 0) {
            selectClause = "SELECT DISTINCT " + propertyName;
        } else {
            selectClause += ", " + propertyName;
        }
         
        return this;
    }
	/**
	 * 如果第1个参数为true，则拼接Select句
	 * @param append
	 * @param propertyName
	 */
	public HqlHelper addSelect(boolean append, String propertyName) {
		if (append) addSelect(propertyName);
		return this;
	}
	
	/**
	 * 生成From子句，默认的别名为'o'
	 * @param clazz	实体类
	 */
	public HqlHelper addFrom(Class clazz, String alias) {
		// 拼接
		if (fromClause.length() == 0) fromClause = clazz.getSimpleName() + " " + alias;
		else fromClause += " , " + clazz.getSimpleName() + " " + alias;;
		return this;
	}

	/**
	 * 生成From子句，使用指定的别。
	 * @param clazz	实体类
	 * @param alias 别名
	 */
	public HqlHelper addFrom(boolean append, Class clazz, String alias) {
		if (append) addFrom(clazz, alias);
		return this;
	}

	/**
	 * 拼接Where子句
	 * @param condition
	 * @param params
	 */
	public HqlHelper addWhere(String condition, Object... params) {
		// 拼接
		if (whereClause.length() == 0) whereClause = " WHERE " + condition;
		else whereClause += " AND " + condition;

		// 保存参数
		if (params != null && params.length > 0) 
			for (Object obj : params) parameters.add(obj);
		return this;
	}

	/**
	 * 如果第1个参数为true，则拼接Where子句
	 * @param append
	 * @param condition
	 * @param params
	 */
	public HqlHelper addWhere(boolean append, String condition, Object... params) {
		if (append) addWhere(condition, params);
		return this;
	}
	
	/**
     * 添加 IN 条件
     * @param propertyName
     * @param paramValues
     * @return
     */
    public HqlHelper addIn(String propertyName, Object...paramValues){
        if(paramValues==null||paramValues.length==0){
            return this;
        }
        if(whereClause==null){
            whereClause=" WHERE "+propertyName+" IN (?";
        }else{
            whereClause+=" AND "+propertyName+"IN (?";
        }
        for(int i=1;i<paramValues.length;i++){
            whereClause+=",?";
        }
        whereClause+=") ";
        // 保存参数?
        for (Object obj : paramValues) {
        	parameters.add(obj);
        }
        return this;
    }

	/**
	 * 拼接OrderBy子句
	 * @param propertyName 属性名
	 * @param isAsc true表示升序，false表示降序
	 */
	public HqlHelper addOrder(String propertyName, boolean isAsc) {
		if (orderByClause.length() == 0) {
			orderByClause = " ORDER BY " + propertyName + (isAsc ? " ASC" : " DESC");
		} else {
			orderByClause += ", " + propertyName + (isAsc ? " ASC" : " DESC");
		}
		return this;
	}
	
	/**
	 * 如果第1个参数为true，则拼接OrderBy子句
	 * @param append
	 * @param propertyName  属性名
	 * @param isAsc true表示升序，false表示降序
	 */
	public HqlHelper addIn(boolean append, String propertyName, boolean isAsc) {
		if (append) addIn(propertyName, isAsc);
		return this;
	}

	/**
	 * 如果第1个参数为true，则拼接OrderBy子句
	 * @param append
	 * @param propertyName  属性名
	 * @param isAsc true表示升序，false表示降序
	 */
	public HqlHelper addOrder(boolean append, String propertyName, boolean isAsc) {
		if (append) addOrder(propertyName, isAsc);
		return this;
	}

	/**
	 * 获取生成的查询数据列表的HQL语句
	 * @return
	 */
	public String getQueryListHql() {
		if(fromClause.equals("")) return "";
		return fromClause + whereClause + orderByClause;
	}

	/**
	 * 获取生成的查询总记录数的HQL语句（没有OrderBy子句）
	 * @return
	 */
	public String getQueryCountHql() {
		if(fromClause.equals("")) return "";
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}

	/**
	 * 获取参数列表，与HQL过滤条件中的'?'一一对应
	 * @return
	 */
	public List<Object> getParameters() {
		return parameters;
	}

	/**
	 * 查询并准备分页信息（放到栈顶）
	 * @param pageNum
	 * @param service
	 * @return
	 */
	public HqlHelper buildPageBeanForStruts2(int pageNum, int pageSize, BaseDao<?> service) {
		PageBean pageBean = service.getPageBeanByHql(pageNum, pageSize, this);
		ActionContext.getContext().getValueStack().push(pageBean);
		return this;
	}

}
