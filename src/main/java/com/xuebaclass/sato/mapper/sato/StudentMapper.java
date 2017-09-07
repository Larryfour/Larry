package com.xuebaclass.sato.mapper.sato;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.util.StringUtil;
import com.xuebaclass.sato.config.SpringSecurityKeycloakAutditorAware;
import com.xuebaclass.sato.model.Student;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.xuebaclass.sato.common.Constant.KEYCLOAK_USER_PREFIX;
import static java.util.Objects.nonNull;

public interface StudentMapper {

    public class StudentSqlProvider extends SpringSecurityKeycloakAutditorAware {
        private final static String TABLE_NAME = "STUDENT";

        public String create() {
            return new SQL() {{
                INSERT_INTO(TABLE_NAME);

                VALUES("ID", "#{id}");
                VALUES("UID", "#{uid}");
                VALUES("NAME", "#{name}");
                VALUES("MOBILE", "#{mobile}");
                VALUES("EMAIL", "#{email}");
                VALUES("QQ", "#{qq}");
                VALUES("GENDER", "#{gender}");
                VALUES("PROVINCE", "#{province}");
                VALUES("CITY", "#{city}");
                VALUES("SCHOOL", "#{school}");
                VALUES("RELATION", "#{relation}");
                VALUES("PARENT_NAME", "#{parentName}");
                VALUES("PARENT_MOBILE", "#{parentMobile}");
                VALUES("ANSWER_TIME", "#{answerTime}");

                VALUES("NIM_ACCOUNT_ID", "#{nimAccountId}");

                VALUES("CREATED_DATE", "UTC_TIMESTAMP()");
                VALUES("CREATED_BY", "'" + getCurrentAuditor() + "'");
                VALUES("LAST_MODIFIED_DATE", "UTC_TIMESTAMP()");
                VALUES("LAST_MODIFIED_BY", "'" + getCurrentAuditor() + "'");
                VALUES("VERSION", "1");
            }}.toString();
        }

        public String update(Map<String, Object> parameters) {
            Student student = (Student) parameters.get("student");
            String studentId = (String) parameters.get("studentId");

            return new SQL() {{
                UPDATE(TABLE_NAME);

                if (!StringUtils.isEmpty(student.getName())) {
                    SET("NAME = #{student.name}");
                }
                if (!StringUtils.isEmpty(student.getMobile())) {
                    SET("MOBILE = #{student.mobile}");
                }
                if (!StringUtils.isEmpty(student.getEmail())) {
                    SET("EMAIL = #{student.email}");
                }
                if (!StringUtils.isEmpty(student.getQq())) {
                    SET("QQ = #{student.qq}");
                }
                if (nonNull(student.getGender())) {
                    SET("GENDER = #{student.gender}");
                }
                if (!StringUtils.isEmpty(student.getProvince())) {
                    SET("PROVINCE = #{student.province}");
                }
                if (!StringUtils.isEmpty(student.getCity())) {
                    SET("CITY = #{student.city}");
                }
                if (!StringUtils.isEmpty(student.getSchool())) {
                    SET("SCHOOL = #{student.school}");
                }
                if (!StringUtils.isEmpty(student.getRelation())) {
                    SET("RELATION = #{student.relation}");
                }
                if (!StringUtils.isEmpty(student.getParentName())) {
                    SET("PARENT_NAME = #{student.parentName}");
                }
                if (!StringUtils.isEmpty(student.getParentMobile())) {
                    SET("PARENT_MOBILE = #{student.parentMobile}");
                }
                if (!StringUtils.isEmpty(student.getAnswerTime())) {
                    SET("ANSWER_TIME = #{student.answerTime}");
                }

                SET("LAST_MODIFIED_DATE = UTC_TIMESTAMP()");
                SET("LAST_MODIFIED_BY = '" + getCurrentAuditor() + "'");

                SET("VERSION = VERSION + 1");

                WHERE("ID = '" + studentId + "'");
            }}.toString();
        }
    }

    /**
     * @param uid
     * @return
     */
    @Select("SELECT * FROM STUDENT WHERE UID = #{uid}")
    Student getStudentByUid(String uid);

    /**
     * @param mobile
     * @return
     */
    @Select("SELECT * FROM STUDENT WHERE MOBILE = #{mobile}")
    Student getStudentByMobile(String mobile);

    @InsertProvider(type = StudentSqlProvider.class, method = "create")
    @SelectKey(statement = "SELECT UUID() FROM DUAL;", keyProperty = "id", before = true, resultType = String.class)
    void create(Student student);

    @Insert("INSERT INTO STUDENT_EXTENSIONS(STUDENT_ID, PROPERTY, VALUE) VALUES(#{studentId}, #{property}, #{value})")
    int createStudentExtension(@Param("studentId") String studentId,
                               @Param("property") String property, @Param("value") String value);

    @Delete("DELETE FROM STUDENT_EXTENSIONS WHERE STUDENT_ID = #{studentId}")
    int deleteStudentExtension(String studentId);

    @UpdateProvider(type = StudentSqlProvider.class, method = "update")
    int update(@Param("studentId") String studentId, @Param("student") Student student);

}
