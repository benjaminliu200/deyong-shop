package com.deyong.service.impl;

import com.deyong.mapper.TbItemCatMapper;
import com.deyong.pojo.TbItem;
import com.deyong.pojo.TbItemCat;
import com.deyong.pojo.TbItemCatExample;
import com.deyong.service.ItemCatService;
import com.ldy.common.pojo.EUTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin on 2017/1/5.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EUTreeNode> getItemCatList(Long parentId) {

        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();

        // 查出父节点id的所有子节点分类
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = itemCatMapper.selectByExample(example);

        // 包装 树节点
        List<EUTreeNode> resultList = new ArrayList<>();
        EUTreeNode treeNode = null;
        for (TbItemCat itemcat: list) {
            treeNode = new EUTreeNode();
            treeNode.setId(itemcat.getId());
            treeNode.setState(itemcat.getIsParent() ? "closed": "open");
            treeNode.setText(itemcat.getName());
            resultList.add(treeNode);
        }
        return resultList;
    }
}
