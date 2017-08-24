package com.xuebaclass.sato.mapper.crm;

import com.xuebaclass.sato.config.SpringSecurityKeycloakAutditorAware;
import com.xuebaclass.sato.model.CustomerTag;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sunhao on 2017-08-17.
 */
public interface CustomerTagMapper {

    class CustomerTagSqlProvider extends SpringSecurityKeycloakAutditorAware {
        private final static String TABLE_NAME = "CUSTOMER_TAG";

        public String create() {
            return new SQL() {{
                INSERT_INTO(TABLE_NAME);

                VALUES("ID", "#{id}");
                VALUES("CUSTOMER_ID", "#{customerId}");
                VALUES("TAG_ID", "#{tagId}");
                VALUES("FLAG", "#{flag}");

                VALUES("CREATED_BY", "'" + getCurrentAuditor() + "'");
                VALUES("CREATED_DATE", "utc_timestamp()");
                VALUES("LAST_MODIFIED_BY", "'" + getCurrentAuditor() + "'");
                VALUES("LAST_MODIFIED_DATE", "utc_timestamp()");
            }}.toString();
        }

        public String cancel(Map<String, Object> parameters) {
            String customerId = (String) parameters.get("customerId");
            List<String> tagIds = (List<String>) parameters.get("tagIds");

            List<String> cancelTagIds = tagIds
                    .stream()
                    .map(string -> {
                        return "'" + string + "'";
                    })
                    .collect(Collectors.toList());

            return new SQL() {{
                UPDATE(TABLE_NAME);

                SET("FLAG=1");
                SET("LAST_MODIFIED_BY='" + getCurrentAuditor() + "'");
                SET("LAST_MODIFIED_DATE=utc_timestamp()");
                WHERE("TAG_ID IN (" + StringUtils.join(cancelTagIds.toArray(), ",") + ")");
                WHERE("CUSTOMER_ID = '" + customerId + "'");
            }}.toString();
        }

    }

    /**
     * @param customerTag
     */
    @InsertProvider(type = CustomerTagSqlProvider.class, method = "create")
    void create(CustomerTag customerTag);

    /**
     * @param customerId
     * @param tagIds
     */
    @UpdateProvider(type = CustomerTagSqlProvider.class, method = "cancel")
    void cancel(@Param("customerId") String customerId, @Param("tagIds") List<String> tagIds);

    /**
     * @param customerId
     */
    @Select("SELECT * FROM CUSTOMER_TAG WHERE CUSTOMER_ID = #{customerId} AND FLAG = 0 ORDER BY TAG_ID")
    List<CustomerTag> getCustomerTags(@Param("customerId") String customerId);

}