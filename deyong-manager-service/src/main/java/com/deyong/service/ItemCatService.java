package com.deyong.service;

import com.ldy.common.pojo.EUTreeNode;

import java.util.List;

/**
 * Created by benjamin on 2017/1/5.
 */
public interface ItemCatService {
    /**
     * 得到树形的分类
     * @return common的树形
     * @param parentId
     */
    List<EUTreeNode> getItemCatList(Long parentId);
}
