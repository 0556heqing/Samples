1.spring
	1, @Controller:
			负责注册一个bean 到spring 上下文中
	2, @RequestMapping:
			注解为控制器指定可以处理哪些 URL 请求.返回值会通过视图解析器解析为实际的物理视图, 
			对于InternalResourceViewResolver视图解析器，通过prefix+returnVal+suffix 这样的方式得到实际的物理视图，然后会转发操作
	3, @RequestBody:
			该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，然后把相应的数据绑定到要返回的对象上 ,再把HttpMessageConverter返回的对象数据绑定到 controller中方法的参数上
	4, @ResponseBody:
			该注解用于将Controller的方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区
	5, @ModelAttribute:　　　
			在方法定义上使用 @ModelAttribute 注解：Spring MVC 在调用目标处理方法前，会先逐个调用在方法级上标注了@ModelAttribute 的方法
			在方法的入参前使用 @ModelAttribute 注解：可以从隐含对象中获取隐含的模型数据中获取对象，再将请求参数 –绑定到对象中，再传入入参将方法入参对象添加到模型中 
	6, @RequestParam:
			在处理方法入参处使用 @RequestParam 可以把请求参 数传递给请求方法
	7, @PathVariable
			绑定 URL 占位符到入参
	8, @ExceptionHandler
			注解到方法上，出现异常时会执行该方法
	9, @ControllerAdvice
			使一个Contoller成为全局的异常处理类，类中用@ExceptionHandler方法注解的方法可以处理所有Controller发生的异常
	10,@Cacheable 负责将方法的返回值加入到缓存中.支持如下几个参数：
		value		缓存位置名称，不能为空，如果使用EHCache，就是ehcache.xml中声明的cache的name
		key			缓存的key，默认为空，既表示使用方法的参数类型及参数值作为key，支持SpEL
		condition	触发条件，只有满足条件的情况才会加入缓存，默认为空，既表示全部都加入缓存，支持SpEL
	11,@CacheEvict：负责清除缓存.支持如下几个参数：
		value		缓存位置名称，不能为空，同上
		key			缓存的key，默认为空，同上
		condition	触发条件，只有满足条件的情况才会清除缓存，默认为空，支持SpEL
		allEntries	true表示清除value中的全部缓存，默认为false
		
SpringMVC与struts2的区别
　　1、springmvc基于方法开发的，struts2基于类开发的。springmvc将url和controller里的方法映射。映射成功后springmvc生成一个Handler对象，
	对象中只包括了一个method。方法执行结束，形参数据销毁。springmvc的controller开发类似web service开发。
　　2、springmvc可以进行单例开发，并且建议使用单例开发，struts2通过类的成员变量接收参数，无法使用单例，只能使用多例。
　　3、经过实际测试，struts2速度慢，在于使用struts标签，如果使用struts建议使用jstl。
		
参考资料：
	Spring MVC 入门基础	:					http://www.cnblogs.com/hellokitty1/p/5158672.html
	 跟开涛学SpringMVC	 ：					http://jinnianshilongnian.iteye.com/blog/1594806
	Spring MVC 教程,快速入门,深入分析：			http://elf8848.iteye.com/blog/875830/
	AOP/ORM/DAO/WEB/上下文(Context)/MVC	http://www.educity.cn/java/1123204.html
	@controller 控制器（注入服务）		
	@service 服务（注入dao）
	@repository dao（实现dao访问）
	@Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注
		
	Spring security实现权限管理				http://blog.csdn.net/zmx729618/article/details/51096593
	初识 Spring Security					http://wiki.jikexueyuan.com/project/spring-security/
	跟我学spring3							http://jinnianshilongnian.iteye.com/blog/1482071

2. hibernate
	1.导入jar包：hibernate 与 spring 的jar包。具体见pom.xml文件
	2.spring_core.xml: spring核心配置文件
	3.在web.xml中配置 spring_core.xml文件

3. Mybatis

4. Shiro
	跟我学Shiro							http://jinnianshilongnian.iteye.com/blog/2049092
	
5. Quartz
	QuartZ Cron表达式	http://www.cnblogs.com/drubber/p/5845014.html