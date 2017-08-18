package com.xuebaclass.sato.mapper.crm;

import com.xuebaclass.sato.config.SpringSecurityKeycloakAutditorAware;
import com.xuebaclass.sato.model.Customer;
import com.xuebaclass.sato.model.request.DistributionRequest;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by kangfei on 2017-08-17.
 */
public interface CustomerMapper {

    class CustomerSqlProvider extends SpringSecurityKeycloakAutditorAware {
        private final static String TABLE_NAME = "CUSTOMER";

        public String create() {
            return new SQL() {{
                INSERT_INTO(TABLE_NAME);

                VALUES("ID", "#{id}");
                VALUES("CONTACT_NAME", "#{contactName}");
                VALUES("CONTACT_MOBILE", "#{contactMobile}");
                VALUES("XUEBA_NO", "#{xuebaNo}");
                VALUES("NAME", "#{name}");
                VALUES("MOBILE", "#{mobile}");
                VALUES("GENDER", "#{gender}");
                VALUES("QQ", "#{qq}");
                VALUES("PARENTS", "#{parents}");
                VALUES("PARENTS_MOBILE", "#{parentsMobile}");
                VALUES("PROVINCE", "#{province}");
                VALUES("CITY", "#{city}");
                VALUES("DISTRICT", "#{district}");
                VALUES("SCHOOL", "#{school}");
                VALUES("GRADE", "#{grade}");
                VALUES("GRADE_NOTE", "#{gradeNote}");
                VALUES("TEACHING_ATERIAL", "#{teachingAterial}");
                VALUES("TEACHING_ATERIAL_NOTE", "#{teachingAterialNote}");
                VALUES("SCORES", "#{scores}");
                VALUES("FULL_MARKS", "#{fullMarks}");
                VALUES("COMMENT", "#{comment}");
                VALUES("TUTORIAL_FLAG", "#{tutorialFlag}");
                VALUES("LEARNING_PROCESS", "#{learningProcess}");
                VALUES("NEXT_TEST", "#{nextTest}");
                VALUES("NEXT_TEST_DATE", "#{nextTestDate}");
                VALUES("ANSWER_INTERVAL", "#{answerInterval}");
                VALUES("OWNED_SALES_ID", "'1'");
                VALUES("OWNED_SALES_USERNAME", "'linyiran'");
                VALUES("OWNED_SALES_NAME", "'林依然'");

                VALUES("CREATED_BY", "'" + getCurrentAuditor() + "'");
                VALUES("CREATED_DATE", "utc_timestamp()");
                VALUES("LAST_MODIFIED_BY", "'" + getCurrentAuditor() + "'");
                VALUES("LAST_MODIFIED_DATE", "utc_timestamp()");
            }}.toString();
        }

        public String update() {
            return new SQL() {{
                UPDATE(TABLE_NAME);

                SET("XUEBA_NO=#{xuebaNo}");
                SET("NAME=#{name}");
                SET("MOBILE=#{mobile}");
                SET("GENDER=#{gender}");
                SET("QQ=#{qq}");
                SET("PARENTS=#{parents}");
                SET("PARENTS_MOBILE=#{parentsMobile}");
                SET("PROVINCE=#{province}");
                SET("CITY=#{city}");
                SET("DISTRICT=#{district}");
                SET("SCHOOL=#{school}");
                SET("GRADE=#{grade}");
                SET("GRADE_NOTE=#{gradeNote}");
                SET("TEACHING_ATERIAL=#{teachingAterial}");
                SET("TEACHING_ATERIAL_NOTE=#{teachingAterialNote}");
                SET("SCORES=#{scores}");
                SET("FULL_MARKS=#{fullMarks}");
                SET("COMMENT=#{comment}");
                SET("TUTORIAL_FLAG=#{tutorialFlag}");
                SET("LEARNING_PROCESS=#{learningProcess}");
                SET("NEXT_TEST=#{nextTest}");
                SET("NEXT_TEST_DATE=#{nextTestDate}");
                SET("ANSWER_INTERVAL=#{answerInterval}");

                VALUES("LAST_MODIFIED_BY", "'" + getCurrentAuditor() + "'");
                VALUES("LAST_MODIFIED_DATE", "utc_timestamp()");

                WHERE("ID = #{id}");
            }}.toString();
        }


        public String getCustomers(Map<String, Object> parameters) {
            String sortField = (String) parameters.get("sortField");
            return new SQL() {
                {
                    SELECT("*");
                    FROM(TABLE_NAME);
                    WHERE("1=1");
                    ORDER_BY(sortField + " desc");
                }
            }.toString();
        }

        public String getMyselfCustomers(Map<String, Object> parameters) {
            String sortField = (String) parameters.get("sortField");
            return new SQL() {
                {
                    SELECT("*");
                    FROM(TABLE_NAME);
                    WHERE("1=1");
                    WHERE("OWNED_SALES_USERNAME = '" + getCurrentAuditorName() + "'");
                    ORDER_BY(sortField + " desc");
                }
            }.toString();
        }

        public String distribution(Map<String, Object> parameters) {
            DistributionRequest distributionRequest = (DistributionRequest) parameters.get("distributionRequest");

            distributionRequest.setCustomerIds(
                    distributionRequest
                            .getCustomerIds()
                            .stream()
                            .map(string -> {
                                return "'" + string + "'";
                            })
                            .collect(Collectors.toList()));


            return new SQL() {{
                UPDATE(TABLE_NAME);

                SET("OWNED_SALES_ID=#{distributionRequest.ownedSalesID}");
                SET("OWNED_SALES_NAME=#{distributionRequest.ownedSalesName}");
                SET("OWNED_SALES_USERNAME=#{distributionRequest.ownedSalesUserName}");

                VALUES("LAST_MODIFIED_BY", "'" + getCurrentAuditor() + "'");
                VALUES("LAST_MODIFIED_DATE", "utc_timestamp()");

                WHERE("ID IN (" + StringUtils.join(distributionRequest.getCustomerIds().toArray(), ",") + ")");
            }}.toString();
        }

    }


    /**
     * @param customer
     */
    @InsertProvider(type = CustomerSqlProvider.class, method = "create")
    @SelectKey(statement = "SELECT UUID() FROM DUAL;", keyProperty = "id", before = true, resultType = String.class)
    void create(Customer customer);

    /**
     * @param customer
     */
    @UpdateProvider(type = CustomerSqlProvider.class, method = "update")
    void update(Customer customer);

    /**
     * @param id
     */
    @Select("SELECT * FROM CUSTOMER WHERE ID = #{id}")
    Customer getById(String id);

    /**
     * @param sortField
     * @param customer
     * @return
     */
    @SelectProvider(type = CustomerSqlProvider.class, method = "getCustomers")
    List<Customer> getCustomers(@Param("sortField") String sortField, Customer customer);

    /**
     * @param sortField
     * @param customer
     * @return
     */
    @SelectProvider(type = CustomerSqlProvider.class, method = "getMyselfCustomers")
    List<Customer> getMyselfCustomers(@Param("sortField") String sortField, Customer customer);

    /**
     * @param distributionRequest
     */
    @UpdateProvider(type = CustomerSqlProvider.class, method = "distribution")
    void distribution(@Param("distributionRequest") DistributionRequest distributionRequest);
}
