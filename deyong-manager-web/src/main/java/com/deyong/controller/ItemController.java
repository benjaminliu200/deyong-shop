package com.deyong.controller;

import com.deyong.pojo.TbItem;
import com.deyong.service.ItemCatService;
import com.deyong.service.ItemParamService;
import com.deyong.service.ItemService;
import com.ldy.common.pojo.EUDataGridResult;
import com.ldy.common.pojo.EUTreeNode;
import com.ldy.common.util.DeyongResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemCatService itemCatService;

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem item = itemService.getItemById(itemId);
		return item;
	}

	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}

	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EUTreeNode> getItemCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		List<EUTreeNode> result = itemCatService.getItemCatList(parentId);
		return result;
	}

	@RequestMapping("/item/param/list")
	@ResponseBody
	public EUDataGridResult getParamItemList(Integer page, Integer rows) {
		return itemParamService.getParamItemList(page, rows);
	}

	@RequestMapping("/item/save")
	@ResponseBody
	public DeyongResult saveItem(TbItem tbItem, String desc) {
		// 添加商品信息
		return itemService.saveItem(tbItem, desc, null);
	}

}