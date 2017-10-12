package com.xuebaclass.sato.mapper.crm;

import com.xuebaclass.sato.config.SpringSecurityKeycloakAutditorAware;
import com.xuebaclass.sato.model.Customer;
import com.xuebaclass.sato.model.request.CustomersMyselfRequest;
import com.xuebaclass.sato.model.request.CustomersRequest;
import com.xuebaclass.sato.model.request.DistributionRequest;
import com.xuebaclass.sato.model.response.CustomersResponse;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

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
                VALUES("DISTRIBUTED_BY", "#{distributedBy}");
                VALUES("DISTRIBUTED_DATE", "#{distributedDate}");
                VALUES("SOURCE", "#{source}");

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

                WHERE("ID = '" + id + "'");
            }}.toString();
        }


        public String getCustomers(Map<String, Object> parameters) {
            String sortField = (String) parameters.get("sortField");
            CustomersRequest request = (CustomersRequest) parameters.get("request");

            String nameSql = "";

            if (request.getTagIds() != null && !request.getTagIds().isEmpty()) {
                nameSql = "SELECT \n" +
                        "      ct.CUSTOMER_ID,\n" +
                        "      GROUP_CONCAT(NAME SEPARATOR ',') TAG_NAMES \n" +
                        "    FROM\n" +
                        "      TAG t,\n" +
                        "      CUSTOMER_TAG ct,\n" +
                        "      (SELECT DISTINCT \n" +
                        "        CUSTOMER_ID \n" +
                        "      FROM\n" +
                        "        CUSTOMER_TAG \n" +
                        "      WHERE TAG_ID IN (" + StringUtils.join(request.getTagIds().toArray(), ",") + ") \n" +
                        "        AND FLAG = 0 ) temp \n" +
                        "    WHERE t.ID = ct.TAG_ID \n" +
                        "      AND t.FLAG = 0 \n" +
                        "      AND ct.FLAG = 0 \n" +
                        "      AND ct.CUSTOMER_ID = temp.CUSTOMER_ID \n" +
                        "    GROUP BY ct.CUSTOMER_ID";
            } else {
                nameSql = "SELECT \n" +
                        "   ct.CUSTOMER_ID,\n" +
                        "   GROUP_CONCAT(NAME SEPARATOR ',') TAG_NAMES \n" +
                        "  FROM\n" +
                        "   TAG t,\n" +
                        "   CUSTOMER_TAG ct\n" +
                        "  WHERE t.ID = ct.TAG_ID \n" +
                        "   AND t.FLAG = 0 \n" +
                        "   AND ct.FLAG = 0 \n" +
                        "  GROUP BY ct.CUSTOMER_ID";
            }

            String part1 = "";
            String part2 = "";

            if (nonNull(request.getTagIds()) && !request.getTagIds().isEmpty()) {

                part1 = "  INNER JOIN \n" +
                        "    (SELECT DISTINCT \n" +
                        "      CUSTOMER_ID \n" +
                        "    FROM\n" +
                        "      CUSTOMER_TAG \n" +
                        "    WHERE FLAG = 0 \n" +
                        "      AND TAG_ID IN (" + StringUtils.join(request.getTagIds().toArray(), ",") + ")) uniq_table \n" +
                        "    ON c.ID = uniq_table.CUSTOMER_ID \n";
            } else {
                part2 = "WHERE 1 = 1 \n";
            }


            if (!"".equals(part1)) {
                part1 = parseCondition(part1, request);
            } else {
                part2 = parseCondition(part2, request);
            }

            String sql = "SELECT \n" +
                    "  c.*,\n" +
                    "  n.TAG_NAMES \n" +
                    "FROM\n" +
                    "  CUSTOMER c \n" +
                    part1 +
                    "  LEFT JOIN (" + nameSql + ") n \n" +
                    "    ON n.CUSTOMER_ID = c.ID \n" +
                    part2;

            sql += " ORDER BY c." + sortField + " DESC ";

            return sql;
        }

        private String parseCondition(String sql, CustomersRequest request) {
            if (!StringUtils.isEmpty(request.getName())) {
                sql += " AND c.name LIKE '%" + request.getName() + "%' \n";
            }

            if (!StringUtils.isEmpty(request.getMobile())) {
                sql += " AND c.mobile LIKE '" + request.getMobile() + "%' \n";
            }

            if (!StringUtils.isEmpty(request.getGrade())) {
                sql += " AND c.grade = '" + request.getGrade() + "' \n";
            }

            if (nonNull(request.getOwnedSalesID())) {
                sql += " AND c.OWNED_SALES_ID = " + request.getOwnedSalesID() + " \n";
            }

            if (!StringUtils.isEmpty(request.getSource())) {
                sql += " AND c.SOURCE = '" + request.getSource() + "' \n";
            }

            if (!StringUtils.isEmpty(request.getFrom()) && !StringUtils.isEmpty(request.getTo())) {
                sql += " AND c.CREATED_DATE BETWEEN '" + request.getFrom() + "' AND '" + request.getTo() + "' \n";
            }
            return sql;
        }

        public String getMyselfCustomers(Map<String, Object> parameters) {
            String sortField = (String) parameters.get("sortField");
            CustomersMyselfRequest request = (CustomersMyselfRequest) parameters.get("request");

            String nameSql = "";

            if (request.getTagIds() != null && !request.getTagIds().isEmpty()) {
                nameSql = "SELECT \n" +
                        "      ct.CUSTOMER_ID,\n" +
                        "      GROUP_CONCAT(NAME SEPARATOR ',') TAG_NAMES \n" +
                        "    FROM\n" +
                        "      TAG t,\n" +
                        "      CUSTOMER_TAG ct,\n" +
                        "      (SELECT DISTINCT \n" +
                        "        CUSTOMER_ID \n" +
                        "      FROM\n" +
                        "        CUSTOMER_TAG \n" +
                        "      WHERE TAG_ID IN (" + StringUtils.join(request.getTagIds().toArray(), ",") + ") \n" +
                        "        AND FLAG = 0 ) temp \n" +
                        "    WHERE t.ID = ct.TAG_ID \n" +
                        "      AND t.FLAG = 0 \n" +
                        "      AND ct.FLAG = 0 \n" +
                        "      AND ct.CUSTOMER_ID = temp.CUSTOMER_ID \n" +
                        "    GROUP BY ct.CUSTOMER_ID";
            } else {
                nameSql = "SELECT \n" +
                        "   ct.CUSTOMER_ID,\n" +
                        "   GROUP_CONCAT(NAME SEPARATOR ',') TAG_NAMES \n" +
                        "  FROM\n" +
                        "   TAG t,\n" +
                        "   CUSTOMER_TAG ct\n" +
                        "  WHERE t.ID = ct.TAG_ID \n" +
                        "   AND t.FLAG = 0 \n" +
                        "   AND ct.FLAG = 0 \n" +
                        "  GROUP BY ct.CUSTOMER_ID";
            }


            String part1 = "";
            String part2 = "";

            if (nonNull(request.getTagIds()) && !request.getTagIds().isEmpty()) {

                part1 = "  INNER JOIN \n" +
                        "    (SELECT DISTINCT \n" +
                        "      CUSTOMER_ID \n" +
                        "    FROM\n" +
                        "      CUSTOMER_TAG \n" +
                        "    WHERE FLAG = 0 \n" +
                        "      AND TAG_ID IN (" + StringUtils.join(request.getTagIds().toArray(), ",") + ")) uniq_table \n" +
                        "    ON c.ID = uniq_table.CUSTOMER_ID \n";
            } else {
                part2 = "WHERE 1 = 1 \n";
            }


            if (!"".equals(part1)) {
                part1 = parseCondition(part1, request);
            } else {
                part2 = parseCondition(part2, request);
            }

            String sql = "SELECT \n" +
                    "  c.*,\n" +
                    "  n.TAG_NAMES \n" +
                    "FROM\n" +
                    "  CUSTOMER c \n" +
                    part1 +
                    "  LEFT JOIN (" + nameSql + ") n \n" +
                    "    ON n.CUSTOMER_ID = c.ID \n" +
                    part2;

            sql += " ORDER BY c." + sortField + " DESC ";

            return sql;
        }

        private String parseCondition(String sql, CustomersMyselfRequest request) {
            sql += " AND c.OWNED_SALES_USERNAME = '" + getCurrentAuditorName() + "' \n";
            if (!StringUtils.isEmpty(request.getName())) {
                sql += " AND c.name LIKE '%" + request.getName() + "%' \n";
            }

            if (!StringUtils.isEmpty(request.getMobile())) {
                sql += " AND c.mobile LIKE '" + request.getMobile() + "%' \n";
            }

            if (!StringUtils.isEmpty(request.getGrade())) {
                sql += " AND c.grade = '" + request.getGrade() + "' \n";
            }

            if (!StringUtils.isEmpty(request.getSource())) {
                sql += " AND c.SOURCE = '" + request.getSource() + "' \n";
            }

            if (!StringUtils.isEmpty(request.getFrom()) && !StringUtils.isEmpty(request.getTo())) {
                sql += " AND c.CREATED_DATE BETWEEN '" + request.getFrom() + "' AND '" + request.getTo() + "' \n";
            }

            return sql;
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
                SET("DISTRIBUTED_BY='" + getCurrentAuditorName() + "'");
                SET("DISTRIBUTED_DATE=utc_timestamp");

                SET("LAST_MODIFIED_BY='" + getCurrentAuditor() + "'");
                SET("LAST_MODIFIED_DATE=utc_timestamp()");

                WHERE("ID IN (" + StringUtils.join(customerIds.toArray(), ",") + ")");
            }}.toString();
        }

    }


    /**
     * @param customer
     * @return
     */
    @InsertProvider(type = CustomerSqlProvider.class, method = "create")
    @SelectKey(statement = "SELECT UUID() FROM DUAL;", keyProperty = "id", before = true, resultType = String.class)
    void create(Customer customer);

    /**
     * @param customer
     * @return
     */
    @UpdateProvider(type = CustomerSqlProvider.class, method = "update")
    void update(@Param("id") String id, @Param("customer") Customer customer);

    /**
     * @param id
     * @return
     */
    @Select("SELECT * FROM CUSTOMER WHERE ID = #{id}")
    Customer getById(String id);

    /**
     * @param mobile
     * @return
     */
    @Select("SELECT * FROM CUSTOMER WHERE CONTACT_MOBILE = #{mobile}")
    Customer getByContactMobile(String mobile);


    /**
     * @param mobile
     * @return
     */
    @Select("SELECT * FROM CUSTOMER WHERE MOBILE = #{mobile}")
    Customer getByMobile(String mobile);

    /**
     * @param mobile
     * @return
     */
    @Select("SELECT * FROM CUSTOMER WHERE MOBILE = #{mobile} AND ID <> #{id}")
    Customer checkMobileExist(@Param("mobile") String mobile, @Param("id") String id);

    /**
     * @param sortField
     * @param request
     * @return
     */
    @SelectProvider(type = CustomerSqlProvider.class, method = "getCustomers")
    List<CustomersResponse> getCustomers(@Param("sortField") String sortField, @Param("request") CustomersRequest request);

    /**
     * @param sortField
     * @param request
     * @return
     */
    @SelectProvider(type = CustomerSqlProvider.class, method = "getMyselfCustomers")
    List<CustomersResponse> getMyselfCustomers(@Param("sortField") String sortField, @Param("request") CustomersMyselfRequest request);

    /**
     * @param distributionRequest
     * @return
     */
    @UpdateProvider(type = CustomerSqlProvider.class, method = "distribution")
    void distribution(@Param("distributionRequest") DistributionRequest distributionRequest);

    /**
     * @param xuebaNo
     * @return
     */
    @Select("SELECT * FROM CUSTOMER WHERE XUEBA_NO = #{xuebaNo}")
    Customer getCustomerByXuebaNo(@Param("xuebaNo") Integer xuebaNo);
}
