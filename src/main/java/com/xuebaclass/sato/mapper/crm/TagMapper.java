package com.xuebaclass.sato.mapper.crm;

import com.xuebaclass.sato.config.SpringSecurityKeycloakAutditorAware;
import com.xuebaclass.sato.model.Tag;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * Created by sunhao on 2017-08-17.
 */
public interface TagMapper {

    class TagSqlProvider extends SpringSecurityKeycloakAutditorAware {
        private final static String TABLE_NAME = "TAG";

        public String create() {
            return new SQL() {{
                INSERT_INTO(TABLE_NAME);

                VALUES("ID", "#{id}");
                VALUES("NAME", "#{name}");
                VALUES("COMMENT", "#{comment}");
                VALUES("TAG_GROUP_ID", "#{tagGroupId}");
                VALUES("FLAG", "#{flag}");

                VALUES("CREATED_BY", "'" + getCurrentAuditor() + "'");
                VALUES("CREATED_DATE", "utc_timestamp()");
                VALUES("LAST_MODIFIED_BY", "'" + getCurrentAuditor() + "'");
                VALUES("LAST_MODIFIED_DATE", "utc_timestamp()");
            }}.toString();
        }

    }

    /**
     * @param tag
     * @return
     */
    @InsertProvider(type = TagSqlProvider.class, method = "create")
    void create(Tag tag);


    /**
     * @param
     * @return
     */
    @Select("SELECT * FROM TAG WHERE FLAG = 0 ORDER BY TAG_GROUP_ID, ID")
    List<Tag> getAllTags();

}
