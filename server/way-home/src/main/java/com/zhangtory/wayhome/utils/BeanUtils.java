package com.zhangtory.wayhome.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/24 11:17
 * @Description:
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
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

    public static <T> List<T> copyListProperties(List<?> source, Class<T> targetClass) {
        if (source == null) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>();
        try {
            for (Object o : source) {
                T tClass = targetClass.newInstance();
                org.springframework.beans.BeanUtils.copyProperties(o, tClass);
                list.add(tClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
