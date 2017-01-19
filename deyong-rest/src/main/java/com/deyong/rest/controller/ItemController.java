package com.deyong.rest.controller;

import com.deyong.rest.dao.JedisClient;
import com.deyong.rest.service.ItemService;
import com.ldy.common.util.DeyongResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by benjamin on 2017/1/19.
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/info/{itemId}")
    @ResponseBody
    public DeyongResult getBaseItemInfo(@PathVariable long itemId) {
        return itemService.getItemBaseInfo(itemId);
    }

    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public DeyongResult getItemDesc(@PathVariable long itemId) {
        return itemService.getItemDesc(itemId);
    }
}
