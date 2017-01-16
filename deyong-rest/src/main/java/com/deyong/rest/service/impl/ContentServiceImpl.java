package com.deyong.rest.service.impl;

import com.deyong.mapper.TbContentMapper;
import com.deyong.pojo.TbContent;
import com.deyong.pojo.TbContentExample;
import com.deyong.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by benjamin on 2017/1/16.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Value("REST_BASE_URL")
    private String REST_BASE_URL;
    @Value("REST_INDEX_AD_URL")
    private String REST_INDEX_AD_URL;

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public String getContentList() {
        String baseUrl = REST_BASE_URL + REST_INDEX_AD_URL;

        return null;
    }

    @Override
    public List<TbContent> getContentList(long contentid) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentid);
        return contentMapper.selectByExample(example);
    }
}
