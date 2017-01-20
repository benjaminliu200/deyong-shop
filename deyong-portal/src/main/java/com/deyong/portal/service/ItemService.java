package com.deyong.portal.service;

import com.deyong.pojo.TbItem;
import com.deyong.portal.pojo.Item;
import com.deyong.portal.pojo.ItemInfo;

/**
 * Created by benjamin on 2017/1/20.
 */
public interface ItemService {
    /**
     * 获得商品的基本信息
     * @param itemId
     * @return
     */
    ItemInfo getItemInfo(long itemId);

    /**
     * 获得商品的描述信息
     * @param itemId
     * @return
     */
    String getItemDesc(long itemId);

    /**
     * 获得商品的规格参数
     * @param itemId
     * @return
     */
    String getItemParam(long itemId);
}
