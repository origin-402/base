package com.jd.em.base.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author KING
 *
 * <p>Description: error_path</p>
 *
 * 2019年1月21日
 *
 */
@Controller
public class MainErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";
	   
	 @RequestMapping(value=ERROR_PATH)
	 public String handleError(){  
	        return "error/404";  
	 }
	   
	 @Override  
	 public String getErrorPath() {  
	  return ERROR_PATH;  
	 }
}
