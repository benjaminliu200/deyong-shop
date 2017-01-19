package com.deyong.rest.service.impl;

import com.deyong.mapper.TbItemDescMapper;
import com.deyong.mapper.TbItemMapper;
import com.deyong.mapper.TbItemParamItemMapper;
import com.deyong.pojo.TbItem;
import com.deyong.pojo.TbItemDesc;
import com.deyong.pojo.TbItemParamItem;
import com.deyong.pojo.TbItemParamItemExample;
import com.deyong.rest.dao.JedisClient;
import com.deyong.rest.service.ItemService;
import com.ldy.common.util.DeyongResult;
import com.ldy.common.util.ExceptionUtil;
import com.ldy.common.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品信息管理
 * Created by benjamin on 2017/1/19.
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;

    @Value("${REDIS_ITEM_EXPIRE}")
    private Integer REDIS_ITEM_EXPIRE;

    @Override
    public DeyongResult getItemBaseInfo(long itemId) {
        // 从缓存中取商品信息
        try {
            String itemString = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
            if (!StringUtils.isBlank(itemString))
                return DeyongResult.ok(JsonUtils.jsonToPojo(itemString, TbItem.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        try {
            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(item));
            // 添加缓存过期时间
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":base", REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DeyongResult.ok(item);
    }


    @Override
    public DeyongResult getItemDesc(long itemId) {
        // 从缓存中取商品信息
        try {
            String itemString = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
            if (!StringUtils.isBlank(itemString))
                return DeyongResult.ok(JsonUtils.jsonToPojo(itemString, TbItemDesc.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        try {
            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(itemDesc));
            // 添加缓存过期时间
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DeyongResult.ok(itemDesc);
    }

    @Override
    public DeyongResult getItemParam(long itemId) {
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
            if (!StringUtils.isBlank(json))
                return DeyongResult.ok(JsonUtils.jsonToPojo(json, TbItemParamItem.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            TbItemParamItem tbItemParamItem = list.get(0);

            try {
                jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJson(tbItemParamItem));
                jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param", REDIS_ITEM_EXPIRE);
                return DeyongResult.ok(tbItemParamItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return DeyongResult.build(400, "无此商品参数");
    }
}
