package com.xuebaclass.sato.mapper.crm;

import com.xuebaclass.sato.model.Sales;
import com.xuebaclass.sato.model.SalesTarget;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * Created by sunhao on 2017-10-25.
 */
public interface SalesTargetMapper {

    class TargetSqlProvider {

        private final static String TABLE_NAME = "SALES_TARGET";

        public String create() {
            return new SQL() {{
                INSERT_INTO(TABLE_NAME);

                VALUES("SALES_ID", "#{salesId}");
                VALUES("TARGET_AMOUNT", "#{targetAmount}");
                VALUES("TARGET_ORDERS", "#{targetOrders}");
                VALUES("TARGET_MONTH", "#{targetMonth}");
            }}.toString();
        }
    }

    /**
     * @param
     * @return
     */
    @Select("SELECT * FROM SALES_TARGET WHERE TARGET_MONTH = #{targetMonth} AND STATUS = false")
    List<SalesTarget> getTargets(String targetMonth);

    /**
     * @param
     * @return
     */
    @Update("UPDATE SALES_TARGET SET STATUS= 1 WHERE ID = #{targetId}")
    void deleteTarget(Integer targetId);

    /**
     * @param
     * @return
     */
    @Update("UPDATE SALES_TARGET SET TARGET_AMOUNT = #{salesTarget.targetAmount}, TARGET_ORDERS = #{salesTarget.targetOrders} WHERE ID = #{salesTarget.id}")
    void update(@Param("salesTarget") SalesTarget salesTarget);


    @InsertProvider(type = TargetSqlProvider.class, method = "create")
    void create(SalesTarget target);

    @Select("SELECT * FROM SALES_TARGET WHERE SALES_ID = #{salesId} AND TARGET_MONTH = #{targetMonth} AND STATUS = false")
    SalesTarget getBySalesIdAndMonth(@Param("salesId") String salesId, @Param("targetMonth") String targetMonth);


}
