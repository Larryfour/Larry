package com.xuebaclass.sato.controller;

import com.xuebaclass.sato.model.Tag;
import com.xuebaclass.sato.model.TagGroup;
import com.xuebaclass.sato.model.request.TagSetRequest;
import com.xuebaclass.sato.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sunhao on 2017-08-11.
 */
@RestController
@RequestMapping("tags")
public class TagController {
    private static final Logger logger = LoggerFactory.getLogger(TagController.class);

    @Autowired
    private TagService tagService;

    /**
     * 创建标签组
     *
     * @return
     */
    @PostMapping(value = "/group")
    public ResponseEntity createTagGroup(@RequestBody TagGroup tagGroup) throws Exception {
        logger.info("********************** 创建标签组 *****************************");

        tagService.createTagGroup(tagGroup);
        return ResponseEntity.ok(tagGroup);
    }

    /**
     * 创建标签
     *
     * @return
     */
    @PostMapping
    public ResponseEntity createTag(@RequestBody Tag tag) throws Exception {
        logger.info("********************** 创建标签 *****************************");

        tagService.createTag(tag);
        return ResponseEntity.ok(tag);
    }

    /**
     * 获取标签列表
     *
     * @return
     */
    @GetMapping
    public ResponseEntity getTags() throws Exception {
        logger.info("********************** 获取标签列表 *****************************");

        return ResponseEntity.ok(tagService.getAllTags());
    }

    /**
     * 获取标签管理列表
     *
     * @return
     */
    @GetMapping(value = "/management")
    public ResponseEntity getManagementTags() throws Exception {
        logger.info("********************** 获取标签管理列表 *****************************");

        return ResponseEntity.ok(tagService.getManagementTags());
    }

    /**
     * 客户标签设置
     *
     * @return
     */
    @PostMapping(value = "/customer/{customerId}/setting")
    public ResponseEntity customerTagSetting(@PathVariable String customerId,
                                         @RequestBody TagSetRequest tagSetRequest) throws Exception {
        logger.info("********************** 客户标签设置 *****************************");

        return ResponseEntity.ok(tagService.customerTagSetting(customerId, tagSetRequest));
    }

    /**
     * 获取客户标签
     *
     * @return
     */
    @GetMapping(value = "/customer/{customerId}/myself")
    public ResponseEntity getCustomerTags(@PathVariable String customerId) throws Exception {
        logger.info("********************** 获取客户标签 *****************************");

        return ResponseEntity.ok(tagService.getCustomerTags(customerId));
    }
}
