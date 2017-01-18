package com.deyong.rest.solrj;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * Created by Benjamin on 2017/1/18.
 */
public class SolrjTest {

    @Test
    public void addDocument() throws Exception {
        // 创建一个连接
        SolrServer solrServer = new HttpSolrServer("http://192.168.0.224:8080/solr");
        // 创建文档
        SolrInputDocument document = new SolrInputDocument();
        // 将文档写入索引库
        document.addField("id", "test003");
        document.addField("item_title", "zhangsan3");
        document.addField("item_price", 4421);
        // 提交
        solrServer.add(document);
        solrServer.commit();
    }

    @Test
    public void deleteDocument() throws Exception {
        // 创建一个连接
        SolrServer solrServer = new HttpSolrServer("http://192.168.0.224:8080/solr");

        solrServer.deleteById("test002");

//        solrServer.deleteByQuery("id:test002");
        solrServer.commit();
    }
}
