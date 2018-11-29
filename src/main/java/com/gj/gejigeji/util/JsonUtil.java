package com.gj.gejigeji.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gj.gejigeji.vo.ErrorResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JsonUtil {

    public static void writeResponse(Object value) throws IOException {
        writeResponse(value, HttpServletResponse.SC_OK);
    }

    public static void writeResponse(Object value, int status) throws IOException {
        if ( ! (value instanceof String) ) {
            value = serialize(value);
        }
        HttpServletResponse response = ServletUtil.currentResponse();
        response.setStatus(status);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.getWriter().print(value);
    }

    public static void writeErrorResponse(String code, List<String> errors, int status) throws IOException {
        ErrorResult result = new ErrorResult();
        result.setErrors(errors);
        result.setCode(code);
        writeResponse(result, status);
    }

    public static String serialize(Object value) throws JsonProcessingException {
        ObjectMapper objectMapper = SpringContextHolder.getBean(ObjectMapper.class);
        return objectMapper.writeValueAsString(value);
    }

    public static <T> T deserialize(String json, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = SpringContextHolder.getBean(ObjectMapper.class);
        return objectMapper.readValue(json, clazz);
    }
}
