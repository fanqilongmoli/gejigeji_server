package com.gj.gejigeji.interceptor;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HttpInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(HttpInterceptor.class);

    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("ThreadLocal StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //开始时间
        long beginTime = System.currentTimeMillis();
        startTimeThreadLocal.set(beginTime);
//        logger.debug("开始计时: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS").format(beginTime), request.getRequestURI());

        logger.warn("开始计时" + request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 得到线程绑定的局部变量（开始时间）

        long beginTime = startTimeThreadLocal.get();
        // 2、结束时间

        long endTime = System.currentTimeMillis();

        final long l = endTime - beginTime;
//        logger.debug("计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
//                new SimpleDateFormat("hh:mm:ss.SSS").format(endTime),
//                DateFormatUtils.format(endTime - beginTime,"hh:mm:ss.SSS"),
//                request.getRequestURI(),
//                Runtime.getRuntime().maxMemory() / 1024 / 1024,
//                Runtime.getRuntime().totalMemory() / 1024 / 1024,
//                Runtime.getRuntime().freeMemory() / 1024 / 1024,
//                (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);

        logger.warn("耗时时间统计=======（）（）======》url" + request.getRequestURI() + ";耗时" + DateFormatUtils.format(endTime - beginTime, "mm:ss.SSS"));

    }
}
