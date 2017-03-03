package com.heqing.samplesFramework.spring.aspect;

import java.net.InetAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import com.alibaba.fastjson.JSONObject;

@Aspect
public class TestAspect {
	
	private static Log log = LogFactory.getLog(TestAspect.class);
	
	//配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	@Pointcut("execution(* com.heqing.samplesFramework.spring.service.impl.*.*(..))")
    public void serviceImpl(){}

    /**  
     * Before 
     * 在核心业务执行前执行，不能阻止核心业务的调用。 时接受JoinPoint切入点对象,可以没有该参数
     * @param joinPoint  
     */  
	@Before("serviceImpl()")
	public void before() {
//		System.out.println("前置通知");
	}
	
	/**  
     * After  
     * 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此Advice 
     * @param joinPoint 
     */  
	@After("serviceImpl()")
	public void after() {
//		System.out.println("最终通知");
	}
	
	/**  
     * AfterReturning  
     * 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice 
     * @param joinPoint 
     */  
	@AfterReturning("serviceImpl()")  
    public void doAfter(){  
//		System.out.println("后置通知"); 
    }  
	
	/** 
     * 核心业务逻辑调用异常退出后，执行此Advice，处理错误信息 
     * 注意：执行顺序在Around Advice之后 
     * @param joinPoint 
     * @param ex 
     */  
	@AfterThrowing(pointcut="serviceImpl()", throwing="ex")  
    public void doAfterThrow(Exception ex){  
//        System.out.println("例外通知");  
        log.error(ex.getMessage());
    } 
	
	/**  
     * Around  
     * 手动控制调用核心业务逻辑，以及调用前和调用后的处理, 
     * 注意：当核心业务抛异常后，立即退出，转向AfterAdvice 
     * 执行完AfterAdvice，再转到ThrowingAdvice 
     * @param pjp 
     * @return 
     * @throws Throwable 
     */ 
	@Around("serviceImpl()")  
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{  
//        System.out.println("进入环绕通知");  
        long start = System.currentTimeMillis();
        Object obj = null;
		try {
			obj = parsingJoinPoint(pjp);	//可用来处理权限
			
			long end = System.currentTimeMillis();
			if(log.isInfoEnabled()){
				log.info("around " + pjp + "\tUse time : " + (end - start) + " ms!");
			}
		} catch (Throwable e) {
			long end = System.currentTimeMillis();
			if(log.isInfoEnabled()){
				log.info("around " + pjp + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
			}
		}
//        System.out.println("退出环绕通知");  
		return obj;
    }  
	
	private static Object parsingJoinPoint(ProceedingJoinPoint point) throws Throwable{
		//拦截的实体类
		Object target = point.getTarget();
		//拦截的方法名称
		Signature method = point.getSignature();
		//拦截的方法c数
		Object[] parames = point.getArgs();
		//拦截的方法参数类型
		Class[] parameTypes = ((MethodSignature)method).getMethod().getParameterTypes();
		//拦截的方法返回类型
		Class returnType = ((MethodSignature) method).getReturnType();
		//继续执行拦截的方法，屏蔽则不会执行。可作权限处理
		Object returnValue = point.proceed();
		//当前访问用户的IP地址
		String ip = InetAddress.getLocalHost().getHostAddress();
		
		StringBuffer sb = new StringBuffer();
		sb.append("============用户访问信息==============");
		sb.append("\n-->ip : "+ip);
		sb.append("\n-->ClassName : "+target.getClass());
		sb.append("\n-->MethodName : "+method.getName());
		
		JSONObject parame = new JSONObject();
		for(int i=0; i<parames.length; i++) parame.put(i+"", parames[i]);
		sb.append("\n-->Parameter : "+parame);
		
		parame = new JSONObject();
		for(int i=0; i<parameTypes.length; i++) parame.put(i+"", parameTypes[i]);
		sb.append("\n-->ParameterTypes : "+parame);
		parame = new JSONObject();
		parame.put("returnValue", returnValue);
		sb.append("\n-->returnType : "+returnType);
		sb.append("\n-->returnValue : "+parame.get("returnValue"));
		log.info(sb);
		
		return returnValue;
	}

}
