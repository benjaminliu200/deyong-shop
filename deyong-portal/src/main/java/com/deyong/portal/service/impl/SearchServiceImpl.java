package com.deyong.portal.service.impl;

import com.deyong.portal.pojo.Item;
import com.deyong.portal.pojo.SearchResult;
import com.deyong.portal.service.SearchService;
import com.ldy.common.util.DeyongResult;
import com.ldy.common.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by benjamin on 2017/1/19.
 */
@Service
public class SearchServiceImpl implements SearchService{

    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    @Override
    public SearchResult search(String queryString, int page) {
        // 调用 deyong-search 服务
        // 查询参数
        Map<String, String> params = new HashMap<>();
        params.put("q", queryString);
        params.put("page", page + "");

        try {
            String json = HttpClientUtil.doGet(SEARCH_BASE_URL, params);
            DeyongResult deyongResult = DeyongResult.formatToPojo(json, SearchResult.class);
            if (deyongResult.getStatus() == 200) {
                return (SearchResult) deyongResult.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
