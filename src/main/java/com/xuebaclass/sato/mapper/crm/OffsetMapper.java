package com.xuebaclass.sato.mapper.crm;

import com.xuebaclass.sato.model.Offset;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by sunhao on 2017-08-17.
 */
public interface OffsetMapper {

    class OffsetSqlProvider {
        private final static String TABLE_NAME = "OFFSET_RECORD";

        public String create() {
            return new SQL() {{
                INSERT_INTO(TABLE_NAME);

                VALUES("ID", "#{id}");
                VALUES("SALES_ID", "#{salesId}");
                VALUES("OFFSET_BEFORE", "#{offsetBefore}");
                VALUES("OFFSET_AFTER", "#{offsetAfter}");
                VALUES("REWARDS", "#{rewards}");
                VALUES("OFFSET", "#{offset}");
                VALUES("OFFSET_DATE", "#{offsetDate}");
                VALUES("STATUS", "#{status}");
                VALUES("COMMENT", "#{comment}");
            }}.toString();
        }

        public String update(Map<String, Object> parameters) {
            Offset offset = (Offset) parameters.get("offset");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            return new SQL() {{
                UPDATE(TABLE_NAME);

                SET("SALES_ID=" + offset.getSalesId());
                SET("OFFSET_BEFORE=" + offset.getOffsetBefore());
                SET("OFFSET_AFTER=" + offset.getOffsetAfter());
                SET("REWARDS=" + offset.getRewards());
                SET("OFFSET=" + offset.getOffset());
                SET("OFFSET_DATE='" + format.format(offset.getOffsetDate()) + "'");
                SET("STATUS=" + offset.getStatus());
                SET("COMMENT='" + offset.getComment() + "'");
                WHERE("ID=" + offset.getId());
            }}.toString();
        }
    }

    /**
     * @param offset
     * @return
     */
    @InsertProvider(type = OffsetSqlProvider.class, method = "create")
    void create(Offset offset);

    /**
     * @param offset
     * @return
     */
    @InsertProvider(type = OffsetSqlProvider.class, method = "update")
    void update(@Param("offset") Offset offset);

    /**
     * @param salesId
     * @param offsetDate
     * @return
     */
    @Select("SELECT * FROM OFFSET_RECORD WHERE SALES_ID = #{salesId} AND OFFSET_DATE = #{offsetDate} AND STATUS = false LIMIT 1")
    Offset getByDate(@Param("salesId") Integer salesId, @Param("offsetDate") String offsetDate);

}
