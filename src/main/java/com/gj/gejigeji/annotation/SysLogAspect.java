package com.gj.gejigeji.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gj.gejigeji.util.JsonUtil;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class SysLogAspect {

    @Pointcut("@annotation(com.gj.gejigeji.annotation.SysLog)")
    public void logPointCut() {

    }


    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        long beginTime = System.currentTimeMillis();

        //执行方法
        Object proceed = point.proceed();

        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return proceed;
    }

    /**
     * 保存日志
     *
     * @param point
     * @param time
     */
    private void saveSysLog(ProceedingJoinPoint point, long time) {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();


        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println(request);

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        // 获取请求的方法的名字
        String name = point.getTarget().getClass().getName();

        String ApiOperation = method.getAnnotation(ApiOperation.class).value();

        //请求的参数
        Object[] args = point.getArgs();
        try {
            String serialize = JsonUtil.serialize(args[0]);
            System.out.println(serialize);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(args.length);
    }
}
