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
 * Created by sunhao on 2017-08-17.
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
                VALUES("OWNED_SALES_ID", "#{ownedSalesID}");
                VALUES("OWNED_SALES_USERNAME", "#{ownedSalesUserName}");
                VALUES("OWNED_SALES_NAME", "#{ownedSalesName}");

                VALUES("CREATED_BY", "'" + getCurrentAuditor() + "'");
                VALUES("CREATED_DATE", "utc_timestamp()");
                VALUES("LAST_MODIFIED_BY", "'" + getCurrentAuditor() + "'");
                VALUES("LAST_MODIFIED_DATE", "utc_timestamp()");
            }}.toString();
        }

        public String update(Map<String, Object> parameters) {
            String id = (String) parameters.get("id");
            Customer customer = (Customer) parameters.get("customer");

            return new SQL() {{
                UPDATE(TABLE_NAME);

                SET("XUEBA_NO=#{customer.xuebaNo}");
                SET("NAME=#{customer.name}");
                SET("MOBILE=#{customer.mobile}");
                SET("GENDER=#{customer.gender}");
                SET("QQ=#{customer.qq}");
                SET("PARENTS=#{customer.parents}");
                SET("PARENTS_MOBILE=#{customer.parentsMobile}");
                SET("PROVINCE=#{customer.province}");
                SET("CITY=#{customer.city}");
                SET("DISTRICT=#{customer.district}");
                SET("SCHOOL=#{customer.school}");
                SET("GRADE=#{customer.grade}");
                SET("GRADE_NOTE=#{customer.gradeNote}");
                SET("TEACHING_ATERIAL=#{customer.teachingAterial}");
                SET("TEACHING_ATERIAL_NOTE=#{customer.teachingAterialNote}");
                SET("SCORES=#{customer.scores}");
                SET("FULL_MARKS=#{customer.fullMarks}");
                SET("COMMENT=#{customer.comment}");
                SET("TUTORIAL_FLAG=#{customer.tutorialFlag}");
                SET("LEARNING_PROCESS=#{customer.learningProcess}");
                SET("NEXT_TEST=#{customer.nextTest}");
                SET("NEXT_TEST_DATE=#{customer.nextTestDate}");
                SET("ANSWER_INTERVAL=#{customer.answerInterval}");

                SET("LAST_MODIFIED_BY='" + getCurrentAuditor() + "'");
                SET("LAST_MODIFIED_DATE=utc_timestamp()");

                WHERE("ID = '"+id+"'");
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

            List<String> customerIds = distributionRequest
                    .getCustomerIds()
                    .stream()
                    .map(string -> {
                        return "'" + string + "'";
                    })
                    .collect(Collectors.toList());

            return new SQL() {{
                UPDATE(TABLE_NAME);

                SET("OWNED_SALES_ID=#{distributionRequest.ownedSalesID}");
                SET("OWNED_SALES_NAME=#{distributionRequest.ownedSalesName}");
                SET("OWNED_SALES_USERNAME=#{distributionRequest.ownedSalesUserName}");

                SET("LAST_MODIFIED_BY='" + getCurrentAuditor() + "'");
                SET("LAST_MODIFIED_DATE=utc_timestamp()");

                WHERE("ID IN (" + StringUtils.join(customerIds.toArray(), ",") + ")");
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
    void update(@Param("id") String id, @Param("customer") Customer customer);

    /**
     * @param id
     */
    @Select("SELECT * FROM CUSTOMER WHERE ID = #{id}")
    Customer getById(String id);

    /**
     * @param mobile
     */
    @Select("SELECT * FROM CUSTOMER WHERE CONTACT_MOBILE = #{mobile}")
    Customer getByContactMobile(String mobile);


    /**
     * @param mobile
     */
    @Select("SELECT * FROM CUSTOMER WHERE MOBILE = #{mobile}")
    Customer getByMobile(String mobile);

    /**
     * @param mobile
     */
    @Select("SELECT * FROM CUSTOMER WHERE MOBILE = #{mobile} AND ID <> #{id}")
    Customer checkMobileExist(@Param("mobile") String mobile, @Param("id") String id);

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
