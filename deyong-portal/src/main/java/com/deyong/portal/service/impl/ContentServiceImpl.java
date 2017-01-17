package com.deyong.portal.service.impl;

import com.deyong.mapper.TbContentMapper;
import com.deyong.pojo.TbContent;
import com.deyong.portal.service.ContentService;
import com.ldy.common.util.DeyongResult;
import com.ldy.common.util.ExceptionUtil;
import com.ldy.common.util.HttpClientUtil;
import com.ldy.common.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benjamin on 2017/1/17.
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_INDEX_AD_URL}")
    private String REST_INDEX_AD_URL;

    @Override
    public String getContentList() {
        String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
        try {
            DeyongResult deyongResult = DeyongResult.formatToList(result, TbContent.class);
            List<TbContent> data = (List<TbContent>) deyongResult.getData();
            List<Map> resultList = new ArrayList<>();
            Map temp;
            for (TbContent content :
                    data) {
                temp = new HashMap();
                temp.put("src", content.getPic());
                temp.put("heigth", 240);
                temp.put("width", 670);
                temp.put("srcB", content.getPic2());
                temp.put("widthB", 550);
                temp.put("heightB", 240);
                temp.put("href", content.getUrl());
                temp.put("alt", content.getSubTitle());
                resultList.add(temp);
            }
            return JsonUtils.objectToJson(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
