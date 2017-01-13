package com.deyong.controller;

import com.deyong.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by benjamin on 2017/1/12.
 */
@Controller
public class ItemParamItemController {
    @Autowired
    private ItemParamItemService itemParamItemService;

    @RequestMapping("/showitem/{itemId}")
    public String showItemParam(@PathVariable long itemId, Model model) {
        String itemParam = itemParamItemService.getItemParamByItemid(itemId);
        model.addAttribute("itemParam", itemParam);
        return "item";
    }
}
