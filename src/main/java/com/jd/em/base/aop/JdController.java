package com.jd.em.base.aop;

import java.lang.annotation.*;

/**
 * @author KING
 *
 * <p>Description: </p>
 *
 * 2019年1月21日
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface JdController {

	public String description() default "该方法未定义注释详情"; 
}
