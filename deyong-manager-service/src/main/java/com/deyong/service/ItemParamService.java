package com.deyong.service;

import com.deyong.pojo.TbItemParam;
import com.ldy.common.pojo.EUDataGridResult;
import com.ldy.common.util.DeyongResult;

/**
 * Created by benjamin on 2017/1/5.
 */
public interface ItemParamService {
    /**
     * 分页展示所有的参数类型
     * @param page
     * @param rows
     * @return
     */
    EUDataGridResult getParamItemList(int page, int rows);

    /**
     * 通过 参数id得到参数
     * @param cid 参数类型id
     * @return 包装的返回对象
     */
    DeyongResult getParamItemByCid(long cid);

    /**
     * 插入 参数类型
     * @param itemParam 参数类型的实例
     * @return  包装的返回对象
     */
    DeyongResult insertItemParam(TbItemParam itemParam);
}
