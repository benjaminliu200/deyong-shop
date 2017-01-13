package com.deyong.portal.service.impl;

import com.deyong.mapper.TbItemCatMapper;
import com.deyong.portal.pojo.CatNode;
import com.deyong.portal.pojo.CatResult;
import com.deyong.portal.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by benjamin on 2017/1/13.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public CatResult getCatNode() {
        CatResult catResult = new CatResult();
        catResult.setData(getCatList(0));
        return null;
    }

    /**
     * 查询分类列表
     * @param parentid 父id
     * @return
     */
    private List<?> getCatList(long parentid) {

        return null;
    }
}
