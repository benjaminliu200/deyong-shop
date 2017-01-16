package com.deyong.rest.controller;

import com.deyong.rest.pojo.CatResult;
import com.deyong.rest.service.ItemCatService;
import com.ldy.common.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by benjamin on 2017/1/16.
 */
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;
   /* @RequestMapping(value = "/itemcat/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String getItemCatList(String callback) {
        CatResult catResult = itemCatService.getItemCatList();
        // 把pojo转化为字符串
        String json = JsonUtils.objectToJson(catResult);
        return callback + "("+ json +");";
    }*/

    @RequestMapping(value = "/itemcat/list")
    @ResponseBody
    public Object getItemCatList(String callback) {
        CatResult catResult = itemCatService.getItemCatList();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }
}
