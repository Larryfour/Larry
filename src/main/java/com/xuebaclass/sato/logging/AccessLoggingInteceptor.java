package com.xuebaclass.sato.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wenyong on 2017/7/20.
 */
public class AccessLoggingInteceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AccessLoggingInteceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // ip, uid
        String ip = request.getRemoteAddr();
        String username = request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "";
        long startTime = (Long)request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;

        Exception errorException = (Exception) request.getAttribute(DispatcherServlet.EXCEPTION_ATTRIBUTE);

        final StringBuilder b = new StringBuilder();
        b.append(ip);
        b.append(" ");
        b.append(request.getMethod());
        b.append(" ");
        b.append(request.getRequestURI());
        if (request.getQueryString() != null) {
            b.append("?"+request.getQueryString());
        }
        b.append(" ");
        b.append(response.getStatus());
        b.append(" ");
        b.append("0");
        b.append(" ");
        b.append(username);
        b.append(" ");
        b.append(executeTime);
        b.append(" \"");
        b.append(request.getHeader("User-Agent"));
        b.append("\" ");
        if (errorException != null) {
            b.append("\""+errorException.getMessage()+"\"");
        }
        logger.info(b.toString());
    }
}
