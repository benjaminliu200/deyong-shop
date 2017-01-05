package com.deyong.service.impl;

import com.deyong.mapper.TbItemMapper;
import com.deyong.pojo.TbItem;
import com.deyong.pojo.TbItemCatExample;
import com.deyong.pojo.TbItemExample;
import com.deyong.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldy.common.pojo.EUDataGridResult;
import com.ldy.common.pojo.EUTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品查询Service
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年11月11日下午4:28:55
 * @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Override
	public TbItem getItemById(Long itemId) {
		//TbItem item = itemMapper.selectByPrimaryKey(itemId);
		TbItemExample example = new TbItemExample();
		//创建查询条件
		TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		//判断list中是否为空
		TbItem item = null;
		if (list != null && list.size() > 0) {
			item = list.get(0);
		}
		return item;
	}

	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		// 设置分页
		PageHelper.startPage(page, rows);

		// 执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);

		// 取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);

		// 返回处理结果
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());

		return result;
	}


}