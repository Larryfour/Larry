package com.xuebaclass.sato.mapper.crm;

import com.xuebaclass.sato.config.SpringSecurityKeycloakAutditorAware;
import com.xuebaclass.sato.model.CallRecord;
import com.xuebaclass.sato.model.Tag;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sunhao on 2017-08-17.
 */
public interface CallRecordMapper {

    class CallRecordSqlProvider extends SpringSecurityKeycloakAutditorAware {

        public String getStudentRecords(Map<String, Object> parameters) {
            String from = (String) parameters.get("from");
            String to = (String) parameters.get("to");

            String sql = "SELECT \n" +
                    "  cr.CUSTOMER_NUMBER,\n" +
                    "  cr.SALES_ID,\n" +
                    "  cr.STATUS,\n" +
                    "  cr.CALL_DURATION,\n" +
                    "  cr.START_TIME \n" +
                    "FROM\n" +
                    "  CALL_RECORDS cr, CUSTOMER c\n" +
                    "WHERE cr.START_TIME BETWEEN '" + from + "' \n" +
                    "  AND '" + to + "' \n" +
                    "  AND cr.CUSTOMER_NUMBER = c.MOBILE\n" +
                    "  AND cr.SALES_ID <>0\n" +
                    "  ORDER BY cr.SALES_ID,cr.CUSTOMER_NUMBER\n";
            return sql;
        }

    }


    /**
     * @param from
     * @param to
     * @return
     */
    @SelectProvider(type = CallRecordSqlProvider.class, method = "getStudentRecords")
    List<CallRecord> getStudentRecords(@Param("from") String from, @Param("to") String to);

}
