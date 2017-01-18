package com.deyong.rest.controller;

import com.deyong.rest.service.RedisService;
import com.ldy.common.util.DeyongResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by benjamin on 2017/1/18.
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/content/{contentCid}")
    @ResponseBody
    public DeyongResult contentCacheSync(@PathVariable long contentCid) {
        return redisService.asynContent(contentCid);
    }
}
