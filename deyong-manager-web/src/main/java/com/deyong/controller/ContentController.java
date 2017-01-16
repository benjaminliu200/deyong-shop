package com.deyong.controller;

import com.deyong.pojo.TbContent;
import com.deyong.service.ContentService;
import com.ldy.common.pojo.EUDataGridResult;
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

    @RequestMapping("/query/list")
    @ResponseBody
    public EUDataGridResult getContentList(Integer page, Integer rows) {
        return contentService.getContentList(page, rows);
    }

    @RequestMapping("/save")
    @ResponseBody
    public DeyongResult insertContent(TbContent content) {
        return contentService.insertContent(content);
    }
}
