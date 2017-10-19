package com.xuebaclass.sato.mapper.crm;

import com.xuebaclass.sato.config.SpringSecurityKeycloakAutditorAware;
import com.xuebaclass.sato.model.request.SalesDailyMyselfRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * Created by sunhao on 2017-08-17.
 */
public interface ReportMapper {

    class ReportSqlProvider extends SpringSecurityKeycloakAutditorAware {
        public String getDistributedCount(Map<String, Object> parameters) {
            SalesDailyMyselfRequest request = (SalesDailyMyselfRequest) parameters.get("salesDailyMyselfRequest");

            String sql = "SELECT \n" +
                    "  OWNED_SALES_ID,\n" +
                    "  DATE(\n" +
                    "    DATE_ADD(\n" +
                    "      DISTRIBUTED_DATE,\n" +
                    "      INTERVAL 8 DAY_HOUR\n" +
                    "    )\n" +
                    "  ) DIS_DATE,\n" +
                    "  COUNT(*) TOTAL_NUMBER \n" +
                    "FROM\n" +
                    "  CUSTOMER \n" +
                    "WHERE OWNED_SALES_ID = '" + request.getSalesId() + "' \n" +
                    "  AND DISTRIBUTED_DATE BETWEEN '" + request.getFrom() + "' \n" +
                    "  AND '" + request.getTo() + "' \n" +
                    "GROUP BY OWNED_SALES_ID,\n" +
                    "  DIS_DATE";

            return sql;
        }

        public String getCallDuration(Map<String, Object> parameters) {
            Map options = (Map) parameters.get("options");

            String sql = "SELECT \n" +
                    "  SALES_ID,\n" +
                    "  DATE(START_TIME) S_TIME,\n" +
                    "  CEIL(SUM(CALL_DURATION) / 60) TOTAL_MIN \n" +
                    "FROM\n" +
                    "  CALL_RECORDS \n" +
                    "WHERE STATUS = '双方接听' \n" +
                    "  AND SALES_ID ='" + options.get("salesId") + "' \n" +
                    "  AND START_TIME BETWEEN '" + options.get("startDate") + "' \n" +
                    "  AND '" + options.get("endDate") + "' \n" +
                    "GROUP BY SALES_ID,\n" +
                    "  S_TIME ";

            return sql;
        }

        public String getOffset(Map<String, Object> parameters) {
            Map options = (Map) parameters.get("options");

            String sql = "SELECT \n" +
                    "  * \n" +
                    "FROM\n" +
                    "  OFFSET_RECORD \n" +
                    "WHERE SALES_ID = '" + options.get("salesId") + "'" +
                    "  AND OFFSET_DATE BETWEEN '" + options.get("startDate") + "' \n" +
                    "  AND '" + options.get("endDate") + "' ";

            return sql;
        }


    }

    /**
     * @param salesDailyMyselfRequest
     * @return
     */
    @SelectProvider(type = ReportSqlProvider.class, method = "getDistributedCount")
    List<Map> getDistributedCount(@Param("salesDailyMyselfRequest") SalesDailyMyselfRequest salesDailyMyselfRequest);

    /**
     * @param options
     * @return
     */
    @SelectProvider(type = ReportSqlProvider.class, method = "getCallDuration")
    List<Map> getCallDuration(@Param("options") Map options);

    /**
     * @param options
     * @return
     */
    @SelectProvider(type = ReportSqlProvider.class, method = "getOffset")
    List<Map> getOffset(@Param("options") Map options);


}
