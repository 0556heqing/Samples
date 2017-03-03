package com.heqing.samplesBase.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class TimeUtil {

	/** 
     * 计算两个日期之间相差的天数 
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
	 * @throws ParseException 
     */ 
    public static int daysBetween(Date smdate,Date bdate) throws ParseException {  
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	smdate=sdf.parse(sdf.format(smdate));
    	bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();  
        cal.setTime(smdate);  
        long time1 = cal.getTimeInMillis();               
        cal.setTime(bdate);  
        long time2 = cal.getTimeInMillis();       
        long between_days=(time2-time1)/(1000*3600*24);
          
       return Integer.parseInt(String.valueOf(between_days));         
    }  
	    
	/**
	*字符串的日期格式的计算
	* @param smdate 较小的时间
    * @param bdate  较大的时间
    * @return 相差天数
	* @throws ParseException 
	*/
    public static int daysBetween(String smdate,String bdate) throws ParseException{
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();  
        cal.setTime(sdf.parse(smdate));  
        long time1 = cal.getTimeInMillis();               
        cal.setTime(sdf.parse(bdate));  
        long time2 = cal.getTimeInMillis();       
        long between_days=(time2-time1)/(1000*3600*24);
          
       return Integer.parseInt(String.valueOf(between_days));   
    }
	
	/**
	*根据出生时间获取年龄
    * @param date  出生时间
    * @return 年龄
	*/
    public static int getAge(Date birthDay) {  
        Calendar cal = Calendar.getInstance();  
  
        if (cal.before(birthDay)) {  
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");  
        }  
        int yearNow = cal.get(Calendar.YEAR);  
        int monthNow = cal.get(Calendar.MONTH);  
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
        cal.setTime(birthDay);  
  
        int yearBirth = cal.get(Calendar.YEAR);  
        int monthBirth = cal.get(Calendar.MONTH);  
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);  
  
        int age = yearNow - yearBirth;  
  
        if (monthNow <= monthBirth) {  
            if (monthNow == monthBirth) {  
                if (dayOfMonthNow < dayOfMonthBirth) age--;  
            }else{  
                age--;  
            }  
        }  
        return age;  
    }  

}
