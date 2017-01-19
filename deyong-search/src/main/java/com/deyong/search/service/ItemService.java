package com.deyong.search.service;

import com.ldy.common.util.DeyongResult;

/**
 * Created by Benjamin on 2017/1/18.
 */
public interface ItemService {

    /**
     * 导入所有的item进 solr索引库中。
     * @return
     */
    DeyongResult importAllItems();

    /**
     * 将item 添加进solr索引库中
     * @param itemID
     * @return
     */
    DeyongResult importItem(long itemID);

    /**
     * 将item从索引库中删除
     * @param itemID
     * @return
     */
    DeyongResult deleteItem(long itemID);
}
