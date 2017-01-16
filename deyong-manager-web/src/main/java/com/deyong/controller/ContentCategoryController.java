package com.deyong.controller;

import com.deyong.service.ContentCategoryService;
import com.ldy.common.pojo.EUTreeNode;
import com.ldy.common.util.DeyongResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by benjamin on 2017/1/16.
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") long parentId) {
        return contentCategoryService.getContentCategory(parentId);
    }

    @RequestMapping("/create")
    @ResponseBody
    public DeyongResult insertContentCat(long parentId, String name) {
        return contentCategoryService.insertContentCat(parentId, name);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public DeyongResult deleteContentCat(long parentId, long id) {
        return contentCategoryService.deleteContentCat(parentId, id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public DeyongResult updateContentCat(long id, String name) {
        return contentCategoryService.updateContentCat(id, name);
    }
}
