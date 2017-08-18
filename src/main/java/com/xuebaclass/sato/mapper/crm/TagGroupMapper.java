package com.xuebaclass.sato.mapper.crm;

import com.xuebaclass.sato.config.SpringSecurityKeycloakAutditorAware;
import com.xuebaclass.sato.model.TagGroup;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by kangfei on 2017-08-17.
 */
public interface TagGroupMapper {

    class TagGroupSqlProvider extends SpringSecurityKeycloakAutditorAware{
        private final static String TABLE_NAME = "TAG_GROUP";

        public String create() {
            return new SQL() {{
                INSERT_INTO(TABLE_NAME);

                VALUES("ID", "#{id}");
                VALUES("NAME", "#{name}");
                VALUES("COMMENT", "#{comment}");
                VALUES("FLAG", "#{flag}");

                VALUES("CREATED_BY", "'" + getCurrentAuditor() + "'");
                VALUES("CREATED_DATE", "utc_timestamp()");
                VALUES("LAST_MODIFIED_BY", "'" + getCurrentAuditor() + "'");
                VALUES("LAST_MODIFIED_DATE", "utc_timestamp()");
            }}.toString();
        }

    }

    /**
     * 创建订单
     *
     * @param tagGroup
     */
    @InsertProvider(type = TagGroupSqlProvider.class, method = "create")
    void create(TagGroup tagGroup);


}
