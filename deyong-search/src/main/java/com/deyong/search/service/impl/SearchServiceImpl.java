package com.deyong.search.service.impl;

import com.deyong.search.dao.SearchDao;
import com.deyong.search.pojo.SearchResult;
import com.deyong.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by benjamin on 2017/1/19.
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;
    @Override
    public SearchResult search(String queryString, int page, int rows) throws Exception {
        // 创建查询对象
        SolrQuery query = new SolrQuery();
        // 设置查询条件
        query.setQuery("item_title:" + queryString);

        // 设置分页
        query.setRows(rows);
        query.setStart((page-1) * rows);

        // 设置默认搜索域
        query.set("df", "item_keywords");
        //设置高亮显示
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");

        // 执行查询
        SearchResult result = searchDao.search(query);

        // 计算查询总页数
        long recordCount = result.getRecordCount();
        long pageCount = (recordCount + rows - 1) / rows;
        result.setPageCur(page);
        result.setPageCount(pageCount);
        return result;
    }
}
