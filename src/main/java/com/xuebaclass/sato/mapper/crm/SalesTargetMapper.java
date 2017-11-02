package com.xuebaclass.sato.mapper.crm;

import com.xuebaclass.sato.model.SalesTarget;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by sunhao on 2017-10-25.
 */
public interface SalesTargetMapper {
    /**
     * @param
     * @return
     */
    @Select("SELECT * FROM SALES_TARGET WHERE TARGET_MONTH = #{targetMonth} AND STATUS = false")
    List<SalesTarget> getTargets(String targetMonth);
}
