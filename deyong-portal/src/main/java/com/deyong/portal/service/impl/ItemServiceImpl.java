package com.deyong.portal.service.impl;

import com.deyong.pojo.TbItem;
import com.deyong.pojo.TbItemDesc;
import com.deyong.pojo.TbItemParamItem;
import com.deyong.portal.pojo.Item;
import com.deyong.portal.pojo.ItemInfo;
import com.deyong.portal.service.ItemService;
import com.ldy.common.util.DeyongResult;
import com.ldy.common.util.ExceptionUtil;
import com.ldy.common.util.HttpClientUtil;
import com.ldy.common.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by benjamin on 2017/1/20.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${ITEM_BASE_INFO}")
    private String ITEM_BASE_INFO;

    @Value("${ITEM_DESC_INFO}")
    private String ITEM_DESC_INFO;

    @Value("${ITEM_PARAM_INFO}")
    private String ITEM_PARAM_INFO;


    @Override
    public ItemInfo getItemInfo(long itemId) {
        try {
            String itemString = HttpClientUtil.doGet(REST_BASE_URL + ITEM_BASE_INFO + itemId);
            if (!StringUtils.isBlank(itemString)) {
                DeyongResult deyongResult = DeyongResult.formatToPojo(itemString, ItemInfo.class);
                if (deyongResult.getStatus() == 200)
                    return (ItemInfo) deyongResult.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getItemDesc(long itemId) {
        try {
            String itemString = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_INFO + itemId);
            if (!StringUtils.isBlank(itemString)) {
                DeyongResult deyongResult = DeyongResult.formatToPojo(itemString, TbItemDesc.class);
                if (deyongResult.getStatus() == 200)
                    return ((TbItemDesc) deyongResult.getData()).getItemDesc();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getItemParam(long itemId) {
        try {
            String itemString = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_INFO + itemId);
            DeyongResult deyongResult = DeyongResult.formatToPojo(itemString, TbItemParamItem.class);
            if (deyongResult.getStatus() == 200) {
                TbItemParamItem itemParamItem = (TbItemParamItem) deyongResult.getData();

                List<Map> jsonList = JsonUtils.jsonToList(itemParamItem.getParamData(), Map.class);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
