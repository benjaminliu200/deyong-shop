package com.deyong.portal.controller;

import com.deyong.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by benjamin on 2017/1/12.
 */
@Controller
public class IndexController {

    @Autowired
    private ContentService contentService;
    @RequestMapping("/index")
    public String showIndex(Model model) {
        String json = contentService.getContentList();
        model.addAttribute("ad1", json);
        return "index";
    }
}
