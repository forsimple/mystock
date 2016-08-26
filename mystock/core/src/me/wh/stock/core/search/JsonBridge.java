package me.wh.stock.core.search;

import java.util.Map;

import me.wh.stock.core.util.JacksonUtil;

import org.hibernate.search.bridge.ParameterizedBridge;
import org.hibernate.search.bridge.TwoWayStringBridge;

public class JsonBridge implements TwoWayStringBridge, ParameterizedBridge{
    Map<String, String> parameters=null;
    @Override
    public String objectToString(Object object) {
        return JacksonUtil.toJson(object);
    }

    @Override
    public Object stringToObject(String stringValue) {
        return JacksonUtil.jsonToBean(stringValue, Object.class);
    }

    @Override
    public void setParameterValues(Map<String, String> parameters) {
        this.parameters=parameters;
    }
}
