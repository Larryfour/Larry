package com.xuebaclass.sato.common;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenyong on 2017/6/21.
 */
public class SatoSort {
    public static String getSort(Pageable pageable, String defaultSort) {
        if (pageable != null && pageable.getSort() != null) {
            List<Sort.Order> newOrders = new ArrayList<>();
            for (Sort.Order order : pageable.getSort()) {
                newOrders.add(order.withProperty(addUnderscores(order.getProperty())));
            }
            return StringUtils.collectionToCommaDelimitedString(newOrders).replace(":", "").replace(";", "").toUpperCase();
        }
        return defaultSort;
    }

    private static String addUnderscores(String name) {
        StringBuffer buf = new StringBuffer(name.replace('.', '_'));
        for (int i = 1; i < buf.length() - 1; i++) {
            if (Character.isLowerCase(buf.charAt(i - 1)) && Character.isUpperCase(buf.charAt(i)) && Character
                .isLowerCase(buf.charAt(i + 1))) {
                buf.insert(i++, '_');
            }
        }
        return buf.toString().toUpperCase();
    }
}
