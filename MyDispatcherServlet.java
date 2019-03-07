package com.user.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

//自定义拦截器，设置编码
public class MyDispatcherServlet extends DispatcherServlet {
	
	private static final long serialVersionUID = 1L;
	
    @Override
    protected void doService(HttpServletRequest request, 
            HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");
        super.doService(request, response);
    }
    
}
