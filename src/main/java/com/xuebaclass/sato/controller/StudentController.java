package com.xuebaclass.sato.controller;

import com.xuebaclass.sato.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sunhao on 2017-08-11.
 */
@RestController
@RequestMapping("students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 根据UID 获取学生信息
     *
     * @return
     */
    @GetMapping(value = "/uid/{uid}")
    public ResponseEntity getStudentByUid(@PathVariable(value = "uid") String uid) {
        return ResponseEntity.ok(studentService.getStudentByUid(uid));
    }
}
