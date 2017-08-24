package com.xuebaclass.sato.mapper.sato;

import org.apache.ibatis.annotations.Select;

import com.xuebaclass.sato.model.Student;

public interface StudentMapper {

    /**
     * @param uid
     * @return
     */
    @Select("SELECT * FROM STUDENT WHERE UID = #{uid}")
    Student getStudentByUid(String uid);

}
