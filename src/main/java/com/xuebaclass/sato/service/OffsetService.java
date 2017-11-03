package com.xuebaclass.sato.service;

import com.xuebaclass.sato.model.Offset;

/**
 * Created by sunhao on 2017-08-11.
 */
public interface OffsetService {
    void create(Offset offset) throws Exception;

    Offset getByDate(String offsetDate) throws Exception;
}
