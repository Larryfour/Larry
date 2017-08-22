package com.xuebaclass.sato.controller;

import com.xuebaclass.sato.model.Tag;
import com.xuebaclass.sato.model.TagGroup;
import com.xuebaclass.sato.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sunhao on 2017-08-11.
 */
@RestController
@RequestMapping("tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/group/create", method = RequestMethod.POST)
    public ResponseEntity createTagGroup(@RequestBody TagGroup tagGroup) {
        tagService.createTagGroup(tagGroup);
        return ResponseEntity.ok(200);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity createTag(@RequestBody Tag tag) {
        tagService.createTag(tag);
        return ResponseEntity.ok(200);
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity getTags() {
        return ResponseEntity.ok(tagService.getTags());
    }
}
