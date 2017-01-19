package com.deyong.search.dao;

import com.deyong.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * Created by benjamin on 2017/1/19.
 */
public interface SearchDao {
    SearchResult search(SolrQuery query) throws Exception;
}
