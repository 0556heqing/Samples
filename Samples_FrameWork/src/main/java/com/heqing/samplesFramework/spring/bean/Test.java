package com.heqing.samplesFramework.spring.bean;

import java.io.Serializable;

public class Test implements Serializable {
	
	private int id;
	private String name;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override  
    public String toString() {  
        return "test = {id:"+id+",name:"+name+"}";  
    }
}
