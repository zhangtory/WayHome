package com.zhangtory.wayhomecore.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhangtory
 * @date: 10/24 11:17
 * @description: 对象处理
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    /**
     * 将对象转换为map
     * @param obj
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj) {
        Class<?> clazz = obj.getClass();
        Map<String, Object> map = new HashMap<>((int) (clazz.getDeclaredFields().length / 0.75D));
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = null;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            map.put(fieldName, value);
        }
        return map;
    }

}
