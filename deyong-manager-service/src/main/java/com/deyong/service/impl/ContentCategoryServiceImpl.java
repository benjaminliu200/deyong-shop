package com.deyong.service.impl;

import com.deyong.mapper.TbContentCategoryMapper;
import com.deyong.pojo.TbContentCategory;
import com.deyong.pojo.TbContentCategoryExample;
import com.deyong.service.ContentCategoryService;
import com.ldy.common.pojo.EUTreeNode;
import com.ldy.common.util.DeyongResult;
import com.ldy.common.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by benjamin on 2017/1/16.
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EUTreeNode> getContentCategory(long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);

        // 装数据的tree
        List<EUTreeNode> resultList = new ArrayList<>();
        for (TbContentCategory contentCategory : list) {
            EUTreeNode node = new EUTreeNode();
            node.setText(contentCategory.getName());
            node.setState(contentCategory.getIsParent() ? "closed": "open");
            node.setId(contentCategory.getId());
            resultList.add(node);
        }

        return resultList;
    }

    @Override
    public DeyongResult insertContentCat(long parentId, String name) {

        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
        // 得到当前时间
        Date now = new Date();
        // 如果这个父节点不是父节点，就将这个节点变成父节点
        if (!contentCategory.getIsParent()) {
            contentCategory.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(contentCategory);
        }
        TbContentCategory addContentCat = new TbContentCategory();
        addContentCat.setIsParent(false);
        addContentCat.setName(name);
        addContentCat.setCreated(now);
        addContentCat.setId(IDUtils.genItemId());
        addContentCat.setParentId(parentId);
        addContentCat.setStatus(1);
        addContentCat.setUpdated(now);
        contentCategoryMapper.insert(addContentCat);
        return DeyongResult.ok(addContentCat);
    }

    @Override
    public DeyongResult deleteContentCat(long parentId, long id) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        criteria.andIdNotEqualTo(id);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        // 父分类，如果父分类除了当前这个要删除的分类，不存在分了，就说明这个父分类没有孩子。
        TbContentCategory parentContentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (list == null && list.size() < 1) {
            parentContentCategory.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKey(parentContentCategory);
        }
        contentCategoryMapper.deleteByPrimaryKey(id);
        return DeyongResult.ok();
    }

    @Override
    public DeyongResult updateContentCat(long id, String name) {
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        contentCategory.setName(name);
        contentCategoryMapper.updateByPrimaryKey(contentCategory);
        return DeyongResult.ok();
    }
}
