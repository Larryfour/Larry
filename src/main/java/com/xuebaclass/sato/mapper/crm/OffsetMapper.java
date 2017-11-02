package com.xuebaclass.sato.mapper.crm;

import com.xuebaclass.sato.model.Offset;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

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
     * @param salesId
     * @param offsetDate
     * @return
     */
    @Select("SELECT * FROM OFFSET_RECORD WHERE SALES_ID = #{salesId} AND OFFSET_DATE = #{offsetDate} AND STATUS = false")
    Offset getByDate(@Param("salesId") Integer salesId, @Param("offsetDate") String offsetDate);

}
