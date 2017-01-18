package com.deyong.search.service.impl;

import com.deyong.search.mapper.ItemMapper;
import com.deyong.search.pojo.Item;
import com.deyong.search.service.ItemService;
import com.ldy.common.util.DeyongResult;
import com.ldy.common.util.ExceptionUtil;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin on 2017/1/18.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private SolrServer solrServer;


    @Override
    public DeyongResult importAllItems() {
        List<SolrInputDocument> solrInputDocuments = new ArrayList<>();
        SolrInputDocument temp;
        List<Item> list =  itemMapper.getItemList();
        try {
            for (Item item : list) {
                temp = new SolrInputDocument();
                temp.addField("id", item.getId());
                temp.addField("item_title", item.getTitle());
                temp.addField("item_sell_point", item.getSell_point());
                temp.addField("item_price", item.getPrice());
                temp.addField("item_image", item.getImage());
                temp.addField("item_category_name", item.getCategory_name());
                temp.addField("item_desc", item.getItem_desc());
                solrInputDocuments.add(temp);
            }

            solrServer.add(solrInputDocuments);
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return DeyongResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return DeyongResult.ok();
    }
}
