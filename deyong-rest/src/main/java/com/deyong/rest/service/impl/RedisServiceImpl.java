package com.deyong.rest.service.impl;

import com.deyong.rest.dao.JedisClient;
import com.deyong.rest.service.RedisService;
import com.ldy.common.util.DeyongResult;
import com.ldy.common.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by benjamin on 2017/1/18.
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_KEY}")
    private String INDEX_CONTENT_KEY;

    @Override
    public DeyongResult asynContent(long contentCid) {
        try {
            jedisClient.hdel(INDEX_CONTENT_KEY, String.valueOf(contentCid));
        } catch (Exception e) {
            e.printStackTrace();
            return DeyongResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return DeyongResult.ok();
    }
}
