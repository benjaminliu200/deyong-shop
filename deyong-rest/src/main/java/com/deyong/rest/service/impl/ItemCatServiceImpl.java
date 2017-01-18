package com.deyong.rest.service.impl;

import com.deyong.mapper.TbItemCatMapper;
import com.deyong.pojo.TbItemCat;
import com.deyong.pojo.TbItemCatExample;
import com.deyong.rest.dao.JedisClient;
import com.deyong.rest.pojo.CatNode;
import com.deyong.rest.pojo.CatResult;
import com.deyong.rest.service.ItemCatService;
import com.ldy.common.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin on 2017/1/13.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${ITEAM_CAT_KEY}")
    private String ITEAM_CAT_KEY;

    @Override
    public CatResult getItemCatList() {
        CatResult catResult = new CatResult();
        List list = null;
        // 从缓冲中取数据
        try {
            String cacheString = jedisClient.hget(ITEAM_CAT_KEY, String.valueOf(0));
            if (!StringUtils.isBlank(cacheString)) {
                list = JsonUtils.jsonToList(cacheString, Object.class);
            } else {
                list = getCatList(0);
                jedisClient.hset(ITEAM_CAT_KEY, String.valueOf(0), JsonUtils.objectToJson(list));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        catResult.setData(list);
        return catResult;
    }

    /**
     * 查询分类列表
     * @param parentId 父id
     * @return
     */
    private List<?> getCatList(long parentId) {

        // 创建查询条件
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        // 执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        // 返回值list
        List resultList = new ArrayList<>();
        // 页面只能显示14列，如果超出就会样式破坏，定义一个count记录 ，到14个退出循环
        int count = 0;
        for (TbItemCat itemCat : list) {
            if (itemCat.getIsParent()) {
                CatNode node = new CatNode();
                if (parentId == 0) {
                    node.setName("<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
                } else {
                    node.setName(itemCat.getName());
                }
                node.setUrl("/products/" + itemCat.getId() + ".html");
                node.setItem(getCatList(itemCat.getId()));
                resultList.add(node);
                // 第一层只取14个。
                if (parentId == 0 && ++count >= 14) {
                    break;
                }
            } else {
                // 如果事叶子结点，就直接加入一个字符串
                resultList.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName());
            }
        }
        return resultList;
    }
}
