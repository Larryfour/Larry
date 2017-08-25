package com.xuebaclass.sato.mapper.crm;

import com.xuebaclass.sato.config.SpringSecurityKeycloakAutditorAware;
import com.xuebaclass.sato.model.CustomerTag;
import com.xuebaclass.sato.model.Tag;
import com.xuebaclass.sato.model.response.CustomerTagResponse;
import org.apache.ibatis.annotations.*;
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
            List<Tag> tags = (List<Tag>) parameters.get("tags");

            List<String> cancelTagIds = tags
                    .stream()
                    .map(t -> {
                        return "'" + t.getId() + "'";
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

        public String getCustomerTags(Map<String, Object> parameters) {
            String customerId = (String) parameters.get("customerId");

            return new SQL() {{
                SELECT("ct.CUSTOMER_ID,t.ID,t.NAME");
                FROM("CUSTOMER_TAG ct,TAG t");
                WHERE("ct.TAG_ID = t.ID");
                WHERE("ct.FLAG = 0");
                WHERE("ct.CUSTOMER_ID='" + customerId + "'");
                ORDER_BY("t.ID");

            }}.toString();
        }
    }

    /**
     * @param customerTag
     * @return
     */
    @InsertProvider(type = CustomerTagSqlProvider.class, method = "create")
    void create(CustomerTag customerTag);

    /**
     * @param customerId
     * @param tags
     * @return
     */
    @UpdateProvider(type = CustomerTagSqlProvider.class, method = "cancel")
    void cancel(@Param("customerId") String customerId, @Param("tags") List<Tag> tags);

    /**
     * @param customerId
     * @return
     */
    @SelectProvider(type = CustomerTagSqlProvider.class, method = "getCustomerTags")
    @Results({@Result(column = "CUSTOMER_ID", property = "customerId"),
            @Result(column = "ID", property = "tagId"),
            @Result(column = "NAME", property = "tagName")})
    List<CustomerTagResponse> getCustomerTags(@Param("customerId") String customerId);

}
