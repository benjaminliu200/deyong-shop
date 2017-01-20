package com.deyong.portal.pojo;

import com.deyong.pojo.TbItem;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by benjamin on 2017/1/20.
 */
public class ItemInfo extends TbItem {

    public String[] getImages() {
        String image = getImage();
        if (!StringUtils.isBlank(image)) {
            return image.split(",");
        }
        return null;
    }
}
