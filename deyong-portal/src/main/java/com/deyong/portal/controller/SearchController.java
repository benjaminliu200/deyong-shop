package com.deyong.portal.controller;

import com.deyong.portal.pojo.SearchResult;
import com.deyong.portal.service.SearchService;
import com.ldy.common.util.DeyongResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

    // 加了 @ResponseBody 就会把返回的写入到Response对象的body数据区， 就直接返回search了，  就不会进入 WEB-INF/jsp下的search.jsp了。
    @RequestMapping("/search")
    public String search(
            @RequestParam("q") String queryString,
            @RequestParam(defaultValue = "1") Integer page,
            Model model) {
        if (!StringUtils.isBlank(queryString)) {
            try {
                queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        SearchResult searchResult = searchService.search(queryString, page);
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", searchResult.getPageCount());
        model.addAttribute("itemList", searchResult.getItems());
        model.addAttribute("page", page);
        return "search";
    }
}
