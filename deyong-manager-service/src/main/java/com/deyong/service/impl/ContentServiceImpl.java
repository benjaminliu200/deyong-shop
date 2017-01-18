package com.deyong.service.impl;

import com.deyong.mapper.TbContentMapper;
import com.deyong.pojo.TbContent;
import com.deyong.pojo.TbContentExample;
import com.deyong.service.ContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldy.common.pojo.EUDataGridResult;
import com.ldy.common.util.DeyongResult;
import com.ldy.common.util.ExceptionUtil;
import com.ldy.common.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by benjamin on 2017/1/16.
 */
@Service
public class ContentServiceImpl implements ContentService{

    @Autowired
    private TbContentMapper contentMapper;

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_CONTENT_SYNC_URL}")
    private String REST_CONTENT_SYNC_URL;

    @Override
    public EUDataGridResult getContentList(Integer page, Integer rows) {
        // 设置分页参数
        PageHelper.startPage(page, rows);

        // 查询 内容。
        TbContentExample example = new TbContentExample();
        List<TbContent> list = contentMapper.selectByExample(example);

        // 取分页信息
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);

        EUDataGridResult result = new EUDataGridResult();
        result.setRows(pageInfo.getList());
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public DeyongResult insertContent(TbContent content) {
        Date now = new Date();
        content.setUpdated(now);
        content.setCreated(now);
        contentMapper.insert(content);
        // 添加缓存同步 缓存逻辑
        try {

            HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
            // 给管理员发送一个短信，发送一封邮件
            return DeyongResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return DeyongResult.ok();
    }
}
