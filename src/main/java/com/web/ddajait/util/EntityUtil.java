package com.web.ddajait.util;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntityUtil {
    // 소스 객체에서 대상 객체로 속성을 복사
      public static void copyNonNullProperties(Object source, Object target) {

        if (source == null || target == null) {
            throw new IllegalArgumentException("Source and target must not be null");
        }
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    // null 속성 이름을 가져오는 메서드
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        
        // log.info("[EntityUtil][emptyNames] : "+emptyNames);
        return emptyNames.toArray(result);
    }
}
