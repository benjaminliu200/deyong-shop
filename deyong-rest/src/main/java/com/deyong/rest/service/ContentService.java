package com.deyong.rest.service;

import com.deyong.pojo.TbContent;

import java.util.List;

/**
 * Created by benjamin on 2017/1/16.
 */
public interface ContentService {



    /**
     * 通过contentid得到 旗下所有分类的content
     * @param contentid 分类id
     * @return 包含内容的content集合
     */
    List<TbContent> getContentList(long contentid);

}
