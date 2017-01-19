package com.deyong.portal.service;

import com.deyong.portal.pojo.SearchResult;

/**
 * Created by benjamin on 2017/1/19.
 */
public interface SearchService {

    public SearchResult search(String queryString, int page);
}
