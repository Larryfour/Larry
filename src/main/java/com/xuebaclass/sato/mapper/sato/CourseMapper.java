package com.xuebaclass.sato.mapper.sato;

import com.xuebaclass.sato.config.SpringSecurityKeycloakAutditorAware;
import com.xuebaclass.sato.model.request.SalesDailyMyselfRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface CourseMapper {

    class CourseSqlProvider extends SpringSecurityKeycloakAutditorAware {
        public String getReservedCoursesCount(Map<String, Object> parameters) {
            SalesDailyMyselfRequest request = (SalesDailyMyselfRequest) parameters.get("salesDailyMyselfRequest");

            String sql = "SELECT \n" +
                    "  DATE(DATE_ADD(CREATED_DATE, INTERVAL 8 DAY_HOUR)) RESERVE_DATE,\n" +
                    "  COUNT(*) RESERVE_NUMBER\n" +
                    "FROM\n" +
                    "  COURSE \n" +
                    "WHERE CATEGORY = 'Experiences' \n" +
                    "  AND SUBSTRING_INDEX(CREATED_BY, ':', - 1) = '" + request.getUserName() + "'\n" +
                    "  AND CREATED_DATE BETWEEN '" + request.getFrom() + "' \n" +
                    "  AND '" + request.getTo() + "'\n" +
                    "  AND STATE <> 'Cancelled' \n" +
                    "GROUP BY RESERVE_DATE";

            return sql;
        }

        public String getCompletedCoursesCount(Map<String, Object> parameters) {
            SalesDailyMyselfRequest request = (SalesDailyMyselfRequest) parameters.get("salesDailyMyselfRequest");

            String sql = "SELECT \n" +
                    "  DATE(DATE_ADD(START, INTERVAL 8 DAY_HOUR)) COMPLETED_DATE,\n" +
                    "  COUNT(*) COMPLETED_NUMBER\n" +
                    "FROM\n" +
                    "  COURSE \n" +
                    "WHERE CATEGORY = 'Experiences' \n" +
                    "  AND SUBSTRING_INDEX(CREATED_BY, ':', - 1) = '" + request.getUserName() + "'\n" +
                    "  AND START BETWEEN '" + request.getFrom() + "' \n" +
                    "  AND '" + request.getTo() + "'\n" +
                    "  AND STATE = 'Completed'   \n" +
                    "GROUP BY COMPLETED_DATE";

            return sql;
        }

        public String getStudentCourseStartTime() {

            String sql = "SELECT \n" +
                    "  IFNULL(s.MOBILE, '') AS studentMobile,\n" +
                    "  DATE_FORMAT(\n" +
                    "    DATE_ADD(MIN(c.START), INTERVAL 8 DAY_HOUR),\n" +
                    "    '%Y-%m-%d %H:%i:%S'\n" +
                    "  ) AS startTime \n" +
                    "FROM\n" +
                    "  COURSE c,\n" +
                    "  STUDENT s \n" +
                    "WHERE c.CATEGORY = 'Experiences' \n" +
                    "  AND c.STATE = 'Completed' \n" +
                    "  AND c.STUDENT_ID = s.ID \n" +
                    "GROUP BY s.MOBILE \n";

            return sql;
        }
    }

    /**
     * @param salesDailyMyselfRequest
     * @return
     */
    @SelectProvider(type = CourseSqlProvider.class, method = "getReservedCoursesCount")
    List<Map> getReservedCoursesCount(@Param("salesDailyMyselfRequest") SalesDailyMyselfRequest salesDailyMyselfRequest);

    /**
     * @param salesDailyMyselfRequest
     * @return
     */
    @SelectProvider(type = CourseSqlProvider.class, method = "getCompletedCoursesCount")
    List<Map> getCompletedCoursesCount(@Param("salesDailyMyselfRequest") SalesDailyMyselfRequest salesDailyMyselfRequest);

    /**
     * @param
     * @return
     */
    @SelectProvider(type = CourseSqlProvider.class, method = "getStudentCourseStartTime")
    List<Map> getStudentCourseStartTime();
}
