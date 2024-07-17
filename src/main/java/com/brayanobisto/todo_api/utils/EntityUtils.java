package com.brayanobisto.todo_api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EntityUtils {

    private static final Logger logger = LoggerFactory.getLogger(EntityUtils.class);

    public static <T> void copyNonNullProperties(T source, T target) {
        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = target.getClass().getDeclaredFields();

        Map<String, Field> targetFieldMap = new HashMap<>();
        for (Field field : targetFields) {
            field.setAccessible(true);
            targetFieldMap.put(field.getName(), field);
        }

        for (Field sourceField : sourceFields) {
            try {
                sourceField.setAccessible(true);
                Object value = sourceField.get(source);
                if (value != null) {
                    Field targetField = targetFieldMap.get(sourceField.getName());
                    if (targetField != null) {
                        targetField.set(target, value);
                    }
                }
            } catch (IllegalAccessException e) {
                logger.error("Error copying properties from source to target", e);
                throw new RuntimeException("Error copying properties", e);
            }
        }
    }
}
