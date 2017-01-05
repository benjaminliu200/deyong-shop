package com.deyong.service;

import com.deyong.pojo.TbItem;
import com.ldy.common.pojo.EUDataGridResult;
import com.ldy.common.pojo.EUTreeNode;

import java.util.List;

/**
 * Created by Benjamin on 2017/1/4.
 */
public interface ItemService {
    TbItem getItemById(Long itemId);

    /**
     * 得到通用的分页 model
     * @param page 当前页
     * @param rows 总页数
     * @return
     */
    EUDataGridResult getItemList(int page, int rows);
}
