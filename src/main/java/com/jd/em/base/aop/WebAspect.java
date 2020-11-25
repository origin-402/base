package com.jd.em.base.aop;

import com.jd.em.base.domain.PageData;
import com.jd.em.base.domain.User;
import com.jd.em.base.service.log.LogService;
import com.jd.em.base.utils.Const;
import com.jd.em.base.utils.CusAccessObjectUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author KING
 *
 * <p>Description: 访问统计</p>
 *
 * 2019年1月21日
 *
 */
@Aspect
@Component
public class WebAspect {

	@Resource
	private LogService logService;
	
	ThreadLocal<Long> startTime = new ThreadLocal<>();
	
	@Pointcut("execution(public * com.jd.em.base.controller..*.*(..))")
	public void webAccess(){}
	
	@Before("webAccess()")
	public void doBefore(JoinPoint joinPoint)throws Throwable{
		startTime.set(System.currentTimeMillis());
    	String methodName = joinPoint.getSignature().getName();
		if(null!=methodName&&!"".equals(methodName)){
			if (!(methodName.startsWith("set") || methodName.startsWith("get"))) {
				Class<? extends Object> pointClass = joinPoint.getTarget().getClass();
				Class<? extends Object>[] parameterTypes = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
				Method method = pointClass.getMethod(methodName,parameterTypes);
				if(null!=method){
					boolean hasAnno = method.isAnnotationPresent(JdController.class);
					if(hasAnno){
						JdController cicController = method.getAnnotation(JdController.class);
						String des = cicController.description();
						HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
						PageData pd = new PageData();
						User user = (User)request.getSession().getAttribute(Const.SESSION_USER);
						pd.put("des", des);
						pd.put("username", user.getName());
						pd.put("userid", user.getId());
						pd.put("ip", CusAccessObjectUtil.getIpAddress(request));
						logService.saveLog(pd);
					}
				}
			}
		}
	}
	
	@AfterReturning(returning = "re", pointcut = "webAccess()")
	public void doAfterReturning(Object re){
		System.out.println(System.currentTimeMillis() - startTime.get());
		startTime.remove();
	}
}
