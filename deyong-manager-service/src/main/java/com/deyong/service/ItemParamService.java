package com.deyong.service;

import com.ldy.common.pojo.EUDataGridResult;

/**
 * Created by benjamin on 2017/1/5.
 */
public interface ItemParamService {
    EUDataGridResult getParamItemList(int page, int rows);
}
