package com.deyong.search.controller;

import com.deyong.search.pojo.SearchResult;
import com.deyong.search.service.SearchService;
import com.ldy.common.util.DeyongResult;
import com.ldy.common.util.ExceptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

/**
 * Created by benjamin on 2017/1/19.
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public DeyongResult search(@RequestParam("q") String queryString,
                               @RequestParam(defaultValue="1")Integer page,
                               @RequestParam(defaultValue="60")Integer rows) {
        // 查询条件不可为空
        if (StringUtils.isBlank(queryString)) {
            return DeyongResult.build(400, "查询条件不可为空");
        } else {
            try {
                queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        SearchResult search;
        try {
            search = searchService.search(queryString, page, rows);
        } catch (Exception e) {
            e.printStackTrace();
            return DeyongResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return DeyongResult.ok(search);
    }
}
