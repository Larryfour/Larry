package com.xuebaclass.sato.mapper.crm;

import com.xuebaclass.sato.config.SpringSecurityKeycloakAutditorAware;
import com.xuebaclass.sato.model.DynamicRecord;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * Created by sunhao on 2017-08-17.
 */
public interface DynamicRecordMapper {

    class RecordSqlProvider extends SpringSecurityKeycloakAutditorAware {
        private final static String TABLE_NAME = "DYNAMIC_RECORD";

        public String create() {
            return new SQL() {{
                INSERT_INTO(TABLE_NAME);

                VALUES("ID", "#{id}");
                VALUES("CUSTOMER_ID", "#{customerId}");
                VALUES("COMMENT", "#{comment}");
                VALUES("TYPE", "#{type}");
                VALUES("NAME", "#{name}");
                VALUES("USERNAME", "'" + getCurrentAuditorName() + "'");

                VALUES("CREATED_BY", "'" + getCurrentAuditor() + "'");
                VALUES("CREATED_DATE", "utc_timestamp()");
                VALUES("LAST_MODIFIED_BY", "'" + getCurrentAuditor() + "'");
                VALUES("LAST_MODIFIED_DATE", "utc_timestamp()");
            }}.toString();
        }

    }


    /**
     * @param dynamicRecord
     * @return
     */
    @InsertProvider(type = RecordSqlProvider.class, method = "create")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = String.class)
    void create(DynamicRecord dynamicRecord);

    /**
     * @param customerId
     * @return
     */
    @Select("SELECT * FROM DYNAMIC_RECORD WHERE CUSTOMER_ID = #{customerId} ORDER BY CREATED_DATE DESC")
    List<DynamicRecord> getRecordByCustomerId(String customerId);

}
