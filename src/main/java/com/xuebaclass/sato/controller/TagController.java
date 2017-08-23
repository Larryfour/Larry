package com.xuebaclass.sato.controller;

import com.xuebaclass.sato.model.Tag;
import com.xuebaclass.sato.model.TagGroup;
import com.xuebaclass.sato.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sunhao on 2017-08-11.
 */
@RestController
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping(value = "/group")
    public ResponseEntity createTagGroup(@RequestBody TagGroup tagGroup) {
        tagService.createTagGroup(tagGroup);
        return ResponseEntity.ok(tagGroup);
    }

    @PostMapping
    public ResponseEntity createTag(@RequestBody Tag tag) {
        tagService.createTag(tag);
        return ResponseEntity.ok(tag);
    }

    @GetMapping
    public ResponseEntity getTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @GetMapping(value = "/select/management")
    public ResponseEntity getManagementTags() {
        return ResponseEntity.ok(tagService.getManagementTags());
    }
}
