package com.deyong.service.impl;

import com.deyong.mapper.TbItemParamItemMapper;
import com.deyong.pojo.TbItemParamItem;
import com.deyong.pojo.TbItemParamItemExample;
import com.deyong.service.ItemParamItemService;
import com.ldy.common.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by benjamin on 2017/1/11.
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;
    @Override
    public String getItemParamByItemid(long itemid) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemid);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list == null || list.size() == 0) {
            return "";
        }
        String paramData = list.get(0).getParamData();

        List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
        // 生成html
        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n" );
        sb.append("  <tbody>\n" );

        for (Map m1 : jsonList) {
            sb.append("	<tr>\n");
            sb.append("	  <th class=\"tdTitle\" colspan=\"2\">"+ m1.get("group") +"</th>\n");
            sb.append("	</tr>\n");
            List<Map> lists = (List<Map>) m1.get("params");
            for (Map l1 : lists) {
                sb.append("	<tr>\n");
                sb.append("	  <td class=\"tdTitle\">"+ l1.get("k") +"</td>\n");
                sb.append("	  <td>"+ l1.get("v") +"</td>\n");
                sb.append("	</tr>\n");
            }
        }
        sb.append("  </tbody>\n" );
        sb.append("</table>");
        return sb.toString();
    }
}
