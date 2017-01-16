package com.deyong.service;

import com.ldy.common.pojo.EUTreeNode;
import com.ldy.common.util.DeyongResult;

import java.util.List;

/**
 * Created by benjamin on 2017/1/16.
 */
public interface ContentCategoryService {
    /**
     * 得到内容分类树形列表
     * @param parentId
     * @return
     */
    List<EUTreeNode> getContentCategory(long parentId);

    /**
     * 添加内容分类列表
     * @param parentId 在哪一个parent下面添加分类列表
     * @param name 分类名
     * @return 是否添加成功
     */
    DeyongResult insertContentCat(long parentId, String name);
}
