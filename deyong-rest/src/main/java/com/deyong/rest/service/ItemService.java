package com.deyong.rest.service;

import com.ldy.common.util.DeyongResult;

/**
 * Created by benjamin on 2017/1/19.
 */
public interface ItemService {

    /**
     * 得到商品基本信息
     * @param itemId
     * @return
     */
    DeyongResult getItemBaseInfo(long itemId);

    /**
     * 得到商品描述信息
     * @param itemId
     * @return
     */
    DeyongResult getItemDesc(long itemId);

    /**
     * 得到商品规格参数
     * @param itemId
     * @return
     */
    DeyongResult getItemParam(long itemId);
}
