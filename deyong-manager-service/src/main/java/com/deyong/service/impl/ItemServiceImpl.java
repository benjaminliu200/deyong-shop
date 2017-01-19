package com.deyong.service.impl;

import com.deyong.mapper.TbItemDescMapper;
import com.deyong.mapper.TbItemMapper;
import com.deyong.mapper.TbItemParamItemMapper;
import com.deyong.pojo.TbItem;
import com.deyong.pojo.TbItemDesc;
import com.deyong.pojo.TbItemExample;
import com.deyong.pojo.TbItemParamItem;
import com.deyong.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldy.common.pojo.EUDataGridResult;
import com.ldy.common.util.DeyongResult;
import com.ldy.common.util.HttpClientUtil;
import com.ldy.common.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
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

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Value("${SOLR_BASE_URL}")
	private String SOLR_BASE_URL;
	@Value("${SOLR_SAVE_URL}")
	private String SOLR_SAVE_URL;
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

	@Override
	public DeyongResult saveItem(TbItem tbItem, String desc, String itemParams) {
		Date date = new Date();
		long id = IDUtils.genItemId();
		tbItem.setId(id);
		//商品状态，1-正常，2-下架，3-删除
		tbItem.setStatus((byte) 1);
		tbItem.setCreated(date);
		tbItem.setUpdated(date);
		itemMapper.insert(tbItem);

		//创建TbItemDesc对象
		DeyongResult result = insertItemDesc(id, date, desc);

		// 添加规格参数
		if (result.getStatus() != 200) {
			throw new RuntimeException();
		}

		result = insertItemParamItem(id, itemParams);

		// 添加规格参数
		if (result.getStatus() != 200) {
			throw new RuntimeException();
		}

		// 将添加的商品加入到 solr索引库中 调用search 服务
		HttpClientUtil.doGet(SOLR_BASE_URL + SOLR_SAVE_URL + id);
		return DeyongResult.ok();
	}

	/**
	 * 插入 itemDesc ，与item在同一个事物当中
	 * @param id    item的id
	 * @param date    日期
	 * @param desc    描述
	 */
	private DeyongResult insertItemDesc(long id, Date date, String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(id);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDesc.setItemDesc(desc);
		itemDescMapper.insert(itemDesc);
		return DeyongResult.ok();
	}

	private DeyongResult insertItemParamItem(long itemid, String paramItems) {
		Date now = new Date();
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemid);
		itemParamItem.setCreated(now);
		itemParamItem.setUpdated(now);
		itemParamItem.setParamData(paramItems);
		itemParamItemMapper.insert(itemParamItem);
		return DeyongResult.ok();
	}

}