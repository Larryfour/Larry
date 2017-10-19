package com.xuebaclass.sato.mapper.sato;

import com.xuebaclass.sato.config.SpringSecurityKeycloakAutditorAware;
import com.xuebaclass.sato.model.request.SalesDailyMyselfRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

    class OrderSqlProvider extends SpringSecurityKeycloakAutditorAware {
        public String getFirstOrdersCount(Map<String, Object> parameters) {
            SalesDailyMyselfRequest request = (SalesDailyMyselfRequest) parameters.get("salesDailyMyselfRequest");

            String sql = "SELECT \n" +
                    "  DATE(\n" +
                    "    DATE_ADD(\n" +
                    "      IFNULL(t.PAYTIME, o.CREATETIME),\n" +
                    "      INTERVAL 8 DAY_HOUR\n" +
                    "    )\n" +
                    "  ) PAY_DATE,\n" +
                    "  COUNT(*) TOTAL_NUMBER,\n" +
                    "  SUM(o.TRADEAMOUNT) TOTAL_TRADEAMOUNT \n" +
                    "FROM\n" +
                    "  ORDERS o \n" +
                    "  LEFT JOIN TRANSACTION t \n" +
                    "    ON o.ID = t.ORDERID \n" +
                    "WHERE o.OTYPE = 2 \n" +
                    "  AND o.STATUS = 0 \n" +
                    "  AND SUBSTRING_INDEX(o.CREATEBY, ':', - 1) = '" + getCurrentAuditorName() + "' \n" +
                    "  AND IFNULL(t.PAYTIME, o.CREATETIME) BETWEEN '" + request.getFrom() + "' \n" +
                    "  AND '" + request.getTo() + "' \n" +
                    "GROUP BY PAY_DATE ";

            return sql;
        }

        public String getRepeatOrdersCount(Map<String, Object> parameters) {
            SalesDailyMyselfRequest request = (SalesDailyMyselfRequest) parameters.get("salesDailyMyselfRequest");

            String sql = "SELECT \n" +
                    "  DATE(\n" +
                    "    DATE_ADD(\n" +
                    "      IFNULL(t.PAYTIME, o.CREATETIME),\n" +
                    "      INTERVAL 8 DAY_HOUR\n" +
                    "    )\n" +
                    "  ) PAY_DATE,\n" +
                    "  COUNT(*) TOTAL_NUMBER,\n" +
                    "  SUM(o.TRADEAMOUNT) TOTAL_TRADEAMOUNT \n" +
                    "FROM\n" +
                    "  ORDERS o \n" +
                    "  LEFT JOIN TRANSACTION t \n" +
                    "    ON o.ID = t.ORDERID \n" +
                    "WHERE o.OTYPE = 3 \n" +
                    "  AND o.STATUS = 0 \n" +
                    "  AND SUBSTRING_INDEX(o.CREATEBY, ':', - 1) = '" + getCurrentAuditorName() + "' \n" +
                    "  AND IFNULL(t.PAYTIME, o.CREATETIME) BETWEEN '" + request.getFrom() + "' \n" +
                    "  AND '" + request.getTo() + "' \n" +
                    "GROUP BY PAY_DATE ";

            return sql;
        }
    }

    /**
     * @param salesDailyMyselfRequest
     * @return
     */
    @SelectProvider(type = OrderSqlProvider.class, method = "getFirstOrdersCount")
    List<Map> getFirstOrdersCount(@Param("salesDailyMyselfRequest") SalesDailyMyselfRequest salesDailyMyselfRequest);

    /**
     * @param salesDailyMyselfRequest
     * @return
     */
    @SelectProvider(type = OrderSqlProvider.class, method = "getRepeatOrdersCount")
    List<Map> getRepeatOrdersCount(@Param("salesDailyMyselfRequest") SalesDailyMyselfRequest salesDailyMyselfRequest);
}
