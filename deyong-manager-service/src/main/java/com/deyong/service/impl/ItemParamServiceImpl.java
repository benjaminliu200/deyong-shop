package com.deyong.service.impl;

import com.deyong.mapper.TbItemParamMapper;
import com.deyong.pojo.TbItemParam;
import com.deyong.pojo.TbItemParamExample;
import com.deyong.service.ItemParamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldy.common.pojo.EUDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by benjamin on 2017/1/5.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService{

    @Autowired
    private TbItemParamMapper itemParamMapper;

    @Override
    public EUDataGridResult getParamItemList(int page, int rows) {
        PageHelper.startPage(page, rows);
        TbItemParamExample example = new TbItemParamExample();

        List<TbItemParam> list = itemParamMapper.selectByExample(example);

        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(pageInfo.getList());
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
