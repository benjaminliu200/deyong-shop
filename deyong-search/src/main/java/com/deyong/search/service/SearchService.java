package com.deyong.search.service;

import com.deyong.search.pojo.SearchResult;

/**
 * Created by benjamin on 2017/1/19.
 */
public interface SearchService {
    SearchResult search(String queryString, int page, int rows) throws Exception;
}
