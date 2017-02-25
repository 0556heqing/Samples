package com.heqing.samplesBase.utils;

import java.io.IOException;
import java.util.List;

/**
 * JSON数据返回集合
 * @param <T>
 */
public class GsonUtil<T>{
	
    private boolean success;//标识成功失败	
    private String msg;		//提示信息
    private T data;			//返回数据
    private Long total;		//总条数 
    private List<T> rows;	//返回数据集合

    public GsonUtil() {}
    
    /**
     * 构造函数
     * @param success 标识成功失败
     * @throws IOException 
     */
    public GsonUtil(boolean success) {
        this.success = success;
    }

    /**
     * 构造函数
     * @param suggestAction 提示类型
     * @param msg 提示信息
     * @throws IOException 
     */
    public GsonUtil(String msg) throws IOException  {
        this.success = true;
        this.msg = msg;
    }

    /**
     * 构造函数
     * @param success 标识成功失败
     * @param msg 提示信息
     * @throws IOException 
     */
    public GsonUtil(boolean success, String msg) throws IOException{
        this.success = success;
        this.msg = msg;
    }

    /**
     * 构造函数
     * @param success 标识成功失败
     * @param msg 提示信息
     * @param total 总条数 
     * @throws IOException 
     */
    public GsonUtil(boolean success, String msg, Long total) throws IOException{
        this.success = success;
        this.msg = msg;
        this.total=total;
    }
    
    /**
     * 赋值主体数据
     * @param obj 主体数据对象
     * @return <GsonUtil>
     * @throws IOException 
     */
    public GsonUtil<T> conSetData(T obj) {
        this.data = obj;
        return this;
    }

    /**
     * 赋值主体数据
     * @param obj 主体数据对象
     * @return <GsonUtil>
     * @throws IOException 
     */
    public GsonUtil<T> convtData(List<T> obj) throws IOException {
        this.rows = obj;
        return this;
    }

    /**
     * 赋值主体数据
     * @param obj 主体数据对象
     * @param total 数量
     * @return <GsonUtil>
     * @throws IOException 
     */
    public GsonUtil<T> convtData(List<T> obj, Long total) {
        this.rows = obj;
        this.total=total;
        return this;
    }

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}  
}
