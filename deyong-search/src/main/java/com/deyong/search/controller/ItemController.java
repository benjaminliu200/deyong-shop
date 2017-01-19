package com.deyong.search.controller;

import com.deyong.search.service.ItemService;
import com.ldy.common.util.DeyongResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Benjamin on 2017/1/18.
 */
@Controller
@RequestMapping("/manager")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/importall")
    @ResponseBody
    public DeyongResult importAllItem() {
        return itemService.importAllItems();
    }

    @RequestMapping("/import/{itemID}")
    @ResponseBody
    public DeyongResult importItem(@PathVariable long itemID) {
        return itemService.importItem(itemID);
    }

    @RequestMapping("/delete/{itemID}")
    @ResponseBody
    public DeyongResult deleteItem(@PathVariable long itemID) {
        return itemService.deleteItem(itemID);
    }
}
