package me.wh.stock.core.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Jackson 处理json
 * 
 * @author luoyong
 * @date 2016-5-19
 */
public final class JacksonUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JacksonUtil.class.getName());

    private static ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
    }

    /**
     * 将对象转换成json
     * 
     * @param obj
     * @return
     */
    public static String toJson(final Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            LOG.error("", e);
        } catch (JsonMappingException e) {
            LOG.error("", e);
        } catch (IOException e) {
            LOG.error("", e);
        }
        return null;
    }

    /**
     * 将json转换成bean对象
     * 
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T jsonToBean(final String json, final Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonParseException e) {
            LOG.error("", e);
        } catch (JsonMappingException e) {
            LOG.error("", e);
        } catch (IOException e) {
            LOG.error("", e);
        }
        return null;
    }

    private JacksonUtil() {
    }
}
