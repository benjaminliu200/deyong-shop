package com.deyong.service;

import com.deyong.pojo.TbItem;
import com.ldy.common.pojo.EUDataGridResult;
import com.ldy.common.util.DeyongResult;

/**
 * Created by Benjamin on 2017/1/4.
 */
public interface ItemService {
    /**
     * 得到 item 通过itemid
     * @param itemId
     * @return
     */
    TbItem getItemById(Long itemId);

    /**
     * 得到通用的分页 model
     * @param page 当前页
     * @param rows 总页数
     * @return
     */
    EUDataGridResult getItemList(int page, int rows);

    /**
     * 存储Item
     * @param tbItem item的pojo对象
     * @param desc 商品描述
     * @param itemParams 商品参数
     */
    DeyongResult saveItem(TbItem tbItem, String desc, String itemParams);
}
