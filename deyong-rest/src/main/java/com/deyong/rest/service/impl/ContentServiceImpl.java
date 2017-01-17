package com.deyong.rest.service.impl;

import com.deyong.mapper.TbContentMapper;
import com.deyong.pojo.TbContent;
import com.deyong.pojo.TbContentExample;
import com.deyong.rest.dao.JedisClient;
import com.deyong.rest.service.ContentService;
import com.ldy.common.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by benjamin on 2017/1/16.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_KEY}")
    private String INDEX_CONTENT_KEY;

    @Override
    public List<TbContent> getContentList(long contentid) {
        // 从缓存中取内容
        try {
            String cacheString = jedisClient.hget(INDEX_CONTENT_KEY, String.valueOf(contentid));
            if (!StringUtils.isBlank(cacheString)) {
                return JsonUtils.jsonToList(cacheString, TbContent.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentid);
        List<TbContent> list = contentMapper.selectByExample(example);


        // 从缓存中存内容
        try {
            String cacheString = JsonUtils.objectToJson(list);
            jedisClient.hset(INDEX_CONTENT_KEY, String.valueOf(contentid), cacheString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
