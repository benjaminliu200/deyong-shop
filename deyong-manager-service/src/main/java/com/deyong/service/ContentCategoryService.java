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

    /**
     * 接收parentid、id两个参数。删除id对应的记录。需要判断parentid对应的记录下是否有子节点。如果没有子节点。需要把parentid对应的记录的isparent改成false。
     注意：删除直接是物理删除
     * @param parentId 父id
     * @param id 当前内容分类id
     * @return 删除成功返回 result
     */
    DeyongResult deleteContentCat(long parentId, long id);

    /**
     * 更新 contentcat的name列
     * @param id 分类的主键id
     * @param name 分类的名称
     * @return 返回的DeyongResult
     */
    DeyongResult updateContentCat(long id, String name);
}
