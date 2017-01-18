package com.deyong.rest.service;

import com.ldy.common.util.DeyongResult;

/**
 * Created by benjamin on 2017/1/18.
 */
public interface RedisService {
    DeyongResult asynContent(long contentCid);
}
