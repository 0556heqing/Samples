package com.heqing.samplesBase.bean;

import java.io.IOException;
import java.util.List;

/**
 * JSON数据返回集合
 */
public class JsonResult<T>{

    private boolean success;//标识成功失败
    private String 	msg;	//提示信息
    private List<T> rows;	//返回数据集合
    private T 		data;	//返回数据
    private Long 	total;	//总条数 

    /**
     * 构造函数
     * @param success 标识成功失败
     * @throws IOException 
     */
    public JsonResult(boolean success) {
        this.success = success;
    }
    public JsonResult(boolean success, String msg) throws IOException{
        this.success = success;
        this.msg = msg;
    }
    public JsonResult(boolean success, String msg, Long total) throws IOException{
        this.success = success;
        this.msg = msg;
        this.total=total;
    }
    public JsonResult(String msg) throws IOException  {
        this.success = true;
        this.msg = msg;
    }

    /**
     * 赋值主体数据
     * @param obj 主体数据对象
     * @return <JsonResult>
     * @throws IOException 
     */
    public JsonResult<T> convtData(List<T> obj) throws IOException {
        this.rows = obj;
        return this;
    }
    public JsonResult<T> convtData(List<T> obj, Long total) {
        this.rows = obj;
        this.total=total;
        return this;
    }
    
    public JsonResult<T> conSetData(T obj) {
        this.data = obj;
        return this;
    }

	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
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


    public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}

	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
