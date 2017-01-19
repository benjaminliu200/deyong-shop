package com.deyong.search.dao.impl;

import com.deyong.search.dao.SearchDao;
import com.deyong.search.pojo.Item;
import com.deyong.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by benjamin on 2017/1/19.
 */
@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private SolrServer solrServer;

    @Override
    public SearchResult search(SolrQuery query) throws Exception {
        // solr 执行查询
        QueryResponse response = solrServer.query(query);
        // 遍历查询结果，将结果保存到pojo中
        SolrDocumentList solrDocuments = response.getResults();
        //取高亮显示
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        List<Item> items = new ArrayList<>();
        Item item;
        for (SolrDocument solrDocument: solrDocuments) {
            // 创建单一商品
            item = new Item();
            item.setId((String)solrDocument.get("id"));

            // 取高亮显示结果
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String title;
            if (list != null && list.size() > 0) {
                title = list.get(0);
            } else {
                title = (String) solrDocument.get("item_title");
            }
            item.setTitle(title);
            item.setImage((String)solrDocument.get("item_image"));
            item.setPrice((long) solrDocument.get("item_price"));
            item.setCategory_name((String) solrDocument.get("item_category_name"));
            item.setSell_point((String) solrDocument.get("item_cell_point"));
            items.add(item);
        }
        // 包装SearchResult对象
        SearchResult searchResult = new SearchResult();
        searchResult.setRecordCount(solrDocuments.getNumFound());
        searchResult.setItems(items);
        return searchResult;
    }
}
