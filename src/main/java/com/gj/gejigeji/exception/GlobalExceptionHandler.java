package com.gj.gejigeji.exception;

import com.gj.gejigeji.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.ResolvableType;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @ExceptionHandler(value = Exception.class)
    public void defaultExceptionHandle(Exception e) {
        log.error("GlobalExceptionHandler defaultExceptionHandle error", e);
        try {
            JsonUtil.writeResponse(e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (IOException e1) {
            log.error("GlobalExceptionHandler defaultExceptionHandle error", e1);
        }
    }

    @ExceptionHandler(value = BaseRuntimeException.class)
    public void baseRuntimeExceptionHandle(HttpServletRequest request, BaseRuntimeException e) {
        log.error("GlobalExceptionHandler baseRuntimeExceptionHandle error", e);
        try {
            JsonUtil.writeErrorResponse(e.getCode(), Arrays.asList(messageSource.getMessage(e.getCode(), null, RequestContextUtils.getLocale(request))), e.getStatus());
        } catch (IOException e1) {
            log.error("GlobalExceptionHandler baseRuntimeExceptionHandle error", e1);
        }
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public void parametersExceptionHandle(HttpServletRequest request, MethodArgumentNotValidException e) {
        log.error("GlobalExceptionHandler parametersExceptionHandle error", e);
        try {
            List<FieldError> errorList = e.getBindingResult().getFieldErrors();
            List<String> errors = new ArrayList<>(errorList.size());
            errorList.forEach(d -> {
                String prop = "";
                try {

                    String code = new StringBuilder(fieldFullName(e.getBindingResult().getTarget().getClass(), d.getField())).toString();
                    final Locale locale1 = LocaleContextHolder.getLocale();
                    final Locale locale = RequestContextUtils.getLocale(request);
                    String name = messageSource.getMessage(code, null, locale);
                    if (name != null) {
                        prop = name;
                    }
                } catch (Exception e1) {
                    log.error("GlobalExceptionHandler parametersExceptionHandle", e1);
                }
                errors.add(prop + d.getDefaultMessage());
            });
            JsonUtil.writeErrorResponse(null, errors, HttpServletResponse.SC_BAD_REQUEST);
        } catch (IOException e1) {
            log.error("GlobalExceptionHandler parametersExceptionHandle error", e1);
        }
    }

    private String fieldFullName(Class<?> clazz, String name){
        if (name.contains(".")) {
            String[] strings = name.split("\\.");
            for (int i = 0; i < strings.length; i ++) {
                name = strings[i];
                if (name.contains("[")) { //数组等去除下标
                    name = name.substring(0, name.indexOf('['));
                }
                if (i == strings.length - 1) {
                    return ReflectionUtils.findField(clazz, name).toString().split(" ")[2];
                }
                Field field = ReflectionUtils.findField(clazz, name);
                if (field.getType().isArray() || field.getType().isInterface()) { //如果是数组或者Collection等集合类型，则需要获取泛型实际类型
                    clazz = ResolvableType.forField(field).getGeneric(0).resolve();
                } else {
                    clazz = field.getType();
                }
            }
        }
        final String s = ReflectionUtils.findField(clazz, name).toString();
        return s.split(" ")[2];
    }

}
