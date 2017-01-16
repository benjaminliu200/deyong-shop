package com.deyong.service;

import com.deyong.pojo.TbContent;
import com.ldy.common.pojo.EUDataGridResult;
import com.ldy.common.util.DeyongResult;

import java.util.List;

/**
 * Created by benjamin on 2017/1/16.
 */
public interface ContentService {
    /**
     * 分页展示所有的内容列表
     * @param page 当前页数
     * @param rows 总条数
     * @return easyUI的grid分页数据列表
     */
    EUDataGridResult getContentList(Integer page, Integer rows);

    /**
     * 插入 content表
     * @param content content的model类
     * @return 插入返回结果
     */
    DeyongResult insertContent(TbContent content);


}
