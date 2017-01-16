package com.deyong.rest.controller;

import com.deyong.pojo.TbContent;
import com.deyong.rest.service.ContentService;
import com.ldy.common.util.DeyongResult;
import com.ldy.common.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by benjamin on 2017/1/16.
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/list/{contentCategoryId}")
    @ResponseBody
    public DeyongResult getContentList(@PathVariable Long contentCategoryId) {
        try {
            List<TbContent> list = contentService.getContentList(contentCategoryId);
            return DeyongResult.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return DeyongResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
