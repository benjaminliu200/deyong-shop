package com.deyong.controller;

import com.deyong.pojo.TbItemParam;
import com.deyong.service.ItemParamService;
import com.ldy.common.pojo.EUDataGridResult;
import com.ldy.common.util.DeyongResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by benjamin on 2017/1/10.
 */

@Controller
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/item/param/query/itemcatid/{itemCatid}")
    @ResponseBody
    public DeyongResult getItemParamByCatID(@PathVariable Long itemCatid) {
        return itemParamService.getParamItemByCid(itemCatid);
    }

    @RequestMapping("/item/param/list")
    @ResponseBody
    public EUDataGridResult getParamItemList(Integer page, Integer rows) {
        return itemParamService.getParamItemList(page, rows);
    }

    @RequestMapping("/item/param/save/{itemCatid}")
    @ResponseBody
    public DeyongResult saveItemCat(@PathVariable Long itemCatid, String paramData) {
        // 创建pojo对象
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(itemCatid);
        itemParam.setParamData(paramData);
        return itemParamService.insertItemParam(itemParam);
    }
}
