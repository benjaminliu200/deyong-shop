package com.deyong.service.impl;

import com.deyong.mapper.TbItemParamMapper;
import com.deyong.pojo.TbItemParam;
import com.deyong.pojo.TbItemParamExample;
import com.deyong.service.ItemParamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldy.common.pojo.EUDataGridResult;
import com.ldy.common.util.DeyongResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public DeyongResult getParamItemByCid(long cid) {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list = itemParamMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return DeyongResult.ok();
        }
        return null;
    }

    @Override
    public DeyongResult insertItemParam(TbItemParam itemParam) {
        Date now = new Date();
        itemParam.setUpdated(now);
        itemParam.setCreated(now);
        itemParamMapper.insert(itemParam);
        return DeyongResult.ok();
    }
}
