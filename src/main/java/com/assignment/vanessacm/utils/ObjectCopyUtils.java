package com.assignment.vanessacm.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

/**
 * This class is responsible to copy data from one object to another
 * Util to help with entity update
 */
public class ObjectCopyUtils {
    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    private static String[] getNullPropertyNames (Object source) {
        final BeanWrapper wrappedSource  = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                     .map(FeatureDescriptor::getName)
                     .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                     .toArray(String[]::new);
    }
}
